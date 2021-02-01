package com.security.filter;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class DefencesServletRequest extends HttpServletRequestWrapper {

	public DefencesServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		String path = getQueryString();
		if(StringUtils.isEmpty(path)){
			return value;
		}
		if(path.indexOf(name + "=") > -1){
			return defencesHtml(value);
		}
		return defencesSql(value);
	}
	
	
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> maps = super.getParameterMap();
		Map<String, String[]> params = new HashMap<String, String[]>();
		String path = getQueryString();
		for(String key : maps.keySet()){
			String[] str = maps.get(key);
			if(StringUtils.isEmpty(path) || str == null || str.length == 0){
				params.put(key, str);
				continue;
			}
			String[] values = new String[str.length];
			for (int i = 0; i < str.length; i++) {
				if(path.indexOf(key + "=") < 0){
					values[i] = defencesSql(str[i]);
				}else{
					values[i] = defencesHtml(str[i]);
				}
			}
			params.put(key, values);
		}
		return params;
	}
	
	
	@Override
	public String[] getParameterValues(String name) {
		String[] str = super.getParameterValues(name);
		String path = getQueryString();
		if(StringUtils.isEmpty(path) || str == null || str.length == 0){
			return str;
		}
		
		String[] values = new String[str.length];
		for (int i = 0; i < str.length; i++) {
			if(path.indexOf(name + "=") > -1){
				values[i] = defencesHtml(str[i]);
			}else{
				values[i] = defencesSql(str[i]);
			}
			
		}
		return values;
	}
	
	public static void main(String[] args) {
//		System.out.println(defencesSql("\'"));
//		System.out.println(defencesHtml("'\"({}"));
	}
	
	private String defencesSql(String value){//对参数进行替换
		if(StringUtils.isEmpty(value)){
			return value;
		}
		value = value.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");//sql注入替换
		return value;
	}
	
	private String defencesHtml(String value){
		if(StringUtils.isEmpty(value)){
			return value;
		}
		value = value.replaceAll("<", "&lt;").replaceAll("'", "").replaceAll("\"", "").replaceAll("\\(", "&nbsp;(").replaceAll("\\{", "").replaceAll("\\}", "");//html标签替换
		return value;
	}
}
