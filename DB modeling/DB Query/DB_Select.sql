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
select * from team_member order by team_id ASC;
select * from entry;
select * from match_result;
select * from notice;

SELECT *
FROM match_schedule m
LEFT OUTER JOIN vote_match v
ON m.match_schedule_id = v.match_schedule_id
LEFT OUTER JOIN(
SELECT 
team_id, count(team_member_id) total_num
FROM team_member
GROUP BY team_id
) tm
ON v.team_id = tm.team_id
LEFT OUTER JOIN vote_match_setting vs
ON vs.vote_match_id = v.vote_match_id
JOIN team t 
ON m.home_team_id = t.team_id
LEFT OUTER JOIN team t2
ON m.away_team_id = t2.team_id
WHERE v.match_schedule_id = 3
AND v.team_id = 1