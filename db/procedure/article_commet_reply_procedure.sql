CREATE DEFINER=`jack`@`localhost` PROCEDURE `article_comment_reply_procedure`(
	in _articleId int,
	in _commentId int
)
BEGIN
    
    select
	c.id
    ,u.id as userId
    ,u.email
    ,ud.nick_name as nickName
    ,ud.local
    ,c.content
    ,date_format(c.created_at, '%Y-%m-%d %H:%i:%S') as createdAt
    ,c.del_flag as delFlag 
    ,(select count(reputation) from comm_reputation where reputation = 'recommend' and comment_id = c.id ) as recommend
    ,(select count(reputation) from comm_reputation where reputation = 'decommend'and comment_id = c.id ) as decommend
    from comment as c
    join users as u on c.user_id = u.id
    join user_detail as ud on u.id = ud.user_id
	where c.article_id = _articleId AND c.parent_id = _commentId
    ;
    
    
END