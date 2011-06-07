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

import java.util.Map.Entry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import edu.illinois.my.helpers.DataObject;

final class ImmutableMapParameters extends DataObject<ImmutableMapParameters>
        implements Parameters {
    private final ImmutableMap<String, String> parameters;

    ImmutableMapParameters(ImmutableMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String get(String key) {
        String value = parameters.get(key);
        return value;
    }

    @Override
    public ImmutableList<NameValuePair> asNameValuePairs() {
        ImmutableList.Builder<NameValuePair> builder = ImmutableList.builder();
        ImmutableSet<Entry<String, String>> parameterKeys =
                parameters.entrySet();
        for (Entry<String, String> entry : parameterKeys) {
            String key = entry.getKey();
            String value = entry.getValue();
            NameValuePair pair = new BasicNameValuePair(key, value);
            builder.add(pair);
        }
        return builder.build();
    }

    @Override
    public ImmutableMap<String, String> asMap() {
        return parameters;
    }

    @Override
    protected void buildEquals(ImmutableMapParameters rhs,
            EqualsBuilder equalsBuilder) {
        equalsBuilder.append(parameters, rhs.parameters);
    }

    @Override
    protected void buildHashCode(HashCodeBuilder hashCodeBuilder) {
        hashCodeBuilder.append(parameters);
    }

    @Override
    protected void buildToString(ToStringBuilder toStringBuilder) {
        toStringBuilder.append("parameters", parameters);
    }

    @Override
    protected int getInitialPrime() {
        return 29934571;
    }

    @Override
    protected int getMultiplierPrime() {
        return 1375028513;
    }

    @Override
    public boolean contains(String key) {
        return parameters.containsKey(key);
    }
}
