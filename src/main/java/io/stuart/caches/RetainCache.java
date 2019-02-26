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

package io.stuart.caches;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.RetainCacheImpl;
import io.stuart.entities.cache.MqttRetainMessage;

public interface RetainCache {

    static RetainCache create(Ignite ignite, CacheConfiguration<String, MqttRetainMessage> cfg) {
        return new RetainCacheImpl(ignite, cfg);
    }

    void save(MqttRetainMessage message);

    boolean delete(String topic);

    List<MqttRetainMessage> get(String topic, int qos);

    int size();

}
