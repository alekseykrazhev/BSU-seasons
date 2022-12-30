-- ПРОСТЕЙШИЕ ЗАПРОСЫ: --
-- Выдать информацию о местоположении пациента  Паниковский Самюэль Михайлович --
SELECT ADDRESS FROM H_PATIENT WHERE (SURNAME = 'Паниковский' AND FIRSTNAME = 'Самюэль' AND middle_name = 'Михайлович');

-- Выдать информацию о пациентах, проживающих в Урюпинске и Черноморске. --
SELECT * FROM H_PATIENT WHERE (ADDRESS = 'г. Черноморск' OR ADDRESS = 'г. Урюпинск');

-- ФУНКЦИИ: --
-- Найти услуги с минимальной стоимостью. --
select * from H_SERVICES where PRICE = (select min(PRICE) from H_SERVICES);

-- Выдать информацию обо всех визитах, состоявшихся не позднее 20 сентября 2022 года. --
select * from H_VISIT where DATE_VISIT <= to_date('20-09-2022', 'dd-mm-yyyy');

-- Подсчитать число специалистов, сведения о которых имеются в базе данных --
select count(*) from H_DOCTOR;

-- Найти работников, имена которых начинаются на букву А. Имена выдать на нижнем регистре. --
select lower(FIRSTNAME) from H_DOCTOR where FIRSTNAME like ('А%');

-- Выдать информацию о визитах, указав дату в формате день(число), месяц(название), год. --
SELECT id_doctor, id_patient, TO_CHAR(date_visit, 'DD MONTH YEAR') AS date_visit FROM h_visit;

-- Выдать информацию о специальностях, изменив названия “Хирург” и “Травматолог” на “Костоправ” --
SELECT CASE WHEN TITLE = 'Хирург' OR TITLE = 'Травматолог' THEN 'Костоправ' ELSE TITLE END AS NEW_TITLE, code_spec FROM h_speciality;

-- HAVING: --
-- Определите среднюю стоимость услуг которые оказывались не менее 3 раз в день. --
SELECT AVG(h_services.price)
FROM h_services, h_visit
GROUP BY h_visit.date_visit
HAVING COUNT(h_visit.date_visit) >= 3;

-- СОЕДИНЕНИЕ ПО РАВЕНСТВУ: --
-- Выдать сведения о специалистах каждой специальности. --
SELECT h_doctor.SURNAME, h_doctor.FIRSTNAME, h_doctor.MIDDLE_NAME, h_speciality.TITLE
FROM h_doctor, h_speciality
where h_doctor.code_spec = h_speciality.code_spec

-- СОЕДИНЕНИЕ НЕ ПО РАВЕНСТВУ: --
-- Укажите сведения об оказанных пациентам услугах, попадающих в вилку: минимальная стоимость услуги - минимальная стоимость плюс 50. Укажите соответствующую вилке  услугу. --
SELECT h_patient.SURNAME, h_patient.FIRSTNAME, h_patient.MIDDLE_NAME, (select min(price) from h_services), h_services.price
from h_patient, h_services, h_visit
where h_services.price >= (select min(price) from h_services) and h_services.price <= (select min(price) from h_services) + 50;
