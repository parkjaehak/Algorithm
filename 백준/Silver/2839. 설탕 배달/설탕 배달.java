import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        //3과 5크기만 담을 수 있는 상자가 있고 n이 주어질때 n개의 물건을 3과 5크기의 상자에 담을때 
        //상자의 수가 최소가 되도록 하고 싶고 그때 필요한 상자의 수를 출력하는 프로그램을 작성하세요.
        //n을 3과 5크기에 모두 담을 수 없다면 -1을 출력하세요.

        Scanner sc = new Scanner((System.in));

        int n = sc.nextInt();

        //5크기의 상자를 먼저 담는 그리디 알고리즘 이용
        int minBoxes = -1;
        int box5 = n / 5;

        for(int i = box5; i >= 0; i--){
            //5상자의 최대가능개수부터 차례로 확인한다.
            int remaining = n - (i * 5);

            if(remaining % 3 == 0){
                //남은 개수가 3의 배수인지 확인한다.
                int box3 = remaining / 3;
                minBoxes = i + box3;
                break;
            }

        }

        System.out.println(minBoxes);

    }
}