package com.lifeidroid.schooltech;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	public static final String CHARSET = "UTF-8";
	public static final String APPID = "com.lifeidorid.schooltech";
	public static final String URL = "http://192.168.253.1:8080/schooltech/index.jsp";
	//public static final String URL = "http://schooltech.sinaapp.com/schooltech/WebContent/";
	public static final int ERROR0 = 0;
	public static final int ERROR1 = 2;
	public static final int ERROR2 = 3;
	public static final int SUCCESS = 1;

	public static final String KEY_ACTION = "action";
	public static final String KEY_EMAILMD5 = "email_md5";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_STATUS = "status";

	public static final String ACTION_LOGIN = "login";
	public static final String KEY_MOTTO = "motto";
	public static final String KEY_TOKEN = "token";
	public static final String KEY_HEAD = "head";
	public static final String KEY_NIKENAME = "nikename";
	public static final String ACTION_REGISTER = "register";
	public static final String KEY_CACHEPATH = "cachePath";
	public static final String KEY_DEFAULT_SCHOOLNAME = "default_school_name";
	public static final String KEY_DEFAULT_SCHOOLID = "default_scholl_id";
	public static final String KEY_DEFAULT_DEPTID = "default_dept_id";
	public static final String ACTION_GETSCHOOL = "getschool";
	public static final String KEY_ITEMS = "items";
	public static final String KEY_SCHOOLNAME = "schoolname";
	public static final String KEY_SCHOOLID = "schoolId";
	public static final String KEY_SCHOOLLOGO = "schoollogo";
	public static final String KEY_COURSENUM = "coursenum";
	public static final String ACTION_GETNEWCOURSE = "getnewcourse";
	public static final String KEY_DEPTID = "deptId";
	public static final String KEY_PAGE = "page";
	public static final String KEY_PERPAGE = "perpage";
	public static final String KEY_COURSEID = "courseId";
	public static final String KEY_COURSENAME = "coursename";
	public static final String KEY_TECHNAME = "techname";
	public static final String KEY_COURSELOGO = "courselogo";
	public static final String KEY_GRADE = "grade";
	public static final String KEY_STUDENTNUM = "studentnum";
	public static final String ACTION_GETDEPT = "getdept";
	public static final String KEY_DEPTNAME = "deptname";
	public static final String ACTION_GETHOTCOURSE = "gethotcourse";
	public static final String ACTION_GETRECOMMENDCOURSE = "getrecommendcourse";
	public static final int REFRESH = 0;
	public static final int LOADMORE = 1;
	public static final int ALLDEPT = 0;
	public static final String ACTION_GETCOURSEINFO = "getcourinfo";
	public static final String KEY_COURSEINFO = "courseinfo";
	public static final String KEY_TECHINFO = "techinfo";
	public static final String KEY_TECHHEAD = "techhead";
	public static final String ACTION_GETCOURSEDISCUSS = "getcourdiscuss";
	public static final String KEY_DISCUSSRAPLYNUM = "replynum";
	public static final String KEY_DISCUSSTIME = "discusstime";
	public static final String KEY_DISCUSSCONTENT = "content";
	public static final String KEY_STUDENTHEAD = "studhead";
	public static final String KEY_STUDNETNIKE = "studnike";
	public static final String KEY_STUDENTEMAIL = "studemail";
	public static final String KEY_DISUCUSSID = "discussId";
	public static final String KEY_NOTEID = "noteId";
	public static final String KEY_NOTECONTENT = "content";
	public static final String KEY_NOTETIME = "notetime";
	public static final String KEY_COLLECTNUM = "collectnum";
	public static final String ACTION_GETCOURSENOTE = "getcournote";
	public static final String KEY_CHAPTERID = "chapterId";
	public static final String KEY_CHAPTERNAME = "chaptername";
	public static final String KEY_CHAPTERTIME = "chaptertime";
	public static final String ACTION_GETCOURSECHAPTER = "getcourchapter";
	public static final String ACTION_SENDCOMMENT = "sendcomment";
	public static final String KEY_MSG = "msg";
	public static final int RESOULT_NEED_REFRESH = 1;
	public static final String KEY_WHETHERCOLLECTED = "whethercollected";
	public static final int COLLECTED = 1;
	public static final int CANCELCOLLECTED = 0;
	public static final String KEY_COLLECTNOTE = "collectnote";
	public static final String KEY_CHAPTER_URL = "chapterurl";
	public static final String ACTION_SENDDISCUSS = "senddiscuss";
	public static final String ACTION_SENDNOTE = "sendnote";
	public static final String ACTION_COLLECT_COURSE = "collectcourse";
	public static final String ACTION_MODIFICATION = "modification";
	public static final String KEY_SEX = "sex";

	/**
	 * 存取Email
	 * 
	 * @param context
	 * @param email
	 */
	public static void cacheEmail(Context context, String email) {
		Editor editor = context.getSharedPreferences(APPID,
				Context.MODE_PRIVATE).edit();
		editor.putString(KEY_EMAILMD5, email);
		editor.commit();
	}

	public static String getCacheEmail(Context context) {
		return context.getSharedPreferences(APPID, Context.MODE_PRIVATE)
				.getString(KEY_EMAILMD5, null);
	}

	/**
	 * 存取Password
	 * 
	 * @param context
	 * @param password
	 */
	public static void cachePassword(Context context, String password) {
		Editor editor = context.getSharedPreferences(APPID,
				Context.MODE_PRIVATE).edit();
		editor.putString(KEY_PASSWORD, password);
		editor.commit();
	}

	public static String getCachePassword(Context context) {
		return context.getSharedPreferences(APPID, Context.MODE_PRIVATE)
				.getString(KEY_PASSWORD, null);
	}

	/**
	 * 存取default_school_name
	 * 
	 * @param context
	 * @param password
	 */
	public static void cacheDefaultSchoolName(Context context,
			String default_school_name) {
		Editor editor = context.getSharedPreferences(APPID,
				Context.MODE_PRIVATE).edit();
		editor.putString(KEY_DEFAULT_SCHOOLNAME, default_school_name);
		editor.commit();
	}

	public static String getCacheDefaultSchoolName(Context context) {
		return context.getSharedPreferences(APPID, Context.MODE_PRIVATE)
				.getString(KEY_DEFAULT_SCHOOLNAME, "石家庄学院");
	}

	/**
	 * 存取default_school_Id
	 * 
	 * @param context
	 * @param password
	 */
	public static void cacheDefaultSchoolId(Context context,
			int default_school_id) {
		Editor editor = context.getSharedPreferences(APPID,
				Context.MODE_PRIVATE).edit();
		editor.putInt(KEY_DEFAULT_SCHOOLID, default_school_id);
		editor.commit();
	}

	public static int getCacheDefaultSchoolId(Context context) {
		return context.getSharedPreferences(APPID, Context.MODE_PRIVATE)
				.getInt(KEY_DEFAULT_SCHOOLID, 1);
	}

	/**
	 * 存取default_dept_Id
	 * 
	 * @param context
	 * @param password
	 */
	public static void cacheDefaultDeptId(Context context, int default_dept_id) {
		Editor editor = context.getSharedPreferences(APPID,
				Context.MODE_PRIVATE).edit();
		editor.putInt(KEY_DEFAULT_DEPTID, default_dept_id);
		editor.commit();
	}

	public static int getCacheDefaultDeptId(Context context) {
		return context.getSharedPreferences(APPID, Context.MODE_PRIVATE)
				.getInt(KEY_DEFAULT_DEPTID, 0);
	}

}
