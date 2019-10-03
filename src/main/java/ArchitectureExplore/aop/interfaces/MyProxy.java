package ArchitectureExplore.aop.interfaces;


import ArchitectureExplore.aop.ProxyChain;

public interface MyProxy {

    /* 执行链式代理 */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
