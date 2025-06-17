package viajafacil.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import viajafacil.dto.TourPackageDTO;

@Service
public interface TourPackageService {
	
	List<TourPackageDTO> findAll();
	
	Optional <TourPackageDTO> findById(Long id);
	
	void save(TourPackageDTO tourPackageDTO);
	
	void updateById(Long id, TourPackageDTO tourPackageDTO);
	
	void deleteById(Long id);

}
