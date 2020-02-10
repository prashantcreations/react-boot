package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // this provide globaly  exception hadeling  
@RestController
public class CustomeResponseEntityException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handelProjectException(ProjectIdException proEx, WebRequest webReq) {
		ProjectIdExceptionResponse exceptionRes = new ProjectIdExceptionResponse(proEx.getMessage());
		return new ResponseEntity(exceptionRes,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handelBacklogException(ProjectBacklogException proEx, WebRequest webReq){
		ProjectBacklogExceptionResponse backlogExp = new ProjectBacklogExceptionResponse(proEx.getMessage());
		return new ResponseEntity(backlogExp,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNameAlreadyExistException(UserNameAlreadyExistException alredyExist,WebRequest webReq){
		UserNameAlreadyExistExceptionResponse userAlredyExist = new UserNameAlreadyExistExceptionResponse(alredyExist.getMessage());		
		return new ResponseEntity(userAlredyExist,HttpStatus.BAD_REQUEST);
	}

}
