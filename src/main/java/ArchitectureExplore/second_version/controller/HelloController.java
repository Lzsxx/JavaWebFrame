package ArchitectureExplore.second_version.controller;

import ArchitectureExplore.first_version.annotation.mAutowired;
import ArchitectureExplore.first_version.annotation.mController;
import ArchitectureExplore.first_version.annotation.mRequestMapping;
import ArchitectureExplore.first_version.service.HelloService;

@mController
public class HelloController {
    @mAutowired
    private HelloService helloService;

    @mRequestMapping("/hello")
    public String print(){
        return helloService.sayHello();
    }

}