# JQuery

### 소개

1. 매우 간결하지만 많은 기능을 처리할 수 있는  JavaScript Library 
2. DOM 문법을 매우 간결하게 해줌
3. 습득하기 쉬우며 호환성처리에 시간을 많이 소비하지 않음
4. Ajax를 지원함
5. 참고사이트 : https://api.jquery.com/ (꾸준히 업데이트됨)



### 사용법



1. VS code에서 기능을 지원함
2. VS code에서 좀더 편리하게 사용하기위해 다음과 같이 탬플릿 설정을 저장해주면 간단하게 불러 올수 있음

```
 1. VScodet실행 -> Manager(톱니바퀴 버튼) -> User Snippets -> html.json
 2. 하단내용 타이핑(복붙)후 저장
 3. html문서 생성 -> !!타이핑+tab -> JQuery 사용가능 html문서가 됨
 ------------------------------------------------------------------------------------
 {
        // Place your snippets for html here. Each snippet is defined under a snippet name and has a prefix, body and 
        // description. The prefix is what is used to trigger the snippet and the body will be expanded and inserted. Possible variables are:
        // $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. Placeholders with the 
        // same ids are connected.
        // Example:
        "!!": {
                "prefix": "!!",
                "body": [
                        "<!DOCTYPE html>",
                        "<html lang='ko'>",
                        "<head>",
                        "<meta charset='UTF-8'>",
                        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>",
                        "<title>$1</title>",
                        "<style>$2</style>",
                        "<script src='https://code.jquery.com/jquery-3.5.0.js'></script>",
                        "<script>",
                        "$(function () {",
                        "$3",
                        "});",
                        "</script>",
                        "</head>",
                        "<body>",
                        "$4",
                        "</body>",
                        "</html>",
                ],
                "description": "Log output to console"
        }
}
```



