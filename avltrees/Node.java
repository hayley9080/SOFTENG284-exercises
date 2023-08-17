package avltrees;

public class Node {
  public int key;
  public int height;

  public Node left;
  public Node right;

  public Node(int key) {
    this.key = key;
    this.height = 0;

    this.left = null;
    this.right = null;
  }

  public void height() {
    int leftHeight = -1;
    int rightHeight = -1;

    if (left != null) {
      leftHeight = left.height;
    }

    if (right != null) {
      rightHeight = right.height;
    }

    height = Math.max(leftHeight, rightHeight) + 1;
  }
}
