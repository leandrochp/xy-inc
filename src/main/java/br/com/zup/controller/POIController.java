package br.com.zup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.model.POI;
import br.com.zup.service.POIService;

@RestController
public class POIController {

	@Autowired
	POIService poiService;

	@GetMapping("/pois")
	private List<POI> getAllPOIs() {
		return poiService.getAllPOIs();
	}

	@GetMapping("/pois/x/{x}/y/{y}")
	private List<POI> getPOIs(@PathVariable("x") Integer x, @PathVariable("y") Integer y) {
 		return poiService.getPOIByXAndY(x, y);
	}
	
	@PostMapping("/pois")
	private POI savePOI(@RequestBody POI poi) {
		poiService.saveOrUpdate(poi);
		return poi;
	}
}
