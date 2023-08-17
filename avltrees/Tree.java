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
}
