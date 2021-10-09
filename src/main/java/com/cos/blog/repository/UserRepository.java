package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

//	jpa 네이밍 전략
//	findBy
//	select * from user where username = ? and password = ?
	User findByUsernameAndPassword(String username, String passwrod);

//	@Query(value = "select * from user where username = ? and password = ?", nativeQuery = true)
//	User login(String username, String password);

}
