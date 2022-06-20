# 실습과제

### 02. 01의 확인 버튼을 누르면 AJEX get형식의 login 요청이 처리되어 다음과 같이 새로운 alert 응답을 하는 UserController.java를 작성

>test.js

```js
$(function() {
	$("#login_btn").click(function() {
		let id = $("#id").val();
		let passwd = $('#pw').val();

		let url = "http://localhost:8000/login?id=" + id + "&passwd=" + passwd;
		console.log(url);

		fetch(url)
			.then(response => response.json())
			.then((data) => alert(data.name + "님 login ok Status Success"));
		console.log(`id:${id}, passwd:${passwd}`);
	});
});
```

>UserService.java

```java
package com.example.sample;

import java.util.Map;

public interface UserService {

  UserDTO CheckIdPw(Map map);

}
```

>UserMapper.java

```java
package com.example.sample;

import java.util.Map;

public interface UserMapper {

  UserDTO CheckIdPw(Map map);

}
```

> UserServiceImpl.java

```java
package com.example.sample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.example.sample.UserServiceImpl")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper mapper;

  @Override
  public UserDTO CheckIdPw(Map map) {
    // TODO Auto-generated method stub
    return mapper.CheckIdPw(map);
  }

}
```

>UserController.java

```java
package com.example.sample;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  @Qualifier("com.example.sample.UserServiceImpl")
  private UserService service;

  @GetMapping("/")
  public String loginCheck() {

    return "test1";
  }

  @GetMapping("/login")
  @ResponseBody
  public UserDTO CheckIdPw(@RequestParam String id, @RequestParam String passwd) {

    Map map = new HashMap();
    map.put("id", id);
    map.put("passwd", passwd);

    UserDTO dto = service.CheckIdPw(map);

    return dto;
  }

}
```

> user.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?> 
 
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.sample.UserMapper">
<select id="CheckIdPw" parameterType="Map" resultType="com.example.sample.UserDTO">
      select *
      from user
      where id = #{id}
      and passwd = #{passwd}
   </select>
</mapper>
```
