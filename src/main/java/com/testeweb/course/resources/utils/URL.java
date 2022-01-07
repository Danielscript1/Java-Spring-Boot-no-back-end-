package com.testeweb.course.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

public class URL {
	
	//metodo decode param, para descodifcar um paramentro passado na url
	public static String  decodeParam(String s) {
		try {
			URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		return s;
	}
	
	//essa classe, tem como objetivo receber os paramentros passado como string na url e converter para integer
	
	public static List<Long> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Long>list = new ArrayList<>();
		for(int i = 0; i<vet.length;i++ ) {
			list.add(Long.parseLong(vet[i]));
		}
		return list;
	}
	
	/*
	 * Segue sugestão usando lambda:

		public static List<Integer> decodeIntList(String string){

		String[] s = string.split(",");

    	return Arrays.asList(s).stream().map(obj->  Integer.parseInt(obj)).collect(Collectors.toList());

}
	 * 
	 * 
	 * */
}
