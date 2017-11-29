package com.society.utils;

public class SocietyUtils {
	
	public static <T> T getIndexValue(int index, T[] array) {
		try {
			return array[index];
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static Integer setNullIfZero(Integer value) {
		if(value == 0)
			return null;
		return value;			
	}
}
