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
delete from search where search_id = 2;
update search_reservation set reservation_status = 0 where search_id = 4;

SELECT
s.search_id
FROM search s
JOIN search_reservation sr
ON s.search_id = sr.search_id
WHERE s.match_schedule_id = 4
AND sr.team_id_taker = 2;


