package viajafacil.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import viajafacil.entity.Cart;
import viajafacil.entity.CartItem;
import viajafacil.entity.Pago;
import viajafacil.entity.TourPackage;
import viajafacil.entity.User;
import viajafacil.entity.Venta;
import viajafacil.entity.VentaItem;
import viajafacil.repository.CartRepository;
import viajafacil.repository.PagoRepository;
import viajafacil.repository.UserRepository;
import viajafacil.repository.VentaItemRepository;
import viajafacil.repository.VentaRepository;
import viajafacil.service.VentaService;

@Service
public class VentaServiceImplementation implements VentaService{

	VentaRepository ventaRepository;
	VentaItemRepository ventaItemRepository;
	CartRepository cartRepository;
	UserRepository userRepository;
	PagoRepository pagoRepository;
	
	public VentaServiceImplementation(VentaRepository ventaRepository, VentaItemRepository ventaItemRepository, UserRepository userRepository, PagoRepository pagoRepository, CartRepository cartRepository){
		this.ventaRepository = ventaRepository;
		this.ventaItemRepository = ventaItemRepository;
		this.userRepository = userRepository;
		this.pagoRepository = pagoRepository;
		this.cartRepository = cartRepository;
	}
	
	@Override
	public void createVenta(Long idUser, Long idPago, Long idCart) {

		Optional<User> userOp = userRepository.findById(idUser);
		Optional<Pago> pagoOp = pagoRepository.findById(idPago);
		Optional<Cart> cartOp = cartRepository.findById(idCart);
		User user = userOp.get();
		Pago pago = pagoOp.get();
		Cart cart = cartOp.get();
		
		Venta venta = new Venta();
		venta.setUser(user);
		venta.setPago(pago);
		
		List<VentaItem> listVentaItem = new ArrayList<>();
		for(CartItem cartItem : cart.getListCartItems()) {
			TourPackage tourPackage = cartItem.getTourPackage();
			if(tourPackage != null) {
				VentaItem ventaItem = new VentaItem();
				ventaItem.setTour_package_id(tourPackage.getId());
				ventaItem.setDeparture_place(tourPackage.getDeparture_place());
				ventaItem.setDescription(tourPackage.getDescription());
				ventaItem.setDestination(tourPackage.getDestination());
				ventaItem.setStart_date(tourPackage.getStart_date());
				ventaItem.setEnd_date(tourPackage.getEnd_date());
				ventaItem.setPassangers(tourPackage.getPassangers());
				ventaItem.setPrice(tourPackage.getPrice());
				ventaItem.setTransport(tourPackage.getTransport());
				ventaItem.setVenta(venta);
				listVentaItem.add(ventaItem);
			}
		}
		venta.setListVentaItems(listVentaItem);
		ventaRepository.save(venta);
	}

	@Override
	public List<Venta> showVentaByUserId(Long idUser) {
		
		return ventaRepository.findAllByUser_Id(idUser);
		
	}

	@Override
	public List<Venta> showAllVentas() {
		
		return ventaRepository.findAll();
		
	}

}
