package com.ra.busBooking.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ra.busBooking.dto.UserRegisterDTO;
import com.ra.busBooking.model.Bookings;
import com.ra.busBooking.model.User;
import com.ra.busBooking.dto.BookingdDTO;

public interface UserService extends UserDetailsService{

	User save(UserRegisterDTO userRegisteredDTO);
	
	Bookings updateBookings(BookingdDTO bookingDTO,UserDetails user);
	
	void sendEmail(BookingdDTO bookingDTO, User users, String nameGenrator) ;
		// TODO Auto-generated method stub
	
	
}
