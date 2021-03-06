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
package com.expediagroup.rhapsody.core.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;

import com.expediagroup.rhapsody.core.factory.FieldwiseConfigFactory;
import com.expediagroup.rhapsody.util.ConfigLoading;

public final class LoggingConfig {

    private final String category;

    private final Level onNext;

    private final Level onError;

    public LoggingConfig(String category, Level onNext, Level onError) {
        this.category = category;
        this.onNext = onNext;
        this.onError = onError;
    }

    public String getCategory() {
        return category;
    }

    public Level getOnNext() {
        return onNext;
    }

    public Level getOnError() {
        return onError;
    }

    public static final class Factory extends FieldwiseConfigFactory<LoggingConfig> {

        public Factory() {
            super(LoggingConfig.class);
        }

        public LoggingConfig create(Class categoryClass) {
            return copyInto(Factory::new).withCategory(categoryClass).create();
        }

        public Factory withCategory(Class categoryClass) {
            put("category", categoryClass.getName());
            return this;
        }

        public Factory withOnNext(Level onNext) {
            put("onNext", onNext);
            return this;
        }

        @Override
        protected Map<String, Object> createDefaults() {
            Map<String, Object> defaults = new HashMap<>();
            defaults.put("category", LoggingConfig.class.getName());
            defaults.put("onNext", Level.FINE);
            defaults.put("onError", Level.SEVERE);
            return defaults;
        }

        @Override
        protected LoggingConfig construct(Map<String, Object> configs) {
            return new LoggingConfig(
                ConfigLoading.loadOrThrow(configs, "category", Function.identity()),
                ConfigLoading.loadOrThrow(configs, "onNext", Level::parse),
                ConfigLoading.loadOrThrow(configs, "onError", Level::parse)
            );
        }
    }
}
