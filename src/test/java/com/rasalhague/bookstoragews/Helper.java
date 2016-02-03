package com.rasalhague.bookstoragews;

import com.rasalhague.bookstoragews.model.Book;
import com.rasalhague.bookstoragews.model.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

public class Helper {
    public static final String DEFAULT_XML_STRING = "<?xml version=\"1.0\"?>\n" +
            "<catalog>\n" +
            "   <book id=\"bk101\">\n" +
            "      <author>Gambardella, Matthew</author>\n" +
            "      <title>XML Developer's Guide</title>\n" +
            "      <genre>Computer</genre>\n" +
            "      <price>44.95</price>\n" +
            "      <publish_date>2000-10-01</publish_date>\n" +
            "      <description>An in-depth look at creating applications \n" +
            "      with XML.</description>\n" +
            "   </book>\n" +
            "   <book id=\"bk102\">\n" +
            "      <author>Ralls, Kim</author>\n" +
            "      <title>Midnight Rain</title>\n" +
            "      <genre>Fantasy</genre>\n" +
            "      <price>5.95</price>\n" +
            "      <publish_date>2000-12-16</publish_date>\n" +
            "      <description>A former architect battles corporate zombies, \n" +
            "      an evil sorceress, and her own childhood to become queen \n" +
            "      of the world.</description>\n" +
            "   </book>\n" +
            "</catalog>\n";

    public static String catalogToXml(Catalog catalog) throws JAXBException {
        StringWriter sw = new StringWriter();
        Marshaller m = JAXBContext.newInstance(Catalog.class).createMarshaller();
        m.marshal(catalog, sw);
        return sw.toString();
    }

    public static Catalog XmlToCatalog(String xml) throws JAXBException {
        StringReader sr = new StringReader(xml);
        Unmarshaller unmarshaller = JAXBContext.newInstance(Catalog.class).createUnmarshaller();
        return (Catalog) unmarshaller.unmarshal(sr);
    }

    public static Catalog getDefaultCatalog() throws JAXBException {
        return XmlToCatalog(DEFAULT_XML_STRING);
    }

    public static Catalog generateTestCatalog() {
        ArrayList<Book> list = new ArrayList<>();

        Book book = new Book();
        book.setId("1");
        book.setDescription("TestCatalog");
        book.setGenre("TestCatalog");
        list.add(book);

        book = new Book();
        book.setId("2");
        book.setDescription("TestCatalog");
        book.setGenre("TestCatalog");
        list.add(book);

        Catalog catalog = new Catalog();
        catalog.setCatalog(list);

        return catalog;
    }
}
