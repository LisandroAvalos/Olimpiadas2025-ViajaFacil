package viajafacil.service.implementation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import viajafacil.dto.TourPackageDTO;
import viajafacil.entity.TourPackage;
import viajafacil.repository.TourPackageRepository;
import viajafacil.service.TourPackageService;

@Service
public class TourPackageServiceImplementation implements TourPackageService{

	TourPackageRepository tourPackageRepository;
	
    public TourPackageServiceImplementation(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
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
		return tourPackageRepository.findById(id);
	}

	@Override
	public void save(TourPackageDTO tourPackageDTO) {
		TourPackage tourPackage = new TourPackage();

		tourPackage.setPassengers(tourPackageDTO.getPassengers());
		tourPackage.setDestination(tourPackageDTO.getDestination());
		tourPackage.setStart_date(tourPackageDTO.getStart_date());
		tourPackage.setEnd_date(tourPackageDTO.getEnd_date());
		tourPackage.setTransport(tourPackageDTO.getTransport());
		tourPackage.setDescription(tourPackageDTO.getDescription());
		tourPackage.setPrice(tourPackageDTO.getPrice());
		
		tourPackageRepository.save(tourPackage);
	}
	
	@Override
	public void updateById(Long id, TourPackageDTO tourPackageDTO) {
			
		Optional<TourPackage> tourPackageOptional = tourPackageRepository.findById(id);
		
		if(tourPackageOptional.isPresent()) {
			TourPackage tourPackage = tourPackageOptional.get();
			tourPackage.setPassengers(tourPackageDTO.getPassengers());
			tourPackage.setDestination(tourPackageDTO.getDestination());
			tourPackage.setStart_date(tourPackageDTO.getStart_date());
			tourPackage.setEnd_date(tourPackageDTO.getEnd_date());
			tourPackage.setTransport(tourPackageDTO.getTransport());
			tourPackage.setDescription(tourPackageDTO.getDescription());
			tourPackage.setPrice(tourPackageDTO.getPrice());
			tourPackageRepository.save(tourPackage);
		}
		
	}

	@Override
	public void deleteById(Long id) {
		if(tourPackageRepository.existsById(id)) {
			tourPackageRepository.deleteById(id);
		}
	}

	private TourPackageDTO mapToTourPackageDTO(TourPackage tourPackage){
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setId(tourPackage.getId());
		tourPackageDTO.setPassengers(tourPackage.getPassengers());
		tourPackageDTO.setDestination(tourPackage.getDestination());
		tourPackageDTO.setStart_date(tourPackage.getStart_date());
		tourPackageDTO.setEnd_date(tourPackage.getEnd_date());
		tourPackageDTO.setTransport(tourPackage.getTransport());
		tourPackageDTO.setDescription(tourPackage.getDescription());
		tourPackageDTO.setPrice(tourPackage.getPrice());
        return tourPackageDTO;
    }

}
