package com.rasalhague.bookstoragews.model;

import javax.xml.bind.annotation.XmlElement;

public class Book {
    Integer id;
    String title;
    String genre;
    String price;
    String publish_date;
    String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (publish_date != null ? !publish_date.equals(book.publish_date) : book.publish_date != null) return false;
        return description != null ? description.equals(book.description) : book.description == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (publish_date != null ? publish_date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

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
