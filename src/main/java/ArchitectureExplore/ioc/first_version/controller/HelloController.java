package ArchitectureExplore.ioc.first_version.controller;

import ArchitectureExplore.ioc.first_version.annotation.MyAutowired;
import ArchitectureExplore.ioc.first_version.annotation.MyController;
import ArchitectureExplore.ioc.first_version.annotation.MyRequestMapping;
import ArchitectureExplore.ioc.first_version.service.HelloService;

@MyController
public class HelloController {

    @MyAutowired
    private HelloService helloService;

    @MyRequestMapping("/hello")
    public String hello(){
        return helloService.printHello();
    }
}
