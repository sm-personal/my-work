package com.ssm.application.controller;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ssm.application.pojo.FibonacciSeries;

@RestController
@RequestMapping
public class FibonacciController {
	private static final Gson GSON = new GsonBuilder().create();
	private static final Integer INTEGER_TWO = new Integer(2);

	//Use Http verb GET
	@GetMapping(value = "/helloWorld/fibonacci/{number}")
	public ResponseEntity<String> generateFibonacciSeries(
			@PathVariable int number) {
		try {
			if(NumberUtils.INTEGER_ZERO != number) {
				int[] fibonacciValues = new int[number];
				//generate the fibonacci series
				for(int i = NumberUtils.INTEGER_ZERO; i < number; i++){ 
					fibonacciValues[i] = fibonacci(i);
				}
				final FibonacciSeries fibonacciSeries = new FibonacciSeries(fibonacciValues);

				//build the JSON response
				final String jsonResponse = GSON.toJson(fibonacciSeries);

				//return the success response
				return new ResponseEntity<String>(jsonResponse, HttpStatus.OK);
			}
			else {
				//return the response with error code
				return new ResponseEntity<String>("Please enter a non zero integer", HttpStatus.INTERNAL_SERVER_ERROR);			}
		}
		catch (Exception ex) {
			//return the response with error code
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static int fibonacci(int number){ 
		//first 2 numbers in the series will be 0 and 1
		if(number <= NumberUtils.INTEGER_ONE){ 
			return number; 
		} 
		//recursive call
		return fibonacci(number - NumberUtils.INTEGER_ONE) + fibonacci(number - INTEGER_TWO); 
	} 


}
