package com.niyang.googlemarket.protocol;

import com.niyang.googlemarket.http.HttpHelper;
import com.niyang.googlemarket.http.HttpHelper.HttpResult;

/**
 * 访问网络的基类
 * 
 * @author niyang
 *
 */
public class BaseProtocol {
	
	public void getData() {
		//先判断是否有缓存,有的话就加载缓存
		getDataFromServer();
	}

	//从网络请求数据
	private void getDataFromServer() {
		HttpResult httpResult = HttpHelper.get("");
		if (httpResult!=null) {
			String result = httpResult.getString();
			System.out.println("访问结果:"+result);
		}
	}
}
