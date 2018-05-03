package service;

import annotation.mService;

@mService
public class HelloService {
    private String hello = "Hello Service";
    public String sayHello(){
        return hello;
    }
}
