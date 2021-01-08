package com.kodark.news.service;

import java.util.Map;


public interface UsersProceduerService {
	public void execuUsersProcedure(Map<String, Object> params);
	public String writeCommentReply(Map<String, Object> params);
}
