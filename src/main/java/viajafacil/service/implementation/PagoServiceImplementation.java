package viajafacil.service.implementation;

import org.springframework.stereotype.Service;

import viajafacil.dto.PagoDTO;
import viajafacil.entity.Pago;
import viajafacil.repository.PagoRepository;
import viajafacil.service.PagoService;

@Service
public class PagoServiceImplementation implements PagoService{

	PagoRepository pagoRepository;
	
	PagoServiceImplementation(PagoRepository pagoRepository){
		this.pagoRepository = pagoRepository;
	}
	
	@Override
	public Pago save(PagoDTO pagoDTO) {
		
		Pago pago = new Pago();
		pago.setCardNumber(pagoDTO.getCardNumber());
		pago.setCodeSec(pagoDTO.getCodeSec());
		pago.setDni(pagoDTO.getDni());
		pago.setExpirationDate(pagoDTO.getExpirationDate());
		pago.setName(pagoDTO.getName());
		pago.setTotalPrice(pagoDTO.getTotalPrice());
		return pagoRepository.save(pago);
		
	}

}
