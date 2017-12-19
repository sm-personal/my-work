package com.ssm.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloWorldController {

	//Use Http verb GET
	@GetMapping(value = "/helloWorld")
	public ResponseEntity<String> hello() {
		//return a String with "Hello World" response
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

}