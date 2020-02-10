package com.example.demo.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.domain.User;

@Controller
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);//checking abject should be of user class
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target ;
		if(user.getPassword().length() < 6) {
			errors.rejectValue("password", "Length", "Password Lenght Should be more then 6");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match", "Password and ConfirmPassword Should be same");
		}
			
	}
	
}
