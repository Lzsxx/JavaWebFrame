package ArchitectureExplore.first_version.service;

import ArchitectureExplore.first_version.annotation.MyService;

@MyService
public class HelloService {
    private String hello = "Hello Service";
    public String printHello(){
        return hello;
    }
}
