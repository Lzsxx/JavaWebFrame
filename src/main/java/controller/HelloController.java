package controller;

import annotation.MyAutowired;
import annotation.MyController;
import annotation.MyRequestMapping;
import service.HelloService;

@MyController
public class HelloController {

    @MyAutowired
    private HelloService helloService;

    @MyRequestMapping("/hello")
    public String hello(){
        return helloService.printHello();
    }
}
