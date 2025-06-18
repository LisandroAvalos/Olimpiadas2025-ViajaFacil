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
	public void save(TourPackageDTO tourPackageDto) {
		TourPackage tourPackage = new TourPackage();

		tourPackage.setPassangers(tourPackageDto.getPassangers());
		tourPackage.setDestination(tourPackageDto.getDestination());
		tourPackage.setDeparture_place(tourPackageDto.getDeparture_place());
		tourPackage.setStart_date(tourPackageDto.getStart_date());
		tourPackage.setEnd_date(tourPackageDto.getEnd_date());
		tourPackage.setTransport(tourPackageDto.getTransport());
		tourPackage.setDescription(tourPackageDto.getDescription());
		tourPackage.setPrice(tourPackageDto.getPrice());
		
		tourPackageRepository.save(tourPackage);
	}
	
	@Override
	public void updateById(Long id, TourPackageDTO tourPackageDto) {
			
		Optional<TourPackage> tourPackageOptional = tourPackageRepository.findById(id);
		
		if(tourPackageOptional.isPresent()) {
			TourPackage tourPackage = tourPackageOptional.get();
			tourPackage.setPassangers(tourPackageDto.getPassangers());
			tourPackage.setDestination(tourPackageDto.getDestination());
			tourPackage.setDeparture_place(tourPackageDto.getDeparture_place());
			tourPackage.setStart_date(tourPackageDto.getStart_date());
			tourPackage.setEnd_date(tourPackageDto.getEnd_date());
			tourPackage.setTransport(tourPackageDto.getTransport());
			tourPackage.setDescription(tourPackageDto.getDescription());
			tourPackage.setPrice(tourPackageDto.getPrice());
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
		TourPackageDTO tourPackageDto = new TourPackageDTO();
		tourPackageDto.setId(tourPackage.getId());
		tourPackageDto.setPassangers(tourPackage.getPassangers());
		tourPackageDto.setDestination(tourPackage.getDestination());
		tourPackageDto.setDeparture_place(tourPackage.getDeparture_place());
		tourPackageDto.setStart_date(tourPackage.getStart_date());
		tourPackageDto.setEnd_date(tourPackage.getEnd_date());
		tourPackageDto.setTransport(tourPackage.getTransport());
		tourPackageDto.setDescription(tourPackage.getDescription());
		tourPackageDto.setPrice(tourPackage.getPrice());
        return tourPackageDto;
    }

}
