package com.test.ssm.application.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ssm.application.MyApplication;
import com.ssm.application.controller.HelloWorldController;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
@ContextConfiguration(classes={MyApplication.class})
public class HelloWorldControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void hello() throws Exception {
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/helloWorld").accept(
						MediaType.APPLICATION_JSON);
		final MvcResult result = mvc.perform(requestBuilder).andReturn();

		final String expected = "Hello World";

		assertEquals(expected, result.getResponse()
				.getContentAsString());
	}


}
