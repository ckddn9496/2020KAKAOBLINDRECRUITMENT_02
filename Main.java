import java.util.Stack;

/* KAKAO 2020 신입 개발자 블라인드 채용
 * 2번
 * */
public class Main {

	public static void main(String[] args) {
//		String p = "(()())()"; // return "(()())()"
//		String p = ")("; // return "()"
		String p = "()))((()"; // return "()(())()"
					
		System.out.println(new Solution().solution(p));
	}
}

class Solution {
    public String solution(String p) {
        String answer = "";
        answer = makeRightBracket(p);
        return answer;
    }

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

	private String makeRightBracket(String p) {
		if (p.isEmpty()) return p;
		
		int idx = findBalancedBracketIdx(p);
//		System.out.print(p);
//		System.out.println("   idx: " + idx);
		if (idx == -1) return p;
		
		String u = p.substring(0, idx);
		String v = p.substring(idx);
		
		String fixedU = u;
		if (checkRight(u)) {
			return u + makeRightBracket(v);
		} else {
			String newStr = "(" + makeRightBracket(v) + ")" + manufactureU(u);
			return newStr;
		}
	}

	private String manufactureU(String u) {
		String right = u.substring(1, u.length()-1);
		right = right.replace('(', '*').replace(')', '(').replace('*',')');
		return right;
	}

	private int findBalancedBracketIdx(String p) {
		if (p.isEmpty()) return -1; // 빈 문자열이면 그대로
		int open = 0, close = 0;
		
		if (p.charAt(0) == '(') open++;
		else close++;
//		System.out.println("open , close : " + open + ", " + close);
		for (int i = 1; i < p.length(); i++) {
			if (p.charAt(i) == '(') open++;
			else close++;
			if (open == close)
				return i+1;
		}
		
		return -1;
	}
    
}

