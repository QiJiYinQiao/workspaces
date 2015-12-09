package com.oasys.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 获取客户端IP地址和Mac地址工具类
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public class ClientUtil {

	/** 本地服务IP. */
	private static final String LOOPBACK_ADDR = "127.0.0.1";

	/** 本机服务地址. */
	private static final String LOCALHOST_ADDR = "0:0:0:0:0:0:0:1";

	/**
	 * 私有的构造方法,工具类不能进行实例化
	 */
	private ClientUtil() {

	}

	/**
	 * 获取客户端的ip地址的方法
	 * 
	 * @return 客户端的ip地址
	 */
	public static String getIpAddr() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 通过客户端的ip获取客户端的mac地址的方法
	 * 
	 * @return 客户端地址的mac地址
	 */
	public static String getMacAddr() {
		try {
			// 是不是127.0.0.1
			boolean isLoopbackAddr = LOOPBACK_ADDR.equals(getIpAddr());
			// 是不是localhost,0:0:0:0:0:0:0:1
			boolean isLocalhostAddr = LOCALHOST_ADDR.equals(getIpAddr());
			return isLoopbackAddr || isLocalhostAddr ? getLocalMacAddr()
					: getClientMacAddr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取非服务端的客户端请求的mac地址
	 * 
	 * @return 访问的mac地址
	 * @throws Exception
	 */
	private static String getClientMacAddr() throws Exception {
		UdpGetClientMacAddr umac = new UdpGetClientMacAddr(getIpAddr());
		return umac.GetRemoteMacAddr();
	}

	/**
	 * 获取本服务端的请求的mac地址
	 * 
	 * @return 本服务访问的mac地址
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	private static String getLocalMacAddr() throws UnknownHostException,
			SocketException {
		// 获取本机的IP对象
		InetAddress inetAddress = InetAddress.getLocalHost();
		// 根据本地IP对象获取mac地址的字节数组
		byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
				.getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = mac.length; i < len; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}
		return sb.toString().trim().toUpperCase();
	}

}
