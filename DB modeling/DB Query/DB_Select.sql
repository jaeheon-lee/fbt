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

delete from team_score where team_taker_id = 1;
update search_reservation set reservation_status = 0 where search_id =4;
update match_schedule set away_team_id = null where match_schedule_id =4;
update assignment set due_date = "2020-12-15 22:00" where assignment_id = 3;

delete from assignment_reservation where assignment_res_id = 5;

update assignment_reservation set reservation_status = 0 where assignment_id = 3;
delete from vote_match;
update match_schedule set home_team_id = 1 where match_schedule_id = 5;

