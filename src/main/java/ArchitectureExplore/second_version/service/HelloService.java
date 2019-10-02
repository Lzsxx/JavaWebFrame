package ArchitectureExplore.second_version.service;

import ArchitectureExplore.first_version.annotation.mService;

@mService
public class HelloService {
    private String hello = "Hello Service";
    public String sayHello(){
        return hello;
    }
}
