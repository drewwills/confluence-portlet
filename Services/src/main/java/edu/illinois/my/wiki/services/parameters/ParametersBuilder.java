package edu.illinois.my.wiki.services.parameters;

import java.util.Map;

public interface ParametersBuilder {
    void add(Map<String, String> parameters);

    void addFirstValues(Map<String, String[]> parameters);

    void add(String key, String value);

    void add(Parameters parameters);

    Parameters build();
}
