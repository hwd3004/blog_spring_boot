package com.cos.blog.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 (IoC)
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

//	import org.springframework.transaction.annotation.Transactional;
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글을 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제하기(int id, PrincipalDetail principal) {

		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글을 찾을 수 없습니다.");
		});

		if (board.getUser().getId() != principal.getUser().getId()) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}

		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		
//		영속화 완료
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글을 찾을 수 없습니다.");
		});

//		더티 체킹, 자동 업데이트됨, db flush
//		함수 종료시(Service가 종료될 때) 트랜잭션 종료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}

}
