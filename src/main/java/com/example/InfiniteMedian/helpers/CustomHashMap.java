package com.example.InfiniteMedian.helpers;

/**
 * Custom HashMap implementation
 * @param <K> Type of the key
 * @param <V> Type of the value
 */
public class CustomHashMap<K, V>{

    private Entry<K, V> table[];
    private int size;

    private final int SIZE = 2048;


    /**
     * Instantiates new {@link CustomHashMap} with {@link CustomHashMap#size} zero
     * and table size of {@link CustomHashMap#SIZE}
     */
    public CustomHashMap(){
        this.table = new Entry[SIZE];
        this.size = 0;
    }

    /**
     * Generates a perfect hash for the possible symbols from
     * Binance crypto trade platform
     * @param key The string to hash, The exchange trade symbol name for our case
     * @return A perfect hash for the key (symbol)
     */
    public int getHash(K key){
        String _key = key.toString().toLowerCase();
        /**
         * Prime number are chosen because one can not be a factor of another,
         * and these specific primes are chosen because sum of any multiples of any
         * combinations of the numbers in the list does not result in integer multiples
         * of the others, which makes the hash function perfect when the calculation below
         * is performed
         */
        int[] specialPrimesList = {41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        int hash = 0;
        for (int i=0; i<_key.length() && i<13; i++){
            hash+= ((int) _key.charAt(i)) * specialPrimesList[i];
        }
        return hash;
    }

    /**
     * Get size of the hashmap
     * @return size of the hashmap
     */
    public int size(){
        return  size;
    }

    /**
     * Puts new entry to the HashMap
     * @param key key of the entry
     * @param value value to put
     */
    public void put(K key, V value){
        int hash = getHash(key) % SIZE;
        Entry<K, V> entry = table[hash];
        if (entry == null){
            table[hash] = new Entry<>(key, value);
        }else{
            while(entry.next != null){
                if (entry.getKey().equals(key)){
                    entry.setValue(value);
                    return;
                }
                entry = entry.next;
            }

            if (entry.getKey().equals(key)){
                entry.setValue(value);
                return;
            }
            entry.next = new Entry<>(key, value);
        }
        this.size++;
    }

    /**
     * Gets value from the hashmap
     * @param key key of the entry
     * @return null if no entry is found by the specified key,
     * and returns the value of the key is found
     */
    public V get(K key){
        int hash = getHash(key) % SIZE;
        Entry<K, V> entry = table[hash];
        if (entry == null){
            return null;
        }

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * Checks if the hashmap contains the desired key
     * @param key the key to search for
     * @return true if entry is found with the specified key, false otherwise
     */
    public boolean containsKey(K key){
        return (get(key) != null);
//        int hash = getHash(key) % SIZE;
//        Entry<K, V> entry = table[hash];
//        if (entry == null){
//            return  false;
//        }
//        while (entry != null){
//            if (entry.getKey().equals(key)){
//                return true;
//            }
//            entry = entry.next;
//        }
//        return false;
    }

    /**
     * Removes entry from the hashmap and returns the removed value
     * @param key the key to look for in the hashmap
     * @return null if no key is found, otherwise the removed entry
     */
    public Entry<K, V> remove(K key){
        int hash = getHash(key) % SIZE;
        Entry<K, V> entry = table[hash];
        if (entry == null){
            return  null;
        }
        if (entry.getKey().equals(key)){
            table[hash] = entry.next;
            entry.next = null;
            return entry;
        }

        Entry<K,V> previous = entry;
        entry = entry.next;

        while(entry != null){
            if (entry.getKey().equals(key)){
                previous.next = entry.next;
                entry.next = null;
                return entry;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;

    }

    /**
     * LinkedList kind of class that will be an entry for the custom hashmap
     * @param <K> Type of the key
     * @param <V> Type of the value
     */
    private class Entry<K, V>{
        private K key;
        private V value;
        public Entry<K, V> next;


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

