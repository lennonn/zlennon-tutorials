package com.zl.instances.designpattern.dynamicproxy;

public class ConcreteTarget implements Target {

	@Override
	public void doSomething() {
		System.out.println("Dynamic Proxy Test");
	}

}
