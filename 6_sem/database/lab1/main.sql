-- 1 --

CREATE OR REPLACE FUNCTION calculate_bonus(
    id_doctor NUMBER,
    surname_doctor VARCHAR2
)
RETURN NUMBER
IS
    doctor_bonus NUMBER;
    total_sum NUMBER;
    doctor_id NUMBER;
BEGIN
    IF surname_doctor IS NOT NULL THEN
        SELECT ID_DOCTOR INTO doctor_id FROM H_DOCTOR WHERE SURNAME = surname_doctor;
    ELSIF id_doctor IS NOT NULL THEN
        doctor_id := id_doctor;
    end if;

    SELECT SUM(PRICE) INTO total_sum FROM H_VISIT NATURAL JOIN H_SERVICES
                                     WHERE H_VISIT.ID_DOCTOR = doctor_id;

    doctor_bonus := 0.2 * total_sum;

    RETURN doctor_bonus;
END calculate_bonus;

-- 2 --

CREATE OR REPLACE FUNCTION calculate_price_with_date(
    card_number NUMBER,
    surname_patient VARCHAR2,
    date_start DATE,
    date_end DATE
)
RETURN NUMBER
IS
    patient_id NUMBER;
    total_sum NUMBER;
    end_date DATE;
BEGIN
    IF surname_patient IS NOT NULL THEN
        SELECT N_CARD INTO patient_id FROM H_PATIENT WHERE SURNAME = surname_patient;
    ELSIF card_number IS NOT NULL THEN
        patient_id := card_number;
    end if;

    end_date := date_end;

    IF end_date IS NULL THEN
        end_date := current_date;
    end if;

    SELECT SUM(PRICE) INTO total_sum FROM H_VISIT NATURAL JOIN H_SERVICES
                                     WHERE H_VISIT.ID_PATIENT = patient_id AND H_VISIT.DATE_VISIT BETWEEN date_start AND end_date;

    RETURN total_sum;

END calculate_price_with_date;

-- 3 --

CREATE OR REPLACE FUNCTION count_sale(
    card_number NUMBER,
    surname_patient VARCHAR2
)
RETURN NUMBER
IS
    patient_id NUMBER;
    sale NUMBER;
    total_sum NUMBER;
BEGIN
    IF surname_patient IS NOT NULL THEN
        SELECT N_CARD INTO patient_id FROM H_PATIENT WHERE SURNAME = surname_patient;
    ELSIF card_number IS NOT NULL THEN
        patient_id := card_number;
    end if;

    SELECT SUM(PRICE) INTO total_sum FROM H_VISIT NATURAL JOIN H_SERVICES
                                     WHERE H_VISIT.ID_PATIENT = patient_id;

    IF total_sum < 50 THEN
        sale := 3;
    ELSIF total_sum >= 50 AND total_sum < 100 THEN
        sale := 10;
    ELSIF total_sum >= 100 AND total_sum < 200 THEN
        sale := 20;
    ELSIF total_sum >= 200 AND total_sum < 400 THEN
        sale := 30;
    ELSE
        sale := 40;
    end if;

    RETURN sale;
end count_sale;

-- 4 --

CREATE OR REPLACE FUNCTION count_patient(
    doctor_id NUMBER,
    doctor_surname VARCHAR2
)
RETURN NUMBER
IS
    id_doc NUMBER;
    amount_patient NUMBER := 0;
BEGIN
    IF doctor_surname IS NOT NULL THEN
        SELECT H_DOCTOR.ID_DOCTOR INTO id_doc FROM H_DOCTOR WHERE SURNAME = doctor_surname;
    ELSIF doctor_id IS NOT NULL THEN
        id_doc := doctor_id;
    end if;

    SELECT COUNT(*) AS "Amount Patient" INTO amount_patient FROM H_VISIT WHERE H_VISIT.ID_DOCTOR = id_doc;
    RETURN amount_patient;
end;

-- 5 --

CREATE OR REPLACE TYPE DoctorInfo AS OBJECT(
        FirstSurname varchar2(40),
        SecondSurname varchar2(40),
        Amount number
);

CREATE OR REPLACE FUNCTION min_max_amount_patient(
    date_start DATE,
    date_end DATE
)
RETURN DoctorInfo
IS
    Amount number;
    FirstDoctor varchar2(40);
    SecondDoctor varchar2(40);
    start_date DATE := date_start;
    end_date DATE := date_end;
BEGIN
    IF end_date IS NULL THEN
        SELECT max(DATE_VISIT) INTO end_date FROM H_VISIT;
    end if;

    WITH
    DoctorData AS (
        SELECT COUNT(*) AS Amount,H_Visit.ID_Doctor,Surname,
               ROW_NUMBER() OVER(ORDER BY H_Visit.ID_Doctor) AS Dummy
                FROM H_Visit
                    JOIN H_Doctor ON H_Doctor.ID_Doctor=H_Visit.ID_Doctor
                WHERE Date_Visit BETWEEN start_date AND end_date
                GROUP BY H_Visit.ID_Doctor,Surname
    ),
    ExtraData AS(
        SELECT  T1.Amount,T1.Surname AS LeftDoctor,T2.Amount,T2.Surname AS RightDoctor,
                    ABS(T1.Amount-T2.Amount) AS Diff
        FROM DoctorData T1
        JOIN DoctorData T2 ON T1.Dummy != T2.Dummy
    )
    SELECT LeftDoctor, RightDoctor, Diff INTO FirstDoctor, SecondDoctor, Amount FROM ExtraData
                                                            WHERE Diff = (SELECT max(Diff) FROM ExtraData) AND ROWNUM=1;

RETURN DoctorInfo(FirstDoctor,SecondDoctor,Amount);
end;

-- 6 --

ALTER TABLE H_Doctor ADD bonus NUMBER(9,2);

CREATE OR REPLACE FUNCTION count_bonus(
    doctor_surname VARCHAR2,
    date_start DATE,
    date_end DATE
)
RETURN NUMBER
IS
    pragma autonomous_transaction;
    temp NUMBER;
    bonus NUMBER;
    start_date DATE := date_start;
    end_date DATE := date_end;
BEGIN
    IF start_date IS NULL THEN
        SELECT MIN(DATE_VISIT) INTO start_date FROM H_VISIT;
    end if;

    IF end_date IS NULL THEN
        SELECT MAX(DATE_VISIT) INTO end_date FROM H_VISIT;
    end if;

    WITH
        doc AS (
            SELECT SUM(PRICE) AS sum_price, SURNAME FROM H_VISIT
                                                    JOIN H_DOCTOR HD ON H_VISIT.ID_DOCTOR = HD.ID_DOCTOR
                                                    JOIN H_SERVICES HS ON H_VISIT.CODE_SRV = HS.CODE_SRV
                                                    WHERE DATE_VISIT BETWEEN start_date AND end_date
                                                    GROUP BY SURNAME
        )

    SELECT sum_price INTO bonus FROM doc WHERE SURNAME = doctor_surname;

    IF bonus < 50 THEN
        temp:=10;
    ELSIF bonus >=50 AND bonus < 100 THEN
        temp:=15;
    ELSIF bonus>=100 AND bonus < 400 THEN
        temp:=30;
    ELSE
        temp:=50;
    END IF;

RETURN (bonus*temp)/100;
end count_bonus;

CREATE OR REPLACE PROCEDURE add_bonus(
    doc_surname VARCHAR2,
    doc_id NUMBER,
    doc_spec NUMBER,
    date_start DATE,
    date_end DATE
)
IS
BEGIN
    IF doc_surname IS NOT NULL OR doc_id IS NOT NULL THEN
        UPDATE H_DOCTOR SET H_DOCTOR.bonus=count_bonus(doc_surname, date_start, date_end)
        WHERE H_DOCTOR.SURNAME = doc_surname;
    ELSIF doc_spec IS NOT NULL THEN
        UPDATE H_DOCTOR SET H_DOCTOR.bonus=count_bonus(doc_surname, date_start, date_end)
        WHERE H_DOCTOR.CODE_SPEC = (SELECT Code_Spec FROM H_SERVICES WHERE Title=doc_spec);
    ELSE
        UPDATE H_Doctor SET Bonus=count_bonus(H_DOCTOR.Surname,date_start,date_end);
    end if;
end;

-- 7 --

CREATE OR REPLACE PROCEDURE add_new_doctor(
    doc_surname VARCHAR2,
    doc_name VARCHAR2,
    doc_middle_name VARCHAR2,
    doc_spec VARCHAR2
)
IS
    check_spec NUMBER := 0;
    spec_code NUMBER;
    intern_id NUMBER;
BEGIN
    IF doc_spec IS NOT NULL THEN
        SELECT COUNT(CODE_SPEC) INTO check_spec FROM H_SPECIALITY WHERE TITLE=doc_spec;

        IF check_spec = 0 THEN
            check_spec := SEQ_SPEC.nextval;
            INSERT INTO H_SPECIALITY VALUES (check_spec, doc_spec);
        ELSE
            SELECT CODE_SPEC INTO check_spec FROM H_SPECIALITY WHERE TITLE=doc_spec;
        end if;

        INSERT INTO H_DOCTOR VALUES (SEQ_DOC.nextval, doc_surname, doc_name, doc_middle_name, check_spec, NULL);
    ELSE
        SELECT CODE_SPEC INTO spec_code FROM (SELECT CODE_SPEC, COUNT(CODE_SPEC) AS Cnt FROM H_DOCTOR
                                                        GROUP BY CODE_SPEC ORDER BY Cnt ASC) WHERE ROWNUM = 1;
        SELECT ID_DOCTOR INTO intern_id FROM H_DOCTOR WHERE CODE_SPEC=1408 AND ROWNUM=1;
        UPDATE H_DOCTOR SET CODE_SPEC=spec_code WHERE ID_DOCTOR=intern_id;
        INSERT INTO H_DOCTOR VALUES (SEQ_DOC.nextval, doc_surname, doc_name, doc_middle_name, 1408, NULL);
    end if;
end;

-- 8 --

ALTER TABLE H_Patient ADD Clinic_Site NUMBER(9,2);

CREATE OR REPLACE PROCEDURE add_new_patient(
    patient_surname VARCHAR2,
    patient_name VARCHAR2,
    patient_middle_name VARCHAR2,
    address VARCHAR2
)
IS
BEGIN
    IF address IS NOT NULL AND address IN ('Артемовск',' Черноморск','Верхнехолмск','Южногорск') THEN
        INSERT INTO H_PATIENT VALUES (SEQ_PATIENT_2.nextval, patient_surname, patient_name, patient_middle_name, address, NULL, 1);
    ELSIF address IS NOT NULL AND address IN ('Бердянск', 'Прокопьевск', 'Криворуков', 'Урюпинск') THEN
        INSERT INTO H_PATIENT VALUES (SEQ_PATIENT_2.nextval, patient_surname, patient_name, patient_middle_name, address, NULL, 2);
    ELSE
        INSERT INTO H_PATIENT VALUES (SEQ_PATIENT_2.nextval, patient_surname, patient_name, patient_middle_name, address, NULL, 3);
    end if;
end;

-- 9 --

CREATE OR REPLACE PROCEDURE change_date(
    date_start DATE,
    date_end DATE,
    count OUT NUMBER
)
IS
BEGIN
    SELECT COUNT(*) INTO count FROM H_VISIT GROUP BY DATE_VISIT HAVING DATE_VISIT = date_start;
    UPDATE H_VISIT SET DATE_VISIT=date_end WHERE DATE_VISIT=date_start;
end;

-- 10 --

CREATE TABLE ARCHIVE_H_VISIT (
     N_VISIT NUMBER NOT NULL ,
     ID_DOCTOR NUMBER,
     ID_PATIENT NUMBER,
     DATE_VISIT DATE,
     CODE_SRV NUMBER
);

CREATE OR REPLACE PROCEDURE delete_visit(
    period NUMBER,
    count_delete OUT NUMBER
)
IS
    begin_date DATE;
    end_date DATE;
BEGIN
    SELECT MIN(DATE_VISIT) INTO begin_date FROM H_VISIT;
    IF period IS NOT NULL THEN
        end_date := period + begin_date;
    ELSE
        SELECT MAX(DATE_VISIT) INTO end_date FROM H_VISIT;
    end if;

    SELECT COUNT(*) INTO count_delete FROM H_VISIT WHERE DATE_VISIT BETWEEN begin_date AND end_date;
    INSERT INTO ARCHIVE_H_VISIT (SELECT * FROM H_VISIT WHERE DATE_VISIT BETWEEN begin_date AND end_date);
    DELETE FROM H_VISIT WHERE DATE_VISIT BETWEEN begin_date AND end_date;
end;
