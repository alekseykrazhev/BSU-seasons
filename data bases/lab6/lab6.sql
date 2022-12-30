-------------------------------1-------------------------------------------------------------
WITH
    JoinTables AS(
        SELECT * FROM H_Doctor
    	INNER JOIN H_Speciality on H_Doctor.code_spec=H_Speciality.code_spec
        ),
    Task1 AS(
        SELECT surname,firstname,middle_name,title FROM JoinTables
        GROUP BY title,surname,firstname,middle_name
        HAVING title IN ('Терапевт','Невролог','Хирург')
        ORDER BY title,surname),
    Task2 AS(
        SELECT surname,firstname,middle_name,title FROM JoinTables
        GROUP BY title,surname,firstname,middle_name
        HAVING title IN ('Офтальмолог','Дерматовенеролог','Интерн')
        ORDER BY title,surname DESC),
    Task3 AS(
        SELECT surname,firstname,middle_name,title FROM JoinTables
        GROUP BY title,surname,firstname,middle_name
        HAVING title NOT IN ('Терапевт','Невролог','Хирург','Офтальмолог','Дерматовенеролог','Интерн')
        )
SELECT * FROM Task1
UNION ALL
SELECT * FROM Task2
UNION ALL
SELECT * FROM Task3;


-------------------------------2-------------------------------------------------------------
WITH
    JoinTables AS(
        SELECT * FROM H_Doctor
    	INNER JOIN H_Speciality on H_Doctor.code_spec=H_Speciality.code_spec
        ),
    AmtTable AS(
        SELECT title,COUNT(*) AS amount FROM JoinTables
        GROUP BY title
        ),
    TempTable1 AS(
        SELECT
        title,
        amount,
        ROW_NUMBER() over (ORDER BY title) AS counter
        FROM AmtTable)
SELECT DISTINCT T1.title,T1.amount,
       T2.title,T2.amount,
       ABS(T1.amount-T2.amount) AS difference
FROM TempTable1 T1
JOIN TempTable1 T2 ON T2.counter!=T1.counter
ORDER BY T1.title;


-------------------------------3-------------------------------------------------------------
INSERT INTO H_VISIT (N_VISIT,ID_DOCTOR,ID_PATIENT,DATE_VISIT,CODE_SRV) VALUES (SEQ_VISIT.NEXTVAL, 18, 216002, TO_DATE('21.09.90','DD.MM.RR'), 110);
INSERT INTO H_VISIT (N_VISIT,ID_DOCTOR,ID_PATIENT,DATE_VISIT,CODE_SRV) VALUES (SEQ_VISIT.NEXTVAL, 15, 216002, TO_DATE('21.09.90','DD.MM.RR'), 130);
INSERT INTO H_VISIT (N_VISIT,ID_DOCTOR,ID_PATIENT,DATE_VISIT,CODE_SRV) VALUES (SEQ_VISIT.NEXTVAL, 17, 216002, TO_DATE('21.09.90','DD.MM.RR'), 140);

--hospital started 21.09.90

WITH
    JoinTables AS(
        SELECT * FROM H_Doctor
        LEFT JOIN H_Visit ON H_Visit.id_doctor=H_Doctor.id_doctor
    ),
    MinDate AS(
        SELECT surname,firstname,middle_name,date_visit,
               MIN(date_visit) over (PARTITION BY surname,firstname,middle_name) as FirstVisit
        FROM JoinTables
        )
SELECT DISTINCT surname,firstname,middle_name,firstvisit FROM MinDate
WHERE FirstVisit=TO_DATE('21.09.90','DD.MM.RR');


-------------------------------4-------------------------------------------------------------
WITH
    JoinTables AS(
        SELECT * FROM H_Doctor
        LEFT JOIN H_Visit ON H_Visit.id_doctor=H_Doctor.id_doctor
    )
SELECT surname,firstname,middle_name,n_visit,id_patient,date_visit,
       LEAD(date_visit) over (ORDER BY date_visit) AS next_visit
FROM JoinTables;


-------------------------------5-------------------------------------------------------------
WITH
    JoinTables AS(
        SELECT * FROM H_Visit
        LEFT JOIN H_Services ON H_Visit.code_srv=H_Services.code_srv
    ),
    TempTable AS(
        SELECT id_patient,date_visit,title,price,
               AVG(price) over() AS AvgPrice,
               MIN(price) over() AS MinPrice,
               MAX(price) over() AS MaxPrice
        FROM JoinTables
        )
SELECT id_patient,date_visit,title,price,
       ROUND(price-AvgPrice,2) as diifAvg,ROUND(price-MinPrice,2) as diffMin, ROUND(price-MaxPrice,2) as diffMax
FROM TempTable;


-------------------------------6-------------------------------------------------------------
with ranking as
    (select d.id_doctor as did, coalesce(count(v.n_visit), 0) as vn
    from h_doctor d left join h_visit v on d.id_doctor = v.id_doctor
    group by d.id_doctor
    )
select d.*, r.vn as visits_number,
rank() over(order by r.vn desc) as rank
from h_doctor d join ranking r on d.id_doctor = r.did;


-------------------------------7-------------------------------------------------------------
select distinct sp.code_spec, s.code_srv, s.title, s.price,
max(s.price) over(partition by sp.code_spec) as max,
min(s.price) over(partition by sp.code_spec) as min,
max(s.price) over(partition by sp.code_spec) - min(s.price) over(partition by sp.code_spec) as max_difference
from h_speciality sp
join h_doctor d on sp.code_spec = d.code_spec
join h_visit v on v.id_doctor = d.id_doctor
join h_services s on s.code_srv = v.code_srv;


-------------------------------8-------------------------------------------------------------
WITH
    JoinTables AS(
        SELECT * FROM H_Doctor
        LEFT JOIN H_Visit ON H_Visit.id_doctor=H_Doctor.id_doctor
    ),
    TempTable AS(
        SELECT surname,firstname,middle_name,n_visit,id_patient,date_visit,code_srv,
               AVG(to_number(to_char(date_visit, 'J'))) over (PARTITION BY surname) AS DiffBtwVisits,
               MAX(to_number(to_char(date_visit, 'J'))) over (PARTITION BY surname) AS MaxVisitNum,
               MAX(date_visit) over (PARTITION BY surname) AS MaxVisit
        FROM JoinTables
    )
SELECT DISTINCT surname,firstname,middle_name,
     MaxVisit+round(MaxVisitNum-DiffBtwVisits) AS NextVisit
FROM TempTable
WHERE date_visit IS NOT NULL;

SELECT * FROM H_Doctor
LEFT OUTER JOIN H_Visit ON H_Doctor.id_doctor=H_Visit.id_doctor
WHERE H_Visit.id_doctor IS NULL;


-------------------------------9-------------------------------------------------------------
with ranking_doctor_in_date_range as (select d.id_doctor as did, coalesce(count(v.n_visit), 0) as vn from h_doctor d left join h_visit v on d.id_doctor = v.id_doctor
where v.date_visit between TO_DATE('15.09.22','DD.MM.RR') and TO_DATE('19.09.22','DD.MM.RR')
group by d.id_doctor)
-- find max doctor visit
select * from
(
    select d.*, rd.vn,
    rank() over (order by rd.vn desc) as rk
    from h_doctor d join ranking_doctor_in_date_range rd on d.id_doctor = rd.did
) x
where x.rk = 1;

with ranking_doctor_in_date_range as (select d.id_doctor as did, coalesce(count(v.n_visit), 0) as vn from h_doctor d left join h_visit v on d.id_doctor = v.id_doctor
where v.date_visit between TO_DATE('15.09.22','DD.MM.RR') and TO_DATE('19.09.22','DD.MM.RR')
group by d.id_doctor)
-- find max doctor visit
select * from
(
    select d.*, rd.vn,
    rank() over (order by rd.vn) as rk
    from h_doctor d join ranking_doctor_in_date_range rd on d.id_doctor = rd.did
) x
where x.rk = 1;


-------------------------------10-------------------------------------------------------------
with    ranking_patient as (select p.patient_id as pid, coalesce(count(v.n_visit), 0) as vn from h_patient p left join h_visit v on p.patient_id = v.id_patient
group by p.patient_id),
        -- sum of prices of services for each patient
        services_patient as (select p.patient_id as pid, coalesce(sum(s.price), 0) sum_services from h_patient p left join h_visit v on p.patient_id = v.id_patient join h_services s on s.code_srv = v.code_srv
        group by p.patient_id),
        -- avg sum of all services
        avg_services as (select sum(s.price) as avg_price from h_visit v natural join h_services s)

-- put rank
select p.*, r.vn as visits_number, sp.sum_services as sum_number,
rank() over(order by r.vn desc) as rank,
CASE
    WHEN (select avg(s.price) as avg_price from h_visit v natural join h_services s) > sp.sum_services then 1
    ELSE 0
END AS is_sum_exceeded
from h_patient p join ranking_patient r on p.patient_id = r.pid join services_patient sp on sp.pid = r.pid;

