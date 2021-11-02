const index = {
	init: function() {
		$("#btn-save").on("click", () => {
			// 자바스크립트에서 펑션과 화살표함수의 this가 다른 것에 주의
			this.save();
		});

		$("#btn-update").on("click", () => {
			this.update();
		});

	},
	save: function() {
		const data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		// console.log(data);
		// console.log("-------------------");
		// console.log(JSON.stringify(data));

		// ajax 통신을 이용해서, 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",

			// http body 데이터
			data: JSON.stringify(data),

			//   MIME
			contentType: "application/json; charset=utf-8",

			//   dataType: "json",
		})
			.done(function(resp) {
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},

	update: function() {
		const data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		console.log(data)

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
		})
			.done(function(resp) {
				alert("회원수정이 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},

};

index.init();
