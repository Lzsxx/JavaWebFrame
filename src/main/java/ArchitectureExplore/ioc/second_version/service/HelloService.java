package ArchitectureExplore.ioc.second_version.service;

import ArchitectureExplore.ioc.second_version.annotation.mService;

@mService
public class HelloService {
    private String hello = "Hello Service";
    public String sayHello(){
        return hello;
    }
}
