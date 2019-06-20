package com.niyang.googlemarket.protocol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.niyang.googlemarket.http.HttpHelper;
import com.niyang.googlemarket.http.HttpHelper.HttpResult;
import com.niyang.googlemarket.utils.IOUtils;
import com.niyang.googlemarket.utils.StringUtils;
import com.niyang.googlemarket.utils.UIUtils;

/**
 * 访问网络的基类
 * 
 * @author niyang
 *
 */
public abstract class BaseProtocol {

	// index 表示的是从哪个位置开始返回20条数据,用于分页
	public void getData(int index) {
		// 先判断是否有缓存,有的话就加载缓存
		String result = getCache(index);

		if (StringUtils.isEmpty(result)) {
			result=getDataFromServer(index);
		}
	}

	// 从网络请求数据
	// index 表示的是从哪个位置开始返回20条数据,用于分页
	private String getDataFromServer(int index) {
		//
		HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey() + "?index=" + index + getParams());

		if (httpResult != null) {
			String result = httpResult.getString();
			System.out.println("访问结果:" + result);
			return result;
		}
		return null;
	}

	// 获取网络连接关键字,子类必须实现
	public abstract String getKey();

	// 获取网络连接的参数,子类必须实现
	public abstract String getParams();

	// 写缓存
	// 以URL为key,以json为value
	public void setCache(int index, String json) {
		// 以URL为文件名,以json为文件内容,保存在本地
		File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
		// 生成缓存文件
		File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParams());
		FileWriter fw = null;
		try {
			fw = new FileWriter(cacheFile);
			// 缓存失效的截至时期
			long deadline = System.currentTimeMillis() + 30 * 60 * 1000;// 半个小时的有效期
			fw.write(deadline + "\n");// 在第一行写入缓存时间

			fw.write(json);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(fw);
		}
	}

	// 读缓存
	public String getCache(int index) {
		File cacheDir = UIUtils.getContext().getCacheDir();// 本应用的缓存文件夹
		// 生成缓存文件
		File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParams());

		if (cacheFile.exists()) {
			// 判断缓存是否存在
			BufferedReader br=null;
			try {
				br = new BufferedReader(new FileReader(cacheFile));
				String deadline = br.readLine();
				long deadtime = Long.parseLong(deadline);
				if (System.currentTimeMillis() < deadtime) {// 当前时间小于截至时间,说明缓存有效
					// 缓存有效
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					return sb.toString();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				IOUtils.close(br);
			}

		}
		return null;
	}
}
