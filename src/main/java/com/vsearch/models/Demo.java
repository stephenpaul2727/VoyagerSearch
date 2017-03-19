package com.vsearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/*
 * A Model class which is a solr document(similar to entity in db).We give the parent core name in whcih the document is present.
 */
@SolrDocument(solrCoreName = "indexrepo")
public class Demo {
	
	@Id
	@Indexed
	private String id;
	
	@Indexed(name="pubyear_i", type="int")
	private int pubyear_i;
	
	@Indexed(name ="title_s", type="string")
	private String title_s; 
	
	@Indexed(name ="author_s", type="string")
	private String author_s;
	
	@Indexed(name="series_s",type="string")
	private String series_s;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPubyear_i() {
		return pubyear_i;
	}

	public void setPubyear_i(int pubyear_i) {
		this.pubyear_i = pubyear_i;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getAuthor_s() {
		return author_s;
	}

	public void setAuthor_s(String author_s) {
		this.author_s = author_s;
	}

	public String getSeries_s() {
		return series_s;
	}

	public void setSeries_s(String series_s) {
		this.series_s = series_s;
	}
	
}
