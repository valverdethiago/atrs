package com.crossover.atrs.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.atrs.model.Flight;
import com.crossover.atrs.service.FlightService;
import com.crossover.atrs.to.FlightSearchTo;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService service;

	@RequestMapping(value = "/load/{id}", method = GET)
	public Flight loadProject(@PathVariable("id") String id) {
		return service.load(id);
	}

	@RequestMapping(value = "/save", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Flight save(@RequestBody Flight flight) {
		return service.save(flight);
	}

	@RequestMapping(value = "/archive", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Flight archive(@RequestBody Flight flight) {
		return service.archive(flight);
	}

	@RequestMapping(value = "/activate", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Flight activate(@RequestBody Flight flight) {
		return service.activate(flight);
	}

	@RequestMapping(value = "/find", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Flight> find(OAuth2Authentication auth, @RequestBody FlightSearchTo pageable) {
		Page<Flight> page = service.search(pageable);
		return page;
	}

	@RequestMapping(value = "/list", method = GET)
	public Iterable<Flight> list(OAuth2Authentication auth) {
		return service.listAll();
	}

}
