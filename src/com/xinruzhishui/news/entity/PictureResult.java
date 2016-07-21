package com.xinruzhishui.news.entity;

import java.util.List;

public class PictureResult {
    private boolean error;
    private List<Picture> results;
    
	public PictureResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PictureResult(List<Picture> results) {
		super();
		this.results = results;
	}

	public List<Picture> getResults() {
		return results;
	}

	public void setResults(List<Picture> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "QueryResult [results=" + results + "]";
	}
}
