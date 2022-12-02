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
    <script src=${kakaokey}></script>
    <script>

    // navigator.geolocation.getCurrentPosition((position) => {
    //   doSomething(position.coords.latitude, position.coords.longitude);

    // });

    // const latitude  = position.coords.latitude;
    // const longitude = position.coords.longitude;

    // console.log(`위도: ${latitude} 경도: ${longitude}`);


    // const watchID = navigator.geolocation.watchPosition((position) => {
    // doSomething(position.coords.latitude, position.coords.longitude);
    // });

    navigator.geolocation.getCurrentPosition(
    //(가져오기 성공했을 때, error일때)

    (position) =>{

        let latitude = position.coords.latitude
        let longitude = position.coords.longitude

        console.log(latitude);
        console.log(longitude);
    },
    (error) => {console.log("위치정보찾기실패")}

    )
    </script>

</head>
<body>
<div id="map" style="width: 500px;height:350px;"></div>
<p>
    <button onclick="setCenter()">지도 중심좌표 이동시키기</button>
    <button onclick="panTo()">지도 중심좌표 부드럽게 이동시키기</button>
</p>

<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

function setCenter() {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);

    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
}

function panTo() {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(33.450580, 126.574942);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
}
</script>
</body>
</html>