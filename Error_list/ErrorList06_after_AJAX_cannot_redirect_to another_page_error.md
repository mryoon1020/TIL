# 비동기 통신후 다른페이지 이동 오류

### 오류

- 글 작성 후 목록으로 이동하는 기능사용불가
- 서버 쪽에서 파라미터를 받아서 create 처리가 완료 되었음
- return 값도 프론트 쪽으로 아주 잘 넘어옴에도 불구하고 그다음 함수로 이어지지 않음

### 해결

- 처음에는 fetch 구문에서 나타난 문법오류라고 생각했음
- 혹시나 하는 마음에 form태그를 지우고 실행해봄
- 목록으로 잘 이동이 되었음
- form 태그를 div 태그로 변경하였더니 아주 잘되는 것을 확인함

> 비동기 통신 요청부분

```js
function add(formdata){

  return fetch('/notice/create',{
                  method: 'POST',
                  body: formdata,
      
                  })
                  .then(function(response){
                    return response.text()
                  })
                  .catch(alert('실패'));
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
  
if(fileField.files[0] != null){
  formdata.append('fnameMF', fileField.files[0]);
}
  
    add(formdata)
    .then(function(url){
      return location.href = url
    });
  }
```

> 컨트롤러 부분

```java
 @ResponseBody
    @PostMapping("/notice/create")
    public String create(NoticeDTO dto) throws IOException {

        log.info("dto: " + dto.getContent());
        log.info("dto: " + dto.getTitle());
        log.info("dto: " + dto.getWname());
        log.info("dto: " + dto.getFnameMF());

// AWS 사용

        MultipartFile multipartFile = dto.getFnameMF();
        if (dto.getFnameMF() != null && !dto.getFnameMF().equals("")) {
            // 파일명으로 저장된다.
            dto.setFname(multipartFile.getOriginalFilename());
            AwsS3 S3 = awsS3Service.upload(dto.getFnameMF(), "notice");
            dto.setKey((String) S3.getKey());

        }

        if (service.create(dto) > 0) {
            return "/notice/list";
        } else {
            return "error";
        }
    }
```

> ViewPage 부분

```jsp
<!-- 생략 -->

<div class="container">
<h2 class="col-sm-offset-2 col-sm-10">공지 생성</h2>
<div class="form-horizontal" id="form1" >
 
  <div class="form-group"> <!-- <form class="form-group">이었었음 -->
    <label class="control-label col-sm-2" for="wname">작성자</label>
    <div class="col-sm-6">
      <input type="text" name="wname" id="wname" class="form-control" required>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="title">제목</label>
    <div class="col-sm-8">
      <input type="text" name="title" id="title" class="form-control" required>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="content">내용</label>
    <div class="col-sm-8">
    <textarea rows="12" cols="7" id="content" name="content" class="form-control"></textarea>
    </div>
  </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="fnameMF">첨부파일</label>
      <div class="col-sm-6">
        <input type="file" name="fnameMF" id="fnameMF" class="form-control">
      </div>
    </div>

  <div class="form-group">
    <label class="control-label col-sm-2" for="passwd">비밀번호</label>
    <div class="col-sm-6">
      <input type="password" name="passwd" id="passwd" class="form-control" required>
    </div>
  </div>
   <div class="form-group">
   <div class="col-sm-offset-2 col-sm-5">
    <button class="btn" onClick="create()">등록</button>
    <button class="btn" onClick="javscript:location.href = '/notice/list'">목록</button>

    <button type="reset" class="btn">취소</button>
   </div>
 </div>
</div>
</div> <!-- </form> 이어었었음 -->

<!-- 생략 -->
```
