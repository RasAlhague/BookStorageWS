package com.rasalhague.bookstoragews.dao;

import com.rasalhague.bookstoragews.Helper;
import com.rasalhague.bookstoragews.dao.impl.BookStorageDaoImpl;
import com.rasalhague.bookstoragews.model.Catalog;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BookStorageDaoTest {
    private static BookStorageDao bookStorageDao;

    @BeforeClass
    public static void setUp() {
        bookStorageDao = new BookStorageDaoImpl();
    }

    @Before
    public void before() throws IOException {
        Helper.reloadBookStorageFromResources();
    }

    @Test
    public void bookStorageDaoTest() throws Exception {
        Catalog catalog = bookStorageDao.readCatalog();
        catalog.getCatalog().remove(1);
        bookStorageDao.writeCatalog(catalog);
        Catalog changedCatalog = bookStorageDao.readCatalog();
        assertEquals(changedCatalog, catalog);

        catalog.getCatalog().addAll(Helper.generateTestCatalog().getCatalog());
        bookStorageDao.writeCatalog(catalog);
        changedCatalog = bookStorageDao.readCatalog();
        assertEquals(changedCatalog, catalog);

        catalog.getCatalog().clear();
        catalog.getCatalog().addAll(Helper.getDefaultCatalog().getCatalog());
        bookStorageDao.writeCatalog(catalog);
        changedCatalog = bookStorageDao.readCatalog();
        assertEquals(changedCatalog, catalog);
    }
}
