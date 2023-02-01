package com.zl.instances.designpattern.builder;

public class BuilderTest {

	public static void main(String[] args) {
        com.zl.instances.designpattern.builder.Person person = new com.zl.instances.designpattern.builder.Person.Builder(1, "张三").age(18).sex("男").desc("测试使用builder模式").build();
        System.out.println(person.toString());
	}

}
