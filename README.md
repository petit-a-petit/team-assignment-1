# [팀 과제] 노션에서 브로드 크럼스(Breadcrumbs) 만들기

## 목표
노션과 유사한 간단한 페이지 관리 API를 구현해주세요. 각 페이지는 제목, 컨텐츠, 그리고 서브 페이지를 가질 수 있습니다. 또한, 특정 페이지에 대한 브로드 크럼스(Breadcrumbs) 정보도 반환해야 합니다.
<br>

<img width="735" alt="image" src="https://github.com/petit-a-petit/team-assignment-1/assets/139187207/c2b6cd06-1b9f-4fc2-a8f7-36eb8ec8b3d9">

<br>
<br>
<br>

## 요구사항 ❗️
**페이지 정보 조회 API**: 특정 페이지의 정보를 조회할 수 있는 API를 구현하세요.
- 입력: 페이지 ID
- 출력: 페이지 제목, 컨텐츠, 서브 페이지 리스트, **브로드 크럼스 ( 페이지 1 > 페이지 3 > 페이지 5)**

- 특이사항
  - 깊이가 같은 서브페이지가 여러 개일 수 있다
  - 컨텐츠와 서브페이지는 별개이다. 컨텐츠 내에서 서브페이지 위치 고려  X
  - orm 쓰지 말고 **raw 쿼리만 사용**

<br>
<br>
<br>

## Team Rule 📕
### 1. Git Commit
- 항상 Git은 자세히! header 부터 body 까지
![image](https://github.com/petit-a-petit/team-assignment-1/assets/139187207/f7c77232-cab1-4b5c-a85a-96f12c703cf9)
<br>
<br>

### 2. Pull Request
- 테이블 구조
- 코드나 테이블 구조에 대한 설명. (”왜 이 구조가 최선인지?” 등)
- 결과 정보
  - 예시 
   ```json
   {
       "pageId" : 1,
       "title" : 1,
       "subPages" : [],
       "breadcrumbs" : ["A", "B", "C",] // 혹은 "breadcrumbs" : "A / B / C"
   }
   ```

<br>
<br>

### 3. PR Review
- Review는 마음껏 달아주세요!
  - 궁금한 점 🤔
  - 개선하면 좋을 것 같은 점 🚧
  - 인상적인 코드 😮

