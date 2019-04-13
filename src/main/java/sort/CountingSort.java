package sort;

public class CountingSort {

	/**
	 * 시간복잡도 O(n)
	 *
	 * 총 배열은 3개가 필요하다.
	 * input arr
	 * output arr
	 * count arr
	 *
	 * 설명 참조) http://bowbowbow.tistory.com/8
	 *
	 *
	 * @param arr
	 */
	static void sort(char [] arr) {

		int n = arr.length;

		char [] output = new char[n];
		int [] count = new int[256];

		// 1. 각 문자의 출현빈도를 카운팅한다.
		for (int i=0 ; i<n ; i++) {
			count[arr[i]]++;
		}

		// 2. 각 문자의 출현빈도를 누적합으로 바꿔준다.
		for (int i=1 ; i<=255 ; i++) {
			count[i] += count[i-1];
		}

		// 3. output[누적합-1] 의 위치에 "정렬할 숫자" 를 집어넣어 준다.
		// 3-1. 그 후, 숫자를 1 감소시켜 준다.
		for (int i=0 ; i<n ; i++) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		// 4. output array 를 arr 에 복사해준다.
		// 사실 이부분은 안해주고 output array 를 바로 리턴해도 무방하다.
		for (int i=0 ; i<arr.length ; i++) {
			arr[i] = output[i];
		}

	}
}
