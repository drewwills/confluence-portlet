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
