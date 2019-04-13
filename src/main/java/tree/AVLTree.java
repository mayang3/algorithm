package tree;

public class AVLTree {

	static class Node {
		int key;
		int height;

		Node left;
		Node right;

		Node(int key) {
			this.key = key;
			height = 1;
		}
	}

	Node root;

	int height(Node node) {
		if (node == null) {
			return 0;
		}

		return node.height;
	}

	// balanceFactor = height(left subtree) - height(right subtree)
	int getBalanceFactor(Node node) {
		if (node == null) {
			return 0;
		}

		return this.height(node.left) - this.height(node.right);
	}

	// A utility function to right rotate subtree rooted with y
	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights (y 가 x 보다 하위이므로 y 의 높이가 항상 먼저 계산되어야 한다.)
		y.height = Math.max(this.height(y.left), this.height(y.right)) + 1;
		x.height = Math.max(this.height(x.left), this.height(x.right)) + 1;

		// Return new root
		return x;
	}

	// A utility function to left rotate subtree rooted with x
	Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;

		// Perform rotation
		x.left = y;
		y.right = T2;

		// Update heights (y 가 x 보다 하위이므로 y 의 높이가 항상 먼저 계산되어야 한다.)
		y.height = Math.max(this.height(y.left), this.height(y.right)) + 1;
		x.height = Math.max(this.height(x.left), this.height(x.right)) + 1;

		// Return new root
		return x;
	}

	// 여기서 return 되는 node 는 key 값이 insert 될 대상이라고 보면된다.
	// 즉, 여기서 return 되는 node 에 key 값이 들어간다
	Node insert(Node node, int key) {
		// 1. Perform the normal BST insertion
		if (node == null) {
			return new Node(key);
		}

		if (key < node.key) {
			node.left = insert(node.left, key);
		} else if (key > node.key) {
			node.right = insert(node.right, key);
		} else {
			// Duplicate keys not allowed
			return node;
		}

		// 2. Update height of this ancestor node
		node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

		// 3. Get the balance factor of this ancestor node
		// to check whether this node became unbalanced
		int balance = getBalanceFactor(node);

		// 4. if this node becames unbalanced, then there are 4 cases

		// 4-1. Left Left Case
		if (balance > 1 && key < node.left.key) {
			return rightRotate(node);
		}

		// 4-2. Right Right Case
		if (balance < -1 && key > node.right.key) {
			return leftRotate(node);
		}

		// 4-3. Left Right Case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// 4-4. Right Left Case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		// return the (unchanged) node pointer
		return node;
	}

	// Given a non-empty binary search tree,
	// return the node with minimum key value found in that tree.
	// Note that the entire tree does not need to be searched.
	Node minValueNode(Node node) {
		Node current = node;

		// loop down to find the leftmost leaf
		while (current.left != null) {
			current = current.left;
		}

		return current;
	}

	Node deleteNode(Node root, int key) {
		// 1. Perform standard BST delete
		if (root == null) {
			return null;
		}

		// If the key to be deleted smaller than the root's key, then it lies in left subtree.
		// 만약 삭제될 키가 루트의 키보다 적다면, 삭제될 키는 왼쪽 subtree 에 있다.
		if (key < root.key) {
			root.left = deleteNode(root.left, key);
		}

		// If the key to be deleted is greater than the root's key, then it lies in right subtree
		else if (key > root.key) {
			root.right = deleteNode(root.right, key);
		}

		// If key is same as root's key, then this is the node to be deleted.
		else {
			// node with only one child or no child.
			if (root.left == null || root.right == null) {
				Node temp = null;

				if (temp == root.left) {
					temp = root.right;
				} else {
					temp = root.left;
				}

				// No child case
				if (temp == null) {
					temp = root;
					root = null;
				} else {
					// one child case
					// copy the contents of the non-empty child
					root = temp;
				}
			} else {
				// node with two children: Get the inorder
				// successor (smallest in the right subtree)
				Node temp = minValueNode(root.right);

				// Copy the inorder successor's data to this node
				root.key = temp.key;
				// Delete the inorder successor
				root.right = deleteNode(root.right, temp.key);
			}
		}

		if (root == null) {
			return null;
		}

		// 2. update height of the current node
		root.height = Math.max(this.height(root.left), this.height(root.right)) + 1;

		// 3. get the balance factor of this node (to check whether this node became unbalanced)
		int balance = getBalanceFactor(root);

		// Left Left Case
		if (balance > 1 && getBalanceFactor(root.left) >= 0)
			return rightRotate(root);

		// Left Right Case
		if (balance > 1 && getBalanceFactor(root.left) < 0)
		{
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Right Case
		if (balance < -1 && getBalanceFactor(root.right) <= 0)
			return leftRotate(root);

		// Right Left Case
		if (balance < -1 && getBalanceFactor(root.right) > 0)
		{
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	void preOrder(Node node) {
		if (node == null) {
			return;
		}

		System.out.print(node.key + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public static void main(String[] args) {
		insertAVL();
		deleteAVL();
	}

	private static void deleteAVL() {
		AVLTree tree = new AVLTree();

		/* Constructing tree given in the above figure */
		tree.root = tree.insert(tree.root, 9);
		tree.root = tree.insert(tree.root, 5);
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 0);
		tree.root = tree.insert(tree.root, 6);
		tree.root = tree.insert(tree.root, 11);
		tree.root = tree.insert(tree.root, -1);
		tree.root = tree.insert(tree.root, 1);
		tree.root = tree.insert(tree.root, 2);

        /* The constructed AVL Tree would be
           9
          /  \
         1    10
        /  \    \
        0    5    11
        /    /  \
        -1   2    6
         */
		System.out.println("[Insert AVL] Preorder traversal of "+
			"constructed tree is : ");
		tree.preOrder(tree.root);

		tree.root = tree.deleteNode(tree.root, 10);

        /* The AVL Tree after deletion of 10
           1
          /  \
         0    9
        /     / \
        -1    5   11
        /  \
        2    6
         */
		System.out.println("");
		System.out.println("Preorder traversal after "+
			"deletion of 10 :");
		tree.preOrder(tree.root);
	}

	private static void insertAVL() {
		AVLTree insertTree = new AVLTree();

		insertTree.root = insertTree.insert(insertTree.root, 10);
		insertTree.root = insertTree.insert(insertTree.root, 20);
		insertTree.root = insertTree.insert(insertTree.root, 30);
		insertTree.root = insertTree.insert(insertTree.root, 40);
		insertTree.root = insertTree.insert(insertTree.root, 50);
		insertTree.root = insertTree.insert(insertTree.root, 25);


		/* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */

		System.out.println("[Insert AVL] Preorder traversal" +
			" of constructed tree is : ");
		insertTree.preOrder(insertTree.root);
		System.out.println();
	}

}
