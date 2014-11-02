package sumimakito.android.reina.clvnlp;

import android.content.*;
import java.util.*;
import java.util.regex.*;
import android.os.*;

public class ReinaClvNLPEngine
{
	private Context ctx;

	public ReinaClvNLPEngine(Context context)
	{
		this.ctx = context;
		initNLPLib();
	}
	public static class Constant
	{
		public static String RESULT_INSERT_URL = "INSERT_URL";
		public static String RESULT_SET_ALARM = "SET_ALARM";
		public static String RESULT_WEATHER = "WEATHER";
		public static String RESULT_SOCIAL = "SOCIAL";
		public static String RESULT_COLLECTION = "COLLECTION";
		public static String RESULT_WORKING = "WORKING";
	}
	public List<String> instantParse(String str)
	{
		ArrayList<String> list = new ArrayList<String>();
		if (str.length() > 0)
		{
			if(regexSocialPattern(str)){
				list.add(Constant.RESULT_SOCIAL);
			}
			if(regexInsertURLPattern(str)){
				list.add(Constant.RESULT_INSERT_URL);
			}
			if(regexWorkingPattern(str)){
				list.add(Constant.RESULT_WORKING);
			}
		}
		return list;
	}

	private ArrayList<String> listInsertUrl = new ArrayList<String>();
	private ArrayList<String> listSetAlarm = new ArrayList<String>();
	private ArrayList<String> listSocial = new ArrayList<String>();
	private ArrayList<String> listWeather = new ArrayList<String>();
	private ArrayList<String> listCollection = new ArrayList<String>();
	private ArrayList<String> listWorking = new ArrayList<String>();
	private void initNLPLib()
	{
		listSocial.add("打电话");
		listSocial.add("出去玩");
		listSocial.add("出发");
		listSocial.add("和.+去");
		listSocial.add("逛街");
		listSocial.add("回电话");
		listSocial.add("发信息");
		listSocial.add("发短信");
		listSocial.add("手机号");
		listSocial.add("生日");
		listSocial.add("电话号");
		listSocial.add("一起去");
		listSocial.add("礼物");
		listSocial.add("打个电话");
		listSocial.add("出去吃饭");
		listSocial.add("请客");

		listInsertUrl.add("网址");
		listInsertUrl.add("网站");
		listInsertUrl.add("链接");
		listInsertUrl.add("连接");
		listInsertUrl.add("URL");
		listInsertUrl.add("博客");
		listInsertUrl.add("http");

		listSetAlarm.add("别忘了");
		listSetAlarm.add("提醒");
		listSetAlarm.add("");
		
		listWorking.add("开会");
		listWorking.add("航班");
		listWorking.add("公司");
		listWorking.add("项目");
		listWorking.add("报表");
		listWorking.add("会议");
		listWorking.add("部门");
		listWorking.add("(日|月|季度|年)(总结|报)");
	}
	private boolean regexInsertURLPattern(String str)
	{
		if (listInsertUrl.size() > 0)
		{
			StringBuffer sbufferPattern = new StringBuffer();
			sbufferPattern.append(".*(");
			for (int i=0;i < listInsertUrl.size() - 1;i++)
			{
				sbufferPattern.append(listInsertUrl.get(i));
				sbufferPattern.append("|");
			}
			sbufferPattern.append(listInsertUrl.get(listInsertUrl.size() - 1));
			sbufferPattern.append(").*");
			Pattern pattern = Pattern.compile(sbufferPattern.toString(), Pattern.CASE_INSENSITIVE);  
			Matcher matcher = pattern.matcher(str);  
			boolean b= matcher.matches();  
			return b;
		}
		return false;
	}
	private boolean regexSocialPattern(String str)
	{
		if (listSocial.size() > 0)
		{
			StringBuffer sbufferPattern = new StringBuffer();
			sbufferPattern.append(".*(");
			for (int i=0;i < listSocial.size() - 1;i++)
			{
				sbufferPattern.append(listSocial.get(i));
				sbufferPattern.append("|");
			}
			sbufferPattern.append(listSocial.get(listSocial.size() - 1));
			sbufferPattern.append(").*");
			Pattern pattern = Pattern.compile(sbufferPattern.toString(), Pattern.CASE_INSENSITIVE);  
			Matcher matcher = pattern.matcher(str);  
			boolean b= matcher.matches();  
			return b;
		}
		return false;
	}
	private boolean regexWorkingPattern(String str)
	{
		if (listWorking.size() > 0)
		{
			StringBuffer sbufferPattern = new StringBuffer();
			sbufferPattern.append(".*(");
			for (int i=0;i < listWorking.size() - 1;i++)
			{
				sbufferPattern.append(listWorking.get(i));
				sbufferPattern.append("|");
			}
			sbufferPattern.append(listWorking.get(listWorking.size() - 1));
			sbufferPattern.append(").*");
			Pattern pattern = Pattern.compile(sbufferPattern.toString(), Pattern.CASE_INSENSITIVE);  
			Matcher matcher = pattern.matcher(str);  
			boolean b= matcher.matches();  
			return b;
		}
		return false;
	}
}