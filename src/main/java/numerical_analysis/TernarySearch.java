package numerical_analysis;

/**
 * 삼분검색
 *
 * 한번 반복할 때마다 후보 구간의 크기를 2/3 으로 줄여나간다.
 * 때문에 n 회 반복하고 나면 구간의 길이는 |hi-lo| * (2/3)^n 이 된다.
 */
public abstract class TernarySearch {

	// 우리가 최대치를 찾고 싶어하는 함수
	abstract double f(double x);

	double ternary(double lo, double hi) {
		double a = (2 * lo + hi) / 3D; // 1/3 지점의 입력값 (x 축)
		double b = (lo + 2 * hi) / 3D; // 2/3 지점의 입력값 (x 축)

		// 2/3 ~ 3/3 지점 사이에 답이 있다.
		if (f(a) > f(b)) {
			hi = b;
		} else {
			// lo ~ 1/3 지점 사이에 답이 있다.
			lo = a;
		}

		return (lo + hi) / 2D;
	}
}
