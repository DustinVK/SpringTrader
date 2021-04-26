package com.springtrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springtrader.service.ExternalStockService;
import com.springtrader.service.PortfolioService;

@Controller
public class UserController {
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired 
	ExternalStockService stockService;
	
	@RequestMapping("/users/{username}")
    public String getUserInfo(@PathVariable("username") String userName, Authentication auth, Model model){
	  System.out.println(auth.getName());
	  return "user";
    }
	

   
//  @GetMapping("/listHeaders")
//  public ResponseEntity<String> listAllHeaders(
//    @RequestHeader Map<String, String> headers) {
//      headers.forEach((key, value) -> {
//          System.out.println(String.format("Header '%s' = %s", key, value));
//      });
//
//      return new ResponseEntity<String>(
//        String.format("Listed %d headers", headers.size()), HttpStatus.OK);
//  }
}
