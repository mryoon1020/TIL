# Form => Controller 방식 fetch 방식으로 전환하기

### 방법

> createForm.jsp

- form 태그 안의 속성값들을 지운다.

```jsp
<form class="form-horizontal"
      action="./create"
      method="post"
      onsubmit="return checkIn(this)"
      enctype="multipart/form-data"
      >
```

- 결과

```jsp
<form class="form-horizontal" >
```

- 자바스크립트를 작성한다

```javascript
<script>
function add(notice){

return fetch('/notice/create',{
                method: 'post',
                body: JSON.stringify(),
                headers: {'Content-Type': "application/json; charset=utf-8"}
                })
                .then(response => response.json())
                .catch(console.log);

}

function create(){

  var wname = document.getElementById('wname').value;
  var title = document.getElementById('title').value;
  var content = document.getElementById('content').value;
  var fnameMF = document.getElementById('fnameMF').value;
  var passwd = document.getElementById('passwd').value;

//alert(wname);

  const notice = {

    "wname": wname,
    "title": title,
    "content": content,
    "fnameMF": fnameMF,
    "passwd": passwd

  };

  alert(notice);

  add(notice)
    .then(result => console.log(result)
    ); //end add

}

</script>
```

> controller.java

```java
@ResponseBody
    @PostMapping("/notice/create")
    public String create(@RequestBody NoticeDTO dto){

        log.info("dto: "+ dto);

       // service.create(dto);
       return "test";
    }
```

> MDN 참조

- 상기 코드 사용시 로그로 읽어온 값은 잘들어 오지만 비동기통신을 사용할경우 다른 구문을 사용해야함
- MDN의 예제와 같이 코드를 재구성필요

```javascript
const formData = new FormData();
const fileField = document.querySelector('input[type="file"]');

formData.append('username', 'abc123');
formData.append('avatar', fileField.files[0]);

fetch('https://example.com/profile/avatar', {
  method: 'PUT',
  body: formData,
})
.then((response) => response.json())
.then((result) => {
  console.log('성공:', result);
})
.catch((error) => {
  console.error('실패:', error);
});
```

