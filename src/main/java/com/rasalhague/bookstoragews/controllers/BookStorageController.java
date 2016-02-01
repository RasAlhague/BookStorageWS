package com.rasalhague.bookstoragews.controllers;

import com.rasalhague.bookstoragews.bl.Catalog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class BookStorageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Catalog getCatalog() {
        return new Catalog();
    }

    @RequestMapping(value = "/changeBook", method = RequestMethod.PUT)
    public Catalog changeBook() {
        return getCatalog();
    }
}
