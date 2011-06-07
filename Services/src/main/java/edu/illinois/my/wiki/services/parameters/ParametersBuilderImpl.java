/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.illinois.my.wiki.services.parameters;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public final class ParametersBuilderImpl implements ParametersBuilder {
    private final Builder<String, String> builder = ImmutableMap.builder();

    public ParametersBuilderImpl() {}

    @Override
    public void add(Map<String, String> parameters) {
        builder.putAll(parameters);
    }

    @Override
    public void addFirstValues(Map<String, String[]> parameters) {
        for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
            String[] values = parameter.getValue();
            if (values.length != 0) {
                String key = parameter.getKey();
                String firstValue = values[0];
                builder.put(key, firstValue);
            }
        }
    }

    @Override
    public void add(String key, String value) {
        builder.put(key, value);
    }

    @Override
    public void add(Parameters parameters) {
        add(parameters.asMap());
    }

    @Override
    public Parameters build() {
        return new ImmutableMapParameters(builder.build());
    }
}
