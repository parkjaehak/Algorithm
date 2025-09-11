import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
   
        int n = friends.length;
        Map<String, Integer> idx = new HashMap<>();
        for(int i = 0; i < n; i++){
            idx.put(friends[i], i); //이름을 인덱스로 매핑한다
        }
        
        int[][] gift = new int[n][n]; // a가 b에게 준 횟수
        int[]given = new int[n]; //준 갯수
        int[]received = new int[n]; //받은 개수
        
        //gifts를 순회하며 배열을 채운다
        for(String g : gifts){
            String[] parts = g.split(" ");
            int giver = idx.get(parts[0]);
            int receiver = idx.get(parts[1]);
            
            gift[giver][receiver]++;
            given[giver]++;
            received[receiver]++;
        }
        
        //선물 지수 계산
        int[]giftIdx = new int[n];
        for(int i = 0; i < n; i++){
            giftIdx[i] = given[i] - received[i]; //준 선물 - 받은 선물
        }
        
        //다음 달 받을 선물 수 계산
        int [] nextGift = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                //i와 j를 비교한다.
                int iToj = gift[i][j];
                int jToi = gift[j][i];
                
                if(iToj > jToi){
                    //i가 더 많이 줬다면 i가 j로부터 선물을 하나 받는다.
                    nextGift[i]++;
                }else if(jToi > iToj){
                     nextGift[j]++;
                }else{
                    //선물지수를 비교한다.
                    if(giftIdx[i] > giftIdx[j]){
                          nextGift[i]++;
                    }else if(giftIdx[i] < giftIdx[j]){
                        nextGift[j]++;
                    }
                }
            }
        }
        
        
        int answer = 0;
        for(int a : nextGift){
            answer = Math.max(answer, a);
        }
        
        return answer;
        
        
    }
}