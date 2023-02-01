package com.zl.instances.designpattern.observer;

public interface ISubject {

	 public void attach(ConcreteObserver o);
	 public void detach(ConcreteObserver o);
	 public void sendMessage(String s);
}
