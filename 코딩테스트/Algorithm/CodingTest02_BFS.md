# BFS

### 개념

- 그래프 탐색 : 어떤것들이 연속해서 이어질때, 모두 확인하는 방법

  - Graph : Vertex(어떤것) + Edge(이어지는것)

  

### 종류

- BFS : Breadth-first search(너비 우선 탐색)

  - 자식먼저 탐색함
  - 하단 그림 기준: 1 → 2 → 5 → 3 → 4 → 6
  - Queue와 맞음

  

- DFS: Depth-first search(깊이 우선 탐색)

  - 자식의 자식을 먼저 탐색함
  - 하단 그림 기준: 1 → 2 → 3 → 4 → 6 → 5
  - Stack과 맞음

<img src="./imgs/bfs_dfs.png">

### 아이디어

- 시작점에 연결된 Vertex 찾기
- 찾은 Vertex를 Queue에 저장
- Queue의 가장 먼저것을 뽑아서 반복

### 시간 복잡도

- 개념: 알고리즘이 얼마나 오래걸리는지
- Big-O 표기법 : 가장 최대의 시간을 뽑는 것
- BFS : O(V+E)

### 자료구조

- 검색할 그래프
- 방문여부 확인(재방문 금지)
- Queue: BFS 실행

### 예제

- link : https://www.acmicpc.net/problem/1926

![baekjoon_1926](.\imgs\baekjoon_1926.png)