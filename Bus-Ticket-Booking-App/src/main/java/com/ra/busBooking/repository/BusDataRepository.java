package com.ra.busBooking.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ra.busBooking.model.BusData;

@Repository
public interface BusDataRepository extends JpaRepository<BusData, Integer>{

	@Query(value = "select * from Reservation  where reservation.to_destination =:to and reservation.from_destination =:from and reservation.filter_date =:date  order By reservation.filter_date desc " , nativeQuery = true)
	List<BusData> findByToFromAndDate(String to , String from, String date);
	
//	@Query(value = "select * from reservation  where reservation.to_destination =:to AND reservation.from_destination =:from" , nativeQuery = true)
//	List<BusData> findByToFromAndDate(String to, String from);
	

	
	

}