package com.crossover.atrs.test.respository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.atrs.model.Project;
import com.crossover.atrs.respository.DatabaseConfiguration;
import com.crossover.atrs.respository.ProjectRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DatabaseConfiguration.class})
public class ProjectRepositoryTest {
	
	@Autowired
	private ProjectRepository projectRepository;

    @Before
	public void setup() {
		Project p1 = new Project();
		p1.setSummary("Test Project 1");
		p1.setDescription("Description Project 1");
		p1.setTitle("Title Project 1");
		this.projectRepository.save(p1);
		Project p2 = new Project();
		p1.setSummary("Test Project 2");
		p1.setDescription("Description Project 2");
		p1.setTitle("Title Project 2");
		this.projectRepository.save(p2);
	}
	
	@Test
	public void testList() {
		Iterable<Project> projects = this.projectRepository.findAll();
		Assert.assertTrue(projects.iterator().hasNext());
	}
	
}
