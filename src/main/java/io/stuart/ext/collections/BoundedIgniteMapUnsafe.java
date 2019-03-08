/*
 * Copyright 2019 Yang Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

        if (capacity > 0 && set.size() < capacity) {
            // add key into set
            set.add(key);
            // put new value
            cache.put(key, value);

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

        // return result
        return result;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        return cache.get(key);
    }

    public int size() {
        return set.size();
    }

    public boolean isFull() {
        return capacity > 0 && set.size() >= capacity;
    }

}
