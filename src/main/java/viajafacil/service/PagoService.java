package viajafacil.service;

import viajafacil.dto.PagoDTO;
import viajafacil.entity.Pago;
import viajafacil.entity.User;

public interface PagoService {
	
	Pago save(PagoDTO pagoDTO, User user);

}
