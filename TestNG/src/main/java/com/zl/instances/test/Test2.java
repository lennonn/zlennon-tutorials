package com.zl.instances.test;

public class Test2 {
	static{
		   int x=5;
		}
		static int x,y;
		public static void main(String args[]){
		   x--;
		   myMethod( );
		   System.out.println(x+y+ ++x);
		}
		public static void myMethod( ){
		  y=x++ + ++x;
		 }
}
