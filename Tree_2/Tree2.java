package Tree_2;

public class Tree2 {
    TreeNode2 root;
    TreeNode2 parent;
    int height = -1;

    public Tree2(){

    }

    public Tree2(TreeNode2 node){
        this.root = node;
    }

    // code insert secara iteratif
//    public void insert(int dataNode){
//        TreeNode2 newNode = new TreeNode2(dataNode);
//        if(root == null){
//            root = new TreeNode2(dataNode);
//            System.out.println("Data root = " + root.getData());
//        } else{
//            TreeNode2 ptr = root;
//            while(true){
//                if(newNode.getData() <= ptr.getData()){
//                    if(ptr.getLeftNode() == null){
//                        ptr.setLeftNode(newNode);
//                        System.out.println("Node dengan data " + dataNode + " menjadi left child dari " + ptr.getData());                        return;
//                    }else{
//                        ptr = ptr.getLeftNode();
//                    }
//                }else{
//                    if(ptr.getRightNode() == null){
//                        ptr.setRightNode(newNode);
//                        System.out.println("Node dengan data " + dataNode + " menjadi right child dari " + ptr.getData());
//                        return;
//                    }else{
//                        ptr = ptr.getRightNode();
//                    }
//                }
//            }
//        }
//    }

    // insert secara rekursif
    public void insertNode(int value) {
        TreeNode2 temp = new TreeNode2(value);
        if(root == null)
            root = temp;
        else
            insertHelper(root, value);
    }

    public void insertHelper(TreeNode2 node, int value) {
        if (value < node.getData()){
            if (node.getLeftNode() != null) {
                insertHelper(node.getLeftNode(), value);
            } else {
                TreeNode2 newNode = new TreeNode2(value);
                node.setLeftNode(newNode);
                newNode.setParent(node);
                System.out.println("Node dengan data " + value + " menjadi left child dari " + node.getData());
            }
        }
        else if (value > node.getData()) {
            if (node.getRightNode() != null) {
                insertHelper(node.getRightNode(), value);
            } else {
                TreeNode2 newNode = new TreeNode2(value);
                node.setRightNode(newNode);
                newNode.setParent(node);
                System.out.println("Node dengan data " + value + " menjadi right child dari " + node.getData());
            }
        }
    }

    public TreeNode2 search(int value){
        TreeNode2 pointer = root;

        if(pointer.getData() == value){
            System.out.print(pointer.getData());
        } else{
            while(pointer.getData() != value){
                if(value <= pointer.getData()) {
                    System.out.print(pointer.getData() + ",");
                    pointer = pointer.getLeftNode();
                }else if(value > pointer.getData()){
                    System.out.print(pointer.getData() + ",");
                    pointer = pointer.getRightNode();
                }
                if(pointer == null) {
                    System.out.println("Node tidak ditemukan");
                    return null;
                }
            }
        }
        if(pointer != null)
            System.out.println(" Node ditemukan");
        return pointer;
    }

    public TreeNode2 getRoot(){
        return root;
    }

    public void setRoot(TreeNode2 root){
        this.root = root;
    }

    public void preorderTraversal() {
        preorderHelper(root);
    }

    public void inorderTraversal() {
        inorderHelper(root);
    }

    public void postorderTraversal() {
        postorderHelper(root);
    }

    private void preorderHelper(TreeNode2 root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            preorderHelper(root.getLeftNode());
            preorderHelper(root.getRightNode());
        }
    }

    public void inorderHelper(TreeNode2 root) {
        if (root != null) {
            inorderHelper(root.getLeftNode());
            System.out.print(root.getData() + " ");
            inorderHelper(root.getRightNode());
        }
    }

    private void postorderHelper(TreeNode2 root) {
        if (root != null) {
            postorderHelper(root.getLeftNode());
            postorderHelper(root.getRightNode());
            System.out.print(root.getData() + " ");
        }
    }

    private boolean isLeaf(int value){
        boolean check = false;
        TreeNode2 ptr = search(value);

        if(ptr.getRightNode() == null && ptr.getLeftNode() == null)
            check = true;

        return check;
    }

    public int leaf(){
        return leafHelper(root);
    }

    public int leafHelper(TreeNode2 node){
        if(node == null) {
            return 0;
        }
        if(node.getLeftNode() == null && node.getRightNode() == null) {
            System.out.println("leaf : node " + node.getData());
            return 1;
        }
        else
            return leafHelper(node.getLeftNode()) + leafHelper(node.getRightNode());
    }

    public void depth(int value){
        TreeNode2 ptr = search(value);
        if(ptr != null){
            System.out.println("Depth dari node " + value + " : " + findDepthHelper(root, value));
        }
    }

    public int findDepthHelper(TreeNode2 root, int value)
    {
        if (root == null)
            return -1;

        int dist = -1;

        if ((root.getData() == value)||
                (dist = findDepthHelper(root.getLeftNode(), value)) >= 0 ||
                (dist = findDepthHelper(root.getRightNode(), value)) >= 0)
            return dist + 1;

        return dist;
    }

    public void height(int value){
        TreeNode2 ptr = search(value);
        if(ptr != null){
            System.out.println("Height dari node " + value + " : " + findHeight(ptr));
        }
    }

    private int findHeight(TreeNode2 node){
        int left = -1, right = -1, temp;

        if (node.getLeftNode() != null){
            left = findHeight(node.getLeftNode());}
        if (node.getRightNode() != null){
            right = findHeight(node.getRightNode());}

        if (left > right){
            temp = left + 1;
        }else {
            temp = right + 1;
        }
        return temp;
    }

    public void descendant(int value) {
        TreeNode2 ptr = search(value);
        if(ptr != null) {
            descendantHelper(root, value);
        }
    }

    public TreeNode2 descendantHelper(TreeNode2 pointer, int value) {
        while (pointer != null) {
            if (pointer.getData() == value) {
                inorderHelper(pointer.getLeftNode());
                inorderHelper(pointer.getRightNode());
                return pointer;
            } else {
                if (pointer.getData() <= value) {
                    pointer = pointer.getRightNode();
                } else {
                    pointer = pointer.getLeftNode();
                }
            }
        }
        System.out.println("Data " + value + " tidak ditemukan");
        return pointer;
    }

    public boolean delete(int value){
        boolean check = false;
        TreeNode2 node = getCurrent(value);
        if(node != null){
            if(!isLeaf(value) && (node.getLeftNode() != null && node.getRightNode() != null))
                check = delete_2_anak(node);
            else if(!isLeaf(value) && (node.getLeftNode() != null || node.getRightNode() != null))
                check = delete_1_anak(node);
            else
                check = delete_0_anak(node);
        }
        return check;
    }

    private boolean delete_0_anak(TreeNode2 node){
        TreeNode2 parent = node.getParent();
        if(parent.getLeftNode() == node){
            parent.setLeftNode(null);
            node.setParent(null);
        } else {
            parent.setRightNode(null);
            node.setParent(null);
        }
        System.out.println("Node 0 anak terhapus ");
        return true;
    }

    private boolean delete_1_anak(TreeNode2 node){
        TreeNode2 parent = node.getParent();
        TreeNode2 temp = node;

        if(parent.getLeftNode() == temp){
            if(temp.getLeftNode() == null) {
                parent.setLeftNode(temp.getRightNode());
                temp.getRightNode().setParent(parent);
            }else {
                parent.setLeftNode(temp.getLeftNode());
                temp.getRightNode().setParent(parent);
            }
            temp.setParent(null);
        }else {
            if (temp.getLeftNode() == null) {
                parent.setLeftNode(temp.getRightNode());
                temp.getLeftNode().setParent(parent);
            }
            else {
                parent.setLeftNode(temp.getLeftNode());
                temp.getLeftNode().setParent(parent);
            }
            temp.setParent(null);
        }
        System.out.println("Node 1 anak terhapus");
        return true;
    }

    public boolean delete_2_anak(TreeNode2 node) {
        boolean cek = false;
        TreeNode2 parent = node.getParent();

        TreeNode2 successor = getSuccessor(node);

        if(node == root){
            successor.setLeftNode(root.getLeftNode());
            root.getRightNode().setParent(successor);
            root.getLeftNode().setParent(successor);
            root = successor;
        }else if(node.getData() < parent.getData()){
            parent.setLeftNode(successor);
        }
        else{
            parent.setRightNode(successor);
        }

        successor.setLeftNode(node.getLeftNode());
        return true;
    }

    public TreeNode2 getSuccessor(TreeNode2 node) {
        TreeNode2 current, successor, successorParent;

        successor = node;
        successorParent = node;
        current = node.getRightNode();
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftNode();
        }

        if (successor != node.getRightNode()) {
            successorParent.setLeftNode(successor.getRightNode());
            successor.setRightNode(node.getRightNode());
        }
        return successor;
    }

    public TreeNode2 getCurrent(int value){
        TreeNode2 pointer = root;

        if(pointer.getData() == value){
            System.out.print(pointer.getData());
        } else{
            while(pointer.getData() != value){
                if(value <= pointer.getData()) {
                    System.out.print(pointer.getData() + ",");
                    pointer = pointer.getLeftNode();
                }else if(value > pointer.getData()){
                    System.out.print(pointer.getData() + ",");
                    pointer = pointer.getRightNode();
                }
                if(pointer == null) {
                    return null;
                }
            }
        }
        return pointer;
    }

    private TreeNode2 getParent(int value){
        TreeNode2 pointer = root;
        TreeNode2 parent  = root;
        if(pointer.getData() == value){
            System.out.print(pointer.getData());
        } else{
            while(pointer.getData() != value){
                if(value <= pointer.getData()) {
                    pointer = pointer.getLeftNode();
                }else if(value > pointer.getData()){
                    pointer = pointer.getRightNode();
                }
                if(pointer.getLeftNode().getData() == value || pointer.getRightNode().getData() == value) {
                    parent = pointer;
                    return parent;
                }
            }
        }
        return parent;
    }

}
