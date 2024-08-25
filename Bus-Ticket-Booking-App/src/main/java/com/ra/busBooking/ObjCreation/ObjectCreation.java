package com.ra.busBooking.ObjCreation;

import com.ra.busBooking.dto.BookingdDTO;
import com.ra.busBooking.model.Bookings;
import com.ra.busBooking.model.BusData;

public class ObjectCreation {
	
	public static  BookingdDTO createBookingsDTO(BusData busdata) {
		BookingdDTO bks = new BookingdDTO();
		
		bks.setBusName(busdata.getBusName());
		bks.setFilterDate(busdata.getFilterDate());
		bks.setFromDestination(busdata.getFromDestination());
		bks.setToDestination(busdata.getToDestination());
		bks.setPrice(busdata.getPrice());
		bks.setNoOfPersons(0);
		bks.setTime(busdata.getTime());
		bks.setTotalCalculated(0.0);
		
		return bks;
		
	
	}
	
	public static BookingdDTO createBookingsDTO(Bookings busdata) {
		BookingdDTO bks = new BookingdDTO();
		bks.setId(busdata.getId());
				
				bks.setBusName(busdata.getBusName());
				bks.setFilterDate(busdata.getFilterDate());
				bks.setFromDestination(busdata.getFromDestination());
				bks.setToDestination(busdata.getToDestination());
				bks.setNoOfPersons(busdata.getNoOfPersons());
				bks.setTime(busdata.getTime());
				bks.setTotalCalculated(busdata.getTotalCalculated());
				return bks;
			}

}