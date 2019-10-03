package ArchitectureExplore.ioc.first_version.service;

import ArchitectureExplore.ioc.first_version.annotation.MyService;

@MyService
public class HelloService {
    private String hello = "Hello Service";
    public String printHello(){
        return hello;
    }
}
