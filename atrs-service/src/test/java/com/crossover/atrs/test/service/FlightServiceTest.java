package com.crossover.atrs.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.atrs.model.Flight;
import com.crossover.atrs.respository.DatabaseConfiguration;
import com.crossover.atrs.service.FlightService;
import com.crossover.atrs.service.ServiceConfiguration;
import com.crossover.atrs.to.FlightSearchTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServiceConfiguration.class, DatabaseConfiguration.class})
public class FlightServiceTest {
	
	@Autowired
	private FlightService projectService;
	
    @Before
	public void setup() {
		Flight p1 = new Flight();
		p1.setSummary("Test Project 1");
		p1.setDescription("Description Project 1");
		p1.setTitle("Title Project 1");
		this.projectService.save(p1);
	}
	
	@Test
	public void testInitialSearch() {
		FlightSearchTo searchTo = new FlightSearchTo();
		searchTo.setOffset(10);
		searchTo.setOnlyActives(true);
		searchTo.setPageNumber(1);
		searchTo.setSearchTerm(null);
		searchTo.setSort(null);
		Page<Flight> page = this.projectService.search(searchTo);
		Assert.assertEquals(1, page.getTotalElements());
		Assert.assertEquals(1, page.getTotalPages());
	}

}
