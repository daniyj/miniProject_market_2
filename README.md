
# ♻️Mutsa Market Service♻️

### 📌프로젝트 배경
많이 사용하고 있는 🥕당근마켓, 중고나라 등을 착안하여 여러분들만의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.

사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제**안에 대하여 수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드를 만들 것입니다.**<br><br>

### 📌개발 기간

- 23.06.29 - 23.7.5
  <br><br>

### 📌개발 환경

- `java 17`
- `JDK 17.0.1`
- Framework : `springboot 3.1.1`
- Database : `sqlite`
- ORM : `JPA`
- Dependency : `lombok` `data jpa` `web` `devtools` `validation`

### 📌요구사항
<details>
<summary>1️⃣ 중고 물품 관리 요구사항</summary>
<div markdown="1">

1. 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 ****************************************************************************제목, 설명, 최소 가격, 작성자****************************************************************************이다.
    2. 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 최초로 물품이 등록될 때, 중고 물품의 상태는 **판매중** 상태가 된다.
2. 등록된 물품 정보는 누구든지 열람할 수 있다. 
    1. 페이지 단위 조회가 가능하다.
    2. 전체 조회, 단일 조회 모두 가능하다.
3. 등록된 물품 정보는 수정이 가능하다. 
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 물품 정보에 이미지를 첨부할 수 있다.
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    2. 이미지를 관리하는 방법은 자율이다.
5. 등록된 물품 정보는 삭제가 가능하다. 
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

</div>
</details>

<details>
<summary>2️⃣ 물품 댓글 요구사항</summary>
<div markdown="1">

1. 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 **대상 물품, 댓글 내용, 작성자**이다.
    2. 또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
2. 등록된 댓글은 누구든지 열람할 수 있다. 
    1. 페이지 단위 조회가 가능하다.
3. 등록된 댓글은 수정이 가능하다. 
    1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 댓글은 삭제가 가능하다. 
    1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
5. 댓글에는 초기에 비워져 있는 **답글** 항목이 존재한다. 
    1. 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
    2. 답글은 댓글에 포함된 공개 정보이다.

</div>
</details>
       
<details>
<summary>  3️⃣ 구매 제안 요구사항</summary>
<div markdown="1">

1. 등록된 물품에 대하여 구매 제안을 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 **대상 물품, 제안 가격, 작성자**이다.
    2. 또한 구매 제안을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 구매 제안이 등록될 때, 제안의 상태는 **제안** 상태가 된다.
2. 구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다.
    1. 대상 물품의 주인은, 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다. 이때 물품에 등록된 모든 구매 제안이 확인 가능하다. 페이지 기능을 지원한다.
    2. 등록한 사용자는, 조회를 위해서 자신이 사용한 **작성자와 비밀번호**를 첨부해야 한다. 이때 자신이 등록한 구매 제안만 확인이 가능하다. 페이지 기능을 지원한다.
3. 등록된 제안은 수정이 가능하다. 
    1. 이때, 제안이 등록될때 추가한 **작성자와 비밀번호**를 첨부해야 한다.
4. 등록된 제안은 삭제가 가능하다. 
    1. 이때, 제안이 등록될때 추가한 **작성자와 비밀번호**를 첨부해야 한다.
5. 대상 물품의 주인은 구매 제안을 수락할 수 있다. 
    1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 **수락**이 된다.
6. 대상 물품의 주인은 구매 제안을 거절할 수 있다. 
    1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 **거절**이 ****된다.
7. 구매 제안을 등록한 사용자는, 자신이 등록한 제안이 수락 상태일 경우, 구매 확정을 할 수 있다. 
    1. 이를 위해서 제안을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 **확정** 상태가 된다.
    3. 구매 제안이 확정될 경우, 대상 물품의 상태는 **판매 완료**가 된다.
    4. 구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 **거절**이 된다.

</div>
</details>

<details>
<summary>토글 접기/펼치기</summary>
<div markdown="1">

안녕

</div>
</details>

### 📌API
<details>
<summary>1️⃣ 중고 물품 관리</summary>
<div markdown="1">
  
물품 단일 조회
GET /items/{id}<br>
controller : read()<br>
service : readItem()
  
물품 전체 조회
GET /items <br>
controller : readAll()<br>
service : readItemAll()

물품 페이지 단위 조회
GET /items/page?page=0&limit=5<br>
controller : readPage()<br>
service : readItemPaged()

물품 등록
POST /items<br>
controller : create()<br>
service : createItem()

물품 정보 수정
PUT /items/{id}<br>
controller : update()<br>
service :updateItem()

물품 삭제
DELETE /items/{id}<br>
controller : delete()<br>
service :deleteItem()

물품 이미지 등록(미완성)
PUT /items/{id}/image<br>
controller : uploadImage()<br>
service : updateItemImage()

</div>
</details>
       
<details>
<summary>2️⃣ 물품 댓글 관리</summary>
<div markdown="1">

해당 물품의 댓글 전체 조회

GET /items/{itemId}/comments/readAll

controller : readAll()

service : readCommentAll()

댓글 등록

POST /items/{itemId}/comments

controller : create()

service : createComment()

댓글 삭제

DELETE /items/{itemId}/comments/{commentId}

controller : delete()

service : deleteComment()

댓글 수정

PUT /items/{itemId}/comments/{commentId}

controller : update()

service : updateComment()

댓글의 답글 등록

PUT /items/{itemId}/comments/{commentId}/reply

controller : updateCommentReply()

service : updateCommentReply()

</div>
</details>
