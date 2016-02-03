package com.rasalhague.bookstoragews.bl.impl;

import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.dao.BookStorageDao;
import com.rasalhague.bookstoragews.model.Book;
import com.rasalhague.bookstoragews.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class BookStorageServiceImpl implements BookStorageService {
    @Autowired
    BookStorageDao bookStorageDao;

    @Override
    public Catalog readCatalog() {
        return bookStorageDao.readCatalog();
    }

    @Override
    public Catalog updateCatalog(Catalog catalog) {
        Catalog wholeCatalog = bookStorageDao.readCatalog();

        catalog.getCatalog()
               .stream() // parallel stream will produce ConcurrentModEx
               .forEach(book -> {
                   if (!updateBookIfExist(wholeCatalog, book)) {
                       createBookInCatalog(wholeCatalog, book);
                   }
               });

        bookStorageDao.writeCatalog(wholeCatalog);

        return wholeCatalog;
    }

    @Override
    public Catalog deleteBooks(Catalog catalog) {
        Catalog wholeCatalog = bookStorageDao.readCatalog();
        for (Book book : catalog.getCatalog()) {
            for (Iterator<Book> it = wholeCatalog.getCatalog().iterator(); it.hasNext(); ) {
                Book potentialBookToDel = it.next();
                if (book.getId().equals(potentialBookToDel.getId())) {
                    it.remove();
                }
            }
        }
        bookStorageDao.writeCatalog(wholeCatalog);

        return wholeCatalog;
    }

    private boolean updateBookIfExist(Catalog catalogToUpdate, Book bookToUpdate) {
        final boolean[] isUpdated = {false};
        List<Book> catalog = catalogToUpdate.getCatalog();
        catalog.parallelStream()
               .forEach(book -> {
                   if (book.getId().equals(bookToUpdate.getId())) {
                       book.setDescription(bookToUpdate.getDescription());
                       book.setGenre(bookToUpdate.getGenre());
                       book.setPrice(bookToUpdate.getPrice());
                       book.setPublish_date(bookToUpdate.getPublish_date());
                       book.setTitle(bookToUpdate.getTitle());

                       isUpdated[0] = true;
                   }
               });

        return isUpdated[0];
    }

    private Catalog createBookInCatalog(Catalog catalogToUpdate, Book bookToAdd) {
        catalogToUpdate.getCatalog().add(bookToAdd);

        return catalogToUpdate;
    }
}
