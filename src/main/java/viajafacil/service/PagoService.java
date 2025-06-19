package viajafacil.service;

import viajafacil.dto.PagoDTO;
import viajafacil.entity.Pago;

public interface PagoService {
	
	Pago save(PagoDTO pagoDTO);

}
