package edu.illinois.my.wiki.services.streaming.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.base.Preconditions;

import edu.illinois.my.helpers.DataObject;

public final class SuccessOption<T extends Serializable> extends
        DataObject<SuccessOption<T>> implements ExceptionOption<T> {
    private final T object;

    private static final long serialVersionUID = 1L;

    public SuccessOption(T object) {
        this.object = Preconditions.checkNotNull(object);
    }

    @Override
    public Exception getException() {
        throw new IllegalStateException(
                "Always check wasExceptionThrow. It was not in this case.");
    }

    @Override
    public T getObject() {
        return object;
    }

    @Override
    public boolean wasExceptionThrown() {
        return false;
    }

    @Override
    protected void buildEquals(SuccessOption<T> rhs, EqualsBuilder equalsBuilder) {
        equalsBuilder.append(object, rhs.object);
    }

    @Override
    protected void buildHashCode(HashCodeBuilder hashCodeBuilder) {
        hashCodeBuilder.append(object);
    }

    @Override
    protected void buildToString(ToStringBuilder toStringBuilder) {
        toStringBuilder.append("object", object);
    }

    @Override
    protected int getInitialPrime() {
        return 646207823;
    }

    @Override
    protected int getMultiplierPrime() {
        return 475044281;
    }
}
