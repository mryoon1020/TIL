# 프론트에서 데이터가 넘어가지 않는 오류

### 비동기 통신 처리를 하였으나 데이터가 넘어가지 않는 오류 발생

> 비동기 통신 처리부분

```js
document.getElementById("deleteFile").onclick = function () {

  let oldFile = document.getElementById("oldFile").value;
  
  let noticeno = document.getElementById("noticeno").value;
  
  alert(noticeno);
  alert(oldFile);

  const data = {
            "noticeno" : noticeno,
            "oldFile" : oldFile
}

      fetch('/notice/deletefile',{
      
            method: 'POST',
            body:JSON.stringify(data)
            
          })
          .then(response => response.text)
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

> controller 부분

```java
 @ResponseBody
    @PostMapping("/notice/deletefile")
    public String deleteFile(NoticeDTO dto) {

        log.info("noticeno : " + dto.getNoticeno());
        log.info("oldFile : " + dto.getFname());
       	System.out.println("noticeno : " + dto.getNoticeno());
        System.out.println("oldfile : " + dto.getFname());
        
        return null;
```

