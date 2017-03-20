package com.vsearch.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsearch.interfaces.DemoRepository;
import com.vsearch.models.Demo;
/*
A Rest Controller defined to handle the queries in form of a url with request parameters. A Rest controller is used as we have no views to map model with. The controller itself acts as a response body and displays on web page.
Using controller class to follow Spring REST Api Standard.
 */
@RestController
public class QueryController {

	//Autowiring the solrcrudrepository
	@Autowired
	private DemoRepository demoRepository;
	
	@RequestMapping("/")
	public String greeting(){
		return "Welcome to Solr Connect!";
	}
	//Save the given request parameters as a demo object model in the core which calls update to update the core document
	@RequestMapping(value="/saveQuery")
	public String save(@RequestParam("id") String id, @RequestParam("title_s") String title_s, @RequestParam("author_s") String author_s, @RequestParam("series_s") String series_s, @RequestParam("pubyear_i") int pubyear_i)
	{
		Demo demo = new Demo();
		demo.setId(id);
		demo.setAuthor_s(author_s);
		demo.setTitle_s(title_s);
		demo.setPubyear_i(pubyear_i);
		demo.setSeries_s(series_s);
		demoRepository.save(demo);
		return "Details Indexed in Solr Core document";
	}
	//Given a search string as request parameter with page restriction integer between 1 to 10. It will result in the all the rows that matched the given string displayed as page object which we stringify.
	//res will contain the searchquery output
	@RequestMapping(value="/searchQuery")
	public List<Demo> getAllBySearch(@RequestParam("searchString") String searchString, @RequestParam("pageRestrict") int pageRestrict){
		Page<Demo> res = demoRepository.findByCustomQuery(searchString, new PageRequest(0,pageRestrict));
		List<Demo> list =res.getContent();
//		for(Demo demo:list){
//			System.out.println(demo.getAuthor_s());
//			System.out.println(demo.getSeries_s());
//			//we will get pubyear_i as 0 as it is not listed as one of return fields in repository.
//			System.out.println(demo.getPubyear_i());
//		}
		return list;
	}
	
	
	//given a field of type int to sort. THe following controller map sorts the data in document in descending order of the field items.
	//res will contain the searchquery output
	@RequestMapping(value="/descSortQuery")
	public String getAllByDescFilter(@RequestParam("field") String field){
		Iterable<Demo> res = demoRepository.findAll(new Sort(Sort.Direction.DESC,field));
		//uncomment the following lines to observe the data.
//		Iterator<Demo> i = res.iterator();
//		while(i.hasNext()){
//			Demo demo = (Demo) i.next();
//			System.out.println(demo.getPubyear_i());
//			
//		}
		return "successfully retrieved data in the Iterable object res please check!";
	
	}
	//given a field of type int to sort. THe following controller map sorts the data in document in ascending order of the field items.
	//res will contain the searchquery output
	@RequestMapping(value="/asceSortQuery")
	public String getAllByAsceFilter(@RequestParam("field") String field){
		Iterable<Demo> res = demoRepository.findAll(new Sort(Sort.Direction.ASC,field));
		//uncomment the following lines to observe the data.
//		Iterator<Demo> i = res.iterator();
//		while(i.hasNext()){
//			Demo demo = (Demo) i.next();
//			System.out.println(demo.getPubyear_i());
//			
//		}
		return "successfully retrieved data in the Iterable obect res please check!";
	}

	
	
}
