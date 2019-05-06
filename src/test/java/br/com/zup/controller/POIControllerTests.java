package br.com.zup.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.zup.XYIncApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = XYIncApplication.class)
public class POIControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenFindingAllPOIs() throws Exception {
		
		StringBuilder content = new StringBuilder().append("[{\"nome\":\"Lanchonete\",\"x\":27,\"y\":12},")
				.append("{\"nome\":\"Posto\",\"x\":31,\"y\":18},{\"nome\":\"Joalheria\",\"x\":15,\"y\":12},")
				.append("{\"nome\":\"Floricultura\",\"x\":19,\"y\":21},{\"nome\":\"Pub\",\"x\":12,\"y\":8},")
				.append("{\"nome\":\"Supermercado\",\"x\":23,\"y\":6},{\"nome\":\"Churrascaria\",\"x\":28,\"y\":2}]");
		
		mockMvc.perform(get("/pois")).andExpect(status().isOk()).andExpect(content().string(content.toString()));
	}

	@Test
	public void whenFindingPOIsByCoordernates() throws Exception {
		
		int x = 20; int y = 10;
		
		StringBuilder content = new StringBuilder().append("[{\"nome\":\"Lanchonete\",\"x\":27,\"y\":12},")
				.append("{\"nome\":\"Joalheria\",\"x\":15,\"y\":12},{\"nome\":\"Pub\",\"x\":12,\"y\":8},")
				.append("{\"nome\":\"Supermercado\",\"x\":23,\"y\":6}]");
		
		mockMvc.perform(get("/pois/x/" + x + "/y/" +  y))
					.andExpect(status().isOk()).andExpect(content().string(content.toString()));
	}

	@Test
	public void whenSavingPOI() throws Exception {

		StringBuilder content 
			= new StringBuilder().append("{\"nome\":\"LanHouse\",\"x\":8,\"y\":2}");
		
		mockMvc.perform(post("/pois").content("{\"nome\":\"LanHouse\",\"x\":8,\"y\":2}").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().string(content.toString()));
	}

}