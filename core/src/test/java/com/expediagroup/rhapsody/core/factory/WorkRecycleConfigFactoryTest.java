/**
 * Copyright 2019 Expedia, Inc.
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
package com.expediagroup.rhapsody.core.factory;

import java.time.Duration;

import org.junit.Test;

import com.expediagroup.rhapsody.core.work.WorkRecycleConfig;

import static org.junit.Assert.assertEquals;

public class WorkRecycleConfigFactoryTest {

    @Test
    public void configCanBeCreatedWithFieldNames() {
        WorkRecycleConfig.Factory factory = new WorkRecycleConfig.Factory();
        factory.put("recycleExpiration", "P4D");
        factory.put("maxRecycleCount", 20);

        WorkRecycleConfig config = factory.create();

        assertEquals(Duration.ofDays(4L), config.getRecycleExpiration());
        assertEquals(20L, config.getMaxRecycleCount());
    }
}