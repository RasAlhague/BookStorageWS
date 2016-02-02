package com.rasalhague.bookstoragews.bl;

import com.rasalhague.bookstoragews.bl.impl.BookStorageServiceImpl;
import com.rasalhague.bookstoragews.dao.BookStorageDao;
import com.rasalhague.bookstoragews.model.Catalog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookStorageServiceTest {
    @Mock
    BookStorageDao bookStorageDao;

    @InjectMocks
    BookStorageService bookStorageService;

    @Before
    public void setup() {
        bookStorageService = new BookStorageServiceImpl();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readCatalogTest() {
        Catalog catalog = new Catalog();

        when(bookStorageDao.readCatalog()).thenReturn(catalog);

        Catalog readCatalog = bookStorageService.readCatalog();

        verify(bookStorageDao).readCatalog();
        Assert.assertEquals(readCatalog, catalog);
    }

    @Test
    public void updateCatalogTest() {
        Catalog catalog = new Catalog();

        when(bookStorageDao.writeCatalog(any())).thenReturn(any());

        bookStorageService.updateCatalog(catalog);

        verify(bookStorageDao).writeCatalog(any());
    }
}
