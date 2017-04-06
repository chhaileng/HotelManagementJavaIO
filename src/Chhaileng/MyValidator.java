package Chhaileng;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyValidator {
	
	public static boolean numFormat(String n) {
		try {
			Integer.valueOf(n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean allowedName(String name) {
		String regx = "^[\\p{L} .'-]+$";
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(name);
	    return matcher.find();
	}
	
	public static boolean allFree(String arr[][]) {
		for (int i=0 ; i < arr.length ; i++)
			for(int j=0 ; j<arr[0].length ; j++) {
				if (arr[i][j] != null) {
					return false;
				}
			}
		return true;
	}
	
	public static boolean allBusy(String arr[][]) {
		for (int i=0 ; i < arr.length ; i++)
			for(int j=0 ; j<arr[0].length ; j++) {
				if (arr[i][j] == null) {
					return false;
				}
			}
		return true;
	}
	
	public static boolean floorFull(String arr[][], int f) {
		for (int i=0 ; i<arr[f].length ; i++)
			if (arr[f][i] == null)
				return false;
		return true;
	}
}
