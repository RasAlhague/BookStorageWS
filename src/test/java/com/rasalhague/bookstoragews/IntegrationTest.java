package com.rasalhague.bookstoragews;

import com.rasalhague.bookstoragews.bl.BookStorageService;
import com.rasalhague.bookstoragews.controllers.BookStorageController;
import com.rasalhague.bookstoragews.model.Catalog;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class IntegrationTest {
    @Autowired
    private BookStorageService bookStorageService;

    @Autowired
    private BookStorageController bookStorageController;

    private MockMvc mockMvc;

    @Before
    public void before() throws IOException {
        Helper.reloadBookStorageFromResources();
        mockMvc = MockMvcBuilders.standaloneSetup(bookStorageController).build();
    }

    @After
    public void after() throws IOException {
        Helper.reloadBookStorageFromResources();
    }

    @Test
    public void updateCatalogTest() {
        Catalog actualCatalog = bookStorageService.readCatalog();
        actualCatalog.getCatalog().get(0).setDescription("test descr");

        Catalog updatedCatalog = bookStorageService.updateCatalog(actualCatalog);
        Assert.assertEquals(updatedCatalog, actualCatalog);

        updatedCatalog = bookStorageService.readCatalog();
        Assert.assertEquals(updatedCatalog, actualCatalog);
    }

    @Test
    public void createCatalogTest() {
        Catalog actualCatalog = bookStorageService.readCatalog();
        Catalog catalogToMerge = Helper.generateTestCatalog();
        actualCatalog.getCatalog().addAll(catalogToMerge.getCatalog());

        Catalog updatedCatalog = bookStorageService.updateCatalog(actualCatalog);
        Assert.assertEquals(updatedCatalog, actualCatalog);

        updatedCatalog = bookStorageService.readCatalog();
        Assert.assertEquals(updatedCatalog, actualCatalog);
    }

    @Test
    public void controller_createCatalogTest() throws Exception {
        Catalog actualCatalog = Helper.getDefaultCatalog();
        Catalog testCatalog = Helper.generateTestCatalog();
        actualCatalog.getCatalog().addAll(testCatalog.getCatalog());

        mockMvc.perform(put("/changeBook").contentType(MediaType.APPLICATION_XML)
                                          .content(Helper.catalogToXml(testCatalog)))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(Helper.catalogToXml(actualCatalog)))
               .andDo(print());
    }

    @Test
    public void controller_readCatalogTest() throws Exception {
        Catalog actualCatalog = Helper.getDefaultCatalog();

        mockMvc.perform(put("/changeBook").contentType(MediaType.APPLICATION_XML)
                                          .content(Helper.catalogToXml(new Catalog())))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(Helper.catalogToXml(actualCatalog)))
               .andDo(print());
    }

    @Test
    public void controller_removeCatalogTest() throws Exception {
        Catalog actualCatalog = Helper.getDefaultCatalog();

        mockMvc.perform(put("/changeBook").contentType(MediaType.APPLICATION_XML)
                                          .content(Helper.catalogToXml(new Catalog())))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_XML))
               .andExpect(content().string(Helper.catalogToXml(actualCatalog)))
               .andDo(print());
    }
}
