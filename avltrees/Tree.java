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
}
