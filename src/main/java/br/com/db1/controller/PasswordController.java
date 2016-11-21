package br.com.db1.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.db1.domain.Password;

@RestController
public class PasswordController {
	
	@RequestMapping(value="/validade",method=RequestMethod.POST)
	@ResponseBody
	public Password validade(@RequestBody String password) {
		return new Password(password);
	}
}
