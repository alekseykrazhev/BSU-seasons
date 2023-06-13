
-- 1
insert into participant (id, surname, firstname, role, event) values
(SEQ_PART.NEXTVAL, 'Ivanov', 'Pert', SEQ_PART.DEFAULT, select name from event where MIN(num_part));

-- 2
update event set address_id=address_id+1 where address_id = (select address_id from event where success=false);

--3
create table archieve (
    name,
    organization,
    role,
    last_event
);

insert into archieve (name, organization, role, last_event)
select name, organization, mean_role, event from participant where MIN(event_count)
                                                          and participant.last_event_date
                                                              between TO_DATE('12.12.2021', 'DD.MM.RR')
                                                              and TO_DATE('12.12.2022', 'DD.MM.RR');
