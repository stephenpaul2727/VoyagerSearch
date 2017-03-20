package com.vsearch;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vsearch.interfaces.DemoRepository;
import com.vsearch.models.Demo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SolrConfig.class)
public class RepositoryTest {
/*
 * Issue with MockitoJunitRunner raising MockReset error.  
 */
	@Resource
	private DemoRepository demoRepository;
	
	@Before
	public void setUp(){
		demoRepository = Mockito.mock(DemoRepository.class);
		demoRepository.deleteAll();
	}
	
	//Testing the Repository Item Saving Feature
	@Test
	public void RepositoryItemCreationTest() throws Exception {
		final Demo demo = new Demo();
		demo.setId("joke");
		demo.setAuthor_s("kill");
		demo.setPubyear_i(1900);
		demo.setSeries_s("slack");
		demo.setTitle_s("The ranger");
		demoRepository.save(demo);
		assertNotNull(demoRepository.findOne(demo.getId()));
	}
	
	//Testing the Repository Item deletion feature
	@Test
	public void RepositoryItemDeletionTest() throws Exception{
		final Demo demo = new Demo();
		demo.setId("Number4");
		demo.setAuthor_s("Einstein");
		demo.setPubyear_i(2010);
		demo.setSeries_s("LOL");
		demo.setTitle_s("The Relativity");
		demoRepository.save(demo);
		demoRepository.delete(demo);
		assertNull(demoRepository.findOne("Number4"));
	}
	
	
	//Testing the Repository Item updation feature
	@Test
	public void RepositoryItemUpdationTest() throws Exception{
		final Demo demo = new Demo();
		demo.setId("awesomeness");
		demo.setAuthor_s("newton");
		demo.setPubyear_i(2016);
		demo.setSeries_s("avengers");
		demo.setTitle_s("The gravity");
		demoRepository.save(demo);
		demo.setSeries_s("defenders");
		demoRepository.save(demo);
		assertNotEquals("avengers",demoRepository.findOne(demo.getId()).getSeries_s());
	}
	
	//Testing the search query feature
	@Test
	public void RepositoryItemSeaerchTest() throws Exception{
		final Demo demo = new Demo();
		demoRepository.deleteAll();
		demo.setId("awesome");
		demo.setAuthor_s("newton");
		demo.setPubyear_i(2016);
		demo.setSeries_s("avengers");
		demo.setTitle_s("The gravity");
		demoRepository.save(demo);
		demo.setId("contract");
		demo.setAuthor_s("einstein");
		demo.setPubyear_i(2010);
		demo.setSeries_s("defenders");
		demo.setTitle_s("The relativity");
		demoRepository.save(demo);
		demo.setId("awesome");
		demo.setAuthor_s("Tesla");
		demo.setPubyear_i(1880);
		demo.setSeries_s("Legends");
		demo.setTitle_s("The electricity");
		demoRepository.save(demo);
		Page<Demo> res= demoRepository.findByCustomQuery("awesome", new PageRequest(0,2));
		assertEquals(2,res.getNumberOfElements());
		
		
		
		
	}
	
	
	
}
