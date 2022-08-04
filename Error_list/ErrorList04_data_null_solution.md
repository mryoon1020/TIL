# ErrorList04_Solution

- 코드

> js부분

```js
document.getElementById("deleteFile").onclick = function () {

  let oldFile = document.getElementById("oldFile").value;
  
  let noticeno = document.getElementById("noticeno").value;
  
  alert(noticeno);
  alert(oldFile);

//   let formData = new FormData()

//   formdata.append("noticeno", noticeno);
//   formData.append("oldFile", oldFile);

  const data = {
            "noticeno" : noticeno
}
 alert(data);
      fetch('/notice/deletefile',{
      
            method: 'POST',
            headers: {
    'Content-Type': 'application/json',
  },
            body:JSON.stringify(data)
          })
          .then(response => response.json())
          .then(function(response){
            if(response.oldFile == ''){
              alert('삭제를 성공하였습니다')
            }else{
              alert('삭제를 실패하였습니다')
            }
            
          })
          .then(result => document.getElementById('oldFile').value(result))
          //이게맞나?
          .catch(console.log('실패'))

    };
```

> controller

- Fname은 넘어 오지 않지만 noticeno는 잘넘어옴
- 넘어온 noticeno만으로도 데이터 처리 가능함
- Fname 넘기는 것은 계속 방법을 찾는중

```java
  @ResponseBody
    @PostMapping("/notice/deletefile")
    public String deleteFile(@RequestBody NoticeDTO dto) {

        log.info("noticeno : " + dto.getNoticeno());
        log.info("oldFile : " + dto.getFname());
       System.out.println("noticeno : " + dto.getNoticeno());
        System.out.println("oldfile : " + dto.getFname());
        
          service.deleteFile(dto.getNoticeno());
            
  return "파일삭제 성공";

    }
```

