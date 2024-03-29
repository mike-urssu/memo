# MEMO API

## 기술 스택

- Spring Boot, Kotlin, JPA, MySQL

## ERD

![memo erd](https://user-images.githubusercontent.com/69888508/134698233-709f4e81-287d-4712-bfc5-ea7f0af04da9.png)

## [Swagger-Ui 바로가기](http://localhost/swagger-ui/index.html)

## 구현 기능

- 메모 생성하기           `POST /api/v1/memo`
    - 메모에는 제목, 내용, 생성날짜, 수정날짜가 있다.
    - 날짜 형태는 `yyyy-MM-dd HH:mm:ss`이다.
    - Request
      ```json
      {
          "title": "title of memo",
          "content": "content of memo"
      }
      ```
    - Response
      ```text
      HttpStatus.CREATED
      ```

- 특정 메모 조회하기       `GET /api/v1/memo/{memoId}`
    - Response
      ```text
      HttpStatus.OK
      ```
      ```json
      {
          "memo": {
              "memoId": 1,
              "title": "title of memo",
              "content": "content of memo",
              "updatedAt": "2021-09-21 19:13:16"
          }
      }
      ```

- 메모 수정하기           `PUT /api/v1/memo/{memoId}`
    - Request
      ```json
      {
        "title": "updated title of memo",
        "content": "updated content of memo"
      }
      ```
    - Response
      ```text
      HttpStatus.NO_CONTENT
      ```

- 메모 삭제하기           `DELETE /api/v1/memo/{memoId}`
    - Response
      ```text
      HttpStatus.NO_CONTENT
      ```

- 메모 목록 조회하기       `GET /api/v1/memos/?page={page}&date={date}`
    - 날짜를 기준 최신순으로 한 페이지당 5개의 메모를 리스트 형태로 출력한다.
    - 날짜 형태는 `yyyy-MM-dd`이다.
    - Response
    ```text
    HttpStatus.OK
    ```
    ```json
    {
      "page": {
          "size": 5,
          "totalElements": 1,
          "totalPages": 1,
          "number": 0
      },
      "memos": [
          {
              "memoId": 1,
              "title": "title of memo",
              "content": "content of memo",
              "updatedAt": "2021-09-01 00:00:00"
          }
      ]
    }
    ```
