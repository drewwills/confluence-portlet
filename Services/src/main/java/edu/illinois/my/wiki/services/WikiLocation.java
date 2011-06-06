package edu.illinois.my.wiki.services;

import java.io.Serializable;
import java.util.Date;

public interface WikiLocation extends Serializable {
    String getName();

    String getUrlPath();

    Date getLastModificationDate();

    String getLastModificationDateString();

    String getLastModifier();
}
