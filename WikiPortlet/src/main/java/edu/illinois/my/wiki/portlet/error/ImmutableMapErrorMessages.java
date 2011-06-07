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
package edu.illinois.my.wiki.portlet.error;

import com.google.common.collect.ImmutableMap;

public final class ImmutableMapErrorMessages implements ErrorMessages {
    private final ImmutableMap<String, String> errorMessages;

    public ImmutableMapErrorMessages(ImmutableMap<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public String get(ErrorMessageKey errorKey) {
        String mapKey = errorKey.toString();
        if (errorMessages.containsKey(mapKey)) {
            return errorMessages.get(mapKey);
        } else {
            throw new IllegalArgumentException(
                    "The following error key does not have a message: "
                            + errorKey);
        }
    }
}
