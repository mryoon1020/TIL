# 우선 두서없이 적어보는 TIL

- Rent_car_project에 문제가 생겼음

  - NCL 기반으로 처리한 OCL기능, object storage가 기한 만료로 인해 사용불가됨

- 우선 Amazon에서 12개월간 프리티어를 제공하므로 NCL object storage를 aws S3로 변경

  - 사실 NCL object storage 도 Amazon aws S3 기반으로 제작(내가보기에는 똑같은거 같다)되었으므로 비슷할 것으로 추정

- 추후에 이문서는 다시 README 파일로 정리 될것임

- 우선 aws S3만 따로 구현해서 파일 업로드, 다운로드 기능을 사용한후 프로젝트에 적용해볼예정

- 시작일 2022-11-05

  - 관련 자료검색 완료
    - https://docs.aws.amazon.com/ko_kr/sdk-for-java/v1/developer-guide/examples-s3-objects.html#upload-object
    - https://antdev.tistory.com/93

  - 해당자료는 `application.yml` 파일을 사용중
  - `application.properties` 파일과 `application.yml` 과의 차이점
    - https://sowon-dev.github.io/2021/08/17/210818Spring-applicationyml/
    - 입력방식 차이로 보임
    - 가독성은 yml 파일 형식이 훨씬 좋으나 Rent_car_project는 properties 파일을 사용하므로 properties로 변경하여 사용하겠음

