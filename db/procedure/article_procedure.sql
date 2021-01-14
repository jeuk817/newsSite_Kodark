CREATE DEFINER=`jack`@`localhost` PROCEDURE `article_procedure`(
	in _switch varchar(20)
    , inout _id int
    , out _title varchar(200)
    , out _content varchar(5000)
    , out _createdAt datetime
    , out _editedAt datetime
    , out _hit int
	, out _image varchar(200)
	, out _imgDec varchar(200)
	, out _source varchar(200)
	, out _report_id int
	, out _email varchar(50)
	, out _name varchar(20)
    , out result_set  varchar(10)
)
BEGIN
if _switch = 'articleDetail' then
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