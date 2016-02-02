package com.rasalhague.bookstoragews.dao;

import com.rasalhague.bookstoragews.model.Catalog;

public interface BookStorageDao {
    Catalog readCatalog();

    Catalog writeCatalog(Catalog catalog);
}
