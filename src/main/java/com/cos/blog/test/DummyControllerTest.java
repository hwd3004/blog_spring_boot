package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

//	의존성 주입
	@Autowired
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
//			System.out.println(e);
			return "삭제에 실패하였습니다.";
		}

		return id + "번 아이디 삭제되었습니다.";
	}

	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

//	@Transactional - 함수 종료시 자동 커밋
//	email, password 수정
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

//		userRepository.save(requestUser);
		return user;
	}

	// 페이징이 0부터 시작
//	http://localhost:8080/dummy/user?page=0
//	@GetMapping("/dummy/user")
//	public Page<User> pageList(
//			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
////		findAll 메소드가 2개이다
//		Page<User> users = userRepository.findAll(pageable);
//		return users;
//	}
	@GetMapping("/dummy/user")
	public List<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);

//		if(pagingUser.isFirst()) {}
//		if(pagingUser.isLast()) {}
		List<User> users = pagingUser.getContent();
		return users;
	}

//	https://youtu.be/z_yxfFUX1xI?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm
//	{id} 주소로 파라미터 전달
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
//		user/4를 찾으려하는데, 3번까지만 있으면 user가 null이 될 것
//		그럼 return null이 되는데, 프로그램에 문제가 발생됨
//		Optional로 User 객체를 가져오도록 해서 null인지 아닌지 판단해서 리턴해야함

//		.get()을 하면, null이 생길 경우를 가정하지않고 가져오도록 함
//		User user = userRepository.findById(id).get();

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});

//		람다식
//		User user = userRepository.findById(id).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});

//		해당 id에 유저가 없으면, 값을 null로 가져옴
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		});

//		스프링 부트의 MessageConverter가 json으로 변환해서 보내줌
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
