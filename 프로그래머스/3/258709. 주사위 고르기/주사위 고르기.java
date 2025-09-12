import java.util.*;

class Solution {
    int [][]dice;
    ArrayList<int[]> pickList = new ArrayList<>();
    int n;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        int[]comb = new int[n / 2];
        
        //n개의 주사위 중 n/2를 고르는 모든 조합 생성
        comb(0, 0, comb);
        
        int maxWin = -1; //최대 승리 횟수  
        int[] answer = {}; //최대 승리 조합
        
        for(int[] aPick  : pickList){
            boolean[] picked = new boolean[n];
            for(int idx : aPick ){
                picked[idx] = true; //선택한 주사위를 마킹
            }
            
            //b조합만들기
            int[] bPick = new int[n / 2];
            int bIdx = 0;
            for(int i = 0; i < n; i++){
                 if (!picked[i]){
                     bPick[bIdx++] = i; //a에서 선택되지 않은 주사위를 저장한다.
                 } 
            }
            
            
             // A, B 각각의 모든 주사위 합의 경우(합:빈도)를 모두 구한다
            Map<Integer, Integer> aCase = roll(aPick, 0, 0, new HashMap<>());
            Map<Integer, Integer> bCase = roll(bPick, 0, 0, new HashMap<>());
            
            int win = 0;
            // A의 모든 합, B의 모든 합을 비교하여 A > B인 경우의 수의 합을 센다
            for (int aNum : aCase.keySet()) {
                int aCnt = aCase.get(aNum); // A합의 빈도
                for (int bNum : bCase.keySet()) {
                    int bCnt = bCase.get(bNum); // B합의 빈도
                    // A합 > B합이면 두 경우의 곱만큼 승리
                    if (aNum > bNum) {
                        win += aCnt * bCnt;
                    }
                }
            }

            // 승리 경우가 최대면 해당 조합 저장
            if (win > maxWin) {
                maxWin = win;
                answer = aPick.clone();
            }
        }

        Arrays.sort(answer); // 오름차순 정렬(문제 조건)
        // 인덱스는 0-base였으니 1-base로 변환
        for (int i = 0; i < answer.length; i++)
            answer[i] += 1;
        return answer;
    }
    
    void comb(int idx, int depth, int[]comb){
        if(depth == n / 2){
            pickList.add(comb.clone());
            return;
        }
        
        for(int i = idx; i < n; i++){
            comb[depth] = i; //i번주사위 선택
            comb(i + 1, depth + 1, comb);
        }
    }
    
    
    Map<Integer, Integer> roll(int[]pick, int depth, int sum, Map<Integer, Integer> map){
        if(depth == pick.length){
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return map;
        }
        
        int idx = pick[depth];
         for (int face : dice[idx]) {
            roll(pick, depth + 1, sum + face, map);
        }
        return map;
        
    }
}