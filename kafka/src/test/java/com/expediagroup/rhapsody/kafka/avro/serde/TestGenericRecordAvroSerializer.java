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
package com.expediagroup.rhapsody.kafka.avro.serde;

import java.io.IOException;

import org.apache.avro.Schema;

import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;

public final class TestGenericRecordAvroSerializer extends GenericRecordAvroSerializer {

    private final TestSchemaRegistry registry;

    public TestGenericRecordAvroSerializer(TestSchemaRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int register(String subject, Schema schema) throws IOException, RestClientException {
        return registry.register(subject, schema);
    }

    @Override
    public Schema getById(int id) throws IOException, RestClientException {
        return registry.getByID(id);
    }
}
