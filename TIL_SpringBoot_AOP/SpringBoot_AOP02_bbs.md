# AOP 방식으로 BBS 삭제기능 수정하기

### 개요

- 일반 작업 프로세스
  - controller -> dao -> mybatis -> mysql
- 트랜젝션 적용
  - controller -> service -> dao -> mybatis -> mysql
- 게시판 글 삭제시 댓글이 있는 글은 삭제하지 못하게 막음
- 댓글이 있는 글 삭제를 원하면 댓글우선 삭제후 삭제가능

### 코드

1. 게시판 글 삭제시 댓글이 있는 글 삭제불가 기능

> BbsService.java

```java
 int delete(int bbsno);  ===변경===>  void delete(int bbsno);
```

> BbsServiceImpl.java(기존, 변경될 부분만)

```java
 @Override
  public int delete(int bbsno) {
    // TODO Auto-generated method stub
    return mapper.delete(bbsno);
  }
```

>BbsServiceImpl.java(기존, 추가/변경된 부분만)

```java
@Autowired
  private ReplyMapper rmapper; //추가

 @Override
  public void delete(int bbsno) {
    // TODO Auto-generated method stub
    rmapper.bdelete(bbsno);
    mapper.delete(bbsno);
  }
```

> reply.xml

```xml
<delete id="bdelete" parameterType="int">
		DELETE FROM reply
		WHERE bbsno = #{bbsno}
	</delete>
```

> bbs.xml(일부러 오류내보기)

```xml
<delete id="delete" parameterType="int">
delete from bbs
where bbsno1 = #{bbsno}
</delete>
```

2. AOP적용

>build.gradle(하기내용 추가 후 refresh 잊지말 것)

```gradle
 // https://mvnrepository.com/artifact/org.aspectj/aspectjweaver
 implementation group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.6'
```

>TransactionAspect.java

```java
package com.study.bbs;
import java.util.Collections;
import java.util.List;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
@Configuration
public class TransactionAspect {
    @Autowired
    private PlatformTransactionManager transactionManager;
    // 포인트 컷 선언: 트랜잭션이 적용되는 시점
    // private static final String EXPRESSION = "execution(* com.study..service.*Impl.*(..))";
    // om.study 패키지로 시작하며 Impl로 끝나는 class의 모든 메소드에 트랜잭션 적용
    private static final String EXPRESSION = "execution(* com.study..service.*Impl.*(..))";
    @Bean
    public TransactionInterceptor transactionAdvice() {
        List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(rollbackRules);
        transactionAttribute.setName("*");
        MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
        attributeSource.setTransactionAttribute(transactionAttribute);
        return new TransactionInterceptor(transactionManager, attributeSource);
    }
    @Bean
    public Advisor transactionAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }
}
```

> BbsController.java(변경전)

```java
 @PostMapping("/bbs/delete")
  public String delete(int bbsno, String passwd, String oldfile) {
    Map map = new HashMap();
    map.put("bbsno", bbsno);
    map.put("passwd", passwd);

    String upDir = UploadBbs.getUploadDir();

    int pflag = dao.passCheck(map);
    int flag = 0;
    if (pflag == 1) {
      flag = dao.delete(bbsno);
      if (oldfile != null && !oldfile.equals(""))
        Utility.deleteFile(upDir, oldfile);
    }

    if (pflag != 1)
      return "passwdError"; // 비번오류일때 비번오류페이지 보여준다.
    else if (flag != 1)
      return "error";
    else
      return "redirect:list";
  }
```

>BbsController.java(변경후)

```java
@PostMapping("/bbs/delete")
  public String delete(int bbsno, String passwd, String oldfile) {
    Map map = new HashMap();
    map.put("bbsno", bbsno);
    map.put("passwd", passwd);

    String upDir = UploadBbs.getUploadDir();

    int pflag = dao.passCheck(map);
    String url = "./passwdError";
    
    if (pflag==1) {
       try {
         dao.delete(bbsno);
         url = "redirect:./list";
         if(oldfile != null)
            Utility.deleteFile(upDir, oldfile);
       }catch(Exception e) {
         e.printStackTrace();
         url = "./error";
       }
    }
 
    return url;   
  }
```