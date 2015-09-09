package com.bpms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 导出doc
 * @author liuhh
 *
 */
public class WordGeneratorUtil {

	/**
	 * 导出doc文件
	 */
	public static File createDoc(Object data,String tempName) {
		Configuration cf = new Configuration();
		cf.setDefaultEncoding("utf-8");
		cf.setClassForTemplateLoading(WordGeneratorUtil.class, "/");
		File f = new File("temp"
				+ UUID.randomUUID().toString().replaceAll("-", "") + ".doc");
		try {
			Template t = cf.getTemplate(tempName, "utf-8");
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(data, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}

}