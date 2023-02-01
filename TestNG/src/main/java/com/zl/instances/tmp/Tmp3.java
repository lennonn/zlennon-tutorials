/**
 * 
 */
package com.zl.instances.tmp;

/**
 * @author zlennon
 *
 */
public class Tmp3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			String a="hello";
			String t="hello";
			char[] c ={'h','e','l','l','o'};
			System.out.println(a.equals(t));//true
			System.out.println(t.equals(c));//false
			String kString = new String(c);
			System.out.println(t.equals(kString));//true
			System.out.println(t.equals(new String("hello")));//true

	}

}
