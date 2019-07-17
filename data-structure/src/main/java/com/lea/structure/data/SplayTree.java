package com.lea.structure.data;

public class SplayTree<T extends Comparable<T>> {

    SplayNode<T> root;

    public static class SplayNode<T extends Comparable<T>> {
        T key;
        SplayNode<T> parent;
        SplayNode<T> left;
        SplayNode<T> right;

        public SplayNode(T key, SplayNode<T> parent, SplayNode<T> left, SplayNode<T> right) {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }


    //LL 右旋
    private SplayNode<T> leftLeftRotation(SplayNode<T> k2) {
        SplayNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    //rr左旋
    private SplayNode<T> rightRightRotation(SplayNode<T> k1) {
        SplayNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    public SplayNode<T> splay(SplayNode<T> node, T key) {

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            if (key.compareTo(node.left.key) < 0) {

                //右旋
                node = leftLeftRotation(node);

                root = splay(node, key); //递归

            } else if (key.compareTo(node.left.key) > 0) {

                node.left = rightRightRotation(node.left); //左旋

                root = splay(node, key);
            } else {

                //相等的话，放到根节点
                root = leftLeftRotation(node);
            }
        } else if (cmp > 0) {

            if (key.compareTo(node.right.key) > 0) {
                node = rightRightRotation(node);
                root = splay(node, key);
            } else if (key.compareTo(node.right.key) < 0) {
                node.right = leftLeftRotation(node.right);
                root = splay(node, key);
            } else {
                root = rightRightRotation(node);
            }
        } else {
            return node;
        }
        return root;
    }

    //普通的递归查询
    private SplayNode<T> searchNode(SplayNode<T> node, T key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return searchNode(node.left, key);
        } else if (cmp > 0) {
            return searchNode(node.right, key);
        } else {
            return node;
        }
    }


    //查询并伸展
    public SplayNode<T> searchSplay(T key) {
        if (searchNode(root, key) != null) {
            return splay(root, key);
        } else {
            System.out.println("没有这个key");
            return root;
        }
    }

    //普通的插入
    public void insert(SplayTree tree, SplayNode node) {
        SplayNode x = tree.root;
        SplayNode xlast = null;
        if (tree.root == null) {
            tree.root = node;
        } else {
            while (x != null) {
                xlast = x;
                if (x.key.compareTo(node.key) > 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }

            node.parent = xlast;
            if (xlast.key.compareTo(node.key) > 0) {
                xlast.left = node;
            } else {
                xlast.right = node;
            }
        }
    }

    public void midOrder(SplayNode node) {
        if (node != null) {
            midOrder(node.left);
            System.out.print(node.key + " ");
            midOrder(node.right);
        }
    }

    //前序遍历
    public void firstOrder(SplayNode<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            firstOrder(node.left);
            firstOrder(node.right);
        }

    }

    public void insert(T key) {
        SplayNode node = new SplayNode(key, null, null, null);
        insert(this, node);
    }

    public static void main(String[] args) {
        SplayTree<Integer> tree = new SplayTree<>();
        int[] test = {40, 30, 20, 35, 50, 45, 60};
        //1、插入
        for (int i : test) {
            tree.insert(i);
        }

        System.out.print("中序遍历： ");
        tree.midOrder(tree.root);
        System.out.println(" ");

        System.out.print("前序遍历： ");
        tree.firstOrder(tree.root);
        System.out.println(" ");

        tree.root = tree.searchSplay(20);
        System.out.println("\nroot is :" + tree.root.key);


        System.out.print("中序遍历： ");
        tree.midOrder(tree.root);
        System.out.println(" ");

        System.out.print("前序遍历： ");
        tree.firstOrder(tree.root);
        System.out.println(" ");

    }
}
