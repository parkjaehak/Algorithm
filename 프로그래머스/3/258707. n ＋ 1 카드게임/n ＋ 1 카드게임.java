import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int idx = n / 3;
        Set<Integer> myCard = new HashSet<>();
        Set<Integer> newCard = new HashSet<>();
        int round = 0;
        int target = n + 1;
        
        // 처음 손패 채우기
        for (int i = 0; i < idx; i++) {
            myCard.add(cards[i]);
        }
        
        while (true) {
            round++;
            // 카드뭉치 다 소진하면 종료
            if (idx + 1 >= n) break;
            
            // 라운드마다 카드 두 장 추가
            newCard.add(cards[idx++]);
            newCard.add(cards[idx++]);

            boolean flag = false;
            // 1. 코인 안 쓰는 경우: 손패에서만 n+1 만들기
            for (int num : myCard) {
                if (myCard.contains(target - num) && num != target - num) {
                    myCard.remove(num);
                    myCard.remove(target - num);
                    flag = true;
                    break;
                }
            }
            if (flag) continue;
            
            // 2. 코인 1개: 손패와 newCard 조합
            if (coin >= 1) {
                for (int num : myCard) {
                    if (newCard.contains(target - num)) {
                        myCard.remove(num);
                        newCard.remove(target - num);
                        coin--;
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
            }
            
            // 3. 코인 2개: newCard에서만 n+1 만들기
            if (coin >= 2) {
                for (int num : newCard) {
                    if (newCard.contains(target - num) && num != target - num) {
                        newCard.remove(num);
                        newCard.remove(target - num);
                        coin -= 2;
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
            }
            
            // 아무것도 못하면 종료
            break;
        }
        return round;
    }
}
