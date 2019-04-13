package reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author baejunbeom
 */
public class FastReaderWriter {

	static class FastReadWrite {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		String next() {
			try {
				if (st == null || st.hasMoreTokens() == false) {
					// default delimeter
					st = new StringTokenizer(br.readLine());
				}

				return st.nextToken();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		void print(Object o) {
			try {
				bw.write(String.valueOf(o));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		void flush() {
			try {
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
