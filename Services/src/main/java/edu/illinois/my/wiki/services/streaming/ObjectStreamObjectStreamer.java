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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.google.inject.Inject;

import edu.illinois.my.wiki.services.streaming.data.ExceptionOption;
import edu.illinois.my.wiki.services.wrappers.InputStreamInterface;
import edu.illinois.my.wiki.services.wrappers.OutputStreamInterface;

final class ObjectStreamObjectStreamer implements ObjectStreamer {
    @Inject
    ObjectStreamObjectStreamer() {}

    @Override
    public void sendObject(ExceptionOption<?> object,
            OutputStreamInterface stream) {
        try {
            throwingSendObject(object, stream);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public <T extends Serializable> ExceptionOption<T> receiveObject(
            InputStreamInterface stream) {
        try {
            return throwingReceiveObject(stream);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void throwingSendObject(ExceptionOption<?> object,
            OutputStreamInterface stream) throws IOException {
        ObjectOutputStream objectStream = createObjectStream(stream);
        objectStream.writeObject(object);
    }

    private <T extends Serializable> ExceptionOption<T> throwingReceiveObject(
            InputStreamInterface stream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream objectStream = createObjectStream(stream);
        @SuppressWarnings("unchecked")
        ExceptionOption<T> receivedObject =
                (ExceptionOption<T>) objectStream.readObject();
        return receivedObject;
    }

    private ObjectOutputStream createObjectStream(OutputStreamInterface stream)
            throws IOException {
        OutputStream unwrappedStream = stream.getOutputStream();
        return new ObjectOutputStream(unwrappedStream);
    }

    private ObjectInputStream createObjectStream(InputStreamInterface stream)
            throws IOException {
        InputStream unwrappedStream = stream.getInputStream();
        return new ObjectInputStream(unwrappedStream);
    }
}
