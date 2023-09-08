# 페페네 하우스
Contact App (수정 예정)

## :scroll: 목차
1. 프로젝트 소개
2. 개발 기간
3. 멤버 구성
4. 참고 자료
5. 팀 노션

## :telephone: 프로젝트 소개
(추가 예정)

## :alarm_clock: 개발 기간 
2023.09.04 ~ 2023.09.07

2023.09.11 발표 예정 

## :two_men_holding_hands: 멤버 구성 :couple: 
<img src="https://github.com/kt2790.png" width="50" height="50">\
[팀장 배근태](https://github.com/kt2790)


!
[팀원 김현준](https://github.com/boomshh)


!
[팀원 김지견](https://github.com/Odin5din/)


!
[팀원 이수진](https://github.com/sooj36)


## 기능 설명
- #### MainActivity
- - 어플리케이션을 실행했을 때의 메인화면
  - 툴바와 프래그먼트가 올라갈 프레임 레이아웃으로 구성
  - 초기에 앱을 실행해서 메인화면으로 들어오게 되면, 프레임 레이아웃에 HomeFragment가 로드
 
    
- #### HomeFragment
- - 초기화면에 해당하는 프래그먼트로써 뷰페이저와 탭 레이아웃으로 구성되어, 연락처 리스트 화면 & 마이 페이지 화면을 전환
 
- #### AddContactDialogFragment
-  - 연락처 목록을 업데이트 하기 위해 ContactListFragment를 인자로 받고 다이얼로그의 UI와 기능을 나타내기 위해 DialogFragment를 상속받는 클래스
   - 현재 프래그먼트에서 onCreateView, onViewCreated, onStart 함수로 생명주기를 관리
   

- #### ContactListFragment
- - 리사이클러 뷰 화면을 나타내는 프래그먼트
  - 리사이클러뷰는 연락처목록(id, 이름, 번호, email alarm, profile, favorite)의 값을 가진 어댑터가 있습니다. 어댑터의 아이템 클릭 시 ContactDetailFragment로 이동
  - ContactAdapter는 3가지 타입의 뷰홀더를 생성. 뷰홀더가 재활용 될 때 실행되는 메서드로는 슬라이드 될 때 전화걸기, 하트아이콘을 눌렀을 때 Boolean값이 변화하고 불리언 값에 따라 출력되는 이미지가 달라지는 기능
  - 길게 누를 시, 리사이클러뷰의 position을 삭제할 것인지 선택하는 다이얼로그 창 띄움.
  - 마지막으로 AddContactDialogFragment에서 생성된 리스트값을 받아와서 위치 시키는 메서드 추가
    
- #### MyPageFragement
- - 현재 사용자의 상세 정보를 보여주는 화면
  
- #### ContactDetailFragment
- - 마이페이지와 유사한 화면이고 연락처에 있는 사용자의 상세 정보를 보여주는 화면

## :paperclip: 참고 자료
→ 연락처 권한 가져오기


https://jdroid.tistory.com/24


https://bada744.tistory.com/140
   

## :notebook: 팀 노션
https://teamsparta.notion.site/11-b766659969874a1cb82b5e8b91b8b111
