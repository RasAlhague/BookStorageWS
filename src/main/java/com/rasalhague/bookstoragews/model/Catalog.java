package com.rasalhague.bookstoragews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Catalog {
    List<Book> catalog;

    public List<Book> getCatalog() {
        return catalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog1 = (Catalog) o;

        return catalog != null ? catalog.equals(catalog1.catalog) : catalog1.catalog == null;

    }

    @Override
    public int hashCode() {
        return catalog != null ? catalog.hashCode() : 0;
    }

    @XmlElement(name = "book")
    public void setCatalog(List<Book> catalog) {
        this.catalog = catalog;
    }
}
