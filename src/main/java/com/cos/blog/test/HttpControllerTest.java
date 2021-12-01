package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답을 html으로 하려면 Controller

// 사용자가 요청 -> 응답을 데이터로 하려면 RestController
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest";

	@GetMapping("http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "ssar", "1234", "email");
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();

		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter : " + m.getId());
		return "lombok test 완료";
	}

//	select
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {
//		return "get 요청 : " + id + ", " + username;
//	}
	@GetMapping("/http/get")
	public String getTest(Member m) {
//		http://localhost:8080/http/get?id=1&username=asd

		return "get 요청 : " + m.getId() + ", " + m.getUsername();
	}

//	insert
	@PostMapping("/http/post")
	public String postTest(Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername();
	}

//	json으로 보낼때
	@PostMapping("/http/post2")
	public String postTest2(@RequestBody Member m) {
		return "get 요청 : " + m.getId() + ", " + m.getUsername();
	}

//	update
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", " + m.getUsername();
	}

//	delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
