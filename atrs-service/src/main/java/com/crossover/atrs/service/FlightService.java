package com.crossover.atrs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.crossover.atrs.model.Flight;
import com.crossover.atrs.respository.FlightRepository;
import com.crossover.atrs.to.FlightSearchTo;

@Service
public class FlightService {

	@Autowired
	private FlightRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public Flight load(String id) {
		return this.getRepository().findOne(id);
	}
	
	public Flight save(Flight project) {
		return this.getRepository().save(project);
	}
	
	public Page<Flight> search(FlightSearchTo searchTo) {
		if(searchTo == null) {
			return null;
		}		
		if(searchTo.getSearchTerm() == null) {
			// TODO [valverde.thiago] Retirar essa camanga daqui. Query da pau com termos nulos
			searchTo.setSearchTerm("");
		}
		if(searchTo.getSort() == null) {
			searchTo.setSort(new Sort(new Order(Direction.ASC, "title")));
		}
		return this.getRepository().search(searchTo.getSearchTerm(), searchTo.isOnlyActives(), searchTo);
	}

	public Iterable<Flight> listAll() {
		return this.getRepository().findAll();
	}

	public Flight archive(Flight project) {
		if(project == null || project.getArchivationDate() != null) {
			return project;
		}
		project.setArchivationDate(new Date());
		return this.save(project);
	}

	public Flight activate(Flight project) {
		if(project == null || project.getArchivationDate() == null) {
			return project;
		}
		project.setArchivationDate(null);
		return this.save(project);
	}
	
	protected FlightRepository getRepository() {
		return repository;
	}

	protected MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	
}