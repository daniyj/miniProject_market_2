
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
<br>

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
<summary>  4️⃣ 인증 요구사항</summary>
<div markdown="1">

1. 사용자는 **회원가입**을 진행할 수 있다.
    - 회원가입에 필요한 정보는 아이디와 비밀번호가 필수이다.
    - 부수적으로 전화번호, 이메일, 주소 정보를 기입할 수 있다.
    - 이에 필요한 사용자 Entity는 직접 작성하도록 한다.
    
2. **아이디 비밀번호**를 통해 로그인을 할 수 있어야 한다.
    
    
3. 아이디 비밀번호를 통해 로그인에 성공하면, **JWT가 발급**된다. 이 JWT를 소유하고 있을 경우 **인증**이 필요한 서비스에 접근이 가능해 진다.
    - 인증이 필요한 서비스는 추후(미션 후반부) 정의한다.
    
4. JWT를 받은 서비스는 **사용자가 누구인지** 사용자 **Entity를 기준**으로 정확하게 판단할 수 있어야 한다.

</div>
</details>

<details>
<summary>  5️⃣ 관계 설정 요구사항</summary>
<div markdown="1">
  
1. 아이디와 비밀번호를 필요로 했던 테이블들은 실제 사용자 Record에 대응되도록 ERD를 수정하자.
    - ERD 수정과 함께 해당 정보를 적당히 표현할 수 있도록 Entity를 재작성하자.
    - 그리고 ORM의 기능을 충실히 사용할 수 있도록 어노테이션을 활용한다.
    
2. 다른 작성한 Entity도 변경을 진행한다.
    - 서로 참조하고 있는 테이블 관계가 있다면, 해당 사항이 표현될 수 있도록 Entity를 재작성한다.


</div>
</details>

<details>
<summary>  6️⃣기능 접근 요구사항</summary>
<div markdown="1">
1. 본래 “누구든지 열람할 수 있다”의 기능 목록은 사용자가 **인증하지 않은 상태**에서 사용할 수 있도록 한다.<br>
    - 등록된 물품 정보는 누구든지 열람할 수 있다.<br>
    - 등록된 댓글은 누구든지 열람할 수 있다.<br>
    - 기타 기능들<br><br>
    
2. 작성자와 비밀번호를 포함하는 데이터는 **인증된 사용자만 사용**할 수 있도록 한다.<br>
    - 이때 해당하는 기능에 포함되는 아이디 비밀번호 정보는, 1일차에 새로 작성한 사용자 Entity와의 관계로 대체한다.
        - 물품 정보 등록 → 물품 정보와 사용자 관계 설정<br>
        - 댓글 등록 → 댓글과 사용자 관계 설정<br>
        - 기타 등등<br>
    - 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.
    - 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.
    - 등록된 물품에 대하여 구매 제안을 등록할 수 있다.
    - 기타 기능들


</div>
</details>
<br>

### 📌REST API
<details>
<summary>1️⃣ 중고 물품 관리</summary>
<div markdown="1">
  
**물품 단일 조회**<br>
GET /items/{id}<br>
controller : read()<br>
service : readItem()
  
**물품 전체 조회**<br>
GET /items <br>
controller : readAll()<br>
service : readItemAll()

**물품 페이지 단위 조회**<br>
GET /items/page?page=0&limit=5<br>
controller : readPage()<br>
service : readItemPaged()

**물품 등록**<br>
POST /items<br>
controller : create()<br>
service : createItem()

**물품 정보 수정**<br>
PUT /items/{id}<br>
controller : update()<br>
service :updateItem()

**물품 삭제**<br>
DELETE /items/{id}<br>
controller : delete()<br>
service :deleteItem()

**물품 이미지 등록**(미완성)<br>
PUT /items/{id}/image<br>
controller : uploadImage()<br>
service : updateItemImage()

</div>
</details>
       
<details>
<summary>2️⃣ 물품 댓글 관리</summary>
<div markdown="1">

**해당 물품의 댓글 전체 조회**<br>
GET /items/{itemId}/comments/readAll<br>
controller : readAll()<br>
service : readCommentAll()

**댓글 등록**<br>
POST /items/{itemId}/comments<br>
controller : create()<br>
service : createComment()

**댓글 삭제**<br>
DELETE /items/{itemId}/comments/{commentId}<br>
controller : delete()<br>
service : deleteComment()

**댓글 수정**<br>
PUT /items/{itemId}/comments/{commentId}<br>
controller : update()<br>
service : updateComment()

**댓글의 답글 등록**<br>
PUT /items/{itemId}/comments/{commentId}/reply<br>
controller : updateCommentReply()<br>
service : updateCommentReply()

</div>
</details>

<details>
<summary>3️⃣ 구매 제안 관리</summary>
<div markdown="1">

**구매 제안 등록**<br>
POST /items/{itemId}/proposals<br>
controller : createProposal<br>
service : createProposal

**구매 제안 전체보기**(요구사항에 없으나 조회를 위해 추가함)<br>
GET /items/{itemId}/proposals/page<br>
controller : readPageAll<br>
service : readPropAll

(미완성)**구매 제안 조회** (물품 작성자와 구매 제안 당사자만 조회 가능)<br>
GET /items/{itemId}/proposals?writer=작성자&password=비밀번호<br>
controller : readPage<br>
service : readProp<br>
구매 제안 당사자의 제안들은 조회가 가능하나, 해당 물품의 주인의 정보를 입력하면 에러가 난다.

**구매 제안 수정**<br>
PUT /items/{itemId}/proposals/{proposalId}<br>
controller : updateProposal<br>
service : updateProposal

**제안 상태 변경**(물품 주인이 함)<br>
PUT /items/{itemId}/proposals/{proposalId}/status<br>
controller : updateProposalStatus<br>
service : updateProposalStatus

**구매 확정**(제안자가 함)<br>
PUT /items/{itemId}/proposals/{proposalId}/status-confirmed<br>
controller : updateProposalConfirmed<br>
service : updateProposalConfirmed

**제안 삭제**<br>
DELETE /items/{itemId}/proposals/{proposalId}<br>
controller : deleteProposal<br>
service : deleteProposal

</div>
</details>

<details>
<summary> 4️⃣ 회원가입 </summary>
<div markdown="1">

**회원가입**<br>
POST /users/register

</div>
</details>

<details>
<summary> 5️⃣ 로그인 </summary>
<div markdown="1">

**JWT토큰 발급(로그인)**<br>
POST token/issue

</div>
</details>
<br>

### 📌별첨
<details>
<summary> PostCollection </summary>
<div markdown="1">

**postCollection directory**<br>
1. (mini project) maket-comment.postman_collection.json<br>
2. (mini project) market-item.postman_collection.json<br>
3. (mini project) market-proposal.postman_collection.json<br>
4. (project1).postman_collection.json

</div>
</details>
