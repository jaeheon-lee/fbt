select * from search;
select * from search_reservation;
select * from match_schedule;
select * from vote_match;
select * from vote_match_setting;
select * from vote_match_result;
select * from assignment;
select * from assignment_reservation;
select * from employ;
select * from employ_result order by employ_id;
select * from emp_score;
select * from user order by user_reg_date desc;
select * from team;
select * from user_stat;
select * from team_score;
select * from team_member order by team_id ASC;
select * from entry;
select * from match_result;
select * from notice;

insert into employ_result(employ_id, email, emp_result_status)
values(8, 'bioman6@gmail.com', 0);
insert into employ_result(employ_id, email, emp_result_status)
values(8, 'bioman7@gmail.com', 0);