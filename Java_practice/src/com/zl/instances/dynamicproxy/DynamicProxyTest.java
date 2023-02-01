/**
 *
 */
package com.zl.instances.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zlennon
 *
 */
public class DynamicProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 ConcreteTarget c=new ConcreteTarget();//Ԫ����(���������)
         InvocationHandler ih=new ProxyHandler(c);//����ʵ���ĵ��ô������
         //����һ��ʵ��ҵ��ӿڵĴ�����,���ڷ���ҵ����(������ģʽ)��
         //����һ��ָ���ӿڵĴ�����ʵ�����ýӿڿ��Խ���������ָ�ɵ�ָ���ĵ��ô��������ProxyHandler��
         Target target=(Target)Proxy.newProxyInstance(c.getClass().getClassLoader(),c.getClass().getInterfaces(),ih);
         //���ô����෽��,Javaִ��InvocationHandler�ӿڵķ���.
        target.doSomething();

	}

}
