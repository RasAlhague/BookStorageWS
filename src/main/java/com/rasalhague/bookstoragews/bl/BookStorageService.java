package com.rasalhague.bookstoragews.bl;

import com.rasalhague.bookstoragews.model.Catalog;

public interface BookStorageService {
    Catalog readCatalog();

    Catalog updateCatalog(Catalog catalog);

    Catalog deleteBooks(Catalog catalog);
}
