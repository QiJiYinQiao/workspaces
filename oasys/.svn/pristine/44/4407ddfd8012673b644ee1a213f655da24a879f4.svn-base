package com.oasys.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * 数据库备份工具类
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public class DBBackUpUtil {

	/** 文件的名称. */
	private static final String PROPERTIES_NAME = "db-back-up.properties";

	/** 项目的根目录. */
	private static final String BASE_PATH = System.getProperty("oasys");

	/** Properties工具类. */
	private static Properties pro = new Properties();

	/**
	 * 无参数的构造方法,限定工具类,不能进行创建实例
	 */
	private DBBackUpUtil() {

	}

	/** 加载配置文件. */
	static {
		try {
			pro.load(DBBackUpUtil.class.getClassLoader().getResourceAsStream(
					PROPERTIES_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 备份文件名称. */
	private static String DB_BACK_NAME = pro.getProperty("db_back_name");

	/** 备份数据出所在的地址. */
	private static String DB_IP = pro.getProperty("db_ip");

	/** 备份数据库用户. */
	private static String DB_USER = pro.getProperty("db_user");

	/** 备份数据库密码. */
	private static String DB_PWD = pro.getProperty("db_pwd");

	/** 备份数据的数据库名称. */
	private static String DB_NAME = pro.getProperty("db_name");

	/**
	 * 备份数据库的方法
	 * 
	 * @return 备份数据库名称
	 */
	public static String dbBackUp() {

		// 随机生成备份文件的名称
		String fileName = getFileName();
		try {
			// 创建临时文件生成的目录
			createMkdirs(getMkdirsPath());

			// 拼接执行的备份文件的sql命令
			StringBuffer sbs = generateCommand(getFilePath(fileName));

			// 运行命令
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(sbs.toString());

			// 生成备份文件
			createDBBackupFile(getFilePath(fileName), process);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 执行命令完毕,进行保存文件
	 * 
	 * @param fileName
	 *            全路径文件名称
	 * @param process
	 *            执行命令的单独进程
	 * @throws FileNotFoundException
	 *             文件不存在的异常
	 * @throws IOException
	 *             文件读写异常
	 */
	private static void createDBBackupFile(String fileName, Process process)
			throws FileNotFoundException, IOException {

		// 读取备份数据并生成临时文件
		InputStream in = process.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File(fileName));

		int len = 1024;
		int off = 0;
		byte[] b = new byte[len];

		while (-1 != (len = in.read(b, off, len))) {
			fos.write(b, off, len);
			fos.flush();
		}

		in.close();
		fos.close();
	}

	/**
	 * 拼接sql备份的DB命令
	 * 
	 * @param fileName
	 *            制定DB命令保存文件的路径
	 * @return 拼接号的命令
	 */
	private static StringBuffer generateCommand(String fileName) {
		StringBuffer sbs = new StringBuffer();
		sbs.append("mysqldump ");
		sbs.append(" -h  " + DB_IP);
		sbs.append(" --user=" + DB_USER);
		sbs.append(" --password=" + DB_PWD);
		sbs.append(" --lock-all-tables=true");
		sbs.append(" --result-file=" + fileName);
		sbs.append(" --default-character-set=utf8 ");
		sbs.append(DB_NAME);
		return sbs;
	}

	/**
	 * 常见文件的保存目录
	 * 
	 * @param makdirs
	 *            文件目录
	 */
	private static void createMkdirs(String makdirs) {
		File filePathSql = new File(makdirs);
		if (!filePathSql.exists()) {
			filePathSql.mkdirs();
		}
	}

	/**
	 * 获取文件名称
	 * 
	 * @return 文件名称,例如:dbBackUp-20150508105721.sql
	 */
	private static String getFileName() {
		return DB_BACK_NAME + "-"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ Constants.FILE_SUFFIX_SQL;

	}

	/**
	 * 获取压缩文件名称
	 * 
	 * @param fileName
	 *            备份的文件名称,例如:dbBackUp-20150508105721.sql
	 * @return 备份的压缩包文件名称,例如:dbBackUp-20150508105721.zip
	 */
	public static String getZipFileName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."))
				+ Constants.FILE_SUFFIX_ZIP;

	}

	/**
	 * 获取存去备份文件的目录
	 * 
	 * @return 备份文件的目录,例如:attachment\dbBackUp
	 */
	public static String getMkdirsPath() {
		return BASE_PATH + "attachment" + File.separator + DB_BACK_NAME
				+ File.separator;
	}

	/**
	 * 获取存去备份文件压缩的目录
	 * 
	 * @return 备份文件的压缩目录,例如:attachment\dbBackUpZip
	 */
	public static String getZipMkdirsPath() {
		return BASE_PATH + "attachment" + File.separator + DB_BACK_NAME + "Zip"
				+ File.separator;
	}

	/**
	 * 获取保存文件路径
	 * 
	 * @return 要保存的文件的路径,例如:attachment\dbBackUp\dbBackUp-20150508105721.sql
	 */
	private static String getFilePath(String fileName) {
		return getMkdirsPath() + fileName;
	}
}
