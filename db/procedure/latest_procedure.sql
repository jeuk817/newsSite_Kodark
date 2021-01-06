CREATE DEFINER=`root`@`localhost` PROCEDURE `latest_procedure`(
inout section varchar(10)
,out type varchar(6)
,out data varchar(9999)
,out result_set varchar(10)
)
BEGIN
declare num int;
	select count(*) into num from article as a join category as c on a.category_id = c.id 
    where c.name = section;
    if num > 0 then   
		set @id = (select a.id from article as a join category as c on a.category_id = c.id join image as i on a.id = i.article_id where c.name = section);
		set @title = (select a.title from article as a join category as c on a.category_id = c.id join image as i on a.id = i.article_id where c.name = section);
        set @content = (select a.content from article as a join category as c on a.category_id = c.id join image as i on a.id = i.article_id where c.name = section);
        set @image = (select i.image from article as a join category as c on a.category_id = c.id join image as i on a.id = i.article_id where c.name = section);
        set @description = (select i.description from article as a join category as c on a.category_id = c.id join image as i on a.id = i.article_id where c.name = section);
        
        set data = concat('[{id : ',@id,', title : ',coalesce(@title,'null')
						,', content : ',coalesce(@content,'null'),', image : ',coalesce(@image,'null'),', description : '
                        ,coalesce(@description,'null'), ', _link : {rel : "article", href : "/article?articleId", method : "get"}]' );
        
        
      
						  
                        
                          
                           
                          
  		set type = 'latest'; 
		set result_set = '200';
	else 
		set result_set = '404';
    end if;    
END