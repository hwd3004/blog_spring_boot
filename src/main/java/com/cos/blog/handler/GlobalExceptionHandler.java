package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

// @ControllerAdvice - 모든 컨트롤러에서 발생할 수 있는 예외를 잡아 처리해줌
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

//	@ExceptionHandler - @Controller, @RestController가 적용된 Bean 내에서 발생하는
//	예외를 잡아서 하나의 메소드에서 처리해주는 기능

	@ExceptionHandler(value = Exception.class)
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

}
