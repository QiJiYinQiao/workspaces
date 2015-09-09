package com.bpms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
	 static MessageDigest md = null;

	    static {
	        try {
	            md = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException ne) {
	            //log.error("NoSuchAlgorithmException: md5", ne);
	        }
	    }

	    /**
	     * 对一个文件求他的md5值
	     * @param f 要求md5值的文件
	     * @return md5串
	     */
	    public static String md5(File f) {
	        FileInputStream fis = null;
	        try {
	            fis = new FileInputStream(f);
	            byte[] buffer = new byte[8192];
	            int length;
	            while ((length = fis.read(buffer)) != -1) {
	                md.update(buffer, 0, length);
	            }

	            //return new String(Hex.encodeHex(md.digest()));
	            return null;
	        } catch (FileNotFoundException e) {
	           // log.error("md5 file " + f.getAbsolutePath() + " failed:" + e.getMessage());
	            return null;
	        } catch (IOException e) {
	           // log.error("md5 file " + f.getAbsolutePath() + " failed:" + e.getMessage());
	            return null;
	        } finally {
	            try {
	                if (fis != null) {
	                    fis.close();
	                }
	            } catch (IOException ex) {
	            // log.error("文件操作失败",ex);
	            }
	        }
	    }
	    
	    /**
		 * 
		 * @param param
		 * @return param加密后的MD5码
		 * @description 将传入字符串经MD5加密后返回
		 */
		public final static String getMD5(String param) {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };
			try {
				byte[] paramBytes = param.getBytes();
				md.update(paramBytes);
				byte[] result = md.digest();
				int j = result.length;
				char raw[] = new char[j * 2];
				int index = 0;
				for (int i = 0; i < j; i++) {
					byte resultByte = result[i];
					raw[index++] = hexDigits[resultByte >>> 4 & 0xf];
					raw[index++] = hexDigits[resultByte & 0xf];
				}
				return new String(raw);
			} catch (Exception e) {
				return null;
			}
		}

	    /**
	     * 求一个字符串的md5值
	     * @param target 字符串
	     * @return md5 value
	     */
	    public static String md5(String target) {
	    	//DigestUtils.md5Hex(target);
	        return  null;
	    }
	    /**
	     * 可以比较两个文件是否内容相等
	     * @param args 
	     */
	    public static void main(String[] args){
//	        File newFile=new File("D:/files/paoding-analysis.jar.new");
//	        File oldFile=new File("D:/files/paoding-analysis.jar.old");
//	        String s1=md5(newFile);
//	        String s2=md5(oldFile);
//	        System.out.println(s1.equals(s2));
//	        System.out.println(s1);
//	        System.out.println(s2);
	    	String ss="admin";
	    	System.out.println(getMD5(ss));
	    }
}
