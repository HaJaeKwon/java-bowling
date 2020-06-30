# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 볼링 점수판이란?

## 프로그래밍 요구사항
* 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다.
* indent depth는 2를 넘지 않는다.
* method 길이는 15라인을 넘지 않는다.
* 자바 코드 컨벤션을 지킨다.
* else 예약어를 쓰지 않는다.
* 자바 8의 스트림과 람다를 적용해 프로그래밍한다.
* 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
* 규칙 2: else 예약어를 쓰지 않는다.
* 규칙 3: 모든 원시값과 문자열을 포장한다.
* 규칙 4: 한 줄에 점을 하나만 찍는다.
* 규칙 5: 줄여쓰지 않는다(축약 금지).
* 규칙 6: 모든 엔티티를 작게 유지한다.
* 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
* 규칙 8: 일급 콜렉션을 쓴다.
* 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.
* 객체 단위를 가장 작은 단위까지 극단적으로 분리하는 시도를 해본다.
* 1 ~ 9 프레임을 NormalFrame, 10 프레임을 FinalFrame과 같은 구조로 구현한 후 Frame을 추가해 중복을 제거해 본다.
* 다음 Frame을 현재 Frame 외부에서 생성하기 보다 현재 Frame에서 다음 Frame을 생성하는 방식으로 구현해 보고, 어느 구현이 더 좋은지 검토해 본다.

## 요구사항
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

* TDD
- Pin
  - 각 투구마다 넘긴 핀 수
  - [X] 넘김 핀 수를 입력받아 생성
  - [X] 넘긴 핀 수는 10개를 넘을 수 없다
- Pitch
  - 각 프레임 별 점수, 샷 이력
  - [X] 넘긴 핀 수에 따라 남은 핀수가 감소한다
  - [X] 최대 횟수 이상 투구하면 예외 발생
  - [X] 남은 핀 수보다 많이 넘기면 예외 발생
  - [X] 넘긴 핀 수에 따라 적절한 Shot 이력이 추가된다
    - [X] 1번 던져서 넘긴 핀이 10개면 Strike 이력 추가
    - [X] 1번 던져서 넘긴 핀이 10개 미만 Miss 이력 추가
    - [X] 2번 던져서 넘긴 핀이 10개면 Miss,Spare 이력 추가
    - [X] 2번 던져서 넘긴 핀이 10개 미만 Miss,Miss 이력 추가
  - [X] 스코어 추가 시 마다 모든 공을 처리한건지 여부를 반환
- State
  - NotFinish, Finish, Bonus
  - [X] 각 상태별로 더 던질 수 있는지 유무를 반환한다
- Shot
  - Strike, Spare, Miss, Gutter
  - [ ] 입력받은 Score 객체에 따라 Shot 타입이 결정된다
- ShotHistory
  - List<Shot> 일급컬렉션
- Frame
  - 다음 Frame의 pointer를 가지고 있다
  - [ ] 지금까지 진행된 프레임까지 Shot 이력을 반환
- NormalFrame
  - 1~9 프레임
  - [ ] 넘긴 핀 수에 따라 적절한 State 가 반환된다
    - [ ] 1번 던져서 넘긴 핀이 10개면 Finish 반환
    - [ ] 1번 던져서 넘긴 핀이 10개 미만 NotFinish 반환
    - [ ] 2번 던지면 Finish 반환
- FinalFrame
  - 10 프레임
  - [ ] 넘긴 핀 수에 따라 적절한 State 가 반환된다
    - [ ] 1번 던져서 넘긴 핀이 10개면 NotFinish 반환
    - [ ] 1번 던져서 넘긴 핀이 10개 미만 NotFinish 반환
    - [ ] 2번 던져서 넘긴 핀이 10개면 NotFinish 반환
    - [ ] 2번 던져서 넘긴 핀이 10개 미만 Finish 반환
    - [ ] 1번 던져서 넘긴 핀이 10개이면, 보너스 투구 후에 Finish 반환
    - [ ] 2번 던져서 넘긴 핀이 10개이면, 보너스 투구 후에 Finish 반환
- Player
    - [X] 이름의 길이를 입력 받아 생성
    - [X] 이름의 길이가 3자가 아니면 예외 발생
