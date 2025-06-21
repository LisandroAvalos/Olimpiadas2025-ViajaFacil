package viajafacil.service.implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import viajafacil.dto.CartItemDTO;
import viajafacil.dto.PagoDTO;
import viajafacil.entity.Cart;
import viajafacil.entity.Pago;
import viajafacil.entity.User;
import viajafacil.repository.CartRepository;
import viajafacil.repository.PagoRepository;
import viajafacil.service.CartService;
import viajafacil.service.PagoService;

@Service
public class PagoServiceImplementation implements PagoService{

	private PagoRepository pagoRepository;
	private CartService cartService;
	
	PagoServiceImplementation(PagoRepository pagoRepository,
					CartService cartService){
		this.pagoRepository = pagoRepository;
		this.cartService = cartService;
	}
	
	@Override
	public Pago save(PagoDTO pagoDTO, User user) {
		
		Pago pago = new Pago();
		pago.setCardNumber(pagoDTO.getCardNumber());
		pago.setCodeSec(pagoDTO.getCodeSec());
		pago.setDni(pagoDTO.getDni());
		pago.setExpirationDate(pagoDTO.getExpirationDate());
		pago.setName(pagoDTO.getName());
		List<CartItemDTO> listCartItems = cartService.showCartByUserId(user.getId());
		BigDecimal total = listCartItems.stream()
        	    .map(CartItemDTO::getPrice)
        	    .reduce(BigDecimal.ZERO, BigDecimal::add)
        	    .setScale(2, RoundingMode.HALF_UP);
		pago.setTotalPrice(total);
		return pagoRepository.save(pago);
		
	}

}
