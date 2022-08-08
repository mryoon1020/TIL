# File이 업로드하지 않을경우 새로운 글이 생성되지 않는 오류

### 파일 업로드 하지 않을경우 오류

- 프론트에서 오류가 나며 데이터가 서버로 넘어가지 않음
- 아주 간단한 if문으로 `formdata.append` 를 감싸는 것으로 해결 하였음

> id문

```js
if(fileField.files[0] > 0){
  formdata.append('fnameMF', fileField.files[0]);
}
```
> 전체 코드

```js
function add(formdata){
  //wname, title, content, fnameMF, passwd

  for(var pair of formdata.entries()) {
    alert(pair[0]+ ', '+ pair[1]);
  }
  
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
    var key = document.getElementById('fnameMF').value;
  
  const formdata = new FormData();
  const fileField = document.querySelector('input[type="file"]');
  
  formdata.append('wname', wname);
  formdata.append('title', title);
  formdata.append('content', content);
  formdata.append('passwd', passwd);
  
if(fileField.files[0] > 0){
  formdata.append('fnameMF', fileField.files[0]);
}
    add(formdata)
      .then(result => result.text())
      .then(data => console.log(data));
    location.href('/notice/list');
  }
```

### 20220808 오류발생

- 파일을 업로드 할경우 create가 되지 않음
- 상기 if문 수정을 통해 해결 완료
  - 조건문 `> 0` => `!= null` 변경 

```js
if(fileField.files[0] != null){
  formdata.append('fnameMF', fileField.files[0]);
}
```

