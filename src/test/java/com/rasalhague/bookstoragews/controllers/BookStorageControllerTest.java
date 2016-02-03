package com.rasalhague.bookstoragews.controllers;

import com.rasalhague.bookstoragews.Helper;
import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.model.Catalog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @Test
    public void testChangeBook_RequestEmptyXML() throws Exception {
        Catalog catalog = Helper.generateTestCatalog();

        String emptyXmlToSend = Helper.catalogToXml(new Catalog());
        String haveToReturn = Helper.catalogToXml(catalog);

        when(bookStorageService.readCatalog()).thenReturn(catalog);

        mockMvc.perform(put("/changeBook").contentType(MediaType.APPLICATION_XML).content(emptyXmlToSend))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(haveToReturn))
               .andDo(print());

        verify(bookStorageService).readCatalog();
    }

    @Test
    public void testChangeBook_RequestUpdateAndCreate() throws Exception {
        Catalog catalogToSend = Helper.generateTestCatalog();
        catalogToSend.getCatalog().get(0).setDescription("testChangeBook_RequestUpdateAndCreate");
        catalogToSend.getCatalog().removeIf(book1 -> !book1.getId().equals("1"));
        String xmlToSend = Helper.catalogToXml(catalogToSend);

        Catalog catalog = Helper.generateTestCatalog();
        catalog.getCatalog().get(0).setDescription("testChangeBook_RequestUpdateAndCreate");
        String xmlToReceive = Helper.catalogToXml(catalog);

        when(bookStorageService.updateCatalog(catalogToSend)).thenReturn(catalog);

        mockMvc.perform(put("/changeBook").contentType(MediaType.APPLICATION_XML).content(xmlToSend))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(xmlToReceive))
               .andDo(print());

        verify(bookStorageService).updateCatalog(catalogToSend);
    }

    @Test
    public void testChangeBook_RequestDelete() throws Exception {
        Catalog catalogToSend = Helper.generateTestCatalog();
        String xmlToSend = Helper.catalogToXml(catalogToSend);

        Catalog catalogToReceive = new Catalog();
        String xmlToReceive = Helper.catalogToXml(catalogToReceive);

        when(bookStorageService.deleteBooks(catalogToSend)).thenReturn(catalogToReceive);

        mockMvc.perform(delete("/changeBook").contentType(MediaType.APPLICATION_XML).content(xmlToSend))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(xmlToReceive))
               .andDo(print());

        verify(bookStorageService).deleteBooks(catalogToSend);
    }
}
