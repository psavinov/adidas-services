package com.adidas.service.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetProduct() throws Exception {
		this.mockMvc.perform(get("/product/BB5476"))
			.andExpect(status().isOk());
	}

	@Test
	public void testGetProductUnavailable() throws Exception {
		this.mockMvc.perform(get("/product/12345"))
			.andExpect(status().isServiceUnavailable());
	}

}
