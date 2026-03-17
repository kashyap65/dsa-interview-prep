package com.kashyap.dsa.linked_list;

import java.util.HashMap;

public class LRUCache {
    HashMap<Integer,Node> nodeHashMap;
    Node head;
    Node tail;
    int capacity;
    int size=0;
    public LRUCache(int capacity) {
        this.nodeHashMap = new HashMap<>();
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    void addToFront(Node node){
        if(node!=null && head.next!=null){
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
    }

    void removeNode(Node node){
        if(node!=null && node.prev!=null && node.next!=null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    void removeLRU(){
        Node lru = tail.prev;
        removeNode(lru);
    }

    void moveToFront(Node node){
        removeNode(node);
        addToFront(node);
    }

    int get(int key){
        if(nodeHashMap.containsKey(key)){
            Node node = nodeHashMap.get(key);
            moveToFront(node);
            return node.value;
        }
        return -1;
    }

    void put(int key,int value){
        if(nodeHashMap.containsKey(key)){
            Node node = nodeHashMap.get(key);
            removeNode(node);
            node.value = value;
            nodeHashMap.put(key,node);
            addToFront(node);
        }
        else{
            Node node = new Node(key,value);
            addToFront(node);
            nodeHashMap.put(key,node);
            size++;
            if(size>capacity){
                nodeHashMap.remove(tail.prev.key);
                removeLRU();
                size--;
            }
        }
    }
}

class Node{
    int key;
    int value;

    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}