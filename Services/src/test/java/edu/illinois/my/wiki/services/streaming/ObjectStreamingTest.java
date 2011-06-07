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
package edu.illinois.my.wiki.services.streaming;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;

import edu.illinois.my.wiki.services.streaming.data.ExceptionOption;
import edu.illinois.my.wiki.services.streaming.data.SuccessOption;
import edu.illinois.my.wiki.services.wrappers.InputStreamWrapper;
import edu.illinois.my.wiki.services.wrappers.OutputStreamWrapper;

public class ObjectStreamingTest {
    ArrayListMultimap<String, String> map;
    ByteArrayOutputStream outputStream;
    ObjectStreamer streamer = new ObjectStreamObjectStreamer();

    @Before
    public void simpleObjectSetup() throws IOException {
        map = ArrayListMultimap.create();
        map.put("NetID", "jenkins");
        outputStream = new ByteArrayOutputStream();
        streamer.sendObject(
                new SuccessOption<ArrayListMultimap<String, String>>(map),
                new OutputStreamWrapper(outputStream));
        outputStream.flush();
    }

    @Test
    public void shouldSerialize() throws IOException {
        assertTrue(outputStream.size() > 0);
    }

    @Test
    public void shouldUnserialize() throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());
        ExceptionOption<ArrayListMultimap<String, String>> mapHolder =
                streamer.receiveObject(new InputStreamWrapper(inputStream));
        assertThat(mapHolder.getObject(), equalTo(map));
    }
}
