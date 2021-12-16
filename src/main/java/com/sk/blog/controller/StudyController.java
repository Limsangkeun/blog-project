package com.sk.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/std")
public class StudyController {
	
	
	private static final Logger log = LoggerFactory.getLogger(StudyController.class);

	
	@RequestMapping("/detail")
	public String getStudyDetail() {
		return "study/studydetail";
	}
}
