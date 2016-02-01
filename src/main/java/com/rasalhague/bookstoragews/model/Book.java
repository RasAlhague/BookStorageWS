package com.rasalhague.bookstoragews.model;

import javax.xml.bind.annotation.XmlElement;

public class Book {
    Integer id;
    String title;
    String genre;
    String price;
    String publish_date;
    String description;

    public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    @XmlElement
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    @XmlElement
    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }
}
