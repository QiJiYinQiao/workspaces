package com.example.suntest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;

public class UpdateInfoService {
	private Context context;

	public UpdateInfoService(Context context) {
		this.context = context;
	}
	
	public UpdateInfo getUpdateInfo(int urlId)throws Exception{
		String path = context.getResources().getString(urlId);//�õ�config.xml���ŵĵ�ַ
		URL url = new URL(path);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();//����һ����http����
		httpURLConnection.setConnectTimeout(5000);//�������ӵĳ�ʱʱ�䣬����Ϊ����
		httpURLConnection.setRequestMethod("GET");
		InputStream is = httpURLConnection.getInputStream();
		return UpdateInfoParser.getUpdateInfo(is);//����XML
	}
}
