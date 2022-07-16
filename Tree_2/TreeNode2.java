package Tree_2;

public class TreeNode2 {
    private int Data;
    private TreeNode2 leftNode;
    private TreeNode2 rightNode;
    private TreeNode2 parent;

    public TreeNode2(int data){
        this.Data = data;
    }

    public int getData(){
        return this.Data;
    }

    public void setData(int data){
        this.Data = data;
    }

    public TreeNode2 getLeftNode() {
        return leftNode;
    }

    public TreeNode2 getRightNode(){
        return rightNode;
    }

    public void setLeftNode(TreeNode2 node){
        this.leftNode = node;
    }

    public void setRightNode(TreeNode2 node){
        this.rightNode = node;
    }

    public TreeNode2 getParent() {
        return parent;
    }

    public void setParent(TreeNode2 parent) {
        this.parent = parent;
    }
}
