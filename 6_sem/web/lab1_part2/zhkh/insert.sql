-- delete statements --

delete from dispatcher where id = 1 or id = 2;
delete from tenant where id = 1 or id = 2;
delete from worker where id = 1 or id = 2 or id = 3;
delete from application where id = 1 or id = 2;

-- insert statements --

insert into dispatcher values (1);
insert into dispatcher values (2);

insert into tenant values (1, 'first');
insert into tenant values (2, 'second');

insert into worker values (1, 1, false);
insert into worker values (2, 2, false);
insert into worker values (3, 1, true);

insert into application values (1, 1, 1, date('2023-12-29'), 1);
insert into application values (2, 2, 2, date('2002-02-02'), 2);
