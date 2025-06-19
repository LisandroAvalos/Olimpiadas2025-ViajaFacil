package viajafacil.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import viajafacil.entity.Venta;
import viajafacil.entity.User;
import viajafacil.service.UserService;
import viajafacil.service.VentaService;

@Controller
public class VentaController {

    private final VentaService ventaService;
    private final UserService userService;

    public VentaController(VentaService ventaService, UserService userService) {
        this.ventaService = ventaService;
        this.userService = userService;
    }

    // Vista para usuario: ver sus ventas
    @GetMapping("/compras")
    public String mostrarVentasUsuario(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getByEmail(email);

        List<Venta> ventasUsuario = ventaService.showVentaByUserId(user.getId());
        model.addAttribute("ventas", ventasUsuario);

        return "ventas_usuario"; // plantilla Thymeleaf para mostrar ventas del usuario
    }

    // Vista para jefe de ventas: ver todas las ventas o filtrar por usuario
    @GetMapping("/jefe-ventas/ventas")
    public String mostrarVentasJefe(@RequestParam(required = false) Long idUsuario, Model model) {
        List<Venta> ventas;

        if (idUsuario != null) {
            ventas = ventaService.showVentaByUserId(idUsuario);
            model.addAttribute("idUsuario", idUsuario);
        } else {
            ventas = ventaService.showAllVentas();
        }

        model.addAttribute("ventas", ventas);

        return "ventas_jefe"; // plantilla Thymeleaf para mostrar todas las ventas
    }
}
