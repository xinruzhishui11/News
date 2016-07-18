package com.xinruzhishui.news.http.protocol;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.xinruzhishui.news.entity.JsonResult;
import com.xinruzhishui.news.entity.News;

public class InlandProtocol extends BaseProtocol<ArrayList<News>>{
	
	private ArrayList<News> Newslist;

	@Override
	public String getParams() {
		return "guonei";
	}

	@Override
	public ArrayList<News> parseJson(String result) {
		Gson gson = new Gson();
		JsonResult jsonResult = gson.fromJson(result, JsonResult.class);
		Newslist = jsonResult.result.data;
		
		return Newslist;
	}
	
	public ArrayList<News> getNewsist(){
		return Newslist;
	}

}
