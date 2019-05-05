package br.com.zup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.model.POI;
import br.com.zup.repository.POIRepository;

@Service
public class POIService {

	@Autowired
	POIRepository poiRepository;
	
	// max distance
	final int max = 10;
	
	public List<POI> getAllPOIs() {
		List<POI> pois = new ArrayList<POI>();
		poiRepository.findAll().forEach(poi -> pois.add(poi));
		return pois;
	}

	public List<POI> getPOIsByCoordenates(int x, int y) {	
		List<POI> pois = new ArrayList<POI>();
		
		for (POI poi : poiRepository.findAll()) {
			// calculate square
			double sqrt	= Math.sqrt( Math.pow((poi.getX() - x), 2) + Math.pow((poi.getY() - y), 2) );
			// add POIs with max distance = 10
			if (max >= ((int) Math.ceil(sqrt))) {
				pois.add(poi);
			}
		}
		return pois;
	}
	
	public void saveOrUpdate(POI poi) {
		poiRepository.save(poi);
	}
	
}
