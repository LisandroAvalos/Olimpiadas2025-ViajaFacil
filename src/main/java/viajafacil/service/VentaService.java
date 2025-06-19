package viajafacil.service;

import java.util.List;

import viajafacil.entity.Venta;

public interface VentaService {
	
	void createVenta(Long idUser, Long idPago, Long idCart);
	
	List<Venta> showVentaByUserId(Long idUser);
	
	List<Venta> showAllVentas();
	
}
