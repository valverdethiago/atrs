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

import com.crossover.atrs.model.Project;
import com.crossover.atrs.service.ProjectService;
import com.crossover.atrs.to.ProjectSearchTo;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService service;

	@RequestMapping(value = "/load/{id}", method = GET)
	public Project loadProject(@PathVariable("id") String id) {
		return service.load(id);
	}

	@RequestMapping(value = "/save", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Project save(@RequestBody Project project) {
		return service.save(project);
	}

	@RequestMapping(value = "/archive", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Project archive(@RequestBody Project project) {
		return service.archive(project);
	}

	@RequestMapping(value = "/activate", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Project activate(@RequestBody Project project) {
		return service.activate(project);
	}

	@RequestMapping(value = "/find", method = POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Project> find(OAuth2Authentication auth, @RequestBody ProjectSearchTo pageable) {
		Page<Project> page = service.search(pageable);
		return page;
	}

	@RequestMapping(value = "/list", method = GET)
	public Iterable<Project> list(OAuth2Authentication auth) {
		return service.listAll();
	}

}
