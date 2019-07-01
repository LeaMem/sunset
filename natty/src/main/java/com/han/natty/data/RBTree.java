package com.han.natty.data;

public class RBTree<T extends Comparable<T>> {

    //根节点
    private RBTNode<T> mRoot;

    private static final boolean RED = false;

    private static final boolean BLACK = true;



    private void leftRotate(RBTNode<T> x){
        //设置x的右孩子为y
        RBTNode<T> y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            this.mRoot = y;
        }else{
            if(x.parent.left == x){
                x.parent.left = y;
            }else{
                x.parent.right = y;
            }
        }

        y.left = x;
        y.parent = y;
    }


    private void rightRotate(RBTNode<T> y){
        RBTNode<T> x = y.left;
        y.left = x.right;
        if(x.right != null){
            x.right.parent = y;
        }
        x.parent = y.parent;
        if(y.parent == null){
            this.mRoot = x;
        }else{
            if(y == y.parent.right){
                y.parent.right = x;
            }else{
                y.parent.left = x;
            }
        }
        x.right = y;
        y.parent = x;
    }


    private void insertFixup(RBTNode<T> node){

        RBTNode<T> parent, gparent;
        //如果父节点存在，并且父节点的颜色是红色
        while((parent = parentOf(node)) != null && isRed(parent)){
            gparent = parentOf(parent);

            //如果父节点是祖父节点的左孩子
            if(parent == gparent.left){

                //Case1: 叔叔节点是红色
                RBTNode<T> uncle = gparent.right;
                if(uncle != null && isRed((uncle))){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                //Case2: 叔叔是黑色，且当前节点是右孩子
                if(parent.right == node){
                    RBTNode<T> tmp;
                    leftRotate(parent);

                }

            }
        }

    }



    /*
     * 找结点(x)的后继结点。即，查找"红黑树中数据值大于该结点"的"最小结点"。
     */
    public RBTNode<T> successor(RBTNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (x.right != null)
            return minimum(x.right);

        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        RBTNode<T> y = x.parent;
        while ((y!=null) && (x==y.right)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /*
     * 找结点(x)的前驱结点。即，查找"红黑树中数据值小于该结点"的"最大结点"。
     */
    public RBTNode<T> predecessor(RBTNode<T> x) {
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (x.left != null)
            return maximum(x.left);

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        RBTNode<T> y = x.parent;
        while ((y!=null) && (x==y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }


    public T maximum() {
        RBTNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }



    /*
     * 查找最小结点：返回tree为根结点的红黑树的最小结点。
     */
    private RBTNode<T> minimum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        RBTNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的红黑树的最大结点。
     */
    private RBTNode<T> maximum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
    }



    /*
     * (递归实现)查找"红黑树x"中键值为key的节点
     */
    private RBTNode<T> search(RBTNode<T> x, T key) {
        if (x==null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    public RBTNode<T> search(T key) {
        return search(mRoot, key);
    }


    public RBTree() {
        mRoot=null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node!=null ? node.parent : null;
    }
    private boolean colorOf(RBTNode<T> node) {
        return node!=null ? node.color : BLACK;
    }
    private boolean isRed(RBTNode<T> node) {
        return ((node!=null)&&(node.color==RED)) ? true : false;
    }
    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }
    private void setBlack(RBTNode<T> node) {
        if (node!=null)
            node.color = BLACK;
    }
    private void setRed(RBTNode<T> node) {
        if (node!=null)
            node.color = RED;
    }
    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node!=null)
            node.parent = parent;
    }
    private void setColor(RBTNode<T> node, boolean color) {
        if (node!=null)
            node.color = color;
    }




}
