> This is the all and very detailed note during development of this project.
But this is ONLY for personal usage, please don't forward without permission!

# Enviroment set up
- Frontend: `vue create frontend`
  - Element: `npm i element-ui -S`
    - import Element in main.js with the following commands:
      - `import router from './router'`
      - `import ElementUI from 'element-ui';`
      - `Vue.use(ElementUI, "small");`
- Backend: 

- 

# Frontend

## Change Layout

- get code from Element->Layout Container and put that in HomeView.vue 

- App.vue
  ```vue
  <template>
    <div id="app">
      <router-view/>
    </div>
  </template>
  <style>
  #app{
    height: 100%;
  }
  </style>
  ```

- create global.css in ./src/assets
  ```css
  html, body, div {
      margin: 0;
      padding: 0;
  }
  
  html, body {
      height: 100%;
  }
  ```

- import global.css in main.js
  ```js
  import Vue from 'vue'
  import App from './App.vue'
  import router from './router'
  import ElementUI from 'element-ui';  
  import 'element-ui/lib/theme-chalk/index.css';
  import './assets/global.css'
  
  Vue.config.productionTip = false
  Vue.use(ElementUI, "small");
  
  new Vue({
    router,
    render: h => h(App)
  }).$mount('#app')
  
  ```

  

- menu-folder (如何实现侧边栏收缩功能)

# Backend (Springboot)

mytibais

in Postman test the interface(port)

![image-20240110135739036](DReadMe.assets/image-20240110135739036.png)

## Package: Entity

### Class: User

User class provide all atributes of user and correspond getter&setter

## Package: controller

### Class: UserController



## Package: Mapper

### Class: UserMapper



## Functions

### Data sending from Frontend to Backend (database)

Interface test: in Postman 

User in JSON write --> send request to port localhost:9090/user --> UserController: @PostMapping (@RequestBody User user): @RequestBody will transfer the instance of JSON to instance of java

Instance of JSON: (you can use Postman for testing)

```json
{
    "username": "huryy",
    "password": "123",
    "nickname": "hooo",
    "email": "huryy@gmail.com",
    "phone": "1121313",
    "address": "fefsaefa"
}  
```

Instance of Java:

```java
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
}
```

In UserController: 

```java
@PostMapping
public Integer save(@RequestBody User user) { // what is @RequestBody? instance of json from frontend change to instance of java in backend
		return userMapper.insert(user);
}
```

Now you have instance of User names user and you put it as variable in `save` method, this method will `return userMapper.insert(user);` 

userMapper:

```java
@Insert("INSERT INTO sys_user(username, password, nickname, email, phone, address) VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
int insert(User user);
```

userMapper is a instance of UserMapper, a interface. In UserMapper you define some methods to interact with database, in this project is MySQL. How can we interacte in UserMapper with MySQL? **through mybatis**

`@Insert`: The annotation that specify an SQL for inserting record(s). From package Mybatis.

What is Mybatis?

### User one method to add or update (UserController)

```java
//UserController
@PostMapping
// save: add or update. How can I use one method 'sava' to add user or update user? UserService
public Integer save(@RequestBody User user) {
  	return userMapper.insert(user); // now is only insert
}
```

--> 

```java
//UserController
@PostMapping
public Integer save(@RequestBody User user) {
  	return userService.save(user)); // use userService.save to insert(add) or update user
}
```

```java
public class UserService {
  
  @Autowird
  private UserMapper userMapper;
  
  public int save(User user) {
    
    if(user.getId() == null) {
      return userMapper.insert(user);
    } else {
      return userMapper.update(user);
    }
  }
}
```

### Dynamic SQL (UserMapper, User.xml)

When you update, how to update the certain information not all and not others be replaced by null?

```java
//UserMapper

// if you write like following, then except the updated infor all others will be overwroten by null
@Update("UPDATE sys_user SET username=#{username}, password=#{password}, nickname=#{nickname}, email=#{email}, phone=#{phone}, address=#{address} WHERE id = #{id}")
int update(User user);
```

so what should you do? resources/mapper/User.xml --> Dynamic SQL

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.hurryclear.backend.mapper.UserMapper">
    <update id="update">
        UPDATE sys_user
        <set>
            <if test="username != null">
                username=#{username},
            </if>
<!--            <if test="password != null">-->
<!--                password=#{password}-->
<!--            </if>-->
            <if test="password != null">
                password=#{password},
            </if>
            <if test="nickname != null">
                nickname=#{nickname},
            </if>
            <if test="email != null">
                email=#{email},
            </if>
            <if test="phone != null">
                phone=#{phone},
            </if>
            <if test="address != null">
                address=#{address}
            </if>
        </set>
        <where>
            id = #{id}
        </where>
    </update>
</mapper>
```

AND you have to do a very important step!!!
This file will not be recognized by Spring Boot unless you "tell" Spring Boot. How?

```yml
mybatis:
  mapper-locations: classpath:mapper/*.xml #scan all mybatis xml file
```
