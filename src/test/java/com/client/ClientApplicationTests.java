package com.client;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.client.controller.ClientController;
import com.client.controller.ClientRequest;
import com.client.entity.Client;
import com.client.sevice.IClientService;

@WebMvcTest(ClientController.class)
class ClientApplicationTests {

	@MockBean
	private IClientService clientService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getClientByDocument_whenExist_returnOk() throws Exception {
		Client mockclient = new Client(null,
				"C",
				"23445322",
				"Luis",
				"Felipe",
				"Salamanca",
				"Lopez",
				"3005946322",
				"Calle 10a # 4-83",
				"Facatativa");

		String clientRequest = "{\"documentType\":\"C\",\"documentNumber\":\"23445322\"}";

		ClientRequest request = new ClientRequest("C", "23445322");
		Mockito.when(clientService.getClientByDocument(request)).thenReturn(mockclient);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/clients/findbydocument")
				.contentType(MediaType.APPLICATION_JSON).content(clientRequest);

		mockMvc.perform(builder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.secondName").value("Felipe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstSurname").value("Salamanca"));
	}

	@Test
	void getClientByDocument_whenBadSyntax_returnBadRequest() throws Exception {
		ClientRequest request = new ClientRequest("T", "100345456");
		Mockito.when(clientService.getClientByDocument(request)).thenReturn(null);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/clients/findbydocument")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"documentType\":\"T\",\"documentNumber\":\"100345456\"}");

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	void getClientByDocument_whenNotExist_returnNotFound() throws Exception {
		ClientRequest request = new ClientRequest("C", "100345456");
		Mockito.when(clientService.getClientByDocument(request)).thenReturn(null);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/clients/findbydocument")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"documentType\":\"C\",\"documentNumber\":\"100345456\"}");

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
