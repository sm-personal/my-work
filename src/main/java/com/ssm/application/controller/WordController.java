package com.ssm.application.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ssm.application.pojo.Paragraph;
import com.ssm.application.pojo.WordCount;

@RestController
@RequestMapping
public class WordController {
	private static final Gson GSON = new GsonBuilder().create();

	//Use Http verb POST
	@PostMapping(value = "/helloWorld/paragraph")
	public ResponseEntity<String> findWords(
			@RequestBody Paragraph paragraph) {
		try {
			String jsonResponse = StringUtils.EMPTY;
			if(null != paragraph) {
				//accept the paragraph in the request body through POST
				final String text = paragraph.getText();
				//replace all non alphabetic characters and punctuation with empty space as they aren't considered a word
				//if the same word is repeated irrespective of cases, consider it to be the same word, so convert the text to lowercase
				final String[] words = text.replaceAll("[^a-zA-Z ]", StringUtils.EMPTY).replaceAll("\\p{Punct}", StringUtils.EMPTY).toLowerCase().split("\\s+");
				Arrays.sort(words);
				//treemap is sorted on its keys - in this case all the unique words 
				Map<String, Integer> map = new TreeMap<String, Integer>();
				for (String wordString : words) {
					if (map.containsKey(wordString)) {
						//if already present, increment the count of occurrence
						map.put(wordString, map.get(wordString) + NumberUtils.INTEGER_ONE);
					} else {
						//else add it with initial count as 1
						map.put(wordString, NumberUtils.INTEGER_ONE);
					}
				}

				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					//build the JSON response with all the words sorted alphabetically
					jsonResponse = jsonResponse + GSON.toJson(new WordCount(entry.getKey(), entry.getValue()));
				}
			}
			//return the response
			return new ResponseEntity<String>(jsonResponse, HttpStatus.OK);
		}
		catch (Exception ex) {
			//return the response with error code
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
