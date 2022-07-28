# Error List

### 목표 Event

- 파일 삭제 클릭시 DB삭제 및 업로드 파일도 삭제

> fetch를 통해 noticeno와 oldfile을 controller 넘겨보려고 했음

- 실패 원인을 찾지 못했음
- 프론트에서 백으로 넘어가지지 않은 오류가 발생함

```js
function deleteFile(){
    
let oldFile = document.getElementById("oldFile").value;
alert(oldFile);

// const formdata = new FormData();
// alert(formdata);
// formdata.append("oldfile",oldFile);

    fetch('/notice/deletefile/${noticeno}',{
      
      method: 'GET',
    //  body:formdata,
      body: 'text'
      
    })
    .then(response => response.text)
    .then(function(response){
      if(respons.oldFile == ''){
        alert('삭제를 성공하였습니다')
      }else{
        alert('삭제를 실패하였습니다')
      }
      
    })
    .then(result => document.getElementById('oldFile').value(result))
    .catch(console.log('실패'))
  };
```

> AJAX로 변경해서 넘겨 보았음

- controller까지 잘 넘어감

```js
$("#deleteFile").click(function(){
  // function deleteFile(){
    // confirm("정말로 파일을 삭제하시겠습니까?")

    var noticeno = document.querySelector("#noticeno").value;
    var oldFile = document.querySelector("#oldFile").value;
    console.log(noticeno);
    console.log(oldFile);
    var data = { "noticeno" : noticeno,
                  "oldFile" : oldFile              
  } 
    
    alert(data); 
 
    $.ajax({ 
      
    type : "post", 
    
    url : "/notice/deletefile", 
    
    dataType:"json",
    
    data : JSON.stringify(data),

contentType: "application/json",

    success:
    
      function(data){ 
        
          console.log('성공입니다.'); 
          console.log(data); 
        
          alert("성공입니다.") }, 
        
          error:
            function(){ alert("에러입니다"); } });
```

> controller 처리부분

- 값은 넘어오지만 잘 처리 되지 않음
- 다시 해결이 필요한부분

```java
 @PostMapping(value="/notice/deletefile" , produces="application/json")
    public ResponseEntity deleteFile (@RequestBody Map<Object, Object> objectMap){
        System.out.println("objectMap   )    " + objectMap);
        Map<Object,Object>
                map = new HashMap<>();

      int noticeno = (int)map.get("noticeno");
       String oldFile = (String) map.get("oldfile");

                String upDir = UploadNotice.getUploadDir();

        service.deleteFile(noticeno);

        Utility.deleteFile(upDir, oldFile);

        return null;
    }
```

