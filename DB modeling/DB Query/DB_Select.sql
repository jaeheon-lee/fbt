-- showAllMember
SELECT * FROM team_member where team_id = 1;

select * from match_schedule;
select * from vote_match;
select * from vote_match_setting;
select * from vote_match_friend;

SELECT 
v.vote_match_id, v.vote_reg_date, v.due_date,  
m.start_time, m.duration, m.stadium_address, m.match_type,
m.cost, m.stadium_parking, m.content,
vr.attendance, vr.team_member_id, vr.email, tm.totalNum,
vs.type, vs.friend_emp
FROM match_schedule m
JOIN vote_match v
ON v.match_schedule_id = m.match_schedule_id
LEFT OUTER join vote_match_result vr
on v.vote_match_id = vr.vote_match_id
join (
select team_id, count(team_id) totalNum
from team_member
) tm
on tm.team_id=v.team_id
join vote_match_setting vs
on v.vote_match_id = vs.vote_match_id
where v.vote_status = 0
and v.team_id = 1;

select * from team;
select * from team_member where team_id = 2;
select * from vote_match_result;
select * from vote_match_setting;
select * from vote_match;
select * from invite;
select * from match_schedule;

update match_schedule set away_team_id = 3 where match_schedule_id = 2;
update vote_match set match_schedule_id = 3 where vote_match_id = '2-3';

SELECT 
v.vote_match_id, v.vote_reg_date, v.due_date,  
m.start_time, m.duration, m.stadium_address, m.match_type,
m.cost, m.stadium_parking, m.content, m.home_team_id,
vs.type, vs.friend_emp,
t.emblem, t.team_name, t.team_id
FROM match_schedule m
JOIN vote_match v
ON m.match_schedule_id = v.match_schedule_id
JOIN vote_match_setting vs
ON vs.vote_match_id = v.vote_match_id
JOIN team t 
ON m.home_team_id = t.team_id
WHERE (m.home_team_id = 2 OR m.away_team_id = 2)
AND v.vote_status =0
;

SELECT 
vr.vote_match_id,
count(if(vr.team_member_id > 0, 1, null)) voted_num,
count(if(vr.attendance > 0 and team_member_id > 0, 1, null)) attend_num, 
count(if(vr.attendance = 0 and team_member_id > 0, 1, null)) abscent_num, 
count(vr.email) friend_num,
tm.total_num
FROM vote_match_result vr
JOIN vote_match v
ON vr.vote_match_id = v.vote_match_id
JOIN(
SELECT 
team_id, count(team_member_id) total_num
FROM team_member
WHERE team_id = 1
) tm
ON v.team_id = tm.team_id
GROUP BY vr.vote_match_id
;

select * from vote_match_result;

SELECT 
v.vote_match_id, v.vote_reg_date, v.due_date,  
m.start_time, m.duration, m.stadium_address, m.match_type,
m.cost, m.stadium_parking, m.content,
vr.attendance, vr.team_member_id, vr.email , tm.total_num,
vs.type, vs.friend_emp,
t.team_name, t.emblem
FROM match_schedule m
JOIN vote_match v
ON v.match_schedule_id = m.match_schedule_id
LEFT OUTER join vote_match_result vr
on v.vote_match_id = vr.vote_match_id
JOIN (
SELECT team_id, count(team_id) total_num
FROM team_member
) tm
ON tm.team_id=v.team_id
JOIN vote_match_setting vs
ON v.vote_match_id = vs.vote_match_id
JOIN team t
ON v.team_id = t.team_id
WHERE v.vote_status = 0
AND v.team_id = 1;

SELECT 
v.vote_match_id, v.vote_reg_date, v.due_date, 
m.start_time, m.duration, m.stadium_address, m.match_type,
m.cost, m.stadium_parking, m.content,
t.emblem, t.team_name
FROM vote_match v
JOIN match_schedule m
ON v.match_schedule_id = m.match_schedule_id
JOIN team t 
ON m.home_team_id = t.team_id;

SELECT

        

