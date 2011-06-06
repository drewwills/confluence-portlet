package edu.illinois.my.wiki.services.wrappers;

import java.io.Closeable;
import java.io.Flushable;
import java.io.OutputStream;

public interface OutputStreamInterface extends Flushable, Closeable {
    OutputStream getOutputStream();
}
