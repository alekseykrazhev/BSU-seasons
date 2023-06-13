-- 1
create view terapevt_info as
select surname,firstname,middle_name
from h_doctor
where code_spec = (select code_spec
                    from h_speciality
                    where lower(title) = lower('Терапевт'));

select * from terapevt_info;
-- 1

-- 2
create view lazy_doctors as
select surname,firstname, title
from h_doctor
inner join h_speciality on h_speciality.code_spec = h_doctor.code_spec
where id_doctor <> all(select distinct id_doctor
                        from h_visit
                        where id_doctor is not null);
-- 2

--3
create view on_post_doctors_1 as
select DISTINCT surname as Sotrudnik, title as Speciality,
        count(*) over(PARTITION by h_visit.id_doctor) as Kol_patients
from h_doctor
inner join h_speciality on h_speciality.code_spec = h_doctor.code_spec
inner join h_visit on h_visit.id_doctor = h_doctor.id_doctor
where (date_visit between to_date('15.09.22','dd.mm.yy') and to_date('19.09.22','dd.mm.yy'))
        and (h_visit.id_patient is not null);

create view on_post_doctors_2 as
select  surname as Sotrudnik, title as Speciality, Kol_patients
from h_doctor
inner join h_speciality on h_speciality.code_spec = h_doctor.code_spec
inner join (select  id_doctor, count(*) as  Kol_patients
            from h_visit
            where (date_visit between to_date('15.09.22','dd.mm.yy') and to_date('19.09.22','dd.mm.yy'))
             and (h_visit.id_patient is not null)
            group by id_doctor)  tb1
            on h_doctor.id_doctor = tb1.id_doctor;
--3

--4
create view visits_more_two as
select  listagg(distinct (surname||' ' ||firstname),' ') as doctor,
        count(*) as  Kol_patients,
       (sum(price)||','||date_visit) as prices_date
from h_visit
inner join h_services
    on h_services.code_srv = h_visit.code_srv
inner join h_doctor
    on h_doctor.id_doctor = h_visit.id_doctor
where (date_visit between to_date('15.09.22','dd.mm.yy') and to_date('19.09.22','dd.mm.yy'))
and (h_visit.id_patient is not null)
group by h_visit.id_doctor,date_visit
having count(*) >=3;
--4