CREATE DEFINER=`root`@`localhost` PROCEDURE `article_procedure`(
	in _switch varchar(20)
    , inout _id int
    , out _title varchar(200)
    , out _subTitle varchar(500)
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
    , in _category varchar(20)
	, out result_set  varchar(10)
)
BEGIN

-- 기사 상세페이지(16) -----------------------------------------------------------
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

-- 핫뉴스(6) -------------------------------------------------------
	if _switch = 'popular' then
		if _category = 'all' then
			select a.id, a.title, a.sub_title, i.image, i.description as imgDec, created_at
			from article as a
				left outer join image as i on a.id = i.article_id
				left outer join category as c on a.category_id = c.id 
			where a.created_at > DATE_SUB(current_timestamp(), INTERVAL 24 HOUR)
			order by a.hit desc limit 10;
		else 
			select a.id, a.title, a.sub_title, i.image, i.description as imgDec, created_at
			from article as a
				left outer join image as i on a.id = i.article_id
				left outer join category as c on a.category_id = c.id 
			where a.created_at > DATE_SUB(current_timestamp(), INTERVAL 24 HOUR)
				and c.name = _category
			order by a.hit desc limit 10;
			
	end if;

-- 카테고리 정보 ------------------------------------------------------
	if _switch = 'category_info' then
		select * from category;
	end if;
end if;

END