
CREATE DEFINER=`root`@`localhost` PROCEDURE `article_procedure`(
in _switch varchar(20)
,in _id int
,in _article_id int
,in  _reporter_id int
,out _title varchar(200)
,out _content varchar(5000)
,out _createdAt datetime
,out _editedAt datetime
,out _hit int
,out _image varchar(200)
,out _imgDec varchar(200)
,out _source varchar(200)
,out _report_id int
,out _email varchar(50)
,out _name varchar(20)
,out result_set varchar(8)  
)
BEGIN
	if _switch = 'blind' then
		update article set status = 'deleted' where reporter_id = _reporter_id and id =_article_id;
	elseif _switch = 'get_list' then
		select a.id,a.title,a.content, i.image,i.description 
        from article a
        left outer join image i on i.article_id = a.id
        where a.reporter_id = _reporter_id
        ;
	-- 19. 기사 댓글데이터		
	elseif _switch = 'comment' then
		select c.id,u.id as userId,u.email,ud.nick_name as nickName,ud.local, c.content
			,date_format(c.created_at, '%Y-%m-%d %H:%i:%S') as createdAt,c.del_flag as delFlag 
			,(select count(reputation) from comm_reputation where reputation = 'recommend' and comment_id = c.id ) as recommend
			,(select count(reputation) from comm_reputation where reputation = 'decommend'and comment_id = c.id ) as decommend
		from comment as c
		join users as u on c.user_id = u.id
		join user_detail as ud on u.id = ud.user_id
		where c.article_id = _article_id
		limit 15 offset _id
		;
 elseif _switch = 'articleDetail' then
	select article.id, article.title, article.content, article.created_at, article.edited_at, article.hit
		,image.image, image.source, image.description
		, users.id, users.email, user_detail.name
	from article
		inner join image on article.id = image.article_id
		inner join users on reporter_id = users.id
		inner join user_detail on users.id = user_detail.user_id
	where article.id = _id;
	end if;

END