package com.example.InfiniteMedian.helpers;

public class CustomHashMap<K, V>{

    private Entry<K, V> table[];

    private final int SIZE = 2048;
    public CustomHashMap(){
        table = new Entry[SIZE];
    }

    public void put(K key, V value){
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[Math.abs(hash)];
        if (entry == null){
            table[Math.abs(hash)] = new Entry<>(key, value);
        }else{
            while(entry.next != null){
                if (entry.getKey() == key){
                    entry.setValue(value);
                    return;
                }
                entry = entry.next;
            }

            if (entry.getKey() == key){
                entry.setValue(value);
                return;
            }
            entry.next = new Entry<>(key, value);
        }
    }

    public V get(K key){
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[Math.abs(hash)];
        if (entry == null){
            return null;
        }

        while (entry != null) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key){
        return !(this.get(key) == null);
//        int hash = key.hashCode() % SIZE;
//        Entry<K, V> entry = table[Math.abs(hash)];
//        if (entry == null){
//            return  false;
//        }
//        while (entry != null){
//            if (entry.getKey() == key){
//                return true;
//            }
//            entry = entry.next;
//        }
//        return false;
    }

    public Entry<K, V> remove(K key){
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[Math.abs(hash)];
        if (entry == null){
            return  null;
        }
        if (entry.getKey() == key){
            table[Math.abs(hash)] = entry.next;
            entry.next = null;
            return entry;
        }

        Entry<K,V> previous = entry;
        entry = entry.next;

        while(entry != null){
            if (entry.getKey() == key){
                previous.next = entry.next;
                entry.next = null;
                return entry;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;

    }



    private class Entry<K, V>{
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return  this.key;
        }

        public V getValue(){
            return this.value;
        }

        public void setValue(V value){
            this.value = value;
        }
    }
}