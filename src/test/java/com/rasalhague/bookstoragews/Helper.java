package com.rasalhague.bookstoragews;

import com.rasalhague.bookstoragews.model.Book;
import com.rasalhague.bookstoragews.model.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;

public class Helper {
    public static String catalogToXml(Catalog catalog) throws JAXBException {
        StringWriter sw = new StringWriter();
        Marshaller m = JAXBContext.newInstance(Catalog.class).createMarshaller();
        m.marshal(catalog, sw);
        return sw.toString();
    }

    public static Catalog generateTestCatalog() {
        ArrayList<Book> list = new ArrayList<>();

        Book book = new Book();
        book.setId(1);
        book.setDescription("TestCatalog");
        book.setGenre("TestCatalog");
        list.add(book);

        book = new Book();
        book.setId(2);
        book.setDescription("TestCatalog");
        book.setGenre("TestCatalog");
        list.add(book);

        Catalog catalog = new Catalog();
        catalog.setCatalog(list);

        return catalog;
    }
}
