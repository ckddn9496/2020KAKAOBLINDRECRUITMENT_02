# 2020KAKAOBLINDRECRUITMENT_02

## 2020 KAKAO BLIND RECRUITMENT 괄호 수정
> *아직 문제가 공개되지 않이 문제의 설명이 부족할 수 있습니다.*

### 1. 문제설명

input으로 (와 )로 이루어진 String p가 들어온다. 이 괄호들은 갯수는 맞아 **균형잡힌 괄호**는 되지만, **올바른 괄호**는 아니다. 즉, (와 )가 등장하는 갯수는 같지만, 짝이 맞지 않는 것이다. 이를 올바르게 잡아주기 위한 방법은 문제에 이렇게 제시된다.

1. 올바른 괄호이면 그대로 return.
2. 올바르지 않다면 첫 균형잡힌 괄호를 찾아 u, 균형잡힌 괄호를 제외한 괄호들을 v로 나눈다.
3. u가 올바른 괄호면 v에 대해 1번부터 시작한다.
4. u가 올바른 괄호가 아니면 빈 문자열에 ( 를 추가한 후 v에 대해 1번부터 시작한 결과물을 붙인다. 그뒤에 ) 를 붙인다.
5. u는 첫번째와 마지막 괄호를 삭제후 ( 는 )로, ) 는 ( 로 괄호를 뒤집어 ( v ) 뒤에 붙인 후 return한다.

### 2. 풀이

설명에서 나온 방법대로 진행하여 문제를 해결하였다.

올바른 괄호인지 검사하는 메서드는 스택을 이용해 ( 가 들어오면 push, ( 가 들어오면 pop을 해주는 과정으로 pop을 하려 했는데 스택이 비어있거나, 모든 작업이 끝났는데 스택이 비어있지 않으면 올바른 괄호가 아닌 것으로 판단하게 해주었다.
```java
private boolean checkRight(String p) {
  Stack<Character> stack = new Stack<>();
  for (int i = 0; i < p.length(); i++) {
    char c = p.charAt(i);
    if (c == '(')
      stack.push(c);
    else { // ')'
      if (stack.isEmpty())
        return false;
      stack.pop();
    }
  }
  if (stack.isEmpty()) return true;
  else return false;
}
```


균형잡힌 괄호 u를 단순 count로 구현하였고 처음으로 두 괄호의 등장 횟수가 일치하는 부분의 index를 찾아 subString으로 u와 v를 만들어주었다.

```java
private int findBalancedBracketIdx(String p) {
  if (p.isEmpty()) return -1; // 빈 문자열이면 그대로
  int open = 0, close = 0;

  if (p.charAt(0) == '(') open++;
  else close++;
  for (int i = 1; i < p.length(); i++) {
    if (p.charAt(i) == '(') open++;
    else close++;
    if (open == close)
      return i+1;
  }

  return -1;
} 
    

```

괄호를 뒤집는 과정은 replace 함수를 세번 이용해서 만들었다
```java
private String manufactureU(String u) {
  String right = u.substring(1, u.length()-1);
  right = right.replace('(', '*').replace(')', '(').replace('*',')');
  return right;
}
```

