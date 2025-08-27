class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = {"aya", "ye", "woo", "ma"};
        
        for (String b : babbling) {
            String temp = b;
            // 각 발음을 기호로 치환
            for (int i = 0; i < words.length; i++) {
                temp = temp.replace(words[i], String.valueOf(i));
            }
            
            // 모두 숫자로만 이루어져 있는지 체크
            if (temp.matches("[0-3]+")) {
                // 같은 발음이 연속으로 두 번 쓰인 경우 제외
                if (!temp.contains("00") && !temp.contains("11") && 
                    !temp.contains("22") && !temp.contains("33")) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
