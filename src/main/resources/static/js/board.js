const index = {
	init: function() {
		$("#btn-save").on("click", () => {
			// 자바스크립트에서 펑션과 화살표함수의 this가 다른 것에 주의
			this.save();
		});

		$("#btn-delete").on("click", () => {
			this.delete();
		});

		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		const data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		// console.log(data);
		// console.log("-------------------");
		// console.log(JSON.stringify(data));

		// ajax 통신을 이용해서, 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/api/board",

			// http body 데이터
			data: JSON.stringify(data),

			//   MIME
			contentType: "application/json; charset=utf-8",

			//   dataType: "json",
		})
			.done(function(resp) {
				alert("글쓰기가 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},

	delete: function() {
		const id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			contentType: "application/json; charset=utf-8"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error))
		});
	},

	update: function() {
		const id = $("#id").val();
		const data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
		})
			.done(function(resp) {
				alert("글수정이 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},
};

index.init();
