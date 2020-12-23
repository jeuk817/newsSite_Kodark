package com.kodark.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/section", method = RequestMethod.GET)
public class SectionController {
	/*
	 * 섹션페이지(세부섹션 생각하기)
	 * 2020-12-23
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
	@RequestMapping(value = "/tech")
	public String tech() {
		return "/tech";
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
