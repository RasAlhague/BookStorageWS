package com.rasalhague.bookstoragews.dao.impl;

import com.rasalhague.bookstoragews.dao.BookStorageDao;
import com.rasalhague.bookstoragews.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Repository
public class BookStorageDaoImpl implements BookStorageDao {
    private final Logger logger = LoggerFactory.getLogger(BookStorageDaoImpl.class);
    private final String bookStoragePath = "BookStorage.xml";

    private Unmarshaller um;
    private Marshaller m;
    private File file;

    public BookStorageDaoImpl() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Catalog.class);
            um = ctx.createUnmarshaller();
            m = ctx.createMarshaller();
            file = new File(bookStoragePath);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Catalog readCatalog() {
        Catalog catalog = new Catalog();
        try {
            catalog = (Catalog) um.unmarshal(file);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        return catalog;
    }

    @Override
    public Catalog writeCatalog(Catalog catalog) {
        try {
            m.marshal(catalog, file);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        return catalog;
    }
}
