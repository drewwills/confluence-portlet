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
