package trie;

public class Trie {
	// Alphabet size
	static final int ALPHABET_SIZE = 26;
	// Trie Root Node
	static TrieNode root;


	/**
	 * 트라이는 루트에서 한 노드까지 내려가는 경로에서 만나는 글자들을 모으면,
	 *
	 * 해당 노드에 대응되는 접두사를 얻을 수 있기 때문에, 각 노드에는 대응되는 문자열을 저장할 필요가 없다.
	 *
	 * 그래서 트라이의 한 노드를 표현하는 객체(TrieNode) 에는 자손 노드들을 가리키는 포인터 목록과, 이 노드가 종료 노드인지를 나타내는 불린 값 변수로 구성된다.
	 *
	 */
	static class TrieNode {
		// 자식 노드의 포인터
		TrieNode [] children = new TrieNode[ALPHABET_SIZE];
		// 현재 노드가 종료노드인지를 나타내는 포인터
		boolean isEndOfWord;

		TrieNode() {
			this.isEndOfWord = false;

			for (int i = 0; i < ALPHABET_SIZE; i++) {
				children[i] = null;
			}
		}
	}

	// If not present, inserts key into trie
	// If the key is prefix of trie node,
	// just marks leaf node
	static void insert(String key) {
		TrieNode pCrawl = root;

		for (int level = 0; level < key.length() ; level++) {
			int index = key.charAt(level) - 'a';

			if (pCrawl.children[index] == null) {
				pCrawl.children[index] = new TrieNode();
			}

			// 위에서 삽입한 TrieNode 를 root 로 바꾼다.
			// 이렇게 하는 이유는, 문자열의 각각 character 를 자식노드로 할당시키기 위함이다.
			pCrawl = pCrawl.children[index];
		}

		pCrawl.isEndOfWord = true;
	}

	// Returns true if key presents in trie, else false
	static boolean search(String key) {
		TrieNode pCrawl = root;

		// Trie 의 깊이만큼 내려가면서 문자열 character 를 찾는다.
		for (int level = 0 ; level < key.length() ; level++) {
			int index = key.charAt(level) - 'a';

			if (pCrawl.children[index] == null) {
				return false;
			}

			pCrawl = pCrawl.children[index];
		}

		return pCrawl != null && pCrawl.isEndOfWord;
	}

	public static void main(String[] args) {
		String [] keys = {"the", "a", "there", "answer", "any", "by", "bye", "their"};

		String [] output = {"Not present in trie", "Present in trie"};

		root = new TrieNode();

		// Construct trie
		for (int i = 0; i < keys.length; i++) {
			insert(keys[i]);
		}

		// Search for different keys
		if (search("a")) {
			System.out.println("a --- " + output[1]);
		} else {
			System.out.println("a --- " + output[0]);
		}

		if (search("the")) {
			System.out.println("the --- " + output[1]);
		} else {
			System.out.println("the --- " + output[0]);
		}

		if (search("these")) {
			System.out.println("these --- " + output[1]);
		} else {
			System.out.println("these --- " + output[0]);
		}

		if (search("their")) {
			System.out.println("their --- " + output[1]);
		} else {
			System.out.println("their --- " + output[0]);
		}

		if (search("thaw")) {
			System.out.println("thaw --- " + output[1]);
		} else {
			System.out.println("thaw --- " + output[0]);
		}
	}
}