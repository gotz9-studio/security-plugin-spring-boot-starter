# simple-spring-boot-starter

一个简单的 Spring Boot Starter Demo.

目标是将一个自定义逻辑的 Spring-Security 模块改造成 starter

## 模块
|                模块                 | 说明                                                                   |
| :---------------------------------: | :--------------------------------------------------------------------- |
|        security-plugin-core         | 认证授权相关核心逻辑实现类模块                                         |
| security-plugin-spring-boot-starter | spring-boot-starter 模块, 利用 `EnableSecurityPlugin` 注解开启自动装配 |
