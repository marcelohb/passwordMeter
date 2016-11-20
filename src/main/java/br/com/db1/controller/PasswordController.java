package br.com.db1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.db1.domain.Password;

@RestController
public class PasswordController {
	
	@RequestMapping("/validade")
	public Password validade(@RequestParam(required=false, defaultValue="12345678") String password) {
		return new Password(password);
	}
}
