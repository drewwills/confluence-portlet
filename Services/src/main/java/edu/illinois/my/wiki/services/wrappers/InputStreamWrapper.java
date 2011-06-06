package edu.illinois.my.wiki.services.wrappers;

import java.io.IOException;
import java.io.InputStream;

public final class InputStreamWrapper implements InputStreamInterface {
    private final InputStream inputStream;

    public InputStreamWrapper(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
