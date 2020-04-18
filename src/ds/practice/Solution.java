package ds.practice;

class Solution {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap(16);
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);
        map.put(4, 44);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(3, 34);
        System.out.println(map.get(3));
        System.out.println(map.get(4));
    }
    static class LinkedListNode {
        int key;
        int value;
        LinkedListNode next;
        private LinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
        }

    }

    static class MyHashMap {

        LinkedListNode[] buckets;
        int capacity;
        int size;

        private MyHashMap() {
            this.capacity = 16;
            buckets = new LinkedListNode[this.capacity];
        }
        private MyHashMap(int capacity) {
            this.capacity = capacity;
            buckets = new LinkedListNode[capacity];
        }

        public boolean put(int key, int value) {
            int index = ((Integer)key).hashCode() % capacity;
            LinkedListNode bucket = buckets[index];
            if (bucket == null) {
                buckets[index] = new LinkedListNode(key, value);
            } else {
                while (bucket.next != null && bucket.key != key) {
                    bucket = bucket.next;
                }

                if (bucket.key == key) {
                    bucket.value = value;
                } else {
                    bucket.next = new LinkedListNode(key, value);
                }
            }

        }

        public int get(int key) {
            int index = ((Integer)key).hashCode() % capacity;
            LinkedListNode bucket = buckets[index];
            if (bucket == null) {
                return -1;
            } else {
                while (bucket.next != null && bucket.key != key) {
                    bucket = bucket.next;
                }
                if (bucket.key == key) {
                    return bucket.value;
                } else {
                    return -1;
                }
            }
        }

    }
}