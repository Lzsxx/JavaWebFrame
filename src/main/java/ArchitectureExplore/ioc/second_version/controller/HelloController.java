package ArchitectureExplore.ioc.second_version.controller;

import ArchitectureExplore.ioc.first_version.service.HelloService;
import ArchitectureExplore.ioc.second_version.annotation.mAutowired;
import ArchitectureExplore.ioc.second_version.annotation.mController;
import ArchitectureExplore.ioc.second_version.annotation.mRequestMapping;

@mController
public class HelloController {
    @mAutowired
    private HelloService helloService;

    @mRequestMapping("/hello")
    public String print(){
        return helloService.printHello();
    }

}