# Aws S3(네이버 클라우드에서 제공되는 버전) 관련

### 목적

- 기존 로컬에 저장하는 방식에서 네이버 클라우드 보관소에 저장하는 방식으로 변경

- 415에러가 나타나게 되었음
- 415에러: 서버측에서 받는 타입과 client 에서 보내는 타입이 서로 다를때 생기는 에러

> 에러나는 코드

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

</script>
```

```java
 @ResponseBody
    @PostMapping("/notice/create")
    public String create(@RequestBody NoticeDTO dto) throws IOException {
        
         MultipartFile multipartFile = dto.getFnameMF();
        if (dto.getFnameMF() != null && !dto.getFnameMF().equals("")) {
            // 파일명으로 저장된다.
            dto.setFname(multipartFile.getName());
            AwsS3 S3 = awsS3Service.upload(multipartFile, "notice");
            dto.setKey(S3.getKey());

            System.out.println(dto);
        }

        if (service.create(dto) > 0) {
            return "redirect:./list";
        } else {
            return "error";
        }
    }
```

