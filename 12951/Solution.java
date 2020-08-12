class Solution {
    public String solution(String s) {
        String answer = "";
        boolean isFirstLetter = true;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                answer += ' ';
                isFirstLetter = true;
            }
            else if (isFirstLetter && c >= 'a' && c <= 'z'){
                answer += (char)(c - 'a' + 'A');
                isFirstLetter = false;
            }
            else if (!isFirstLetter && c >= 'A' && c <= 'Z') {
                answer += (char)(c - 'A' + 'a');
            }
            else {
                answer += c;
                isFirstLetter = false;
            }
        }
        return answer;
    }
}