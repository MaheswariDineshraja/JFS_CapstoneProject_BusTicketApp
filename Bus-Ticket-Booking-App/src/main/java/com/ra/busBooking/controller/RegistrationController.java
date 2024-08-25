package com.ra.busBooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ra.busBooking.dto.UserRegisterDTO;
import com.ra.busBooking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	 private UserService userService;

	    public RegistrationController(UserService userService) {
	        super();
	        this.userService = userService;
	    }

	    @ModelAttribute("user")
	    public UserRegisterDTO userRegistrationDto() {
	        return new UserRegisterDTO();
	    }

	    @GetMapping
	    @Operation(summary = "Display Registration Form", description = "Displays the registration form for new users.")
	    @ApiResponse(responseCode = "200", description = "Registration form displayed successfully.")
	    public String showRegistrationForm() {
	        return "register";
	    }

	    @PostMapping
	    @Operation(summary = "Register New User", description = "Processes the registration of a new user.")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "302", description = "Redirects to login page after successful registration."),
	        @ApiResponse(responseCode = "400", description = "Bad request - Registration details are invalid.")
	    })
	    public String registerUserAccount(@ModelAttribute("user") 
	              UserRegisterDTO registrationDto) {
	        userService.save(registrationDto);
	        return "redirect:/login";
	    }
}