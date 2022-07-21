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
function add(wname, title, content, fnameMF, passwd){

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
    .then(result => {

    }); //end add

}

</script>
```



