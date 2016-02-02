package com.rasalhague.bookstoragews.controllers;

import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.model.Book;
import com.rasalhague.bookstoragews.model.Catalog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookStorageControllerTest {
    @Mock
    BookStorageService bookStorageService;

    @InjectMocks
    BookStorageController bookStorageController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookStorageController).build();
    }

    private String catalogToXml(Catalog catalog) throws JAXBException {
        StringWriter sw = new StringWriter();
        Marshaller m = JAXBContext.newInstance(Catalog.class).createMarshaller();
        m.marshal(catalog, sw);
        return sw.toString();
    }

    private Catalog generateTestCatalog() {
        ArrayList<Book> list = new ArrayList<>();

        Book book = new Book();
        book.setId(1);
        book.setDescription("TestCatalog");
        book.setGenre("TestCatalog");
        list.add(book);

        book = new Book();
        book.setId(2);
        book.setDescription("TestCatalog");
        book.setGenre("TestCatalog");
        list.add(book);

        Catalog catalog = new Catalog();
        catalog.setCatalog(list);

        return catalog;
    }

    @Test
    public void testChangeBook_RequestEmptyXML() throws Exception {
        Catalog catalog = generateTestCatalog();

        String emptyXmlToSend = catalogToXml(new Catalog());
        String haveToReturn = catalogToXml(catalog);

        when(bookStorageService.readCatalog()).thenReturn(catalog);

        mockMvc.perform(put("/changeBook").contentType(MediaType.APPLICATION_XML).content(emptyXmlToSend))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(haveToReturn))
               .andDo(print());

        verify(bookStorageService).readCatalog();
    }

    @Test
    public void testChangeBook_RequestUpdate() throws Exception {

    }
}
