package com.team.campuspo.utils;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils {
	
	//并未实现上传文件的功能
	public static String post(String actionUrl, Map<String, String> params) {

		try {

			String BOUNDARY = "---------7d4a6d158c9"; // 数据分隔线

			String MULTIPART_FORM_DATA = "multipart/form-data";

			URL url = new URL(actionUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5 * 1000);

			conn.setDoInput(true);// 允许输入

			conn.setDoOutput(true);// 允许输出

			conn.setUseCaches(false);// 不使用Cache

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Connection", "Keep-Alive");

			conn.setRequestProperty("Charset", "UTF-8");

			conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
					+ "; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();

			for (Map.Entry<String, String> entry : params.entrySet()) {// 构建表单字段内容

				sb.append("--");

				sb.append(BOUNDARY);

				sb.append("\r\n");

				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"\r\n\r\n");

				sb.append(entry.getValue());

				sb.append("\r\n");

			}

			DataOutputStream outStream = new DataOutputStream(
					conn.getOutputStream());

			outStream.write(sb.toString().getBytes());// 发送表单字段数据

			byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();// 数据结束标志

			outStream.write(end_data);

			outStream.flush();

			int cah = conn.getResponseCode();

			if (cah != 200)
				throw new RuntimeException("请求url失败");

			InputStream is = new BufferedInputStream(conn.getInputStream());

			int ch;

			StringBuilder builder = new StringBuilder();

			while ((ch = is.read()) != -1) {

				builder.append((char) ch);

			}

			outStream.close();

			conn.disconnect();

			return builder.toString();

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}
	
	public static byte[] parseParamstoBytes(Map<String, String> params) {
		
		StringBuilder sb = new StringBuilder();
		
		for(Map.Entry<String, String> entry : params.entrySet()) {
			try {
				sb.append(entry.getKey())
					.append("=")
					.append(URLEncoder.encode(entry.getValue(),"UTF-8"))
					.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		byte[] body = sb.substring(0, sb.length() - 1).getBytes();
		
		return body;
	}

}
