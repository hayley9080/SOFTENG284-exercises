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
  public boolean contains(int key) {
    Node current = root;
    while (current != null) {
      if (current.key == key) {
        return true;

      } else if (current.key > key) {
        current = current.left;

      } else {
        current = current.right;
      }
    }
    return false;
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
  public void rotateLeft(Node node) {
    Node right = node.right;
    Node rightLeft = right.left;

    right.left = node;
    node.right = rightLeft;

    node.height();
    right.height();
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
  public void rotateRight(Node node) {
    Node left = node.left;
    Node leftRight = left.right;

    left.right = node;
    node.left = leftRight;

    node.height();
    left.height();
  }

  /**
   * rotate is used to rotate a node to the left or right depending on the balance of the node.
   *
   * @param node the node to rotate
   */
  public void rotate(Node node) {
    int balance = balance(node);
    // left heavy
    if (balance > 1) {
      if (balance(node.left) < 0) {
        rotateLeft(node.left);
      }
      rotateRight(node);
    }
    // right heavy
    else if (balance < -1) {
      if (balance(node.right) > 0) {
        rotateRight(node.right);
      }
      rotateLeft(node);
    }
  }
}
