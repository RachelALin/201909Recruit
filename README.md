

# 2019年秋季三峡大学启明星工作室招新网站

>  作者：RachelALin



**招新网站功能：**

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hZTAxLmFsaWNkbi5jb20va2YvSDcyM2E4MWM0YTJiNzQ2ZWM5NDE2Y2NjNDQ3N2ExYWYwVS5wbmc?x-oss-process=image/format,png)

**技术组合：**

*  后端：Spring Boot + thymeleaf模板+Mybatis
*  数据库：MySQL
*  前端UI：Bootstrap框架+JavaScript+Ajax

**工具与环境：**

*  IDEA
*  Maven
*  JDK 8
## 1、需求与功能
### 1.1主要需求
*   前端界面简洁美观
*	图表显示两个组的报名情况（人数，专业，男女比例）
*	采集报名信息的表单验证
*	后台报名信息的增删改查
*	能够查询出笔试的成绩及录取结果
*   能够分组导出excel信息表
*	能录入招新笔试成绩
*	调用云短信API统一发送通知短信题


### 1.2功能规划

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hZTAxLmFsaWNkbi5jb20va2YvSDcyM2E4MWM0YTJiNzQ2ZWM5NDE2Y2NjNDQ3N2ExYWYwVS5wbmc?x-oss-process=image/format,png)

## 2、页面设计与开发

### 2.1 设计

**页面规划：**

前端展示：首页、报名页、报名成功页、成绩查询页、查询成功页

后台管理：状态一览页、登录注册页、学生信息界面、开发组和智能组统计页面、404页面

### 2.2 页面开发

[Bootstrap中文文档3](https://v3.bootcss.com/)

## 3、框架搭建

>  [IDEA下载 https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)

### 3.1 构建与配置

**1、引入Spring Boot模块：**

*  web
*  Thymeleaf
*  MySQL

**2、application.yml配置**

*  使用 thymeleaf 2
```
pom.xml:
```
```xml
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
  <version>2.1.7.RELEASE</version>
```


  	application.yml:

```yaml
spring:
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    mode: HTML
    encoding: UTF-8
    mvc:
      static-path-pattern: /static/**
```

*  数据库连接配置

```yml
spring:
  datasource:
    username: root
    password: ****
    url: jdbc:mysql://127.0.0.1:3306/recruit?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
```

*  Mybatis配置
```
application.yml:
```
```yml
mybatis:
  mapper-locations: classpath:mapping/*Mapper.xml
  type-aliases-package: com.ctguqmx.recruit.pojo
```

### 3.2 安装依赖
```xml
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>



        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>



        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>



        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.16</version>
            <scope>runtime</scope>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>



        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>



        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.10</version>
        </dependency>



        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>3.6</version>
        </dependency>



        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.2</version>
        </dependency>



        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-core</artifactId>
            <version>3.7.1</version>
        </dependency>



        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-dysmsapi</artifactId>
            <version>1.1.0</version>

        </dependency>



        <dependency>
            <groupId>com.google.code.findbugs</groupId>
            <artifactId>annotations</artifactId>
            <version>3.0.1</version>
        </dependency>



        <dependency>
            <groupId>com.qiniu</groupId>
            <artifactId>qiniu-java-sdk</artifactId>
            <version>[7.2.0, 7.2.99]</version>
        </dependency>
```
### 3.3 页面处理



**1、静态页面导入project**

**2、thymeleaf布局**

*  定义fragment
*  使用fragment布局

**3、错误页面美化**

**4、设计与规范**

### 4.1 实体设计

**实体类：**

*  Content类
*  MessageBoard类
*  Student类
*  User类


**Content类：**
```java
package com.ctguqmx.recruit.pojo;

import lombok.Data;

@Data
public class Content {
    private Integer id;
    private String propaganda;
    private String signUp;
    private String seminar;
    private String exam;
}

```

**MessageBoard类：**
```java
package com.ctguqmx.recruit.pojo;

import lombok.Data;

@Data
public class MessageBoard {
    private Integer id;
    private String message;
}

```

**Student类：**
```java
package com.ctguqmx.recruit.pojo;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String studentName;
    private String studentId;
    private String academy;
    private String major;
    private String qq;
    private String tel;
    private String sex;
    private String group;
    private Integer grade;
    private int status;
    private String code;
    }
```
**User类：**
```java
package com.ctguqmx.recruit.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private Integer groupId;
}
```

## 5、后台管理功能实现

### 5.1 登录



**1、构建登录页面和后台管理首页**

**2、UserService和UserMapper**

**3、UserController实现登录**

**4、LoginInterceptor登录拦截器**

### 5.2 注册页面



**1、构建注册页面**


### 5.3 学生信息管理页面
**1、学生分页查询**

**2、学生信息新增**

**3、学生信息修改**

**4、学生信息删除**

**5、学生信息查找**

**6、发送短信通知**

**7、导出Excel表格**

### 5.4 开发组统计页面



**1、学生专业统计**

**2、学生性别统计**

### 5.5 智能组统计页面



**1、学生专业统计**

**2、学生性别统计**

## 6、前端展示功能实现

### 6.1 首页展示



**1、启明星风采轮播图展示**

**2、启明星招新各时间通知**

**3、启明星工作室总体介绍**

**4、启明星工作室各组别介绍**

**5、启明星往届作品展示**

**4、报名入口**

**5、成绩查询入口**



### 6.2 报名页面


### 6.3 报名成功页面

### 6.4 成绩查询页面

### 6.5 查询成功页面

### 问题与收获

 - 审美能力
   审美能力是很重要的一部分，我们这个招新网站界面也是在不断的修改。尽管有时候个人审美不差，但是要符合大众审美最好的解决方法就是不断地借鉴周围人正确的意见不断修改。
 - 逻辑能力
   这可能是我们遇到的比较大的问题，可能我们经常想表达的东西会因为逻辑不够通畅而受阻，导致出现写bug的情况
 - 找bug能力
	找BUG是我们很欠缺的地方，我们通常都是按照正常思维思考问题，按照正常思维去建立项目的功能，缺少了反向思维的能力，不太能考虑到用户会出现什么样的问题。
 - 解决bug能力
    往往我们找到了BUG却不能使用最优解决方案，这里是我们的积累不够。
 - 合作能力
	合作能力并不是作为一个问题放在这里，而是我们的收获，我很感谢我的两位小伙伴非常积极的和我一起完成这次的招新网站，我们的合作体现在项目的构思、建立、修改等各个方面。

 - 技术学习能力
 	虽然我们是按照各自的任务去完成的每一项，可能没有办法涉及到别人的板块，但是我通过改Bug,不断地去学习和记忆后台、表单验证、ajax的写法和用法，这也是我的收获。
 
 ### gitlab网址
[https://git.ctguqmx.com/lyt/qmx_2019_summer_recruit](https://git.ctguqmx.com/lyt/qmx_2019_summer_recruit)