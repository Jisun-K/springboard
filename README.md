# 🧵 SpringBoard - 댓글 트리 게시판

Spring Boot 기반의 게시판 프로젝트입니다.  
댓글/대댓글 트리 구조와 비밀번호 기반 수정·삭제 기능 등 웹 게시판을 구현하였습니다.

---

## 📌 주요 기능

- 게시글 CRUD (Create / Read / Update / Delete)
- 댓글 / 대댓글 (트리 구조) 등록/삭제 (게시글 작성자만 삭제 가능 - 비밀번호 확인 필요)
- 게시글 목록 페이지네이션 (5개씩 보기)
- TailwindCSS 기반의 Thymeleaf + JavaScript UI 구성
- H2 콘솔 연동으로 DB 확인 가능

---

# ⚠️ 데이터 유지 관련

> 💡 **주의: 본 프로젝트는 H2 인메모리 데이터베이스(`jdbc:h2:mem:`)를 사용하므로, 서버를 재시작하면 모든 데이터가 초기화됩니다.**  
> 개발 및 데모 목적으로 적합하지만, 운영용 저장소로는 부적절합니다.

---

## 🚧 앞으로 추가하고 싶은 기능

- ~~댓글 / 대댓글 삭제 기능~~
- 게시글 조회수
- 게시글 검색 기능 (제목/작성자 기준)
- 사용자 인증 연동 (Spring Security or OAuth)

---

## 🧵 댓글 트리 구조 설명

- `Comment` 엔티티는 자기참조 관계 (`@ManyToOne` → parent)로 구성됨
- 서비스에서 모든 댓글을 불러온 뒤, 부모 ID 기준으로 `children` 필드를 채워 트리 구성
- View에서는 `th:each`로 댓글 및 대댓글을 트리 형태로 렌더링

---

## 🔐 비밀번호 인증 방식
-	댓글/게시글 등록 시 비밀번호 입력
-	수정·삭제 버튼 클릭 시 JavaScript prompt → 서버에 password 전달
-	서버에서 저장된 해시값과 비교 후 작업 실행

---

🧪 로컬 실행 방법
	1.	이 저장소를 클론합니다.
	2.	IDE에서 열고 Gradle 빌드 후 실행합니다.
	3.	브라우저에서 http://localhost:8080 접속
	4.	H2 콘솔: http://localhost:8080/h2-console
	•	JDBC URL: jdbc:h2:mem:springboard-db

---

## 📸 화면 미리보기

> ✨ 이미지 캡처 넣기 (게시글 목록 / 상세 / 댓글 UI)

![image](https://github.com/user-attachments/assets/17552a81-b353-468b-9ed3-b9788f6b271c)
![image](https://github.com/user-attachments/assets/01beb0c1-e69e-48ff-8a22-4f9aaa0fa417)
![image](https://github.com/user-attachments/assets/a0f78698-7601-4c1f-9e6b-cda813819177)




<!-- 
✨ 기능추가
🐛 버그수정
🎨 UI변경
♻️ 리팩토링
🔒 인증/보안 관련
💄 UI스타일링

-->
