# MEMO API

## 기술 스택

- Spring Boot, Kotlin, JPA, MySQL

## ERD

![memo ERD](https://user-images.githubusercontent.com/69888508/133885111-30f7b9e1-4cdd-42e9-a01d-2d1f0c191419.png)

## [Swagger-Ui 바로가기](http://localhost/swagger-ui/index.html)

## 구현 기능

- 메모 생성하기           `POST /api/v1/memo`
    - 메모에는 제목, 내용, 생성날짜, 수정날짜가 있다.
    - 날짜 형태는 yyyy-MM-dd HH:mm:ss 이다.
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
    - 기존에 있던 메모를 조회한다.
    - Response
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
    - 기존에 있던 메모를 삭제한다.

- 메모 삭제하기           `DELETE /api/v1/memo/{memoId}`
    - 기존에 있던 메모를 삭제한다.

- 메모 검색하기           `GET /api/v1/memos/?date={date}&page={page}`
    - 날짜를 기준으로 최신순으로 한 페이지당 5개의 메모를 리스트 형태로 출력한다.
    - 날짜 형태는 yyyy-MM-dd이다.
