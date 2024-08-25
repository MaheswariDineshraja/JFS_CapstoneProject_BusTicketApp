//package com.ra.busBooking;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class BusTicketBookingAppApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}

package com.ra.busBooking;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.ra.busBooking.dto.ReservationDTO;
import com.ra.busBooking.dto.UserRegisterDTO;
import com.ra.busBooking.model.BusData;
import com.ra.busBooking.repository.BusDataRepository;
import com.ra.busBooking.repository.UserRepository;
import com.ra.busBooking.service.UserService;

@SpringBootTest
class BusBookingSystemApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BusDataRepository busDataRepository;

    @Test
    public void registerAndLoginWithUserAccount() {
        UserRegisterDTO userRegisteredDTO = new UserRegisterDTO();
        userRegisteredDTO.setEmail_id("user1@example.com");
        userRegisteredDTO.setName("User One");
        userRegisteredDTO.setPassword("123456");
        userRegisteredDTO.setRole("USER");
        userService.save(userRegisteredDTO);
        
        Assert.notNull(userRepo.findByEmail("mageshbe2003@yahoo.co.in"), "User should be found in DB");
        
        UserDetails user = userService.loadUserByUsername("mageshbe2003@yahoo.co.in");
        Assert.notNull(user, "User should be logged in successfully");
    }

    @Test
    public void registerAndLoginAdminAccount() {
        UserRegisterDTO userRegisteredDTO = new UserRegisterDTO();
        userRegisteredDTO.setName("Admin One");
        userRegisteredDTO.setEmail_id("admin1@example.com");
        userRegisteredDTO.setPassword("password1");
        userRegisteredDTO.setRole("ADMIN");
        userService.save(userRegisteredDTO);
        
        Assert.notNull(userRepo.findByEmail("admin1@example.com"), "Admin should be registered successfully");
        
        UserDetails user = userService.loadUserByUsername("admin1@example.com");
        Assert.notNull(user, "Admin should be logged in successfully");
    }

    
    @Test
    public void fetchBusData() {
        // Arrange: Save test data
        BusData bus1 = new BusData();
        bus1.setBusName("MetroBus");
        bus1.setFromDestination("LA");
        bus1.setToDestination("SF");
        bus1.setFilterDate("2024-09-01");
        bus1.setTime("08:00");
        bus1.setPrice(60.0);
        busDataRepository.save(bus1);

        BusData bus2 = new BusData();
        bus2.setBusName("CityLink");
        bus2.setFromDestination("SF");
        bus2.setToDestination("LA");
        bus2.setFilterDate("2024-09-01");
        bus2.setTime("16:00");
        bus2.setPrice(65.0);
        busDataRepository.save(bus2);

        // Act
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setFrom("SF");
        reservationDTO.setTo("LA");
        reservationDTO.setFilterDate("2024-09-01");
        List<BusData> busDataList = busDataRepository.findByToFromAndDate(
            reservationDTO.getTo(), reservationDTO.getFrom()
        );

        // Assert
        Assert.notEmpty(busDataList, "Bus Data should be available with the specified filters");
        Assert.isTrue(busDataList.size() > 0, "Bus Data list should not be empty");
    }

    @Test
    public void registerAndLoginWithMultipleUsers() {
        // User 1
        UserRegisterDTO user1 = new UserRegisterDTO();
        user1.setEmail_id("user2@example.com");
        user1.setName("User Two");
        user1.setPassword("password2");
        user1.setRole("USER");
        userService.save(user1);

        // User 2
        UserRegisterDTO user2 = new UserRegisterDTO();
        user2.setEmail_id("user3@example.com");
        user2.setName("User Three");
        user2.setPassword("password3");
        user2.setRole("USER");
        userService.save(user2);

        Assert.notNull(userRepo.findByEmail("user2@example.com"), "User Two should be registered successfully");
        Assert.notNull(userRepo.findByEmail("user3@example.com"), "User Three should be registered successfully");
        

        UserDetails user = userService.loadUserByUsername("user2@example.com");
        Assert.notNull(user, "User Two should be logged in successfully");

        
    }
}

