package viajafacil.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import viajafacil.dto.PagoDTO;
import viajafacil.entity.Cart;
import viajafacil.entity.Pago;
import viajafacil.entity.User;
import viajafacil.service.CartService;
import viajafacil.service.PagoService;
import viajafacil.service.UserService;
import viajafacil.service.VentaService;

@Controller
public class PagoController {

	PagoService pagoService;
	CartService cartService;
	UserService userService;
	VentaService ventaService;
	
	PagoController(PagoService pagoService, CartService cartService, UserService userService, VentaService ventaService){
		this.pagoService = pagoService;
		this.cartService = cartService;
		this.userService = userService;
		this.ventaService = ventaService;
	}
	
    @GetMapping("/nuevo-pago")
    public String mostrarFormularioPagoNuevo(Model model) {
        model.addAttribute("pago", new PagoDTO());
        return "nuevo_pago";
    }

    @PostMapping("/nuevo-pago")
    public String procesarPago(@Valid @ModelAttribute("pago") PagoDTO pagoDTO, BindingResult result, Model model, Authentication authentication) {
        
    	if (result.hasErrors()) {
            return "nuevo_pago";
        }
        
        String email = authentication.getName();
        User user = userService.getByEmail(email);
        
        Pago pago = pagoService.save(pagoDTO, user);
        
        Cart cart = cartService.getCartByUserId(user.getId());
        
        ventaService.createVenta(user.getId(), pago.getId(), cart.getId());
        
        cartService.clearCartByUserId(user.getId());
        
        return "redirect:/inicio";
    }
}
