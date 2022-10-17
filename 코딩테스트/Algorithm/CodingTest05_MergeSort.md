# 병합정렬

- 출처(https://st-lab.tistory.com/233)
- Tim sort 정렬을 배우기전 미리 공부해야 된다고 함
  - 추가로 이진 삽입 정렬 역시 공부해야 한다고 함
- 장점
  - 항상 두개의 부분리스트로 쪼개 들어감으로 O(NlogN)으로 유지
  - 안정정렬임
  - 간단한게 구현하면서 안정정렬을 하고자 할때 좋은 선택지가 될 수 있음

- 단점
  - 정렬과정에서 추가적 보조 배열 공간사용 => 메모리 사용량이 많음
  - 보조배열에서 원본배열로 복사하는 과정은 매우 많은 시간을 소비
  - 데이터가 많을경우 상대적으로 시간이 많이 소요됨


### 개념

- 문제를 분할하고, 분할한 문제를 정복하여 합치는 과정

- 분할정복(Divide and Conquer) 알고리즘 기반으로 정렬
  - 정렬해야 할 리스트가 주어지면 해당 리스트 분할을 반복
  - 최대한 작게 쪼개진 시점의 부분리스트에서 인접한 원소들끼리 비교하여 정렬하는 방식
- 데이터를 비교하면서 찾기 때문에 **비교정렬**
- 정렬의 대상이 되는 데이터 외 추가 공간을 필요로 하기 때문에 제자리정렬(in-place sort)이 아님
  - 제자리 정렬로 구현가능 하지만 성능을 일부 포기해야 함
- 최대한 작게 문제를 쪼개어 앞의 부분 리스트부터 차례대로 합쳐나가기 때문에 안정정렬(Stable Sort)임



### 방법

1. 주어진 리스트를 절반으로 분할(divide)
2. 분할된 부분리스트의 길이가 1이 아니라면 1번 반복
3. 인접한 부분리스트끼리 정렬하여 합침(conqure)

- 도식화



| 8    | 2    | 6    | 4    | 7    | 3    | 9    | 5    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |

- 1차분할

| 8    | 2    | 6    | 4    |
| ---- | ---- | ---- | ---- |

| 7    | 3    | 9    | 5    |
| ---- | ---- | ---- | ---- |

- 2차분할

| 8    | 2    |
| ---- | ---- |

| 6    | 4    |
| ---- | ---- |

| 7    | 3    |
| ---- | ---- |

| 9    | 5    |
| ---- | ---- |

- 3차 분할

8 // 2 // 6 // 4 // 7 // 3 // 9 // 5 낱개로 나줘짐

**※ 각 정복 시작되면서 정렬이 시작됨**

- 1차 정복

| 2    | 8    |
| ---- | ---- |

| 4    | 6    |
| ---- | ---- |

| 3    | 7    |
| ---- | ---- |

| 5    | 9    |
| ---- | ---- |

- 2차 정복

| 2    | 4    | 6    | 8    |
| ---- | ---- | ---- | ---- |

| 3    | 5    | 7    | 9    |
| ---- | ---- | ---- | ---- |

- 3차 정복

| 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |

- 정렬방법?
  - 각 부분 리스트의 첫번째 원소부터 순차적으로 비교해주면 됨
  - 예시
    - {2, 4, 6, 8} {3, 5, 7, 8}이 합쳐질 때
    - 2와 3비교
    - {2, , , , , , , }
    - 4와 3 비교
    - {2, 3, , , , , , }
    - 4와 5비교
    - {2, 3, 4, , , , , }
    - 중략
    - {2, 3, 4, 5, 6, 7, 8, 9}
- 코드구현

```java
/**
 * 부분리스트는 a배열의 left ~ right 까지이다. 
 * 
 * @param a		정렬할 배열
 * @param left	배열의 시작점
 * @param mid	배열의 중간점
 * @param right	배열의 끝 점
 */
private static void merge(int[] a, int left, int mid, int right) {
	int l = left;		// 왼쪽 부분리스트 시작점
	int r = mid + 1;	// 오른쪽 부분리스트의 시작점 
	int idx = left;		// 채워넣을 배열의 인덱스
	
	
	while(l <= mid && r <= right) {
		/*
		 *  왼쪽 부분리스트 l번째 원소가 오른쪽 부분리스트 r번째 원소보다 작거나 같을 경우
		 *  왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1 증가시킨다.
		 */
		if(a[l] <= a[r]) {
			sorted[idx] = a[l];
			idx++;
			l++;
		}
		/*
		 *  오른쪽 부분리스트 r번째 원소가 왼쪽 부분리스트 l번째 원소보다 작거나 같을 경우
		 *  오른쪽의 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가시킨다.
		 */
		else {
			sorted[idx] = a[r];
			idx++;
			r++;
		}
	}
		
	/*
	 * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (l > mid)
	 * = 오른쪽 부분리스트 원소가 아직 남아있을 경우
	 * 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
	 */
	if(l > mid) {
		while(r <= right) {
			sorted[idx] = a[r];
			idx++;
			r++;
		}
	}
		
	/*
	 * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (r > right)
	 * = 왼쪽 부분리스트 원소가 아직 남아있을 경우
	 * 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
	 */
	else {
		while(l <= mid) {
			sorted[idx] = a[l];
			idx++;
			l++;
		}
	}
	
	/*
	 * 정렬된 새 배열을 기존의 배열에 복사하여 옮겨준다.
	 */
	for(int i = left; i <= right; i++) {
		a[i] = sorted[i];
	}
}
```

- Top-Down 형식

```java
public class Merge_Sort {

	private static int[] sorted;		// 합치는 과정에서 정렬하여 원소를 담을 임시배열
	
	public static void merge_sort(int[] a) {
		
		sorted = new int[a.length];
		merge_sort(a, 0, a.length - 1);
		sorted = null;
	}
	
	// Top-Down 방식 구현
	private static void merge_sort(int[] a, int left, int right) {
		
		/*
		 *  left==right 즉, 부분리스트가 1개의 원소만 갖고있는경우 
		 *  더이상 쪼갤 수 없으므로 return한다.
		 */
		if(left == right) return;
		
		int mid = (left + right) / 2;	// 절반 위치 
		
		merge_sort(a, left, mid);		// 절반 중 왼쪽 부분리스트(left ~ mid)
		merge_sort(a, mid + 1, right);	// 절반 중 오른쪽 부분리스트(mid+1 ~ right)
		
		merge(a, left, mid, right);		// 병합작업
		
	}
	
	/**
	 * 합칠 부분리스트는 a배열의 left ~ right 까지이다. 
	 * 
	 * @param a		정렬할 배열
	 * @param left	배열의 시작점
	 * @param mid	배열의 중간점
	 * @param right	배열의 끝 점
	 */
	private static void merge(int[] a, int left, int mid, int right) {
		int l = left;		// 왼쪽 부분리스트 시작점
		int r = mid + 1;	// 오른쪽 부분리스트의 시작점 
		int idx = left;		// 채워넣을 배열의 인덱스
		
		
		while(l <= mid && r <= right) {
			/*
			 *  왼쪽 부분리스트 l번째 원소가 오른쪽 부분리스트 r번째 원소보다 작거나 같을 경우
			 *  왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1 증가시킨다.
			 */
			if(a[l] <= a[r]) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
			/*
			 *  오른쪽 부분리스트 r번째 원소가 왼쪽 부분리스트 l번째 원소보다 작거나 같을 경우
			 *  오른쪽의 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가시킨다.
			 */
			else {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		}
		
		/*
		 * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (l > mid)
		 * = 오른쪽 부분리스트 원소가 아직 남아있을 경우
		 * 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
		 */
		if(l > mid) {
			while(r <= right) {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		}
		
		/*
		 * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (r > right)
		 * = 왼쪽 부분리스트 원소가 아직 남아있을 경우
		 * 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
		 */
		else {
			while(l <= mid) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
		}
		
		/*
		 * 정렬된 새 배열을 기존의 배열에 복사하여 옮겨준다.
		 */
		for(int i = left; i <= right; i++) {
			a[i] = sorted[i];
		}
	}
}
```

- Bottom-Up 형식

```java
public class Merge_Sort {

	private static int[] sorted;		// 합치는 과정에서 정렬하여 원소를 담을 임시배열
	
	public static void merge_sort(int[] a) {
		
		sorted = new int[a.length];
		merge_sort(a, 0, a.length - 1);
		sorted = null;
	}
	
	// Bottom-Up 방식 구현
	private static void merge_sort(int[] a, int left, int right) {
		
		/*
		 * 1 - 2 - 4 - 8 - ... 식으로 1부터 서브리스트를 나누는 기준을 두 배씩 늘린다.
		 */
		for(int size = 1; size <= right; size += size) {
			
			/*
			 * 두 부분리스트을 순서대로 병합해준다.
			 * 예로들어 현재 부분리스트의 크기가 1(size=1)일 때
			 * 왼쪽 부분리스트(low ~ mid)와 오른쪽 부분리스트(mid + 1 ~ high)를 생각하면
			 * 왼쪽 부분리스트는 low = mid = 0 이고,
			 * 오른쪽 부분리스트는 mid + 1부터 low + (2 * size) - 1 = 1 이 된다.
			 *  
			 * 이 때 high가 배열의 인덱스를 넘어갈 수 있으므로 right와 둘 중 작은 값이
			 * 병합되도록 해야한다. 
			 */
			for(int l = 0; l <= right - size; l += (2 * size)) {
				int low = l;
				int mid = l + size - 1;
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, low, mid, high);		// 병합작업
			}
		}
		
		
		
	}
	
	/**
	 * 합칠 부분리스트는 a배열의 left ~ right 까지이다. 
	 * 
	 * @param a		정렬할 배열
	 * @param left	배열의 시작점
	 * @param mid	배열의 중간점
	 * @param right	배열의 끝 점
	 */
	private static void merge(int[] a, int left, int mid, int right) {
		int l = left;		// 왼쪽 부분리스트 시작점
		int r = mid + 1;	// 오른쪽 부분리스트의 시작점 
		int idx = left;		// 채워넣을 배열의 인덱스
		
		
		while(l <= mid && r <= right) {
			/*
			 *  왼쪽 부분리스트 l번째 원소가 오른쪽 부분리스트 r번째 원소보다 작거나 같을 경우
			 *  왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1 증가시킨다.
			 */
			if(a[l] <= a[r]) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
			/*
			 *  오른쪽 부분리스트 r번째 원소가 왼쪽 부분리스트 l번째 원소보다 작거나 같을 경우
			 *  오른쪽의 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가시킨다.
			 */
			else {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		}
		
		/*
		 * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (l > mid)
		 * = 오른쪽 부분리스트 원소가 아직 남아있을 경우
		 * 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
		 */
		if(l > mid) {
			while(r <= right) {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		}
		
		/*
		 * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (r > right)
		 * = 왼쪽 부분리스트 원소가 아직 남아있을 경우
		 * 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
		 */
		else {
			while(l <= mid) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
		}
		
		/*
		 * 정렬된 새 배열을 기존의 배열에 복사하여 옮겨준다.
		 */
		for(int i = left; i <= right; i++) {
			a[i] = sorted[i];
		}
	}
}
```

