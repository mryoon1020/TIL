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

    // navigator.geolocation.getCurrentPosition(
    //(가져오기 성공했을 때, error일때)

    // (position) =>{

    //     let latitude = position.coords.latitude
    //     let longitude = position.coords.longitude

    //     console.log(latitude);
    //     console.log(longitude);
    // },
    // (error) => {console.log("위치정보찾기실패")}

    // )
    // </script>

</head>
<body>
<div id="map" style="width: 500px;height:350px;"></div>
<p>
    <button onclick="setCenter()">지도 현위치로 이동시키기</button>
    <button onclick="panTo()">지도 중심좌표 부드럽게 이동시키기</button>
</p>

<div id="staticMap" style="width: 500px;height:350px;"></div>

<script>

//현재 좌표 받아오기========================================

let latitude = 0;
let longitude = 0;

navigator.geolocation.getCurrentPosition(

    (position) =>{

        latitude = position.coords.latitude
        longitude = position.coords.longitude

        console.log(latitude);
        console.log(longitude);

        return latitude = position.coords.latitude;
    },

    (error) => {console.log("위치정보찾기실패")}

    );



var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//마커 찍기============================================================

// 이미지 지도에서 마커가 표시될 위치입니다 

console.log("마커용 위도: "+latitude)
console.log("마커용 경도: "+longitude)

setTimeout(() => console.log("마커용 위도: "+latitude), 500);
setTimeout(() => console.log("마커용 경도: "+longitude), 500);

var markerPosition  = setTimeout(() => new kakao.maps.LatLng(latitude, longitude),500); 

// 이미지 지도에 표시할 마커입니다
// 이미지 지도에 표시할 마커는 Object 형태입니다
var marker = {
    position: markerPosition
};

var staticMapContainer  = document.getElementById('map'), // 이미지 지도를 표시할 div  
    staticMapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 이미지 지도의 중심좌표
        level: 3, // 이미지 지도의 확대 레벨
        marker: marker // 이미지 지도에 표시할 마커 
    };    

// 이미지 지도를 생성합니다
var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);

//지도 이동하기=============================================

function setCenter() {
    // 이동할 위도 경도 위치를 생성합니다
    
    var moveLatLon = new kakao.maps.LatLng(latitude, longitude);

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

// 현위치 마커============================================================

// var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
//     mapOption = { 
//         center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//         level: 10 // 지도의 확대 레벨 
//     }; 

// var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
// if (navigator.geolocation) {
    
//     // GeoLocation을 이용해서 접속 위치를 얻어옵니다
//     navigator.geolocation.getCurrentPosition(function(position) {
        
//         var lat = position.coords.latitude, // 위도
//             lon = position.coords.longitude; // 경도
        
//         var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
//             message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
        
//         // 마커와 인포윈도우를 표시합니다
//         displayMarker(locPosition, message);
            
//       });
    
// } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
//     var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
//         message = 'geolocation을 사용할수 없어요..'
        
//     displayMarker(locPosition, message);
// }

// // 지도에 마커와 인포윈도우를 표시하는 함수입니다
// function displayMarker(locPosition, message) {

//     // 마커를 생성합니다
//     var marker = new kakao.maps.Marker({  
//         map: map, 
//         position: locPosition
//     }); 
    
//     var iwContent = message, // 인포윈도우에 표시할 내용
//         iwRemoveable = true;

//     // 인포윈도우를 생성합니다
//     var infowindow = new kakao.maps.InfoWindow({
//         content : iwContent,
//         removable : iwRemoveable
//     });
    
//     // 인포윈도우를 마커위에 표시합니다 
//     infowindow.open(map, marker);
    
//     // 지도 중심좌표를 접속위치로 변경합니다
//     map.setCenter(locPosition);      
// }    

</script>
</body>
</html>