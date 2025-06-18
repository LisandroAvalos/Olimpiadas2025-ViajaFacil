package viajafacil.service;

import java.util.List;

import viajafacil.dto.TourPackageDTO;

public interface TourPackageService {
	
	List<TourPackageDTO> findAll();
	
	TourPackageDTO findById(Long id);
	
	void save(TourPackageDTO tourPackageDTO);
	
	void updateById(Long id, TourPackageDTO tourPackageDTO);
	
	void deleteById(Long id);

}
