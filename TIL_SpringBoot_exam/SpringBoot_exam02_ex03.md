# 실습과제

### 02. 01의 확인 버튼을 누르면 AJEX get형식의 login 요청이 처리되어 다음과 같이 새로운 alert 응답을 하는 UserController.java를 작성

- 모범답안과 함께 fetch부분을 중점적으로 주석을 달았음

>test.js

```js
$(function(){
	$("#login_btn").click(function(){
		alert($("#id").val() +" "+$("#pw").val());
		
		/** consumer */
		
		loginCheck($("#id").val(),$("#pw").val())
		//loginCheck함수를 호출함,
		//뷰페이지(test1.jsp)의 아이디의 값(.val())를 파라미터로 받음
			.then(text => alert(text))
			//함수에서 최종실행된 결과를 .then을 통해 받음
        	//리턴이 fetch면 .then을 쓸수 있음 
			//text라는 변수안에 .then을 통해 들어온 변수를 저장함
			//alert출력
			.catch(console.log);//에러날때 콘솔에 입력
		
	});
});

/** producer */

function loginCheck(id,pw){
	return fetch(`/loginCheck?id=${id}&passwd=${pw}`)
	//url은 ?뒤 /logincheck까지, get방식
	//return이 fetch 이므로 .then을 쓸수 있음
	//컨트롤러로 값이 이동됨
	//controller에서 return 값이 옴(여기서는 text타입으로 옴)
			.then(response => response.text())			
			//fetch는 json, text등 타입상관없이 response로 받아옴
    		//response에 저장된 데이터를 꺼내오려면 .찍고 원하는 타입을 적으면 됨
			//위에서는 편의상 변수를 response라는 이름으로 설정했지만
			//response대신에 다른이름이 들어가도됨,.then(res => res.text())가능
			// .text()는 response안의 데이터를 text타입으로 가져온다는 뜻
			// .json()은 reponse안의 데이터를 json타입으로 가져온다는 뜻
			//여기서 .text()를 쓴이유는 컨트롤러에서 리턴타입이 텍스트 였기 때문
			// .json 하고싶으면 컨트롤러에서 return값을 map으로 하면됨
            // 결국 컨트롤러에서 리턴하는 값에 따라 타입이 정해짐
			.catch(console.log)//에러날때 콘솔에 입력
}
```

>UserService.java

```java
package com.example.sample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserMapper mapper;

  public int loginCheck(Map<String, String> map) {
    // TODO Auto-generated method stub
    return mapper.loginCheck(map);
  }

  public String getName(String id) {

    return mapper.getName(id);
    
  }
}
```

>UserMapper.java

```java
package com.example.sample;

import java.util.Map;

public interface UserMapper {

  int loginCheck(Map<String, String> map);

  String getName(String id);

}
```

>UserController.java

```java
package com.example.sample;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserService service;  
  
	@GetMapping("/")
	public String loginCheck() {
		
		return "test1";
	}
	
	@GetMapping("/loginCheck")
	@ResponseBody//비동기 통신에서 반드시써야함, view페이지의 경로가 아닌이상 반드시 써야함
//@GetMapping(value = "/findID", produces ="application/json;charset=utf-8")
	                              //여기있는 produce는 return 값을 다른걸로 받고 싶을때 씀
	public String loginCheck(@RequestParam Map<String,String> map  ) {
	  
	  //Map<String,String> map = new HashMap<String,String>();
	  log.info("map:"+map);
	  int cnt = service.loginCheck(map);
	  String name = null;
	  if(cnt==1) {
	    name = service.getName(map.get("id"));
	  }
	  return name +"님 login ok Status Success";
	  
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
	<select id="loginCheck" parameterType="Map" resultType="int">
		SELECT
		count(*) FROM user
		WHERE id = #{id}
		and passwd = #{passwd}
	</select>
	<select id="getName" parameterType="String" resultType="String">
		SELECT
		name FROM user
		WHERE id = #{id}
	</select>
</mapper>
```
