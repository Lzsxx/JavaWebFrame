package ArchitectureExplore.first_version.controller;

import ArchitectureExplore.first_version.annotation.MyAutowired;
import ArchitectureExplore.first_version.annotation.MyController;
import ArchitectureExplore.first_version.annotation.MyRequestMapping;
import ArchitectureExplore.first_version.service.HelloService;

@MyController
public class HelloController {

    @MyAutowired
    private HelloService helloService;

    @MyRequestMapping("/hello")
    public String hello(){
        return helloService.printHello();
    }
}
