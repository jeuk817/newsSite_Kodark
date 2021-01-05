package com.kodark.news.mappers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kodark.news.dto.ArticleWaitDto;
import com.kodark.news.dto.UserDto;

public interface AdminMapper {
	@Insert("insert into user (email, pwd, auth) values (#{email}, #{pwd}, #{auth})")
	public void insertReporter(@Param("email") String email, @Param("pwd") String pwd, @Param("auth") String auth);

	@Insert("insert into user_detail (user_id, nick_name, name, local, birth, gender, image) values (#{id}, #{nickname}, #{name}, #{local}, #{birth}, #{gender}, #{image})")
	public void insertReporterDetail(@Param("id") int id, @Param("nickname") String nickname, @Param("name") String name, @Param("local") String local, @Param("birth") Date birth, @Param("gender") String gender,
			@Param("image") String image);
	
	@Select("select * from user where email = #{email}")
	public UserDto getReporterId(@Param("email") String email);
	
	
	@Select ("select user_detail.name, user.email, article.id, article.title from user left join article\r\n"
			+ " on 	user.id = article.reporter_id right join user_detail on user.id = user_detail.user_id;")
	public List<ArticleWaitDto> getWaitArticles() ;
	
}
