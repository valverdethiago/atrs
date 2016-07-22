package com.crossover.atrs.test.respository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.atrs.model.Project;
import com.crossover.atrs.respository.ProjectRepository;
import com.crossover.atrs.test.service.TestConfiguration;
import com.crossover.atrs.to.ProjectSearchTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfiguration.class})
public class ProjectRepositoryTest {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Test
	public void test() {
		ProjectSearchTo to = new ProjectSearchTo();
		to.setOnlyActives(false);
		to.setOffset(0);
		to.setPageSize(2);
		to.setPageNumber(1);
		to.setSearchTerm("Desc");
		Page<Project> page = this.projectRepository.search(to.getSearchTerm(), to.isOnlyActives(), to);
		System.out.println(page.getTotalElements()+" encontrados");
		System.out.println(page.getTotalPages()+" páginas");
		for(Project project : page.getContent()) {
			System.out.println(project.getTitle());
		}
	}

}
