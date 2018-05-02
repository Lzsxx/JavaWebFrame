package service;

import annotation.MyService;

@MyService
public class HelloService {
    private String hello = "Hello Service";
    public String printHello(){
        return hello;
    }
}
