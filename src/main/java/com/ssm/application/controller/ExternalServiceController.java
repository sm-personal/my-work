package com.ssm.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class ExternalServiceController {

	//Use Http verb GET
	@GetMapping(value = "/helloWorld/externalService")
	public ResponseEntity<String> makeExternalServiceCall() {
		try {
			//make a call to external service and return the response
			return new RestTemplate().getForEntity(
					"https://jsonplaceholder.typicode.com/posts",
					String.class);
		}
		catch (Exception ex) {
			//return the response with error code
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}