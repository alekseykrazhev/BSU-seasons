-- 1
create view days_left as
    date('12.12.2022', 'DD:MM:YY') MINUS select date_event from event;

select * from days_left;

-- 2
create view participant_num as
    count(select * from participant where visited=true and event = all(select event_name
                                                                   from event
                                                                   where event.success=true) );

select * from participant_num;

-- 3
create view activity as
    select name, organization from participant where events = all(participant.events);

select * from activity;
