package com.bpms.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 在线预览文档的转换器类
 * 
 * @author liuhh
 *
 */
public class DocConverter {
	/** 文件的名称. */
	private static final String PROPERTIES_NAME = "docConverter.properties";

	/** Properties工具类. */
	private static Properties pro = new Properties();

	/** 加载配置文件. */
	static {
		try {
			pro.load(DBBackUpUtil.class.getClassLoader().getResourceAsStream(
					PROPERTIES_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 　当前运行的系统。环境 windows、linux */
	private static final String SYSTEM_TYPE = pro.getProperty("systemType");

	/** windows下转化插件的位置。 */
	private static final String EXEC_PATH = pro.getProperty("execPath");

	/** 输入路径 ，如果不设置就输出在默认的位置. */
	private static final String OUTPUT_PATH = pro.getProperty("outPutPath");

	/** 不带后缀的源文件名称. */
	private String fileName;

	/** 转化后的PDF的文件。 */
	private File pdfFile;

	/** 转化后的SWF的文件。 */
	private File swfFile;

	/** 转化后的DOC的文件。 */
	private File docFile;

	/**
	 * 构造方法
	 * 
	 * @param fileString
	 *            要转化的源文件
	 */
	public DocConverter(String fileString) {
		init(fileString);
	}

	/**
	 * 初始化
	 * 
	 * @param fileString
	 */
	private void init(String fileString) {
		this.fileName = fileString.substring(0, fileString.lastIndexOf("."));
		this.docFile = new File(fileString);
		this.pdfFile = new File(fileName + ".pdf");

		// 设置输出目录
		if (StringUtils.isNotBlank(OUTPUT_PATH)) {
			// 获取源文件的名称
			String realName = "";
			if (fileName.lastIndexOf("/") != -1) {
				realName += fileName.substring(fileName.lastIndexOf("/"),
						fileName.lastIndexOf("."));
			} else {
				realName += fileName.substring(0, fileName.lastIndexOf("."));
			}

			// 拼接要保存的文件路径
			if (OUTPUT_PATH.charAt(OUTPUT_PATH.length()) == '/') {
				this.swfFile = new File(OUTPUT_PATH + realName + ".swf");
			} else {
				this.swfFile = new File(OUTPUT_PATH + File.separator + realName
						+ ".swf");
			}
		} else {
			this.swfFile = new File(fileName + ".swf");
		}
	}

	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private void doc2pdf() throws Exception {
		// 如果需要转化的文件已经存在，则直接返回，不需要在进行转换
		if (pdfFileExists())
			return;

		// 链接openOffice开始转化为PDF
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		try {
			connection.connect();
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			converter.convert(docFile, pdfFile);
			connection.disconnect();
		} catch (java.net.ConnectException e) {
			e.printStackTrace();
			throw e;
		} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 转换成 swf
	 */
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		// windows环境处理
		if (SYSTEM_TYPE.equalsIgnoreCase("Windows")) {
			try {
				Process p = r.exec(EXEC_PATH + " " + pdfFile.getPath() + " -o "
						+ swfFile.getPath() + " -T 9");
				loadStream(p.getInputStream());
				loadStream(p.getErrorStream());
				if (pdfFileExists()) {
					pdfFile.delete();
				}
				if (docFileExists()) {
					docFile.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} else if (SYSTEM_TYPE.equalsIgnoreCase("Linux")) {// 环境处理
			try {
				Process p = r.exec("pdf2swf " + pdfFile.getPath() + " -o "
						+ swfFile.getPath() + " -T 9");
				loadStream(p.getInputStream());
				loadStream(p.getErrorStream());
				if (pdfFileExists()) {
					pdfFile.delete();
				}
				if (docFileExists()) {
					docFile.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	/**
	 * 读取进程流
	 * 
	 * @param in
	 * @throws IOException
	 */
	private void loadStream(InputStream in) throws IOException {
		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();
		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}
	}

	/**
	 * 转换主方法
	 */
	public void conver() {
		try {
			doc2pdf();
			pdf2swf();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回文件路径
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFileExists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}
	}

	/**
	 * 判断转化后的文件是否存在
	 * 
	 * @return
	 */
	public boolean swfFileExists() {
		return swfFile.exists();
	}

	/**
	 * 判断转化为PDF是否存在
	 * 
	 * @return
	 */
	private boolean pdfFileExists() {
		return pdfFile.exists();
	}

	/**
	 * 判断要转化的文件是否存在
	 * 
	 * @return
	 */
	public boolean docFileExists() {
		return docFile.exists();
	}
}
