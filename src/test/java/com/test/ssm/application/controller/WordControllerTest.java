package com.test.ssm.application.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
import com.ssm.application.controller.WordController;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
@ContextConfiguration(classes={MyApplication.class})
public class WordControllerTest {


	@Autowired
	private MockMvc mvc;

	@Test
	public void findWords() throws Exception {
		final String exampleCourseJson = "{\"text\": \"This is a test paragraph that tests the occurrence of word test\"}";

		final RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/helloWorld/paragraph")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		final MvcResult result = mvc.perform(requestBuilder).andReturn();

		final String expected = "{\"word\":\"a\",\"count\":1}{\"word\":\"is\",\"count\":1}{\"word\":\"occurrence\",\"count\":1}{\"word\":\"of\",\"count\":1}{\"word\":\"paragraph\",\"count\":1}{\"word\":\"test\",\"count\":2}{\"word\":\"tests\",\"count\":1}{\"word\":\"that\",\"count\":1}{\"word\":\"the\",\"count\":1}{\"word\":\"this\",\"count\":1}{\"word\":\"word\",\"count\":1}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
