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
package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import edu.illinois.my.wiki.services.streaming.ObjectStreamer;
import edu.illinois.my.wiki.services.streaming.data.ExceptionOption;
import edu.illinois.my.wiki.services.wrappers.OutputStreamInterface;
import edu.illinois.my.wiki.services.wrappers.OutputStreamWrapper;

final class SendExceptionOptionResponse implements ServletActionResponse {
    private final ExceptionOption<?> objectToSend;
    private final ObjectStreamer streamer;

    private static final String SERIALIZED_CONTENT_TYPE =
            "application/x-java-serialized-object";

    SendExceptionOptionResponse(ExceptionOption<?> objectResponse,
            ObjectStreamer streamer) {
        this.objectToSend = objectResponse;
        this.streamer = streamer;
    }

    @Override
    public void send(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setContentType(SERIALIZED_CONTENT_TYPE);
        OutputStreamInterface responseStream = null;
        try {
            responseStream = getOutputStream(httpResponse);
            streamer.sendObject(objectToSend, responseStream);
            responseStream.flush();
        } finally {
            if (responseStream != null)
                responseStream.close();
        }
    }

    private OutputStreamInterface getOutputStream(
            HttpServletResponse httpResponse) throws IOException {
        return new OutputStreamWrapper(httpResponse.getOutputStream());
    }
}
