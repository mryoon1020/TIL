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

- `formData.append`

  - `FormData` 인터페이스의  `append()` 메서드는 `FormData` 객체의 기존 키(key)에 새 값을 추가함
  - 키가 없는 경우 키를 추가함
  - 사용법은 java의 map과 비슷
  - key, value 순서로 쌍을

  ```js
  formData.append('username', 'Chris');
  formData.append('userpic', myFileInput.files[0], 'chris.jpg');
  ```

  - 동일한 이름의 여러값을 추가할 수 있음

  ```js
  formData.append('userpic[]', myFileInput.files[0], 'chris1.jpg');
  formData.append('userpic[]', myFileInput.files[1], 'chris2.jpg');
  ```

  - 데이터가 루프돌는데 도움되는 구조로 다중파일업로드 처리를 쉽게 할 수 있음

> FormData를 사용하여 변경된 javascript

```js
function add(formdata){
//wname, title, content, fnameMF, passwd
for(var pair of formdata.entries()) {
   alert(pair[0]+ ', '+ pair[1]);
}
//formdata속 저장된 데이터를 출력하는기능
//console로 출력이 되지 않아 alert으로 출력

return fetch('/notice/create',{
                method: 'POST',
                body: formdata,
    
                })
                .then(response =>alert(response.text()))
                .catch(console.log('실패'));

}

function create(){

  var wname = document.getElementById('wname').value;
  var title = document.getElementById('title').value;
  var content = document.getElementById('content').value;
  var passwd = document.getElementById('passwd').value;

const formdata = new FormData();
const fileField = document.querySelector('input[type="file"]');

formdata.append('wname', wname);
formdata.append('fnameMF', fileField.files[0]);
formdata.append('title', title);
formdata.append('content', content);
formdata.append('passwd', passwd);


  add(formdata)
    .then(result => result.text())
    .then(data => console.log(data))

}
```

> 파일 업로드 기능이 추가된 controller

```java
 @ResponseBody
    @PostMapping("/notice/create")
    public String create(NoticeDTO dto) {
	//formdata 사용시에는 컨틀롤러에서 @Requestbody를 쓸필요는 없음
     log.info("dto: "+ dto.getContent());
     log.info("dto: "+ dto.getTitle());
     log.info("dto: "+ dto.getWname());
     log.info("dto: "+ dto.getFnameMF());
     String upDir = UploadNotice.getUploadDir();

     if(dto.getFnameMF() != null && !dto.getFnameMF().equals("")) {
       String fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);

       log.info("fname:" + fname);

        dto.setFname(fname);

        }
        if (service.create(dto) > 0) {
            return "ok";
        } else {
            return "error";
        }

    }
```

