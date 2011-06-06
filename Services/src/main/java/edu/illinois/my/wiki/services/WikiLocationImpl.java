package edu.illinois.my.wiki.services;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import edu.illinois.my.helpers.DataObject;

public final class WikiLocationImpl extends DataObject<WikiLocationImpl>
        implements WikiLocation {
    private final String title;
    private final String urlPath;
    private final long lastModificationDate;
    private final String lastModifier;

    private static final long serialVersionUID = 1L;

    public WikiLocationImpl(String title, String urlPath,
            long lastModificationDate, String lastModifier) {
        this.title = title;
        this.urlPath = urlPath;
        this.lastModificationDate = lastModificationDate;
        this.lastModifier = lastModifier;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getUrlPath() {
        return urlPath;
    }

    @Override
    public Date getLastModificationDate() {
        return new Date(lastModificationDate);
    }

    @Override
    public String getLastModificationDateString() {
        DateFormat formatter =
                DateFormat.getDateTimeInstance(DateFormat.LONG,
                        DateFormat.SHORT);
        Date modifiedDate = getLastModificationDate();
        return formatter.format(modifiedDate);
    }

    @Override
    public String getLastModifier() {
        return lastModifier;
    }

    @Override
    protected void buildToString(ToStringBuilder builder) {
        builder.append("title", title);
        builder.append("urlPath", urlPath);
        builder.append("lastModificationDate", lastModificationDate);
        builder.append("lastModifier", lastModifier);
    }

    @Override
    protected void buildEquals(WikiLocationImpl rhs, EqualsBuilder builder) {
        builder.append(title, rhs.title);
        builder.append(urlPath, rhs.urlPath);
        builder.append(lastModificationDate, rhs.lastModificationDate);
        builder.append(lastModifier, rhs.lastModifier);
    }

    @Override
    protected void buildHashCode(HashCodeBuilder builder) {
        builder.append(title);
        builder.append(urlPath);
        builder.append(lastModificationDate);
        builder.append(lastModifier);
    }

    @Override
    protected int getInitialPrime() {
        return 918386479;
    }

    @Override
    protected int getMultiplierPrime() {
        return 1744012727;
    }
}
