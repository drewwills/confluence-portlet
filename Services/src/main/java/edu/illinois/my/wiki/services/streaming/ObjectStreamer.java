package edu.illinois.my.wiki.services.streaming;

import java.io.Serializable;

import edu.illinois.my.wiki.services.streaming.data.ExceptionOption;
import edu.illinois.my.wiki.services.wrappers.InputStreamInterface;
import edu.illinois.my.wiki.services.wrappers.OutputStreamInterface;

public interface ObjectStreamer {
    /**
     * Sends an object through an OutputStream.
     * 
     * @param object to be sent
     * @param stream where the object is sent
     * @throws RuntimeException holding IOException if the stream throws it
     */
    void sendObject(ExceptionOption<?> object, OutputStreamInterface stream);

    /**
     * Receives an object through an InputStream
     * 
     * @param stream where the object comes from
     * @return the reconstructed object
     * @throws Runtime exception holding ClassNotFoundException if no matching
     *         class can be loaded or IOException if the stream throws it
     */
    <T extends Serializable> ExceptionOption<T> receiveObject(
            InputStreamInterface stream);
}
