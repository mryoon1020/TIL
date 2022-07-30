# ErrorList02_solution

### 해결완료

> 비동기통신 부분

```js
function add(formdata){

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
  formdata.append('fnameMF', fileField.files[0]);
  formdata.append('title', title);
  formdata.append('content', content);
  formdata.append('passwd', passwd);
      
  add(formdata)
      .then(result => result.text())
      .then(data => console.log(data))
```

> controller 부분

- 컨트롤러 최상단부
  - `@RequiredArgsConstructor` :  final을 가지고 있는 필드에 자동으로 생성자 생성
  - `private final AwsS3Service awsS3Service;` : final 추가

```java
@Controller
@RequiredArgsConstructor
public class NoticeController {
    @Autowired
    @Qualifier("com.rentcar.notice.service.NoticeServiceImpl")
    private NoticeService service;

    private final AwsS3Service awsS3Service;

    private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
```

- create 처리부분
- DB에 파일명을 저장하지 못했던 문제 해결
  - `multipartFile.getOriginalFilename()` 을 사용하면 파일명을 가져올 수 있음

```java
 @ResponseBody
    @PostMapping("/notice/create")
    public String create(NoticeDTO dto) throws IOException {

        log.info("dto: "+ dto.getContent());
        log.info("dto: "+ dto.getTitle());
        log.info("dto: "+ dto.getWname());
        log.info("dto: " + dto.getFnameMF());
        
        MultipartFile multipartFile = dto.getFnameMF();
        
        if (dto.getFnameMF() != null && !dto.getFnameMF().equals("")) {
            dto.setFname(multipartFile.getOriginalFilename());
            AwsS3 S3 = awsS3Service.upload(dto.getFnameMF(), "notice");
            dto.setKey((String)S3.getKey());

        }

        if (service.create(dto) > 0) {
            return "/notice/list";
        } else {
            return "error";
        }
    }
```

- SQL 오류 처리부분
  - 대부분의 경우 컬럼명을 입력하면 실행되지만
  - "key"라는 단어는 primary key, foreign key 와 같이 충돌이 남
  - 출동을 피하기 위해 컬럼명 key에 \` `으로 감싸 주었음

```xml
<insert id="create" parameterType="com.rentcar.notice.model.NoticeDTO">
			INSERT INTO notice(title, content, wname, passwd, wdate, fname, `key`)
			VALUES(#{title}, #{content}, #{wname}, #{passwd}, NOW(), #{fname}, #{key})
</insert>
```



