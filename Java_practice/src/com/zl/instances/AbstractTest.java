package com.zl.instances;

public abstract class AbstractTest {
	public int num;
	public AbstractTest() {
		System.out.println("hello");
	}
	public void hello() {
		System.out.println("hello");
	}
	//public abstract String helloWorld();
	public static void main(String[] args) {
		//AbstractTest abstractTest = new AbstractTest() �����಻�ܱ�ʵ�������г��󷽷���ʵ����������,����ʵ��Ϊ�������һ��
	AbstractTest abstractTest = new AbstractTest() {
			public void hello(){
				System.out.println("test");
			};
		};
	}

}
