# Jquery라이브러리 AJAX 통신 오류

### 오류

- 비동기통신을 성공했지만 error로 처리됨
- 서버로 데이터가 잘 이동 됬으며 서버에서도 잘 처리되어 반환함

### 원인

- dataType 부분이 맞지 않았음
- 보내는 데이터와 받는 데이터가 일치하지 않았기때문
- 컨트롤러의 반환값은 url로 text 타입



### 해결

- contentType : 보내는 데이터 타임
- dataType : 서버에서 받는 데이터 타입
- dataType: 'json' => 'text' 변경
- `alert(request.status + '\n' + request.responseText + '\n' + error)` 를 사용하면 좀 더 디테일한 에러내역을 확인 할 수 있음
- https://api.jquery.com/ajaxError/에 원문이 있음



> 비동기 통신 에러코드

```js
document.getElementById("deleteFile").onclick = function () {

  let oldFile = document.getElementById("oldFile").value;
  
  let noticeno = document.getElementById("noticeno").value;

  const data = {
            noticeno : noticeno,
            fname : oldFile
}

$.ajax({
  url: '/admin/notice/deletefile',
  type: 'post',
  dataType:'json',
  data : JSON.stringify(data),
  contentType: 'application/json',
  
  success: function success(){
              alert('삭제를 성공하였습니다')
              location.reload()
            
  },            
  error: function error(){
          alert('삭제를 실패하였습니다')
            
  }

});
};
```


> 컨트롤러부분

```java
 @ResponseBody
    @PostMapping("/admin/notice/deletefile")
    public String deleteFile(@RequestBody NoticeDTO dto) {

       log.info("noticeno : " + dto.getNoticeno());
       log.info("oldFile : " + dto.getFname());
       log.info("key : " + service.read(dto.getNoticeno()).getKey());

// S3 client

            String bucketName = "imagetest";
            String objectName = service.read(dto.getNoticeno()).getKey();

// delete object
        
            try {
                amazonS3.deleteObject(bucketName, objectName);
                System.out.format("Object %s has been deleted.\n", objectName);
            } catch (AmazonS3Exception e) {
                e.printStackTrace();
            } catch(SdkClientException e) {
                e.printStackTrace();
            }

        service.deleteFile(dto.getNoticeno());

        return "/admin/notice/update";

    }
```

> 비동기 통신 에러 수정코드

- dataType: 'json' => 'text' 변경

```js
document.getElementById("deleteFile").onclick = function () {

  let oldFile = document.getElementById("oldFile").value;
  
  let noticeno = document.getElementById("noticeno").value;

  const data = {
            noticeno : noticeno,
            fname : oldFile
}

$.ajax({
  url: '/admin/notice/deletefile',
  type: 'post',
  dataType:'text',
  data : JSON.stringify(data),
  contentType: 'application/json',
  
  success: function (){
              alert('삭제를 성공하였습니다')
              console.log('삭제를 성공하였습니다');
              location.reload()
  },            
  error: function (request, status, error){
          alert(request.status + '\n' + request.responseText + '\n' + error)
  }

});
};
```

