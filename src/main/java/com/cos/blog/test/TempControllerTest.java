package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
//		기본 경로 - src/main/resources/static
//		static 폴더는 '브라우저가 인식할 수 있는 정적 파일'들만 있어야한다
		return "/home.html";
	}

	@GetMapping("/temp/jsp")
	public String tempJsp() {
//		prefix : /WEB-INF/views/
//		suffix : .tempJsp
//		풀네임 : /WEB-INF/views/test.jsp
		return "/test";
	}

}
