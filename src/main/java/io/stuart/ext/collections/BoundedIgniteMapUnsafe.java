package io.stuart.ext.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteSet;

public class BoundedIgniteMapUnsafe<K, V> implements Serializable {

    private static final long serialVersionUID = -7616509587410839589L;

    private final IgniteCache<K, V> cache;

    private final IgniteSet<K> set;

    private final int capacity;

    private volatile int size;

    public BoundedIgniteMapUnsafe(IgniteCache<K, V> cache, IgniteSet<K> set, int capacity) {
        this.cache = cache;
        this.set = set;
        this.capacity = capacity;

        List<K> removes = new ArrayList<>();
        this.set.forEach(item -> {
            if (!cache.containsKey(item)) {
                removes.add(item);
            }
        });

        // remove not existed keys
        this.set.removeAll(removes);
        // initialize cache size
        this.size = this.set.size();
    }

    public IgniteSet<K> getSet() {
        return set;
    }

    public boolean put(K key, V value) {
        if (key == null || value == null) {
            return false;
        }

        if (cache.containsKey(key)) {
            // put new value
            cache.put(key, value);
            // return true
            return true;
        }

        if (capacity > 0 && size < capacity) {
            // add key into set
            set.add(key);
            // put new value
            cache.put(key, value);

            // get size
            int s = size;
            // size + 1
            size = s + 1;

            // return true
            return true;
        } else {
            return false;
        }
    }

    public V remove(K key) {
        if (key == null) {
            return null;
        }

        // get and remove
        V result = cache.getAndRemove(key);
        // remove key from set
        set.remove(key);

        if (result != null) {
            // get size
            int s = size;
            // size - 1
            size = s - 1;
        }

        return result;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        return cache.get(key);
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return capacity > 0 && size >= capacity;
    }

}
