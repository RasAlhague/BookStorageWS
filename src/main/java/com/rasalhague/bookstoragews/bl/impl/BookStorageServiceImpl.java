package com.rasalhague.bookstoragews.bl.impl;

import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.dao.BookStorageDao;
import com.rasalhague.bookstoragews.model.Book;
import com.rasalhague.bookstoragews.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;

@Component
public class BookStorageServiceImpl implements BookStorageService {
    private final Logger logger = LoggerFactory.getLogger(BookStorageServiceImpl.class);

    @Autowired
    BookStorageDao bookStorageDao;

    @PostConstruct
    private void init() {
        // Also will be good to check existing storage on unmarshaling to prevent fake
        try {
            Path pathToBookStorage = Paths.get("BookStorage.xml");
            if (!Files.exists(pathToBookStorage)) {
                Files.copy(this.getClass()
                               .getClassLoader()
                               .getResourceAsStream("EmptyBookStorage.xml"),
                           pathToBookStorage,
                           StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

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
                       if (bookToUpdate.getDescription() != null)
                           book.setDescription(bookToUpdate.getDescription());
                       if (bookToUpdate.getGenre() != null)
                           book.setGenre(bookToUpdate.getGenre());
                       if (bookToUpdate.getPrice() != null)
                           book.setPrice(bookToUpdate.getPrice());
                       if (bookToUpdate.getPublish_date() != null)
                           book.setPublish_date(bookToUpdate.getPublish_date());
                       if (bookToUpdate.getTitle() != null)
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
