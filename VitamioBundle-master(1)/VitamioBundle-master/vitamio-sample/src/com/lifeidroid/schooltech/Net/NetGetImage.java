package com.lifeidroid.schooltech.Net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.lifeidroid.schooltech.Tools.MD5Tool;


public class NetGetImage {
	/**
	 * 获取网络图片，如果图片存在于缓存中，就返回该图片，否则从网络中加载该图片并缓存
	 * 
	 * @param path图片路径
	 * @return返回图片的uri
	 */
	public static Uri getImage(String imagePath, String cachePath) {
		Bitmap headMap;
		File localfile = new File(cachePath, MD5Tool.md5(imagePath) + ".jpg");
		if (localfile.exists()) {
			System.out.println("---------->本地图片");
			return Uri.fromFile(localfile);
		} else {
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(
						imagePath).openConnection();						// 打开图片的地址链接
				connection.setConnectTimeout(5000);							// 设置超时为5s
				connection.setDoInput(true);								// 打开输入
				connection.connect();										// 连接
				InputStream inputStream = connection.getInputStream();		// 打开输入流
				headMap = BitmapFactory.decodeStream(inputStream);			// 将图片从输入流中读出来
				inputStream.close();										// 关闭输入流
				connection.disconnect();									// 关闭连接
				saveFile(headMap, imagePath, cachePath);					// 保存图片
				return Uri.fromFile(localfile);								//返回图片的uri
			} catch (MalformedURLException e) {
				System.out
						.println("NetGetImage_---------->MalformedURLException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("NetGetImae------------->IOException2");
				e.printStackTrace();
			}
		}
		return null;
	}

	private static Bitmap getFileSave(File localFile) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				localFile));
		bis.close();
		return BitmapFactory.decodeStream(bis);
	}

	private static void saveFile(Bitmap bm, String imagePath, String cachePath)
			throws IOException {
		File myCaptureFile = new File(cachePath, MD5Tool.md5(imagePath)
				+ ".jpg");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		bos.flush();
		bos.close();
	}

}
