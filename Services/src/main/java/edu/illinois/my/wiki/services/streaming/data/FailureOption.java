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
package edu.illinois.my.wiki.services.streaming.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.base.Preconditions;

import edu.illinois.my.helpers.DataObject;

public final class FailureOption<T extends Serializable> extends
        DataObject<FailureOption<T>> implements ExceptionOption<T> {
    private final Exception exception;

    private static final long serialVersionUID = 1L;

    public FailureOption(Exception exception) {
        this.exception = Preconditions.checkNotNull(exception);
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public T getObject() {
        throw new IllegalStateException(
                "Always check wasExceptionThrow. It was in this case.");
    }

    @Override
    public boolean wasExceptionThrown() {
        return true;
    }

    @Override
    protected void buildEquals(FailureOption<T> rhs, EqualsBuilder equalsBuilder) {
        equalsBuilder.append(exception, rhs.exception);
    }

    @Override
    protected void buildHashCode(HashCodeBuilder hashCodeBuilder) {
        hashCodeBuilder.append(exception);
    }

    @Override
    protected void buildToString(ToStringBuilder toStringBuilder) {
        toStringBuilder.append("exception", exception);
    }

    @Override
    protected int getInitialPrime() {
        return 1308462619;
    }

    @Override
    protected int getMultiplierPrime() {
        return 1574301119;
    }
}
