package com.kodark.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodark.news.dto.ArticleDto;
import com.kodark.news.dto.CommentDto;
import com.kodark.news.dto.CommentReputationDto;
import com.kodark.news.dto.EmotionDto;

@RequestMapping(value = "/article")
@Controller
public class ArticleController {
	
	/*
	 * 기사상세페이지
	 */
	@RequestMapping(value = "/articleid=?", method = RequestMethod.GET)
	public ArticleDto article() { //기사리스트ex arraylist
		return null;
	}
	
	/*
	 * 기사감정표현데이터
	 */
	@RequestMapping(value = "/emotion?", method = RequestMethod.GET)
	public EmotionDto emotion() {
		return null;
	}
	
	/*
	 * 감정표현선택
	 */
	@RequestMapping(value = "/emotion", method = RequestMethod.POST)
	public EmotionDto emotionSelect() {
		return null;
	}
	
	/*
	 * 기사댓글데이터
	 */
	@RequestMapping(value = "/comment?", method = RequestMethod.GET)
	public CommentDto comment() {
		return null;
	}
	
	/*
	 * 댓글입력
	 */
	@RequestMapping(value = "/comment?", method = RequestMethod.POST)
	public CommentDto addComment() {
		return null;
	}
	
	/*
	 * 대댓글
	 */
	@RequestMapping(value = "/comment/reply?", method = RequestMethod.GET)
	public String reply() {
		return null;
	}
	
	/*
	 * 대댓글작성
	 */
	@RequestMapping(value = "/comment/reply?", method = RequestMethod.POST)
	public CommentDto addReply() {
		return null;
	}
	/*
	 * 댓글추천/비추천
	 */
	@RequestMapping(value = "/comment/reputation", method = RequestMethod.POST)
	public CommentReputationDto reputation() {
		return null;
	}
	
	/*
	 * 댓글신고
	 */
	@RequestMapping(value = "/comment/report?", method = RequestMethod.POST)
	public CommentDto report() {
		return null;
	}
	
	
}

	