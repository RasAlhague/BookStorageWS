package com.rasalhague.bookstoragews.bl.impl;

import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.dao.BookStorageDao;
import com.rasalhague.bookstoragews.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookStorageServiceImpl implements BookStorageService {
    @Autowired
    BookStorageDao bookStorageDao;

    @Override
    public Catalog readCatalog() {
        return null;
    }

    @Override
    public Catalog updateCatalog(Catalog catalog) {
        return null;
    }

    @Override
    public Catalog deleteBooks(Catalog catalog) {
        return null;
    }
}
