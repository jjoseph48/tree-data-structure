package Tree_2;

public class TestTree2 {
    public static void main(String[] args) {

        System.out.println("------- Program Testing Tree -------");
        int[] dataTree = {10, 5, 2, 7, 6, 13, 11, 12, 14};

        Tree2 tree = new Tree2();

        // Proses insert dataTree ke tree1
        for(int i = 0; i < dataTree.length; i++){
            tree.insertNode(dataTree[i]);
        }
        System.out.println();

        tree.delete(5);
        tree.inorderTraversal();
        tree.delete(14);
        tree.inorderTraversal();
//        tree.preorderTraversal();
//        System.out.println();
//        tree.inorderTraversal();
//        System.out.println();
//        tree.postorderTraversal();
//        System.out.println();


    }
}
