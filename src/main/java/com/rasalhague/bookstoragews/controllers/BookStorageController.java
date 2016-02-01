package com.rasalhague.bookstoragews.controllers;

import com.rasalhague.bookstoragews.model.Catalog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class BookStorageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Catalog> getCatalog() {
        return new ResponseEntity<>(new Catalog(), HttpStatus.OK);
    }

    @RequestMapping(value = "/changeBook", method = RequestMethod.PUT)
    public ResponseEntity<Catalog> changeBook(@RequestBody Catalog catalog) {
        return getCatalog();
    }
}
