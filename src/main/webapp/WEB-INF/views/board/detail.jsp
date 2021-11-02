<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm"><button class="btn btn-warning">수정</button></a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>

	<br /> <br />

	<div>
		<p id="id">${board.id}</p>
		<p>${board.user.username}</p>
	</div>

	<div>
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div>
		<div>${board.content}</div>
	</div>
	<hr />

	<div class="card">
		<div class="card-body">
			<textarea class="form-control" rows="1"></textarea>

		</div>
		<div class="card-footer">
			<button class="btn btn-primary">등록</button>
		</div>
	</div>

	<br />

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="comment--box" class="list-group">
			<li id="comment--1" class="list-group-item d-flex justify-content-between">
				<div>댓글 내용</div>
				<div class="d-flex">
					<div class="font-italic">작성자 : ㄴㅁㅇㄴㅁㅇ</div>
					&nbsp;
					<button class="badge">삭제</button>
				</div>
			</li>
		</ul>
	</div>

</div>
<br />

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>