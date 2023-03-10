package com.zl.instances;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectionList {
	public static class ListTest {  
	   
	    @SuppressWarnings("unused")
		private static void testIntersection() {  
	         List<String> list1 = new ArrayList<String>();  
	         List<String> list2 = new ArrayList<String>();  
	         list1.add("abc");  list2.add("abc");  
	         list1.add("123");  list2.add("123");  
	         list1.add("ABC");  
	         list2.add("XYZ");  
	         list1.removeAll(list2);  
	         list1.addAll(list2);  
	         System.out.println("����Ԫ�ظ����ǣ�" + list1.size());        
	  
	    }  
	    @SuppressWarnings("unused")
		private static void testIntersectionSet() {  
	         List<String> list1 = new ArrayList<String>();  
	         List<String> list2 = new ArrayList<String>();  
	         list1.add("abc");  list2.add("abc");  
	         list1.add("123");  list2.add("123");  
	         list1.add("ABC");  
	         list2.add("XYZ");  
	         Set<String> set =new HashSet<String>();  
	         addList2Set(set, list1);  
	         addList2Set(set, list2);  
	         System.out.println("����Ԫ�ظ����ǣ�" + set.size());        
	  
	    }  
	    @SuppressWarnings("unused")
		private static void testUnion() {  
	         List<String> list1 = new ArrayList<String>();  
	         List<String> list2 = new ArrayList<String>();  
	         list1.add("abc");  list2.add("abc");  
	         list1.add("123");  list2.add("123");  
	         list1.add("ABC");  
	         list2.add("XYZ");  
	         list1.retainAll(list2);  
	         System.out.println("����Ԫ�ظ����ǣ�"+list1.size());  
	    }  
	    private static void addList2Set(Set<String> set,List<String> list){  
	        for (String str : list) {  
	            set.add(str);  
	        }  
	    }  
	}  
}
