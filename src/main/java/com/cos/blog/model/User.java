package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity - 클래스를 테이블화, User 클래스가 자동으로 MySQL에 테이블이 생성이 됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert - insert 시에 null인 필드를 제외시킴
public class User {

//	오라클 - 시퀀스, mysql - auto_inc
//	@GeneratedValue(strategy = GenerationType.IDENTITY) - 프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	아이디
	@Column(nullable = false, length = 30, unique = true)
	private String username;

//	비밀번호 해쉬 암호화할 예정
	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

//	Enum을 쓰는게 좋음
//	@ColumnDefault(" 'user' ")
//	private String role;

//	DB에는 RoleType이란게 없으므로, 해당 타입이 String이라는걸 알려줌
	@Enumerated(EnumType.STRING)
	private RoleType role;

//	시간 자동 입력
	@CreationTimestamp
	private Timestamp createDate;

}
