package com.zl.instances.dynamicproxy;

public class ConcreteTarget implements Target {

	@Override
	public void doSomething() {
		System.out.println("Dynamic Proxy Test");
	}

}
