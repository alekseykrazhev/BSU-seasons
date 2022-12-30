update h_services set price = 1 where price < 1

update h_services set price = 1.1*price where price between 10 and 50 and
title != 'Ренгеноскопия'

UPDATE h_services
SET price = CASE
WHEN code_srv = any(
	select distinct h_services.code_srv from h_visit
	join h_doctor on h_visit.id_doctor = h_doctor.id_doctor
	join h_speciality on h_doctor.code_spec = h_speciality.code_spec
	join h_services on h_services.code_srv = h_visit.code_srv
where h_speciality.title='Терапевт'
	) THEN 1.1*price
WHEN code_srv = any(
	select distinct h_services.code_srv from h_visit
	join h_doctor on h_visit.id_doctor = h_doctor.id_doctor
	join h_speciality on h_doctor.code_spec = h_speciality.code_spec
	join h_services on h_services.code_srv = h_visit.code_srv
	where h_speciality.title='Невролог'
	) THEN 1.2*price
Else price
END

UPDATE h_services
SET price = 0.9 * (select distinct avg(h_services.price) from h_visit
	join h_doctor on h_visit.id_doctor = h_doctor.id_doctor
	join h_speciality on h_doctor.code_spec = h_speciality.code_spec
	join h_services on h_services.code_srv = h_visit.code_srv
where h_speciality.title='Терапевт')
where h_services.code_srv in(
	select distinct h_services.code_srv from h_visit
	join h_doctor on h_visit.id_doctor = h_doctor.id_doctor
	join h_speciality on h_doctor.code_spec = h_speciality.code_spec
	join h_services on h_services.code_srv = h_visit.code_srv
	where h_speciality.title='Хирург'
);

update h_doctor set h_doctor.firstname = lower(h_doctor.firstname)
where h_doctor.firstname like 'С%'

update h_doctor set surname = initcap(replace(surname, '-', ' '));

update h_doctor set h_doctor.surname = upper(h_doctor.surname)

update (select v.id_doctor as did, s.code_spec as code from h_visit v
	left join h_doctor d on v.id_doctor = d.id_doctor
	left join h_speciality s on d.code_spec = s.code_spec
	where d.id_doctor is not null) t
set t.did =
	(SELECT id_doctor
	FROM (
	SELECT *
	FROM h_doctor join h_speciality on h_doctor.code_spec =
	h_speciality.code_spec
	where h_speciality.code_spec = t.code
	ORDER BY DBMS_RANDOM.RANDOM)
	WHERE rownum < 2);


insert into h_doctor (id_doctor, surname, firstname, middle_name) values
(SEQ_DOC.NEXTVAL, 'Krazhevskiy', 'Aleksey', null);

update h_doctor set code_spec = 1408 where id_doctor=24;

{
	CREATE TABLE TMP_Doc
	(
	ID_DOCTOR NUMBER (8) NOT NULL ,
	SURNAME VARCHAR2(100 CHAR) ,
	FIRSTNAME VARCHAR2(100 CHAR) ,
	MIDDLE_NAME VARCHAR2 (100 CHAR) ,
	code_spec number (8)not null
	);

	INSERT INTO tmp_doc (id_doctor, surname, firstname, middle_name,
	code_spec)
	SELECT SEQ_DOC.NEXTVAL, surname, firstname, middle_name,
	code_spec
	FROM h_doctor
	WHERE id_doctor in (select distinct id_doctor from h_visit where
	id_doctor is not null);
}

delete from tmp_doc;

INSERT INTO tmp_doc (id_doctor, surname, firstname, middle_name,
code_spec)
select * from h_doctor
where not exists
(select * from h_visit
where h_visit.id_doctor = h_doctor.id_doctor
and h_visit.date_visit between TO_DATE('12.09.2022', 'DD.MM.RR')
and TO_DATE('17.09.2022', 'DD.MM.RR'));

CREATE TABLE tmp_Spec
(
CODE_SPEC NUMBER (8) NOT NULL ,
Title VARCHAR2 (100 CHAR)
);

insert into tmp_spec (code_spec, title)
select code_spec, title from h_speciality
where code_spec not in (select distinct code_spec from h_doctor);

delete from h_visit where date_visit <= ( select min(date_visit) from h_visit) + 7;

delete from h_visit where id_doctor not in (
select id_doctor from h_visit where date_visit = (select max(date_visit) from
h_visit))

delete from h_services where code_srv not in (select distinct code_srv from
h_visit where date_visit = (select max(date_visit) from h_visit) or id_doctor is
null)

delete from h_doctor d where not exists (select * from h_visit where
h_visit.id_doctor = d.id_doctor)

Delete from TMP_Doc;
Delete from TMP_Spec