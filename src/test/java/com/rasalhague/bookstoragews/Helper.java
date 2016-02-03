package com.rasalhague.bookstoragews;

import com.rasalhague.bookstoragews.model.Book;
import com.rasalhague.bookstoragews.model.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Helper {
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
        InputStream is = IntegrationTest.class.getClassLoader().getResourceAsStream("BookStorage.xml");
        String s = new java.util.Scanner(is).useDelimiter("\\A").next();
        return XmlToCatalog(s);
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

    public static void reloadBookStorageFromResources() throws IOException {
        Files.copy(IntegrationTest.class.getClassLoader().getResourceAsStream("BookStorage.xml"),
                   Paths.get("BookStorage.xml"),
                   StandardCopyOption.REPLACE_EXISTING);
    }
}
