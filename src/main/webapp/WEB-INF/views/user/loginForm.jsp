<%@ include file="../layout/header.jsp"%>

<div class="container">

	<!-- config/SecurityConfig(스프링 시큐리티)로 보냄 -->
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username:</label> <input name="username" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input name="password" type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<button id="btn-login" class="btn btn-primary">Submit</button>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>