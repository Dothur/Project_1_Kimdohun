# _중고 제품 거래 플랫폼 ♻️ 멋사마켓 ♻️_

## **_💁🏻‍ 미니 프로젝트 소개_**

> 💡 여러분들이 많이 사용하고 있는 🥕당근마켓, 중고나라 등을 착안하여 여러분들만의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.  
> 사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여 수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드 서비스입니다.

<br>
<details>
<summary>📚 멋사마켓 ERD</summary>
<div markdown="1">       

![erd1](https://github.com/likelion-backend-5th/MiniProject_Basic_KimDohun/assets/80811887/e2b00234-8bd2-43fa-a7b2-9f14219afb96)

</div>
</details>
<details>
<summary>📌 요구사항</summary>
<div markdown="1">       

<aside>
<h2>💡 DAY 1️⃣ 7/26 인증 만들기</h2>  

💡 본래 만들었던 서비스에 **사용자 인증**을 첨부합니다.

1. 사용자는 **회원가입**을 진행할 수 있다.
    - 회원가입에 필요한 정보는 아이디와 비밀번호가 필수이다.
    - 부수적으로 전화번호, 이메일, 주소 정보를 기입할 수 있다.
    - 이에 필요한 사용자 Entity는 직접 작성하도록 한다.


2. **아이디 비밀번호**를 통해 로그인을 할 수 있어야 한다.


3. 아이디 비밀번호를 통해 로그인에 성공하면, **JWT가 발급**된다. 
   이 JWT를 소유하고 있을 경우 **인증**이 필요한 서비스에 접근이 가능해 진다.
    - 인증이 필요한 서비스는 추후(미션 후반부) 정의한다.


4. JWT를 받은 서비스는 **사용자가 누구인지** 사용자 **Entity를 기준**으로 정확하게 판단할 수 있어야 한다.
</aside>

<aside>
<h2>💡 DAY 2️⃣ 7/27 관계 설정하기</h2>  

💡 이전에 만든 물품, 댓글들에 대한 **데이터베이스 테이블**을, **사용자 정보를 포함**하여 고도화 합니다.

1. 아이디와 비밀번호를 필요로 했던 테이블들은 실제 사용자 Record에 대응되도록 ERD를 수정하자.
    - ERD 수정과 함께 해당 정보를 적당히 표현할 수 있도록 Entity를 재작성하자.
    - 그리고 ORM의 기능을 충실히 사용할 수 있도록 어노테이션을 활용한다.


2. 다른 작성한 Entity도 변경을 진행한다.
    - 서로 참조하고 있는 테이블 관계가 있다면, 해당 사항이 표현될 수 있도록 Entity를 재작성한다.
</aside>

<aside>
<h2>💡 DAY 3️⃣ 7/28 기능 접근 설정하기</h2>  

💡 기능들의 사용 가능 여부가 **사용자의 인증 상태**에 따라 **변동**하도록 제작합니다.

1. 본래 “누구든지 열람할 수 있다”의 기능 목록은 사용자가 **인증하지 않은 상태**에서 사용할 수 있도록 한다.
    - 등록된 물품 정보는 누구든지 열람할 수 있다.
    - 등록된 댓글은 누구든지 열람할 수 있다.
    - 기타 기능들


2. 작성자와 비밀번호를 포함하는 데이터는 **인증된 사용자만 사용**할 수 있도록 한다.
    - 이때 해당하는 기능에 포함되는 아이디 비밀번호 정보는, 1일차에 새로 작성한 사용자 Entity와의 관계로 대체한다.
        - 물품 정보 등록 → 물품 정보와 사용자 관계 설정
        - 댓글 등록 → 댓글과 사용자 관계 설정
        - 기타 등등
    - 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.
    - 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.
    - 등록된 물품에 대하여 구매 제안을 등록할 수 있다.
    - 기타 기능들
</aside>

</div>
</details>

<br>

---  
<br>

## ⏱️ _개발 기간_

- `개발 날짜: 2023.07.26 ~ 2023.08.02`

## 🛠️ _개발 환경_

- _IDE: IntelliJ IDEA Ultimate_
- _Language : Java_
- _Tech ( dependency )_
    - _Spring Web_
    - _Spring Boot DevTools_
    - _Spring Security_
    - _jjwt_
    - _Spring Data JPA_
    - _Lombok_
    - _SQLite_

## 📮 _API Documentations_

- [_**View API**_](https://documenter.getpostman.com/view/28054685/2s93zE4LhJ)

<br>

---  
<br>

## 🔄 _History_

### 📅 _23.07.26_

#### 1. _**프로젝트 세팅하기**_

- `.yaml` 파일 설정
    - jwt secret

- `build.gradle` Dependency 추가
    - Spring Security
    - Thymeleaf

### 📅 _23.07.27_

#### 1. **_회원가입, 로그인, 로그아웃 구현_**

- Spring security setting
- UserEntity email, phone, address 도 회원가입 시 기입 가능 ( db 에 저장 됨 )
- username 이 중복인지 아닌지 판별 -> 중복일때 사용자 생성 불가
- formLogin, logout 통해 구현
- `/users/login : .permitAll()` 로 모든 사용자 접근가능
- `/users/register : .anonymous()` 로 인증 되지 않은 모든 사용자 (로그인되지 않은) 접근 가능
- `/users/my-profile & /logout : .authenticated()` 로 허가된 사용자만 접근 가능

#### 2. **_`build.gradle` Dependency 추가_**

- add jjwt

#### 3. **_JWT Token 인증(발급) 구현 추가_**

- TokenController
    - JWT 발급 `/token/issue`
    - /token/secured : Token success test 인증이 필요한 url
- Jwt RequestDto, TokenDto 추가
- JwtTokenUtils 추가
    - validate method : Jwt 유효한지 판단
    - parseClaims : Jwt 받아서 해석 후 사용자 정보 회수
    - generateToken : 사용자 정보 바탕으로 Jwt 생성
- JwtTokenFilter 추가
    - JWT가 포함되어 있으면 포함되어 있는 헤더를 요청
    - JWT 를 회수하여 JWT 가 정상적인 JWT 인지 판단
- JwtTokenFilter 등록, requestMatchers 수정

### 📅 _23.08.02_

#### 1. **_Day2 - Day3 미션 : 관계, 기능접근 설정_**

- Endpoint 마다 테스트 완료
- WebSecurityConfig 에서 url 별 접근 지정
- Entity 끼리 ManyToOne, OneToMany 로 Join
  - 전체적으로 요구사항에 따라 관계 설정
- dto 도 변동에 따라 수정
- Service 코드들 전체적으로 이전에 writer & password 로 인증하였던 코드
  - 발급한 토큰으로 인증하도록 전체 수정 (요구사항에 따라 단순 조회 등은 인증없이 조회가능)

#### 2. **_Postman Collection 업로드 & Readme update_**

<br>

---  
<br>