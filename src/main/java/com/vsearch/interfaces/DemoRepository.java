package com.vsearch.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.vsearch.models.Demo;
/*
 * A custom Repository bean called DemoRepository which extends SolrCrudRepository provided by java which provides useful methods already incorporated.
 */
public interface DemoRepository extends SolrCrudRepository<Demo,String>{
	
		// queries all the document items which contains given id in their id fields.
	 	public List<Demo> findById(String id);
	 
	 	//queries all the document items which contains given search term in any of the fields,only results in the two fields id and title_s(remaining fields are excluded) and displays by limiting a certain amount to a page(Paging).
	    	@Query("q:*:* AND fl=id,title_s")
	    	public Page<Demo> findByCustomQuery(String searchTerm, Pageable pageable);
	   
	    

	   
	 
}
