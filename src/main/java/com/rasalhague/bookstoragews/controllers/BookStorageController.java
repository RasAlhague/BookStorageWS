package com.rasalhague.bookstoragews.controllers;

import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class BookStorageController {
    @Autowired
    BookStorageService bookStorageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Catalog> getCatalog() {
        return new ResponseEntity<>(bookStorageService.readCatalog(), HttpStatus.OK);
    }

    @RequestMapping(value = "/changeBook", method = RequestMethod.PUT)
    public ResponseEntity<Catalog> createUpdateRead(@RequestBody Catalog catalog) {
        Catalog catalogToReturn = catalog.getCatalog().isEmpty() ? bookStorageService.readCatalog() : bookStorageService.updateCatalog(catalog);
        return new ResponseEntity<>(catalogToReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/changeBook", method = RequestMethod.DELETE)
    public ResponseEntity<Catalog> deleteFromCatalog(@RequestBody Catalog catalog) {
        Catalog catalogToReturn = bookStorageService.deleteBooks(catalog);
        return new ResponseEntity<>(catalogToReturn, HttpStatus.OK);
    }
}
