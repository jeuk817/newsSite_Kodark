package com.kodark.news.service;

import java.util.List;
import java.util.Map;

import com.kodark.news.dto.TestDto;

public interface TestService {
	public List<TestDto> getAllTests();
<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
	public TestDto getTest(int id);
//	public String excuEmpProcedure(String name);
//	public void excuEmpProcedure(TestDto testDto);
	public void excuEmpProcedure(Map<String, Object> parameters);
<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
}
