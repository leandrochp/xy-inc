package br.com.zup.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.zup.XYIncApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XYIncApplication.class)
@ContextConfiguration
public class POIServiceTests {

	@Autowired
	private POIService poiService;
	
	@Test
	public void whenFindingAllPOIs_thenCorrect() {
		
		assertThat(poiService.getAllPOIs()).isInstanceOf(List.class);
	}

	@Test
	public void whenFindingPOIsByCoordernates_thenCorrect() {
		int x = 20; int y = 10;
		assertThat(poiService.getPOIsByCoordenates(x, y)).isInstanceOf(List.class);
	}
	
}
