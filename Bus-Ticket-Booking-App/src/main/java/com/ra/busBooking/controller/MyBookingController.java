package com.ra.busBooking.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ra.busBooking.ObjCreation.ObjectCreation;
import com.ra.busBooking.dto.BookingdDTO;
import com.ra.busBooking.model.Bookings;
import com.ra.busBooking.model.User;
import com.ra.busBooking.repository.BookingsRepository;
import com.ra.busBooking.repository.UserRepository;
import com.ra.busBooking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/myBooking")
public class MyBookingController {
	
	private UserService userService;

    public MyBookingController(UserService userService) {
        super();
        this.userService = userService;
    }
	
	@Autowired
	BookingsRepository bookingsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	 @ModelAttribute("bookings")
	    public BookingdDTO bookingDto() {
	        return new BookingdDTO();
	    }
	    
		@GetMapping
		@Operation(summary = "View My Bookings", description = "Displays the list of bookings for the currently authenticated user.")
	    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of bookings.")
	    public String login(Model model) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
	        UserDetails users = (UserDetails) securityContext.getAuthentication().getPrincipal();
	        User user =userRepository.findByEmail(users.getUsername());
			List<Bookings> bs = bookingsRepository.findByUserId(user.getId());
			model.addAttribute("userDetails", user.getName());
			Collections.reverse(bs);
			model.addAttribute("bookings",bs);
			return "myBookings";
		}
		
		@GetMapping("/generate/{id}")
		@Operation(summary = "Generate Booking File", description = "Generates and sends an email with the booking file for a specific booking ID.")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successfully generated and sent the booking file."),
	        @ApiResponse(responseCode = "404", description = "Booking not found.")
	    })
		public String bookPage(@PathVariable int id,Model model) {
			Optional<Bookings> busdata = bookingsRepository.findById(id);
			 Optional<User> users =userRepository.findById(busdata.get().getUserId());
			String user = users.get().getName();
			BookingdDTO bks = ObjectCreation.createBookingsDTO(busdata.get());
			userService.sendEmail(bks, users.get(),busdata.get().getFileName());
	        model.addAttribute("userDetails", user);
	        List<Bookings> bs = bookingsRepository.findByUserId(users.get().getId());
	        Collections.reverse(bs);
			model.addAttribute("bookings",bs);
			return "redirect:/myBooking?success";	
		}
		
		private void setData(Bookings busData, Model model) {
			Optional<User> users =userRepository.findById(busData.getUserId());
			List<Bookings> bs = bookingsRepository.findByUserId(users.get().getId());
	        Collections.reverse(bs);
			model.addAttribute("bookings",bs);
		}
		
}
