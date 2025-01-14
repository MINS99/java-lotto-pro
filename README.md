# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


## 🚀 1단계 - 학습 테스트 실습
## String 클래스에 대한 학습 테스트
### 요구사항 1
- [x] "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
- [x] "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
> 힌트  
배열로 반환하는 값의 경우 assertj의 contains()를 활용해 반환 값이 맞는지 검증한다.  
배열로 반환하는 값의 경우 assertj의 containsExactly()를 활용해 반환 값이 맞는지 검증한다.

### 요구사항 2
- [x] "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.

### 요구사항 3
- [x] "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
- [x] String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
- [x] JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.

## Set Collection에 대한 학습 테스트
+ 다음과 같은 Set 데이터가 주어졌을 때 요구사항을 만족해야 한다.
```java
public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }
    
    // Test Case 구현
}
```

### 요구사항 1
- [x] Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.

### 요구사항 2
- Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현하려한다.
- 구현하고 보니 다음과 같이 중복 코드가 계속해서 발생한다.
- [x] JUnit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.
```java
    @Test
    void contains() {
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(2)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
    }
```

### 요구사항 3
- 요구사항 2는 contains 메소드 결과 값이 true인 경우만 테스트 가능하다. 
- [x] 입력 값에 따라 결과 값이 다른 경우에 대한 테스트도 가능하도록 구현한다.
+ 예를 들어 1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환되는 테스트를 하나의 Test Case로 구현한다.

---

## 🚀 2단계 - 문자열 덧셈 계산기
## 문자열 덧셈 계산기를 통한 TDD/리팩토링 실습
### 기능 요구사항
+ [x] 빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.(예 : “” => 0, null => 0)
+ [x] 숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.(예 : “1”)
+ [x] 숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.(예 : “1,2”)
+ [x] 구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다. (예 : “1,2:3” => 6)
+ [x] “//”와 “\n” 문자 사이에 커스텀 구분자를 지정할 수 있다. (예 : “//;\n1;2;3” => 6)
+ [x] 음수를 전달할 경우 RuntimeException 예외가 발생해야 한다. (예 : “-1,2,3”)
+ [x] 숫자 이외의 값을 전달할 경우 RuntimeException 예외가 발생해야 한다. (예 : “a,2,3”)

### 프로그래밍 요구사항
+ indent(들여쓰기) depth를 2단계에서 1단계로 줄여라.
  + depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
+ 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
  + method가 한 가지 일만 하도록 최대한 작게 만들어라.
+ else를 사용하지 마라.

---  

## 🚀 3단계 - 로또(자동)
### 기능 요구사항
- [x] 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
  - [x] 로또 번호는 랜덤으로 생성된다
  - [x] 로또 번호는 1부터 45사이의 정수이다
  - [x] 로또 한 장당 번호는 6개이다
  - [x] 6개의 번호는 중복이 없다
- [x] 로또 1장의 가격은 1000원이다.
  - [x] 구입 금액은 1000원 단위이다
  - [x] 최소 구매 금액은 1000원이다.
  - [x] 999이하 단위 금액은 버림 처리한다
- [x] 당첨 번호를 입력받는다
  - [x] 당첨 번호는 수동 입력을 통해 생성된다
  - [x] 당첨 번호는 1부터 45사이의 정수이다
  - [x] 당첨 번호는 6개이다 
  - [x] 당첨 번호는 중복이 없다
- [x] 로또 결과를 출력한다
  - [x] 3개일치 ~ 6개일치한 로또 결과를 계산한다
  - [x] (당첨 금액 / 구입 금액) 을 계산한 결과인 수익률을 소수점 둘째 자리까지 계산한다
    - [x] 수익률이 1 미만인 경우 '결과적으로 손해라는 의미임' 을 출력한다
    - [x] 수익률이 1 이상인 경우 '결과적으로 이익이라는 의미임' 을 출력한다
```text  
구입금액을 입력해 주세요.  
14000  
14개를 구매했습니다.  
[8, 21, 23, 41, 42, 43]  
[3, 5, 11, 16, 32, 38]  
[7, 11, 16, 35, 36, 44]  
[1, 8, 11, 31, 41, 42]  
[13, 14, 16, 38, 42, 45]  
[7, 11, 30, 40, 42, 43]  
[2, 13, 22, 32, 38, 45]  
[23, 25, 33, 36, 39, 41]  
[1, 3, 5, 14, 22, 45]  
[5, 9, 38, 41, 43, 44]  
[2, 8, 9, 18, 19, 21]  
[13, 14, 18, 21, 23, 35]  
[17, 21, 29, 37, 42, 45]  
[3, 8, 27, 30, 35, 44]  
  
지난 주 당첨 번호를 입력해 주세요.  
1, 2, 3, 4, 5, 6  
  
당첨 통계
---------  
3개 일치 (5000원)- 1개  
4개 일치 (50000원)- 0개  
5개 일치 (1500000원)- 0개  
6개 일치 (2000000000원)- 0개  
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)  
```  

### 힌트
+ 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
+ Collections.sort() 메소드를 활용해 정렬 가능하다.
+ ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

### 프로그래밍 요구사항
+ 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
  + 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
  + UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
+ indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
  + 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
  + 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
+ 함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다.
  + 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
+ 모든 로직에 단위 테스트를 구현한다. 단, UI(System.out, System.in) 로직은 제외
  + 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
  + UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
+ 자바 코드 컨벤션을 지키면서 프로그래밍한다.
+ else 예약어를 쓰지 않는다.
  + 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
  + else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.

### 기능 목록 및 commit 로그 요구사항
+ 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
+ git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

---

## 🚀 4단계 - 로또(수동)
### 기능 요구사항
- [x] 2등을 위해 추가 번호를 하나 더 추첨한다.
  - [x] 보너스 번호는 1부터 45 사이의 정수이다.
  - [x] 보너스 번호는 당첨 번호와 중복되지 않는다.
- [x] 당첨 통계에 2등도 추가한다.
```java
[... 생략 ...]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

### 힌트
+ 일급 콜렉션을 쓴다.
  + 6개의 숫자 값을 가지는 java collection을 감싸는 객체를 추가해 구현해 본다.
+ 하드 코딩을 하지 않기 위해 상수 값을 사용하면 많은 상수 값이 발생한다. 자바의 enum을 활용해 상수 값을 제거한다. 즉, enum을 활용해 일치하는 수를 로또 등수로 변경해 본다.

### 프로그래밍 요구사항
- java enum을 적용해 프로그래밍을 구현한다.
- 규칙 8: 일급 콜렉션을 쓴다.

---

## 🚀 5단계 - 로또(수동)
### 기능 요구사항
- [x] 입력한 금액에 따라 수동 구매와 관련된 값을 입력 받는다
  - [x] 수동 생성 숫자 장수를 입력 받는다
    - [x] 수동 구매 장수는 최소 0장 ~ 최대 (구입금액 / 로또 가격)장 이다
  - [x] 수동 생성 번호를 입력 받는다.
- [x] 구매 결과를 출력한다
  - [x] 수동으로 구매한 로또 번호를 출력한다
  - [x] 자동으로 구매된 로또 번호를 출력한다
```java
구입금액을 입력해 주세요.
14000

수동으로 구매할 로또 수를 입력해 주세요.
3

수동으로 구매할 번호를 입력해 주세요.
8, 21, 23, 41, 42, 43
3, 5, 11, 16, 32, 38
7, 11, 16, 35, 36, 44

수동으로 3장, 자동으로 11개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

[... 생략 ...]
```

### 힌트
+ 규칙 3: 모든 원시값과 문자열을 포장한다.
  + 로또 숫자 하나는 int 타입이다. 이 숫자 하나를 추상화한 LottoNo 객체를 추가해 구현한다.
+ 예외 처리를 통해 에러가 발생하지 않도록 한다.
  + 사용자가 잘못된 값을 입력했을 때 java exception으로 에러 처리를 한다.
  + java8에 추가된 Optional을 적용해 NullPointerException이 발생하지 않도록 한다.

### 프로그래밍 요구사항
+ 규칙 3: 모든 원시값과 문자열을 포장한다.
+ 규칙 5: 줄여쓰지 않는다(축약 금지).
+ 예외 처리를 통해 에러가 발생하지 않도록 한다.
