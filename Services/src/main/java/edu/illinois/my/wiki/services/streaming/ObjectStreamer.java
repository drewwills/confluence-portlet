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
