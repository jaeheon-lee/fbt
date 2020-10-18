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
vr.attendance, vr.team_member_id, tm.totalNum,
vs.type, vs.friend_emp
FROM match_schedule m
JOIN vote_match v
ON v.match_schedule_id = m.match_schedule_id
LEFT OUTER join vote_match_result vr
on v.vote_match_id = vr.vote_match_id
join (
select team_id, count(team_id) totalNum
from team_member
where team_id =1
) tm
on tm.team_id=v.team_id
join vote_match_setting vs
on v.vote_match_id = vs.vote_match_id
where v.vote_status = 0
and v.team_id = 1;


SELECT 
v.vote_match_id, v.vote_reg_date, v.due_date,  
m.start_time, m.duration, m.stadium_address, m.match_type,
m.cost, m.stadium_parking, m.content,
vr.attendance, vr.team_member_id, tm.total_num,
vs.type, vs.friend_emp
FROM match_schedule m
JOIN vote_match v
ON v.match_schedule_id = m.match_schedule_id
LEFT OUTER join vote_match_result vr
on v.vote_match_id = vr.vote_match_id
join (
select team_id, count(team_id) total_num
from team_member
where team_id =1
) tm
on tm.team_id=v.team_id
join vote_match_setting vs
on v.vote_match_id = vs.vote_match_id
join vote_match_friend vmf
on v.vote_match_id = vmf.vote_match_id
where v.vote_status = 0
and v.team_id = 1;