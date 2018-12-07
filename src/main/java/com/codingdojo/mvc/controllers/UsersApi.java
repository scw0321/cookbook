package com.codingdojo.mvc.controllers;

import java.util.Iterator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersApi {
	//Ajax search
		@RequestMapping(value="/asearch", method=RequestMethod.POST)
		public String aSearch(@RequestParam(value="q", required=false) String input, @RequestParam(value="hits", required=false) Iterator<Object> hits) {
			System.out.println("came here ajax search");
			System.out.println("input "+ input);
			System.out.println("hits: "+ hits);
			return "ajax data succss";
		}
}
