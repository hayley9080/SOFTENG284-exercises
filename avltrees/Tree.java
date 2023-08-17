package avltrees;

public class Tree {
  private Node root;

  public Tree() {
    this.root = null;
  }

  /**
   * contains is used to check if a key is in the tree.
   *
   * @param key the key to check
   * @return true if the key is in the tree, false otherwise
   */
  public boolean contains(Node current, int key) {
    if (current == null) {
      return false;
    }

    if (current.key == key) {
      return true;

    } else if (current.key > key) {
      return contains(current.left, key);

    } else {
      return contains(current.right, key);
    }
  }

  /**
   * balance is used to calculate how out-of-balance a node is.
   *
   * <p>A node is considered out-of-balance if the difference between the height of the left subtree
   * and the right subtree is greater than 1.
   *
   * <p>If positive, left is heavier. If negative, right is heavier.
   *
   * @param node the node to check
   * @return the balance of the node
   */
  public int balance(Node node) {
    int leftHeight = -1;
    int rightHeight = -1;

    if (node.left != null) {
      leftHeight = node.left.height;
    }

    if (node.right != null) {
      rightHeight = node.right.height;
    }

    return leftHeight - rightHeight;
  }

  /**
   * rotateLeft is used when the right subtree is heavier than the left subtree.
   *
   * <p>Moves the inputted node to the left child of the right child of the inputted node. The left
   * child of the right child of the inputted node becomes the right child of the inputted node.
   * Therefore the right child becomes the parent of the inputted node. This therefore rotates the
   * inputted node to the left.
   *
   * @param node the node to rotate
   */
  public Node rotateLeft(Node node) {
    Node right = node.right;
    Node rightLeft = right.left;

    right.left = node;
    node.right = rightLeft;

    node.height();
    right.height();
    return right;
  }

  /**
   * rotateRight is used when the left subtree is heavier than the right subtree.
   *
   * <p>Moves the inputted node to the right child of the left child of the inputted node. The right
   * child of the left child of the inputted node becomes the left child of the inputted node.
   * Therefore the left child becomes the parent of the inputted node. This therefore rotates the
   * inputted node to the right.
   *
   * @param node the node to rotate
   */
  public Node rotateRight(Node node) {
    Node left = node.left;
    Node leftRight = left.right;

    left.right = node;
    node.left = leftRight;

    node.height();
    left.height();
    return left;
  }

  /**
   * rotate is used to rotate a node to the left or right depending on the balance of the node.
   *
   * @param node the node to rotate
   */
  public Node rotate(Node node) {
    int balance = balance(node);
    // left heavy
    if (balance > 1) {
      // left is right heavy
      if (balance(node.left) < 0) {
        node.left = rotateLeft(node.left);
      }
      node = rotateRight(node);
    }
    // right heavy
    else if (balance < -1) {
      // right is left heavy
      if (balance(node.right) > 0) {
        node.right = rotateRight(node.right);
      }
      node = rotateLeft(node);
    }
    return node;
  }

  public void add(int key) {
    root = addRecursive(root, key);
  }

  /**
   * add is used to add a key into the tree recursively.
   *
   * @param key the key to add
   */
  public Node addRecursive(Node current, int key) {
    Node newNode = new Node(key);
    if (root == null) {
      root = newNode;
      return root;
    }
    // root is larger than key - go left
    if (current.key > key) {
      if (current.left == null) {
        current.left = newNode;
      } else {
        current.left = addRecursive(current.left, key);
      }

      // key is larger than root - go right
    } else if (current.key < key) {
      if (current.right == null) {
        current.right = newNode;
      } else {
        current.right = addRecursive(current.right, key);
      }
    }

    current.height();
    return rotate(current);
  }

  public static void main(String[] args) {
    Tree tree = new Tree();
    int n = 1000000;
    for (int i = 0; i < n; i++) {
      tree.add(i);
      tree.add(-i);
    }
    for (int i = 0; i < n; i++) {
      boolean x = tree.contains(tree.root, n - 1);
      boolean y = tree.contains(tree.root, -n + 1);
      if (!x || !y) {
        System.out.println("Error");
      }
    }
    System.out.println("Done");
  }
}
