package com.rasalhague.bookstoragews.dao;

import com.rasalhague.bookstoragews.dao.impl.BookStorageDaoImpl;
import com.rasalhague.bookstoragews.model.Catalog;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookStorageDaoTest {
    private static BookStorageDao bookStorageDao;

    @BeforeClass
    public static void setUp() {
        bookStorageDao = new BookStorageDaoImpl();
    }

    @Test
    public void bookStorageDaoTest() {
        Catalog catalog = bookStorageDao.readCatalog();
        catalog.getCatalog().remove(1);

        bookStorageDao.writeCatalog(catalog);

        Catalog changedCatalog = bookStorageDao.readCatalog();

        assertEquals(changedCatalog, catalog);
    }
}
