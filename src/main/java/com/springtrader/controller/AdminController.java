package com.springtrader.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
	@RequestMapping("/admin")
	public String hello() {
		return "Hello Admin";
	}
}
