package com.crossover.atrs.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.atrs.model.Flight;

public interface FlightRepository extends PagingAndSortingRepository<Flight, String> {
	
	@Query("{ $and : [ "
			+ "        { $or : [  { $where: '?1 == false' },  { archivationDate: { $exists: false } } ] }, "
			+ "        { $or: [ "
			+ "                 { $or : [ { $where: '?0 == null' } , { title : { $regex : ?0 } } ] } , "
			+ "                 { $or : [ { $where: '?0 == null' } , { summary : { $regex : ?0 } } ] } , "
			+ "                 { $or : [ { $where: '?0 == null' } , { description : { $regex : ?0 } } ] }"
			+ "               ] } "
			+ "       ] }" )
	Page<Flight> search(String name, boolean onlyAtives, Pageable pageable);

}
