-- ВЛОЖЕННЫЕ ПОДЗАПРОСЫ: --
--ПОДЗАПРОСЫ, ВЫБИРАЮЩИЕ ОДНУ СТРОКУ--
--Найти имена специалистов, оказавших услуги с минимальной стоимостью.--
select H_DOCTOR.FIRSTNAME, H_DOCTOR.MIDDLE_NAME, H_DOCTOR.SURNAME
from (H_DOCTOR inner join H_VISIT HV on H_DOCTOR.ID_DOCTOR = HV.ID_DOCTOR)
where HV.CODE_SRV = (select HV.CODE_SRV from H_SERVICES where PRICE = (select min(PRICE) from H_SERVICES));

--ПОДЗАПРОСЫ, ВОЗВРАЩАЮЩИЕ БОЛЕЕ ОДНОЙ СТРОКИ--
--Найти имена сотрудников, работавших в тот же день, когда был визит пациента с именем Безенчук Федор Николаевич.--
SELECT SURNAME, FIRSTNAME, MIDDLE_NAME FROM H_DOCTOR
    WHERE ID_DOCTOR IN (SELECT ID_DOCTOR
        FROM H_VISIT
        INNER JOIN H_PATIENT ON H_VISIT.ID_PATIENT = H_PATIENT.N_CARD
        WHERE H_PATIENT.SURNAME = 'Безенчук' AND H_PATIENT.FIRSTNAME = 'Федор' AND H_PATIENT.MIDDLE_NAME = 'Николаевич');

--СРАВНЕНИЕ БОЛЕЕ ЧЕМ ПО ОДНОМУ ЗНАЧЕНИЮ--
--Найти имена сотрудников, обслуживающих пациентов Паниковский, Щукина, Корейко.--
SELECT SURNAME, FIRSTNAME, MIDDLE_NAME FROM H_DOCTOR
    WHERE ID_DOCTOR IN (SELECT ID_DOCTOR
        FROM H_VISIT
        INNER JOIN H_PATIENT ON H_VISIT.ID_PATIENT = H_PATIENT.N_CARD
        WHERE H_PATIENT.SURNAME = 'Паниковский' OR H_PATIENT.SURNAME = 'Щукина' OR H_PATIENT.SURNAME = 'Корейко');

--ОПЕРАТОРЫ ANY/ALL--
--Найти сведения о номерах сотрудников, работавших 15, 20 и 24 сентября--
SELECT ID_DOCTOR FROM H_VISIT
    WHERE DATE_VISIT = ANY ('15-SEP-22', '20-SEP-22', '24-SEP-22');

--Найти сведения о номерах сотрудников, оказывающих какие-либо услуги из тех,  которые были оказаны 12 и 13 сентября..--
SELECT ID_DOCTOR FROM H_VISIT
    WHERE CODE_SRV IN (SELECT CODE_SRV FROM H_VISIT
        WHERE DATE_VISIT = ANY('12-SEP-22', '13-SEP-22'))
    GROUP BY ID_DOCTOR;

--ИСПОЛЬЗОВАНИЕ HAVING С ВЛОЖЕННЫМИ ПОДЗАПРОСАМИ--
--Определить в какие дни были оказаны услуги, средняя суточная стоимость  была больше общей средней стоимости всех имеющихся услуг.--
SELECT DATE_VISIT FROM H_VISIT
    INNER JOIN H_SERVICES ON H_SERVICES.CODE_SRV = H_VISIT.CODE_SRV
    GROUP BY DATE_VISIT
    HAVING AVG(PRICE) > (SELECT AVG(PRICE) FROM H_SERVICES);

--КОРРЕЛИРУЮЩИЕ ПОДЗАПРОСЫ--
--Определить коды специальностей, сотрудников, оказавших услуги во время визитов с 15 по 20 сентября.--
SELECT DISTINCT CODE_SPEC FROM H_DOCTOR main_query
    WHERE main_query.ID_DOCTOR IN (SELECT sub_query.ID_DOCTOR
                                FROM H_VISIT sub_query
                                WHERE sub_query.DATE_VISIT
                                BETWEEN '15-SEP-22' AND '20-SEP-22');

--ОПЕРАТОР EXISTS--
--Определить коды специальностей сотрудников, обслуживающих пациентов, которым были оказаны услуги с кодами 110 - 150.--
SELECT DISTINCT CODE_SPEC FROM H_DOCTOR main_query
    WHERE EXISTS (SELECT sub_query.ID_DOCTOR
                  FROM H_VISIT sub_query
                  WHERE sub_query.CODE_SRV
                  BETWEEN 110 AND 150);

--ОПЕРАТОР NOT EXISTS--
--Определить коды специальностей сотрудников, которые не обслуживали пациентов в период с 15 по 20 сентября.--
SELECT DISTINCT CODE_SPEC FROM H_DOCTOR
    WHERE NOT EXISTS (SELECT ID_DOCTOR
                      FROM H_VISIT
                      WHERE ID_DOCTOR = H_DOCTOR.ID_DOCTOR AND DATE_VISIT
                      BETWEEN '15-SEP-22' AND '20-SEP-22');

--СОСТАВНЫЕ ЗАПРОСЫ--
--Вывести сведения о специальности сотрудников с указанием названий вместо их кодов.--
SELECT SURNAME FROM H_DOCTOR
    UNION
SELECT  TITLE AS CODE_SPEC FROM H_SPECIALITY;

--ОПЕРАТОР CAST--
--Определить целую часть средней стоимости услуг, по датам..--
SELECT DATE_VISIT, CAST(AVG(PRICE) AS INTEGER) AS AVG_PRICE
    FROM H_VISIT INNER JOIN H_SERVICES
            ON H_VISIT.CODE_SRV = H_SERVICES.CODE_SRV
            GROUP BY DATE_VISIT;

--ОПЕРАТОР CASE--
--Разделите список услуг по стоимостным категориям: A) до 5 руб.; B) 5 - 10 руб.; C) 10 – 50 руб.;    D) более 50 руб.--
SELECT TITLE,
    CASE
        WHEN PRICE < 5 THEN 'A'
        WHEN PRICE >= 5 AND PRICE < 10 THEN 'B'
        WHEN PRICE >= 10 AND  PRICE <= 50 THEN 'C'
        WHEN PRICE > 50 THEN 'D'
    END PRICE_CATEGORY
FROM H_SERVICES;

--Перекодируйте номера пациентов, добавив перед номером буквы AM для номеров <=200000,  буквы  NV для номеров >=20000.--
SELECT SURNAME, FIRSTNAME, MIDDLE_NAME, ADDRESS, PHONE,
    CASE
        WHEN H_PATIENT.N_CARD <= 200000 THEN concat('AM', N_CARD)
        WHEN H_PATIENT.N_CARD >= 200000 THEN concat('NV', N_CARD)
    END PATIENT_ID
FROM H_PATIENT;

--ОПЕРАТОР COALESCE (объединяться)--
--Выдать информацию о сотрудниках из, заменив отсутствие имени или отчество строкой, состоящей из 5 пробелов.--
SELECT SURNAME, COALESCE(MIDDLE_NAME, '     ') AS MIDDLE_NAME, COALESCE(FIRSTNAME, '     ') AS FIRSTNAME FROM H_DOCTOR;
