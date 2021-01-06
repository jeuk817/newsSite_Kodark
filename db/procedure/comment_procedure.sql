CREATE DEFINER=`root`@`localhost` PROCEDURE `comment_procedure`(
)
BEGIN
select
	c.id 
 	,u.id as userId
    ,u.email
    ,ud.nick_name as nickName
    ,ud.local
    , c.content
    ,c.created_at as createdAt
    ,c.del_flag as delFlag 
    ,(select count(reputation) from comm_reputation where reputation = 'recommend' and comment_id = c.id ) as recommend
    ,(select count(reputation) from comm_reputation where reputation = 'decommend'and comment_id = c.id ) as decommend
    from comment as c
    join users as u on c.user_id = u.id
    join user_detail as ud on u.id = ud.user_id
	
    ;
    END