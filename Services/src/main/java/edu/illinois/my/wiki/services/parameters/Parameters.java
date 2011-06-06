package edu.illinois.my.wiki.services.parameters;

import org.apache.http.NameValuePair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public interface Parameters {
    String get(String key);

    boolean contains(String key);

    ImmutableList<NameValuePair> asNameValuePairs();

    ImmutableMap<String, String> asMap();
}
