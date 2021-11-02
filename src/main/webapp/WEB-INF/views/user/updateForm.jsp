<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<input type="hidden" id="id" name="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">Username:</label> <input value="${principal.user.username}" type="text" class="form-control" placeholder="Enter username"
				id="username" name="username" readonly>
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email"
				id="email" name="email">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">Submit</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>