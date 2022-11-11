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

    const formData = new FormData();
    const fileField = document.querySelector('input[type="file"][multiple]');
    
    if(fileField.files[0] != null){

        formData.append(fname,fileField.files[0]);

    }

    fetch('/upload', {
        method: 'POST',
        body: formData,
    })
    .then((response) => response.json())
    .then((result) => {
        console.log('성공:', result);
    })
    .catch((error) => {
        console.error('실패:', error);
    });
    

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

                파일이올부분.jpg
    
            </td>

        </tr>

        <tr>

            <td>

                <input type="file" multiple>
    
            </td>

        </tr>

        <tr>
            <td>

                <button type="submit">파일등록</button>&nbsp;

                <button type="submit">파일수정</button>&nbsp;

                <button type="submit">파일삭제</button>

            </td>


        </tr>

    </table>

</div>

<div>
    <p>저장된 파일리스트</p>

    <ul class="fileList" id="fileList">

        <li><input type="hidden" value="${dto.fileNo}"></li>

        <c:forEach var="dto" items="${webTestList}">

        <li>

            <input type="hidden" value="${dto.fileNo}">
            <input type="text" value="${dto.fileNo}">&nbsp;&nbsp;
            <input type="text" value="${dto.fileName}">

        </li>

        </c:forEach>

    </ul>
</div>

</body>
</html>