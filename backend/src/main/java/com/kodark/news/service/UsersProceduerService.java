package com.kodark.news.service;

import java.util.List;
import java.util.Map;


public interface UsersProceduerService {
	public void execuUsersProcedure(Map<String, Object> params);
	
	public Map<String, Object> myPage(Map<String, Object> params);
}
