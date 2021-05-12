# security-plugin-spring-boot-starter

一个简单的 Spring Boot Starter Demo.

目标是将一个自定义逻辑的 Spring-Security 模块改造成 starter

## 模块
|                模块                 | 说明                                                               |
| :---------------------------------: | :----------------------------------------------------------------- |
|        security-plugin-core         | 认证授权相关核心逻辑实现类模块                                     |
|    security-plugin-autoconfigure    | Spring bean 装配模块, 提供 `EnableSecurityPlugin` 注解开启自动装配 |
| security-plugin-spring-boot-starter | spring-boot-starter 模块, 提供 security-plugin 的外部依赖模块      |
