package viajafacil.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import viajafacil.dto.TourPackageDTO;
import viajafacil.entity.TourPackage;
import viajafacil.repository.TourPackageRepository;
import viajafacil.repository.CartItemRepository;
import viajafacil.service.TourPackageService;

@Service
public class TourPackageServiceImplementation implements TourPackageService{

	TourPackageRepository tourPackageRepository;
	CartItemRepository cartItemRepository;
	
    public TourPackageServiceImplementation(TourPackageRepository tourPackageRepository, CartItemRepository cartItemRepository) {
        this.tourPackageRepository = tourPackageRepository;
        this.cartItemRepository = cartItemRepository;
    }
	
	@Override
	public List<TourPackageDTO> findAll() {
		List<TourPackage> tourPackages = tourPackageRepository.findAll();
        return tourPackages.stream()
                .map((tourPackage) -> mapToTourPackageDTO(tourPackage))
                .collect(Collectors.toList());
	}

	@Override
	public TourPackageDTO findById(Long id) {
	    return tourPackageRepository.findById(id)
	            .map(this::mapToTourPackageDTO)
	            .orElse(null);
	}


	@Override
	public void save(TourPackageDTO tourPackageDTO) {
		TourPackage tourPackage = new TourPackage();

		tourPackage.setPassangers(tourPackageDTO.getPassangers());
		tourPackage.setDestination(tourPackageDTO.getDestination());
		tourPackage.setPrice(tourPackageDTO.getPrice());
		
		tourPackageRepository.save(tourPackage);
	}
	
	@Override
	public void updateById(Long id, TourPackageDTO tourPackageDTO) {
			
		Optional<TourPackage> tourPackageOptional = tourPackageRepository.findById(id);
		
		if(tourPackageOptional.isPresent()) {
			TourPackage tourPackage = tourPackageOptional.get();
			tourPackage.setPassangers(tourPackageDTO.getPassangers());
			tourPackage.setDestination(tourPackageDTO.getDestination());
			tourPackage.setPrice(tourPackageDTO.getPrice());
			tourPackageRepository.save(tourPackage);
		}
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		if (tourPackageRepository.existsById(id)) {
			cartItemRepository.deleteByTourPackageId(id);
			tourPackageRepository.deleteById(id);
		}
	}

	private TourPackageDTO mapToTourPackageDTO(TourPackage tourPackage){
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setId(tourPackage.getId());
		tourPackageDTO.setPassangers(tourPackage.getPassangers());
		tourPackageDTO.setDestination(tourPackage.getDestination());
		tourPackageDTO.setPrice(tourPackage.getPrice());
        return tourPackageDTO;
    }

}
