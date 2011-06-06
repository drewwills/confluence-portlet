package edu.illinois.my.wiki.services.wrappers;

import java.io.IOException;
import java.io.OutputStream;

public final class OutputStreamWrapper implements OutputStreamInterface {
    private final OutputStream stream;

    public OutputStreamWrapper(OutputStream stream) {
        this.stream = stream;
    }

    @Override
    public OutputStream getOutputStream() {
        return stream;
    }

    @Override
    public void flush() throws IOException {
        stream.flush();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
