package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

//	@Autowired
//	private HttpSession session;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
//		@RequestBody - json으로 받음
//		@RequestBody가 없으면 key=value, x-www-form-urlencoded 형식으로 받음

		userService.회원가입(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user) {
//
////		principal - 접근주체
//		User principal = userService.로그인(user);
//
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {

		userService.회원수정(user);

//		https://www.youtube.com/watch?v=VESYOJWD5d8&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=64&ab_channel=%EB%8D%B0%EC%96%B4%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D
//		여기서는 트랜잭션이 종료되기 때문에 DB는 변경되지만
//		세션은 변경되지 않은 상태
//		직접 세션을 변경해야함
		
//		세선 등록
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}
