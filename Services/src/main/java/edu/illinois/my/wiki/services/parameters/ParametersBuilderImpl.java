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
