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
select * from user;
select * from team;
select * from user_stat;
select * from team_score;
select * from team_member;
select * from entry;
select * from match_result;
select * from invite;

delete from match_schedule where match_schedule_id = 8;
delete from team_score where team_taker_id = 1;
update search_reservation set reservation_status = 0 where search_id =4;
update match_schedule set away_team_id = null where match_schedule_id =4;
update assignment set due_date = "2020-12-15 22:00" where assignment_id = 3;

delete from assignment_reservation where assignment_res_id = 5;
delete from employ;
update assignment_reservation set reservation_status = 0 where assignment_id = 3;
update match_schedule set is_confirmed = 0 where match_schedule_id = 4;
update employ_result set emp_result_status = 0 where employ_id = 2;
update vote_match_setting set cancel_number = 1 where vote_match_id = 4;
update employ set req_number = 3 where employ_id = 1;
update vote_match set vote_status = 0 where vote_match_id = 5;

delete from emp_score where match_schedule_id = 6;
delete from team_score where match_schedule_id = 6;
delete from entry where match_schedule_id = 6;
delete from match_result where match_schedule_id = 6;
update team set team_manner = 0, team_ability = 0 where team_id = 2;

select * from emp_score;
SELECT
emp_score_id, manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, 
manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, 
match_schedule_id, team_giver_id, emp_email
FROM emp_score
WHERE match_schedule_id = 6
AND emp_email = 'bioman7@gmail.com'
AND team_giver_id = 1;

select * from team_score;
select
avg(manner_promise) manner_promise, avg(manner_contact) manner_contact, avg(manner_rule) manner_rule, 
avg(manner_body_fight) manner_body_fight, avg(manner_slang) manner_slang, avg(manner_smoking) manner_smoking,
avg(manner_uniform) manner_uniform, avg(manner_payment) manner_payment, avg(manner_arrangement) manner_arrangement, 
avg(manner_referee) manner_referee, avg(manner_tackle) manner_tackle, avg(forward) forward, avg(middle) middle, avg(defence) defence
from emp_score
group by emp_email;

UPDATE entry
SET
attendance = 2,
type = 0,
match_score = 9,
goal = 2,
assist = 0
WHERE entry_id = 73;
update user set emp_ability = 0, emp_manner =0;