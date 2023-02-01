package com.zl.java8lamdas;

public class HelloWorld {
    public static void main(String ...args){
        Greeter greeter = ()->System.out.println("Hello World");
        greeter.greet();
    }

}
