package com.devsuperior.dscatalog.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.tests.Factory;

@WebMvcTest(ProductController.class)
public class ProductControllerTests{
    
    @Autowired
    private MockMvc mockMvc;
    //MockBean: Carrega o contexto da aplicação
    @MockBean
    private ProductService service;
    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;

    @BeforeEach
    void setUp() throws Exception {
        //Configura os comportamentos esperados dos objetos mockados
        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));

        Mockito.when(service.findAllPaged(ArgumentMatchers.any())).thenReturn(page);
        
    }

    @Test
    public void findAllShouldReturnPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}