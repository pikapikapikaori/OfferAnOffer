# OfferAnOffer
My part in OfferanOffer project: log-in service and process service.

# 协作文档
本文档描述了需要与提供的接口的信息。

## 后端

### Eureka 注册中心

**当前端口：10086**

#### 需要的配置
- 项目建立时勾选 eureka server ，或添加相应依赖
- 配置文件如下：
```java
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:10086/eureka/
```
- 启动类
  - 启动类中添加注解`@EnableEurekaClient`
  - 类内添加属性：
```java
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```
- 控制器添加注解`@RestController`
- 需要调用其他服务的类内添加`RestTemplate restTemplate`

### 登录模块

**当前端口：8081**

#### 用户表属性
以自增为方式自动生成的唯一主键`id`

填写 email 作为账号名使用的`username`

账号密码`password`

账户身份`identity`，默认为`"interviewee"`，需改为`"hr"`的话目前不提供后端修改

#### 需要的接口
- 创建简历模块内所有表内数据的方法，设想是用户创建时自动在简历模块的主用户表内创建数据

### 流程模块

**当前端口：8082**

#### 提供的接口

- 目前设想简历筛选直接在流程部分内完成

- 在用户投递简历时调用本方法以在流程模块内创建数据
  - 域名：`/system/add_apply`
  - 方式： `post`
  - 参数：
    - `String id`，代表用户 id
    - `String resumeId`，代表用户投递简历的 id ，以 resumeId 作为表唯一主键
    
- 在发送通知后更新流程当前状态
  - 域名：`/hr/update_status`
  - 方式： `post`
  - 参数：
    - `String id`，代表用户 id
    - `String resumeId`，代表用户投递简历的 id ，以 resumeId 作为表唯一主键
    - `String status`，代表目标状态，发送面试通知后更新为`"interview"`

#### 需要的接口

- 获取面试信息的方法，也可以在前端直接调用简历模块实现

## 前端

#### 提供

- 当前登录账号信息存储在 cookie 中，cookie 内容如下：
  - `"id"`，存储用户唯一id
  - `"email"`，存储作为username的用户邮箱
  - `"password"`，存储用户登录密码

- `/assets/js/CookieOperations.js`内的`cookieOperations`中，提供了保存 cookie ，获取 cookie ，清除 cookie 的三个方法
```javascript

export default {
  cookieOperations : {
    setCookie (id, email, password, exdays) {
      var d = new Date()
      d.setTime(d.getTime()+(exdays*24*60*60*1000))
      var expires = 'expires=' + d.toGMTString()
      document.cookie = 'id=' + id + '; ' + 'email=' + email + '; ' + 'password=' + password + '; ' + expires
    },
    getCookie (cname) {
      var name = cname + "="
      var ca = document.cookie.split(';')
      for (var i=0; i<ca.length; i++) {
        var c = ca[i].trim()
        if (c.indexOf(name)===0) {
          return c.substring(name.length,c.length)
        }
      }
      return ''
    },
    clearCookie () {
      document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
  }
}
```
- `/assets/js/Cookie.js`内的`cookieMethods`中，提供
  - `getCurrentId`，无参数，返回当前登录中用户的 id
  - `getCurrentIdentity`，`async`同步函数，需要参数`id`，返回特定 id 用户的身份

#### 需要
- 获得要查看的简历的 id 的方式，设想是存储在`sessionStorage`内，也可以换其他方式，但需要提供对应获取函数
- 以`"hr"`身份登录时，获取要查看的简历所对应的申请者的 id 的方式，设想是存储在`sessionStorage`中，也可以换其他方式，但需要提供对应获取函数
