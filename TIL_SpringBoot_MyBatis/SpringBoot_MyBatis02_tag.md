# Spring Boot MyBatis

### Mapper XML 파일구조

- SQL 문을 가지고 있음
- xml 사용시 DTD(Document Type Definition) 선언 필요
- DTD 선언 이후 `<mapper>` Root Element가 선언됨

```xml-dtd
<!-- DTD 선언 -->
<?xml version="1.0" encoding="UTF-8" ?> 
 
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 루트 엘리먼트 -->
<mapper namespace="">
</mapper>
```

### Mapper SQL 사용

1. **select**

```xml
<select id=”selectPerson” parameterType=”int” resultType=”hashmap”>
SELECT * FROM PERSON WHERE ID = #{id}
</select>
```

 * id
   * Mapper 파일들 내에서 유일한 아이디를 등록
   * `<mapper namespace="">`에서 namespace값이 다른면 같은 id도 
      다른 id로 인식
 * paramaterType
   * 외부로 부터 데이터를 받아올 때 사용하는 속성
   *  insert, update시 사용할 데이터를 받아와서 생성,수정함
   * 받아온 데이터 타입을 명시함
 * resultType
   * 주로 select구문 처리후 DBMS로부터 결과를 받아올때 사용
   * 결과값을 매핑할 자바 객체 타입을 명시
   * select구문에서 생략불가
 * #{}
   * 값(데이터)

2. **insert**
   - 데이터 삽입
   -  insert의 `<selectKey>`는 기본 키 필드의 자동 생성을 지원
   - `<selectKey>`를 사용하면 생성된 키를 쉽게 가져올 수 있음
   - `<selectkey>` 생성된 insertkey를 받아와서 사용할때 사용

```xml
<insert id="insertAuthor" parameterType="domain.blog.Author">
        <selectKey keyProperty="id" resultType="int" >
                select board_seq.nextval as idfrom dual
        </selectKey>
        insert into Author (id,username,password,email,bio)
        values (#{id},#{username},#{password},#{email},#{bio})
</insert>
```

 	3. **update**
     - 데이터 수정

```xml
<update id="updateAuthor" parameterType="domain.blog.Author">
        update Author set
        username = #{username},
        password = #{password},
        email = #{email},
        bio = #{bio}
        where id = #{id}
</update>
```

4. **delete**
   - 데이터 삭제

```xml
<delete id="deleteAuthor” parameterType="int">
        delete from Author where id = #{id}
</delete>
```

5. **resultMap**
   - 결과를 매핑할 때 하나의 java객체로 매핑이 안되는 경우에 사용(join등)
   - 테이블 컬럼명과 매핑할 자바객체의 필드명이 다를때도 사용
   - 추가 tip.
     * dto 필드명, 컬럼명, 저장할 객체명은 동일하게 하는것 권장
       * resultType을 쓰기위함
       * resultmap은 복잡하다는 단점이 있음
   - ` <resultMap>` 을 사용하려면 미리 `<resultMap>` 의 매핑 규칙을 지정한다.

```xml
<resultMap id="selectResult" type="board">
     <result property="num" column = 'seq'>
     <result property="title" column = 'subject'>
     <result property="content" column = 'content'>
     <result property="redate" column = 'redate'>
</resultMap>
         
<select id=”selectBoard” parameterType=”int” resultMap=”selectResult”>
    SELECT * FROM board WHERE num = #{num}
</select>
```

6. **CDATA Section**

   - SQL구문에 '<'를 사용하면 에러가 남
     - xml에서 는 '<'는 태그 시작으로 처리하기 때문
     - 보통은 between을 많이 씀

   - CDATA Section을 사용하게 되면 단순 문자데이터로 인식함

```xml
<select id=”selectBoard” parameterType=”int” resultType=”board”>
    SELECT *
    FROM board
   <![CDATA[
    WHERE num <= #{num}
    ]]>
</select>
```

7. **동적 SQL**
   - 동적 SQL 요소들은 JSTL 이나 XML 기반의 텍스트 프로세서사용과 비슷

- **if**

  - 단일조건문, 조건 만족하는 것만 SQL 실행

  - `<if test="조건"></if>` 형태

```xml
<select id=”findActive”
        parameterType=”Blog” resultType=”Blog”>
        SELECT * FROM BLOG
        WHERE state = ‘ACTIVE’
        <if test=”title != null”>
        AND title like #{title}
        </if>
</select>
 
<select id=”findActiveBlogLike”
parameterType=”Blog” resultType=”Blog”>
        SELECT * FROM BLOG WHERE state = ‘ACTIVE’
        <if test=”title != null”>
        AND title like #{title}
        </if>
        <if test=”author != null and author.name != null”>
        AND author_name like #{author.name}
        </if>
</select>
```

- **choose, when, otherwise**

  - 다중조건문

  - `<choose>` 

    `<when test=조건></when>` 

    `<when test=조건></when>` 

    `<otherwise></otherwise>` :when이 끝나면 가장 마지막에 사용

    `</choose>` 

  - if, else if, else와 유사

```xml
<select id=”findActiveBlogLike”
        parameterType=”Blog” resultType=”Blog”>
        SELECT * FROM BLOG WHERE state = ‘ACTIVE’
        <choose>
        <when test=”title != null”>
                AND title like #{title}
        </when>
        <when test=”author != null and author.name != null”>
                AND author_name like #{author.name}
        </when>
        <otherwise>
                AND featured = 1
        </otherwise>
        </choose>
</select>
```

- **where**

  - SQL에서 where 조건문 사용시 사용함

  - `<where></where>` 형태

  - 오류예시

    ```xml
    <select id=”findActiveBlogLike”
            parameterType=”Blog” resultType=”Blog”>
            SELECT * FROM BLOG
            WHERE
            <if test=”state != null”>
                    state = #{state}
            </if>
            <if test=”title != null”>
                    AND title like #{title}
            </if>
            <if test=”author != null and author.name != null”>
                    AND author_name like #{author.name}
            </if>
    </select>
    ```

  - 예시문의 if조건에 맞는것이 없을경우 다음과 같은 쿼리가 실행됨

    ```sql
    (오류)
    SELECT * FROM BLOG
    WHERE
     
    (오류)
    SELECT * FROM BLOG
    WHERE
    AND title like ‘someTitle’
    ```

  - 오류를 없에기 위해 쿼리대신 `<where></where>` 를 사용함

```xml
<select id=”findActiveBlogLike”
        parameterType=”Blog” resultType=”Blog”>
        SELECT * FROM BLOG
        <where>
        <if test=”state != null”>
                state = #{state}
        </if>
        <if test=”title != null”>
                AND title like #{title}
        </if>
        <if test=”author != null and author.name != null”>
                AND author_name like #{author.name}
        </if>
        </where>
</select>
```

- **set**
  - 쿼리문의 set 대신 `<set></set>`  사용
    - 동적인 update구문의 set요소는 update하고자 하는 컬럼을 동적으로 포함시키기 위해사용함
  - 각 구문의 끝에 `,`  반드시 사용

```xml
<update id="updateAuthorIfNecessary"
        parameterType="domain.blog.Author">
        update Author
        <set>
        <if test="username != null">username=#{username},</if>
        <if test="password != null">password=#{password},</if>
        <if test="email != null">email=#{email},</if>
        <if test="bio != null">bio=#{bio}</if>
        </set>
        where id=#{id}
</update>
```



- **foreach**
  - collection에 대해 반복처리
  - in 조건사용 할때도 있음

| 구분       | 설명                                                        |
| ---------- | ----------------------------------------------------------- |
| collection | 전달받은 인자값                                             |
| item       | 전달받은 인자값을 다른이름으로 대체                         |
| open       | 해당 구문이 시작할때                                        |
| close      | 해당 구문이 끝날때                                          |
| index      | 항목의 인덱스 값을 꺼낼때 사용할 변수 이름을 지정           |
| separator  | 구분자, 한번 이상 반복할때 반복되는 사이에 해당 문을 넣어줌 |

```xml
<select id="selectPostIn" resultType="domain.blog.Post">
        SELECT *
        FROM POST 
        WHERE ID in 
        <foreach item="item" index="index" collection="list"
        open="(" separator="," close=")">
                #{item}
        </foreach>
</select>
```

