# JXMall 电商项目

欢迎来到 JXMall 电商项目！这是一个综合的模块化电商平台，旨在处理在线购物体验的各个方面。以下是项目结构、技术栈及其主要模块的详细概述。

## 项目结构

项目组织成以下模块：

- **jxmall-admin**: 管理模块，用于管理系统和配置。
- **jxmall-auth**: 认证模块，负责用户登录、注册和授权流程。
- **jxmall-cart**: 购物车模块，处理用户购物车中的商品添加、删除和管理。
- **jxmall-common**: 公共模块，包含项目中各模块共享的工具和资源。
- **jxmall-coupon**: 优惠券模块，管理折扣券和促销优惠。
- **jxmall-gateway**: 网关模块，作为应用程序的入口，处理API路由和安全。
- **jxmall-member**: 会员管理模块，处理用户资料、会员等级及相关功能。
- **jxmall-no-authentication-gateway**: 无需认证的网关模块，用于不需要身份验证的路由。
- **jxmall-order**: 订单处理模块，管理订单的创建、更新、支付和历史记录。
- **jxmall-product**: 商品管理模块，处理商品列表、详情、分类和库存。
- **jxmall-search**: 搜索模块，提供商品及其他资源的搜索功能。
- **jxmall-third-party**: 第三方集成模块，用于支付网关、物流等服务的集成。
- **jxmall-ware**: 仓库管理模块，处理库存、库存水平和仓库操作。

## 技术栈

项目采用以下技术栈：

| 组件                         | 版本          | 描述                                        |
|------------------------------|---------------|---------------------------------------------|
| **Java**                     | 17            | 使用最新的Java编程语言                      |
| **Spring Boot**              | 3.2.0         | 构建独立、生产级别的Spring应用程序          |
| **Spring Cloud**             | 2023.0.0      | 构建分布式系统的工具集                      |
| **Spring Cloud Alibaba**     | 2023.0.0.0-RC1| 阿里巴巴开源的微服务工具集                  |
| **Spring Security Core**     | 6.2.2         | 提供认证和授权功能                          |
| **JWT**                      | 0.9.1         | JSON Web Token用于安全认证                  |
| **MySQL**                    | 8.0.11        | 关系型数据库管理系统                        |
| **MyBatis Spring Boot**      | 3.0.2         | 持久层框架，简化数据库操作                  |
| **MyBatis Plus Boot Starter**| 3.5.4         | MyBatis增强工具包                           |
| **Druid**                    | 1.1.20        | 阿里巴巴开源的数据库连接池                  |
| **Redis**                    | 3.2.3         | 内存缓存数据库                              |
| **Redisson**                 | 3.27.2        | Redis的Java客户端                           |
| **Spring Cache**             | 3.2.4         | 缓存抽象层                                  |
| **Spring Boot Starter AMQP** | 3.2.5         | 集成RabbitMQ的消息队列支持                  |
| **Spring Boot Starter Data Elasticsearch**| 3.2.0 | Elasticsearch支持，用于搜索和分析引擎      |
| **Spring Boot Starter Actuator** | 3.2.3 | 提供生产环境下的监控和管理功能             |
| **Swagger**                  | 3.2.0         | API文档生成工具                             |
| **Knife4j OpenAPI3**         | 4.4.0         | 增强版的Swagger UI                          |
| **Hutool**                   | 5.8.27        | Java工具集，提供大量常用工具方法            |
| **Lombok**                   | 1.18.26       | 简化Java代码，自动生成getter/setter等代码  |
| **FastJSON2**                | 2.0.49        | 高性能的JSON处理库                          |
| **Micrometer Tracing**       | 1.2.0         | 微服务监控工具                              |
| **Micrometer Observation**   | 1.12.0        | 收集和报告应用程序的指标                    |
| **Feign Micrometer**         | 12.5          | 声明式HTTP客户端，用于微服务之间的通信      |
| **Feign HC5**                | 13.1          | Feign HTTP客户端                            |
| **Spring Cloud LoadBalancer**| 最新版本       | 负载均衡组件                                |
| **Zipkin Reporter Brave**    | 2.17.0        | 分布式追踪系统                              |
| **PageHelper Boot**          | 1.4.7         | MyBatis分页插件                             |
| **Commons IO**               | 2.13.0        | IO功能的工具包                              |
| **POI**                      | 4.1.2         | 操作Microsoft Office文档的库                |
| **EasyExcel**                | 3.3.4         | 阿里巴巴开源的Excel处理工具                |
| **Alibaba Sentinel**         | 最新版本       | 提供流量控制、熔断降级和系统负载保护功能   |
| **Nacos Discovery**          | 最新版本       | 服务注册和发现组件                          |
| **Nacos Config**             | 最新版本       | 分布式配置管理组件                          |

## 快速开始

要开始使用该项目，请按照以下步骤操作：

1. **克隆仓库**：
    ```sh
    git clone https://github.com/ketd/jxmall.git
    cd jxmall
    ```

2. **安装依赖**：
    每个模块都有自己的依赖项。进入每个模块的目录并安装必要的包。

3. **配置**：
    配置每个模块所需的环境变量和设置。请参阅各模块的README文件以获取具体的配置说明。

4. **运行应用**：
    按正确的顺序启动每个服务，从关键服务如 `jxmall-gateway` 和 `jxmall-auth` 开始，然后启动其他服务。

## 特色功能演示

### 第三方登录
<img src="https://github.com/ketd/jxmall/assets/94940923/8cf27b74-9c76-43ff-9249-bcdba94a12b1" width="800px">



### 支付宝沙箱付款
<img src="https://github.com/ketd/jxmall/assets/94940923/5c270cf5-ab4c-439f-94f8-b31123cebe86" width="500px">

<img src="https://github.com/ketd/jxmall/assets/94940923/f782db81-83ae-49d4-bf61-d18857d26b1c" width="150px">

### RabbitMQ延迟消息队列自动解锁库存
![动画7](https://github.com/ketd/jxmall/assets/94940923/5b062425-3ce9-40cd-bbf1-ff0dadf801b7)

![动画6](https://github.com/ketd/jxmall/assets/94940923/81972023-2247-47a2-8dc4-82d6d38b45bd)

### 对象存储图片上传
![动画5](https://github.com/ketd/jxmall/assets/94940923/fd1ed03e-e8ca-4873-bf39-fb4f7418c34c)


## 贡献指南

我们欢迎对项目的贡献！请阅读我们的 [贡献指南](CONTRIBUTING.md) 以了解如何开始。

## 许可证

该项目使用 MIT 许可证 - 有关详细信息，请参阅 [LICENSE](LICENSE) 文件。

## 联系方式

如果您有任何问题或需要进一步的帮助，请随时提出issue或直接联系我们：[your-email@example.com](mailto:your-email@example.com)。

---

感谢您访问我们的项目！希望您发现它有用，并期待您的贡献。

