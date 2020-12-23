package A3;


/**
 * This class provides the helping methods for Binary Search Tree (BST)
 * @author Raj (40085012)
 *
 */
public class BinaryTree {
	
	/**
	 * Represents a Node in the binary search tree
	 * @author Raj
	 *
	 */
	private class Node {
		
		private int id;  // student id
		private int height;  // height of tree
		private String value;  // name of the student
		private Node left;	// left pointer of a node,
		private Node right;  // right pointer of a node
		
		// Parameterized constructor 
		public Node(int i, String val)
		{
			id = i;
			value = val;
			left = null;
			right = null;
		}

	} // end of inner class Node
	
	static Node temp;
	private Node root;  // root of the tree
	
	/**
	 * This method checks whether the tree is balanced or not
	 * @param node
	 * @return
	 */
	public int getBalance(Node node) {
        return (node == null) ? 0 : height(node.right) - height(node.left);
    }
	
	/**
	 * Returns the height of the BST.
	 * @param node
	 * @return
	 */
	private int height(Node node) {
        return node == null ? -1 : node.height;
    }
	
	/**
	 * Updates the hight of the BST.s
	 * @param node
	 */
	private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }	
	
	/**
	 * Rotate to the right to statisfy the AVL tree property
	 * @param y
	 * @return
	 */
	private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
	
	/**
	 * Rotate to the left to statisfy the AVL tree property
	 * @param y
	 * @return
	 */
	private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
	
	/**
	 * Rebalance the BST to maintain the AVL tree property.
	 * @param z
	 * @return
	 */
	private Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }
	
	/**
	 * Helping method to insert a record in the tree.
	 * @param i
	 * @param val
	 * @param root
	 * @return
	 */
	private Node insert(int i, String val, Node root) {
		if(root == null)
			return new Node(i, val);
		
		if(root.id > i) {
			root.left = insert(i, val, root.left);
		}
		
		else if(root.id < i) {
			root.right = insert(i, val, root.right);
		}
		else {
			System.out.println("Student ID "+i + " is already present");
			return root;
		}
		return rebalance(root);
	}
	
	/**
	 * Insert a student record to the BST.
	 * @param i
	 * @param val
	 */
	public void insertRecord(int i, String val) {
		root = insert(i, val, root);
	}
	
	/**
	 * Store the records in BST by inorder traversal(sorted sequence).
	 * @return
	 */
	public Queue<Integer> inorder() {
		Queue<Integer> q = new Queue<>();
		System.out.flush();
		inorder(root, q);
		return q;
	}
	
	/**
	 * Performs the inorder traversal.
	 * @param root
	 * @param q
	 */
	private void inorder(Node root, Queue<Integer> q) { 
        if (root != null) { 
            inorder(root.left, q);
            q.add(root.id);
            inorder(root.right, q); 
        }
    }	
	
	/**
	 * Returns list of student IDS in the provided range.
	 * @param k1
	 * @param k2
	 * @param size
	 * @return
	 */
	public Queue<Integer> getKeysInRange(int k1, int k2, int size) {
		Node t = root;
		Queue<Integer> q = new Queue<>();
		traverse(q, t, k1, k2);
		return q;
	}
	
	/**
	 * Adds a record to Queue if it lies in the given range of key.
	 * @param q
	 * @param node
	 * @param k1
	 * @param k2
	 */
	private void traverse(Queue<Integer> q, Node node, int k1, int k2) {
		if(node == null)
			return;
		if(node.id > k1) {
			traverse(q, node.left, k1, k2);
		}
		if(node.id >= k1 && node.id <= k2) {
			q.add(node.id);
		}
		if(node.id < k2) {
			traverse(q, node.right, k1, k2);
		}
	}
	
	/**
	 * Remove a node from the BST.
	 * @param key
	 * @return
	 */
	public int remove(int key) {
		temp = null;
		root = remove(root, key);
		return (temp == null) ? 0 : temp.id;
	}
	
	/**
	 * Helping method to remove a node from the BST.
	 * @param node
	 * @param key
	 * @return
	 */
	private Node remove(Node node, int key) {
        if (node == null) {
            return node;
        }
        else if (node.id > key) {
        	if(node.left != null && node.left.id == key) {
        		temp = node.left;
        	}
            node.left = remove(node.left, key);
        } else if (node.id < key) {
        	if(node.right != null && node.right.id == key) {
        		temp = node.right;
        	}
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                node = node.right;
            } 
            else if(node.right == null) {
            	node = node.left;
            }
            else {
                node.id = getMinValue(node.right);
                node.right = remove(node.right, node.id);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }
	
	/**
	 * Minimum value in right sub tree
	 * @param root
	 * @return
	 */
	private int getMinValue(Node root) {
		int value = root.id;
		while(root.left != null) {
			value = root.left.id;
			root = root.left;
		}
		return value;
	}
	
	/**
	 * Gives value associated with a given key.
	 * @param key
	 * @return
	 */
	public String findNode(int key) {
		Node node = root;
		while(node != null) {
			if(node.id == key)
				break;
			node = (node.id > key) ? node.left : node.right;
		}
		if(node == null)
			return null;
		return node.value;
	}
	
	/**
	 * Gives the next record provided a given a record.
	 * @param key
	 * @return
	 */
	public int nextKey(int key) {
		Queue<Integer> q = inorder();
		int next_key = 0;
		while(q.size() > 0) {
			int k1 = q.remove();
			if(k1 == key) {
				if(q.size() >= 1)
					next_key = q.remove();
				break;
			}
		}
		q = null;
		return next_key;
	}
	
	/**
	 * Gives the previous record provided a given a record.
	 * @param key
	 * @return
	 */
	public int previousKey(int key) {
		Queue<Integer> q = inorder();
		int previous = 0;
		if(q.size() > 0 && q.peek() == key) {
			return previous;
		}
		while(q.size() > 0) {
			int k1 = q.remove();
			if(q.size() > 0 && q.peek() == key) {
				previous = k1;
				break;
			}
		}
		q = null;
		return previous;
	}
	
}
