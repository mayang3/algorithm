package number_theory.modular;

/**
 *
 * https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
 *
 * modular 의 속성중,
 *
 * 두수의 연산을 한 뒤 나머지를 취하는 것은
 *
 * 미리 두 수의 나머지를 구한 뒤 연산을 하고 다시 나머지를 취하는 것과 같다. 는 속성이 존재한다.
 *
 * 단, 이 속성은 덧셈, 뺄셈, 그리고 곱셈일 경우에만 해당된다.
 *
 * 나눗셈의 경우 성립하지 않으며, 그렇기 때문에 아래와 같은 방법들을 쓴다.
 *
 *
 * 1) Naive Method, O(m)
 * 2) Extended Euler’s GCD algorithm, O(Log m) [Works when a and m are coprime] -- a 와 m 이 서로소 일때..
 * coprime? - 정수나 다항식들끼리의 최대 공약수가 1
 *
 * 3) Fermat’s Little theorem, O(Log m) [Works when ‘m’ is prime] == m 만 소수일때..
 * -- a 와 m 의 관계 역시 서로소여야 한다. a 와 m 의 관계가 서로소이고 m 이 소수일때 사용할 수 있다.
 *
 */
public class ModularMultiplicativeInverse {

	/**
	 * Naive 한 방법이다.
	 *
	 * naive 한 방법에서는 1 ~ m 까지의 모든 숫자를 시도한다.
	 * 매번 x 의 loop 가 돌때마다, if (a*x)%m 이 1 인지를 확인한다.
	 *
	 * Time Complexity is O(m)
	 *
	 * @param a
	 * @param m
	 * @return
	 */
	static int naiveModInverse(int a, int m) {
		a = a % m;

		for (int x=1 ; x<m ; x++) {
			if ((a * x) % m == 1) {
				return x;
			}
		}

		return 1;
	}

	/**
	 * 이 아이디어는 확장 유클리드 알고리즘을 이용하는 것이다.
	 * 단, a 와 m 은 서로소여야 한다.
	 *
	 * 이 방법은 M 이 소수가 아닌 경우에도 b 의 역원을 찾고 싶을때 사용한다.
	 *
	 * A*b + B*M === 1 (mod M)
	 *
	 * Time Complexity O(Log m)
	 * @param a
	 * @param m
	 * @return
	 */
	static int extendedEuclidModInverse(int a, int m) {
		int m0 = m;
		int y =0, x=1;

		if (m == 1) {
			return 0;
		}

		while (a > 1) {
			// q is quotient
			int q = a / m;
			int t = m;

			// m is remainder now, process
			// same as Euclid's algo
			m = a % m;
			a = t;
			t = y;

			// Update x and y
			y = x - q * y;
			x = t;
		}

		// Make x positive
		if (x < 0) {
			x += m0;
		}

		return x;
	}

	/**
	 * (a/b)%M = (a * modInv(b,M))%M
	 *
	 * @param a
	 * @param m
	 */
	static void modInverse(int a, int m) {
		int g = gcd(a, m);

		if (g != 1) {
			System.out.println("Inverse doesn't exist");
		} else {
			System.out.println("Modular multiplicative inverse is " + power(a, m-2, m));
		}
	}

	// to compute x^y under modulo m
	// 그냥 제곱하는게 아니고, 모듈라 연산을 하면서 제곱해 나간다.

	/*
		x 는 밑, y 는 지수라고 하면, 지수함수를 다음과 같은 식으로 나타내보자. f(y)

		f(0) = 2^0 = 1
		f(1) = 2^1 = x * f(0) = 2*1 = 1
		f(2) = 2^2 = f(1) * f(1)
		f(3) = 2^3 = x * f(2)
		f(4) = 2^4 = 16 = f(2) * f(2)
		f(5) = 2^5 = 32 = x * f(4)
	 */

	static int power(int x, int y, int m) {
		if (y == 0) {
			return 1;
		}

		int p = power(x, y/2, m) % m;
		p = (p * p) % m;

		// 짝수승
		if (y % 2 == 0) {
			return p;
		}

		// 홀수승 - 한번 더 곱해주어야 한다. -> 짝수번 recursive call 되었으므로..
		return (x * p) % m;
	}

	static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}

		return gcd(b % a, a);
	}

}
