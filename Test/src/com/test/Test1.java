package com.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test1 {
	public static void main(String[] args) throws Exception {
		HttpURLConnection urlconn = null;
		URL postUrl;
		String media_id = "eBVZymSsNs_IQIKHYqPY0MIZa4vFZMu6KqrL1Vd-HFPknG-DaJ7UGmRXm50gBPM";

		String downloadURL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+1+"&media_id="+media_id;
		postUrl = new URL(downloadURL);
		urlconn = (HttpURLConnection) postUrl.openConnection();
		urlconn.setRequestMethod("GET");  
		urlconn.setConnectTimeout(5 * 1000);  
		urlconn.connect();
        InputStream inStream = urlconn.getInputStream();//通过输入流获取图片数据  
        byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
		
        File file = new File("c:/Users/sun/Desktop/1.jpg");  
        FileOutputStream fops = new FileOutputStream(file);  
        fops.write(btImg);  
        fops.flush();  
        fops.close();  
        System.out.println("图片已经写入到C盘");  
		/*System.out.println(urlconn.getInputStream());
		File file = new File("C:/Users/Sun/Desktop/naliduo.jpg");
		//BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
		BufferedInputStream bf = new BufferedInputStream(urlconn.getInputStream());
		FileOutputStream fos = new FileOutputStream(new File("c:/Users/sun/Desktop/1.jpg"));
		byte[] b = new byte[2048];
		int size = 0;
		while((size = bf.read(b)) != -1){
			fos.write(b,0,size);
		}
		System.out.println("finish");*/
	
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
}

