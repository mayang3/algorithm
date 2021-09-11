package numerical_analysis;

public abstract class BisectionMethod {
	abstract double f(double x);

	// 이분법의 예제 구현
	double bisection(double lo, double hi) {
		// 반복문 불변식을 강제한다.
		if (f(lo) > 0) {
			double temp = lo;
			lo = hi;
			hi = temp;
		}

		// 반복문 불변식 : f(lo) <= 0 < f(hi)
		// 우리가 푸는 문제에서 정답과 10^-7 이하의 차이가 나는 답들 또한 정답으로 인정한다고 해보자.
		// |lo-hi| <= 2*10^-7 인 상황에서 반복문을 종료하고, 두 값의 평균을 반환하면 최대 오차는 |lo-hi| / 2 <= 10^-7 이 된다.
		while (Math.abs(hi - lo) > 2e-7) {
			double mid = (lo + hi) / 2;

			if (f(mid) <= 0) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return (lo + hi) / 2;
	}

	// lo 와 hi 는 2 * 10^-5 정도 차이가 나는 값이기 때문이 이 함수는 금방 종료할 것 같지만,
	// lo 와 hi 사이에는 double 변수가 표현할 수 있는 값이 하나도 없다.
	// 따라서, lo+hi / 2 는 결국 hi 와 같은 값이 되므로 절대로 종료되지 않는다.
	void infiniteBisection() {
		double lo = 123456123456.1234588623046875;
		double hi = 123456123456.1234741210937500;

		while (Math.abs(lo + hi) > 2e-7) {
			hi = (lo + hi) / 2.0;
		}

		System.out.println("finished!");
	}

	// 1~n 범위의 자연수 중 1/x * x = 1 인 x 의 수를 세는 함수
	// 이 식은 항상 성립해야 하니 원래의 목적대로라면 n 이 반환되어야 하지만 실제론 그렇지 않다.
	// 실제로 countObvious(50) = 49 가 나온다.
	// 이것은 컴퓨터의 실수 표현 방식과 연관이 있다.0
	int countObvious(int n) {
		int same = 0;

		for (int x = 1; x <= n ; x++) {
			double y = 1.0 / x;
			if (y * x == 1.0) {
				++same;
			}
		}

		return same;
	}

	public static void main(String[] args) {
		BisectionMethod bisectionMethod = new BisectionMethod() {
			@Override
			double f(double x) {
				return 0;
			}
		};

//		bisection.infiniteBisection();

		double lo = 123456123456.1234588623046875;
		double lo2 = 123456123456.1234588623046975;
		double lo3 = 123456123456.1234588623046985;
		double hi = 123456123456.1234741210937500;

		System.out.println(lo);
		System.out.println(lo2);
		System.out.println(lo3);
		System.out.println(hi);
		System.out.println((lo + hi) / 2);
		System.out.println(1e19 / Math.pow(2, 101) < 1e-7);
		System.out.println(1e-7);
	}
}