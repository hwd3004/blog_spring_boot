<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<input type="hidden" id="id" value="${board.id}" />

		<div class="form-group">
			<label for="title">Title</label> <input value="${board.title}" name="title" type="text" class="form-control" placeholder="Enter Title" id="title">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content" name="content">${board.content}</textarea>
		</div>
		<button id="btn-update" class="btn btn-primary">Submit</button>
	</form>

</div>
<br />

<script>
	$(".summernote").summernote({
		// placeholder: "Hello Bootstrap 4",
		tabsize : 2,
		height : 300,
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>