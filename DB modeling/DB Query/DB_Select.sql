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

select * from match_schedule m join vote_match v on m.match_schedule_id = v.match_schedule_id
left outer join search s on  s.match_schedule_id = m.match_schedule_id
where s.search_id is null;

select 
m.match_schedule_id, m.start_time, m.duration, m.cost, m.writer, m.reg_date, m.home_content, m.away_content, m.stadium_name, 
m.stadium_type, m.stadium_address, m.stadium_x, m.stadium_y, m.stadium_parking, m.stadium_shower, 
m.match_type, m.home_team_id, m.away_team_id, m.is_confirmed,
date_add(m.start_time, interval m.duration hour) end_time,
t2.team_id away_team_id, t2.emblem away_emblem, t2.team_name away_team_name
FROM match_schedule m
LEFT OUTER JOIN team t2
ON m.away_team_id = t2.team_id
WHERE (m.home_team_id = 1 OR m.away_team_id = 1)
AND (m.start_time >= '2021-02-01' 
AND  date_add('2021-02-28', interval +1 day)>= m.start_time)
AND m.is_confirmed = 1
;