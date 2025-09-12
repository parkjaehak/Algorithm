import java.util.*;

class Solution {
    public static int[] solution(int[][] edges) {
        int stick = 0;
        int donut = 0;
        int eight = 0;

        // 정점의 최대 값
        int maxVertex = 1;
   
        int[] send = new int[1000001];
        int[] receive = new int[1000001];

        // 생성된 정점을 찾기위해 edge 돌며 최대값 찾음
        int createdVertex = 1;
        for (int[] edge : edges) {
            if (maxVertex < edge[0]) {
                maxVertex = edge[0];
            }
            if (maxVertex < edge[1]) {
                maxVertex = edge[1];
            }

            receive[edge[1]]++;
            send[edge[0]]++;
        }

 
        for (int i = 1; i <= maxVertex; i++) {
            if (receive[i] == 0 && send[i] >= 2) {
                //그래스의 수의 합은 2 이상이다.
                createdVertex = i;
            }
        }

        // 정점 수만큼 그래프 생성
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= maxVertex; i++) {
            graph.add(new ArrayList<>());
        }

        // 단방향 그래프 잇기
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        // 이웃 그래프 판단하기
        for (int i = 0; i < graph.get(createdVertex).size(); i++) {
            int startVertex = graph.get(createdVertex).get(i);
                
            // 생성된 정점에서 나가는 각 간선을 서브그래프 시작점으로 사용
            int vertexSize = 1;
            int edgeSize = 0;

            // 시작 정점으로 부터 bfs를 위한 큐 생성
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startVertex);
            // 방문 처리를 위한 배열
            boolean[] visited = new boolean[maxVertex + 1];
            visited[startVertex] = true;
            // 사이클 여부를 판단하는 변수
            boolean isCycle = false;
            while (!queue.isEmpty()) {
                // 현재 정점 위치
                Integer current = queue.poll();

                // 간선 수를 더해줌
                edgeSize += graph.get(current).size();
                // 현재 정점에서 다음 정점으로 가기 위해 for문 돌기
                for (Integer nVertex : graph.get(current)) {
                    // 시작 정점을 다시 방문하는 경우 사이클이 있는 것으로 판단
                    if (nVertex == startVertex) {
                        isCycle = true;
                    }

                    // 방문하지 않은 정점이면 가고 정점 수를 카운트해줌
                    if (!visited[nVertex]) {
                        visited[nVertex] = true;
                        queue.add(nVertex);
                        vertexSize++;
                    }
                }
            }

            // 사이클이 있는 경우 도넛과 8자임
            if (isCycle) {
                // 간선과 정점의 차이가 1이면 8자임
                if (edgeSize - vertexSize == 1) {
                    eight++;
                } else {
                    donut++;
                }
            } else {
                stick++;
            }
        }

        return new int[]{createdVertex, donut, stick, eight};
    }
}