<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>


    
    function addFile(){

    const formData = new FormData();
    const fileField = document.querySelector('input[type="file"]');

    if(fileField.files[0] != null){

        formData.append('file',fileField.files[0]);

    }

    fetch('/upload', {
        method: 'POST',
        body: formData,
    })
    .then((response) => {
        if(!response.ok){
            console.log(response)
        }else{
            response.text()
        }

    }
    
    )
    .then((result) => {
        console.log('성공:', result);
        alert("업로드에 성공했습니다.")
        location.href="/"
    })
    .catch((error) => {
        console.error('실패:', error);
    });
    
    }

    function fileList(){

        fetch('/list/files', {
        method: 'POST',
        body: JSON,
    })
    .then((response) => 
        response.json())
    .then((result) => {

        for(let i = 0; i<result.length;i++){

            let fileList = document.getElementById('fileList');

            let li = document.createElement('li');
            let downLoadLink = document.createElement('a');
            let deleteButton = document.createElement('button');

            let linkText = document.createTextNode(result[i]);
            let buttonText = document.createTextNode("파일삭제");

            let deleteLink = "location.href=";
            deleteLink+="'/deleteFile/"+result[i]+"'";

            downLoadLink.href = "/download/"+result[i];

            deleteButton.setAttribute("onclick", deleteLink);

            downLoadLink.appendChild(linkText);
            deleteButton.appendChild(buttonText);

            fileList.appendChild(li).appendChild(downLoadLink)
            li.appendChild(deleteButton);
        }

        console.log('성공:', result);

    })
    .catch((error) => {
        console.error('실패:', error);
    });

    }

</script>

</head>
<body>

<div class="saveFile">

    <table>

        <th>
            
            파일등록

        </th>

        <tr>

            <td>

                <input type="file" multiple>
    
            </td>

        </tr>

        <tr>
            <td>

                <input type="button" onclick=addFile() value="파일등록">

            </td>


        </tr>

    </table>

</div>

<div>
    <p>저장된 파일리스트</p>

    <!-- <ul class="fileList" id="fileList">

        <c:forEach var="dto" items="${list}">

        <li>

        <input type="text" value="${dto.fileNo}">&nbsp;&nbsp;
            <input type="text" value="${dto.fileName}">&nbsp;<input type="button" onclick=delteFile() value="파일삭제">

        </li>

        </c:forEach> 

    </ul>-->
    <input type="button" onclick=fileList() value="파일목록보기">
    <Ol id="fileList">

    </Ol>
</div>

</body>
</html>