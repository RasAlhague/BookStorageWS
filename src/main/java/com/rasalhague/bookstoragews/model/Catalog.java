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

    @XmlElement(name = "book")
    public void setCatalog(List<Book> catalog) {
        this.catalog = catalog;
    }
}
