package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private int size;
    private BSTNode root;
    public BSTMap() {
        root = null;
    }
    public BSTMap(K key, V value) {
        root = new BSTNode(null, key, value, null);
        size = 1;
    }
    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
        size = 0;
    }
    @Override
    public boolean containsKey(K key) {
        return null != get(key);
    }
    @Override
    public V get(K key) {
        return get(key, root);
    }
    private V get(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp > 0) {
            return get(key, node.right);
        } else {
            return get(key, node.left);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }
    private BSTNode put(K key, V value, BSTNode node) {
        if (node == null) {
            size += 1;
            return new BSTNode(null, key, value, null);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.left = put(key, value, node.left);
        }
        return node;
    }
    public void printInOrder() {
        printInOrder(root);
    }
    private void printInOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.key.toString() + "  ");
        printInOrder(node.right);
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
    private class BSTNode{
        private K key;
        private V value;
        private BSTNode left, right;
        BSTNode() {
            left = null;
            right = null;
        }
        BSTNode(BSTNode left, K key, V value, BSTNode right) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }

    }
}
