package com.example.seleniumdemo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {

	@GetMapping
	public String index(PersonForm personForm) {
		return "person/index";
	}

	@PostMapping
	public String result(@Valid PersonForm personForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "person/index";
		}

		return "person/result";
	}

}
