package controller;

import annotation.mAutowired;
import annotation.mController;
import annotation.mRequestMapping;
import service.HelloService;

@mController
public class HelloController {
    @mAutowired
    private HelloService helloService;

    @mRequestMapping("/hello")
    public String print(){
        return helloService.sayHello();
    }

}