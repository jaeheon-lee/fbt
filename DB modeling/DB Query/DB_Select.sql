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

delete from employ_result where employ_result_id = 1;
select
distinct m.match_schedule_id, m.start_time, m.match_type,
tm.email
from team_member tm
join team t
on t.team_id = tm.team_id
join match_schedule m
on (m.home_team_id = t.team_id or m.away_team_id = t.team_id)
where tm.email = 'bioman7@gmail.com'
;

SELECT
e.employ_id,
t.team_id, t.team_name
FROM employ e
JOIN team t
ON e.team_id_giver = t.team_id
WHERE e.match_schedule_id = 1;
