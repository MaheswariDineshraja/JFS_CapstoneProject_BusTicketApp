package com.ra.busBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ra.busBooking.dto.UserLoginDTO;

import com.ra.busBooking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
@Autowired
	private UserService userService;

	 
    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }
    
	@GetMapping
	@Operation(summary = "Login Page", description = "Displays the login page.")	   
	public String login() {
		return "login";
	}
	
	@PostMapping
	@Operation(summary = "Login User", description = "Processes the login request for a user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully logged in."),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials.")
    })
	public void loginUser(@ModelAttribute("user") 
	UserLoginDTO userLoginDTO) {
		System.out.println("UserDTO"+userLoginDTO);
		 userService.loadUserByUsername(userLoginDTO.getUsername());
		
	}
}
