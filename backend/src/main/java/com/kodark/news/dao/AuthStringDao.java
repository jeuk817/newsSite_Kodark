package com.kodark.news.dao;

public class AuthStringDao {
	//인증 생성
	@Override
	public void createAuthKey(String userEmail, String authKey) throws Exception { // 인증키 DB에 넣기
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userEmail", userEmail);
		map.put("authKey", authKey);

		session.selectOne(namespace + ".createAuthKey", map);
	}

	//인증 여부 확인
	@Override
	public void userAuth(String userEmail) throws Exception { // 인증키 일치시 DB칼럼(인증여부) false->true 로 변경
		session.update(namespace + ".userAuth", userEmail);
	}
}
