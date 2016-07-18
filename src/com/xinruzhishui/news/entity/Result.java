package com.xinruzhishui.news.entity;

import java.util.ArrayList;

public class Result {
	public String stat;
	public ArrayList<News> data;
	@Override
	public String toString() {
		return "Result [stat=" + stat + ", data=" + data + "]";
	}
}
