package edu.illinois.my.wiki.services.wrappers;

import java.io.Closeable;
import java.io.InputStream;

public interface InputStreamInterface extends Closeable {
    InputStream getInputStream();
}
