package com.kodark.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/section", method = RequestMethod.GET)
@Controller
public class SectionController {
	/*
	 * 하위섹션 생각해보기
	 */
	@RequestMapping(value = "/politics")
	public String politics() {
		return "/politics";
	}
	@RequestMapping(value = "/economy")
	public String economy() {
		return "/economy";
	}
	@RequestMapping(value = "/society")
	public String society() {
		return "/society";
	}
	@RequestMapping(value = "/world")
	public String world() {
		return "/world";
	}
	@RequestMapping(value = "/sports")
	public String sports() {
		return "/sports";
	}
	@RequestMapping(value = "/weather")
	public String weather() {
		return "/weather";
	}
}
