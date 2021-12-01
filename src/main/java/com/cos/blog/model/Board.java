package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

//	섬머노트 라이브러리를 쓸 예정, html 태그가 포함됨
//	@Lob - 대용량 데이터
	@Lob
	private String content;

//	조회수
	@ColumnDefault("0")
	private int count;

//	private int userId;
//	DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할수 있다
//	Many = Board, User = One
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

//	https://youtu.be/DtMmXQl4_hw?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm
//	mappedBy - 연관 관계의 주인이 아니다 (FK가 아니다), DB에서 칼럼을 생성하지않는다
//	Reply 클래스에서 정해준 private Board board;/@JoinColumn(name = "boardId")가 FK이다
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
	private List<Reply> reply;

	@CreationTimestamp
	private Timestamp createDate;

}
