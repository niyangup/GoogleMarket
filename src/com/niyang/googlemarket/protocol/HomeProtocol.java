package com.niyang.googlemarket.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niyang.googlemarket.domain.AppInfo;

public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {

	@Override
	public String getKey() {
		return "home";
	}

	@Override
	public String getParams() {
		return "";
	}

	@Override
	public ArrayList<AppInfo> parseData(String json) {

		List<AppInfo> appInfoList = new ArrayList<AppInfo>();

		try {

			JSONObject object = new JSONObject(json);
			JSONArray ja = object.getJSONArray("list");

			ArrayList<AppInfo> appinfoList = new ArrayList<AppInfo>();

			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);

				AppInfo appInfo = new AppInfo();

				appInfo.name = jo.getString("name");
				appInfo.id = jo.getString("id");
				appInfo.packageName = jo.getString("packageName");
				appInfo.iconUrl = jo.getString("iconUrl");
				appInfo.downloadUrl = jo.getString("downloadUrl");
				appInfo.des = jo.getString("des");
				appInfo.size = jo.getLong("size");
				appInfo.stars = (float) jo.getDouble("stars");
				appInfoList.add(appInfo);
			}

			JSONArray pic = object.getJSONArray("picture");

			ArrayList<String> piclist = new ArrayList<String>();
			for (int i = 0; i < pic.length(); i++) {
				String picurl = pic.getString(i);
				piclist.add(picurl);
			}
			
			return appinfoList;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
