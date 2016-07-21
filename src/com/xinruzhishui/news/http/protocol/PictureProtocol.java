package com.xinruzhishui.news.http.protocol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.xinruzhishui.news.entity.Picture;
import com.xinruzhishui.news.entity.PictureResult;
import com.xinruzhishui.news.http.HttpHelper;
import com.xinruzhishui.news.http.HttpHelper.HttpResult;
import com.xinruzhishui.news.utils.IOUtils;
import com.xinruzhishui.news.utils.StringUtils;
import com.xinruzhishui.news.utils.UIUtils;


public class PictureProtocol {

	/**
	 * 获取数据
	 * 
	 * @param index
	 *            分页请求数据的起始位置
	 */
	public ArrayList<Picture> getData(int index) {
		// 先从本地缓存中读取数据,如果有,就直接返回,如果没有,才从网络加载
		String result = getCache(index);
		if (result == null) {
			result = getDataFromNet(index);
		}

		if (result != null) {
			return parseJson(result);
		}

		return null;
	}

	/**
	 * 从本地缓存中读取数据
	 */
	private String getCache(int index) {
		// 获取系统缓存目录
		File cacheDir = UIUtils.getContext().getCacheDir();
		// 以网络链接作为文件名称,保证特定接口对应特定数据
		File cacheFile = new File(cacheDir, "picture_" + index
				);

		if (cacheFile.exists()) {// 缓存文件存在
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(cacheFile));
				String validTime = reader.readLine();// 读取第一行内容,缓存截止时间
				if (System.currentTimeMillis() < Long.parseLong(validTime)) {// 当前时间小于缓存截止时间,说明缓存还在有效期范围内

					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}

					return sb.toString();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.close(reader);
			}
		}

		return null;
	}

	/**
	 * 向本地缓存写数据
	 */
	private void setCache(String result, int index) {
		// 获取系统缓存目录
		File cacheDir = UIUtils.getContext().getCacheDir();
		// 以网络链接作为文件名称,保证特定接口对应特定数据
		File cacheFile = new File(cacheDir, "picture_" + index);

		FileWriter writer = null;
		try {
			writer = new FileWriter(cacheFile);

			// 缓存有效期限, 截止时间设定为半小时之后
			long validTime = System.currentTimeMillis() + 30 * 60 * 1000;
			writer.write(validTime + "\n");// 将缓存截止时间写入文件第一行
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(writer);
		}
	}

	/**
	 * 访问网络获取数据
	 * 
	 * @param index
	 *            分页请求数据的起始位置
	 * @return
	 */
	private String getDataFromNet(int index) {
		HttpResult result = HttpHelper.get(HttpHelper.PICURL +index
				+"/1");
		if (result != null) {
			String strResult = result.getString();
			if (!StringUtils.isEmpty(strResult)) {
				// 将缓存写到本地文件中
				setCache(strResult, index);
				return strResult;
			}
		}

		return null;
	}



	/**
	 * 解析json数据 ,
	 * 
	 * @param result
	 */
	public ArrayList<Picture> parseJson(String result){
		Gson gson = new Gson();
		PictureResult pictureResult = gson.fromJson(result, PictureResult.class);
		List<Picture> pictures = pictureResult.getResults();
		return (ArrayList<Picture>) pictures;
	}


}
