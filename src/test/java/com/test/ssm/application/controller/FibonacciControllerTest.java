package com.test.ssm.application.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ssm.application.MyApplication;
import com.ssm.application.controller.FibonacciController;

@RunWith(SpringRunner.class)
@WebMvcTest(FibonacciController.class)
@ContextConfiguration(classes={MyApplication.class})
public class FibonacciControllerTest {


	@Autowired
	private MockMvc mvc;

	@Test
	public void generateFibonacciSeries() throws Exception {
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/helloWorld/fibonacci/10").accept(
						MediaType.APPLICATION_JSON);
		final MvcResult result = mvc.perform(requestBuilder).andReturn();

		final String expected = "{\"fibonacciSeries\":[0,1,1,2,3,5,8,13,21,34]}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	//Negative test case for error scenario
	@Test
	public void erroneousConditionTest() throws Exception {
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/helloWorld/fibonacci/-1").accept(
						MediaType.APPLICATION_JSON);
		final MvcResult result = mvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
