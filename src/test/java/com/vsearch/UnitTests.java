package com.vsearch;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import com.vsearch.controllers.QueryController;
import com.vsearch.interfaces.DemoRepository;
import com.vsearch.models.Demo;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SolrConfig.class)
public class UnitTests {

	@Mock
	@Resource
	private DemoRepository demoRepository;
	
	@Mock
	private QueryController queryController;
	
	@Mock
	private Demo demo;
	
	@Before
	public void setUp(){
		demoRepository.deleteAll();
	}
	
	//Testing the Repository Item deletion feature
	@Test
	public void RepositoryItemDeletionTest() throws Exception{
		demo.setId("Number4");
		demo.setAuthor_s("Einstein");
		demo.setPubyear_i(2010);
		demo.setSeries_s("LOL");
		demo.setTitle_s("The Relativity");
		demoRepository.save(demo);
		demoRepository.delete(demo);
		assertNull(demoRepository.findOne("Number4"));
	}
	
	@Test
	public void controllerTest() throws Exception {
		assertNotNull(queryController);
	}
	

	
	
}
