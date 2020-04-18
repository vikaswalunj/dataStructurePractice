package practice.SystemDesign;

public class MyMap<K, V> {

    Entry<K, V>[] buckets;
    int Capacity;
    int size;

    public MyMap() {
        buckets = new Entry[16];
        Capacity = 16;
    }

    public MyMap(int cap) {
        buckets = new Entry[cap];
        Capacity = cap;
    }

    public void put(K key, V value) {
        int index = key.hashCode() % Capacity;

        Entry<K, V> existing =  buckets[index];

        if (existing == null) {
            buckets[index] = new Entry<>(key, value, null);
            size++;
        } else {
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                }
                existing = existing.next;
            }
            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = new Entry<>(key, value, null);
                size++;
            }
        }
    }

    public V get(K key){
        int index = key.hashCode() % Capacity;
        Entry<K, V> bucket = buckets[index];
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyMap<>();
        myMap.put("Delhi", "india");
        myMap.put("seattle", "washington");
        myMap.put("dallas", "texas");
        myMap.put("arlington", "virginia");

        System.out.println(myMap.get("seattle"));
        System.out.println(myMap.get("arlington"));
        System.out.println(myMap.get("bhutan"));


    }
}
