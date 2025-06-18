package viajafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import viajafacil.dto.TourPackageDTO;
import viajafacil.service.TourPackageService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TourPackageService tourPackageService;

    // Página principal con listado o búsqueda
    @GetMapping("/inicio")
    public String mostrarInicio(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            TourPackageDTO paquete = tourPackageService.findById(id);
            if (paquete != null) {
                model.addAttribute("paquetes", List.of(paquete));
            } else {
                model.addAttribute("paquetes", List.of());
                model.addAttribute("error", "No se encontró el paquete con id " + id);
            }
        } else {
            model.addAttribute("paquetes", tourPackageService.findAll());
        }
        return "index";
    }

    // Mostrar detalle en otra ruta permitida
    @GetMapping("/paquete/{id}")
    public String verDetallePaquete(@PathVariable Long id, Model model) {
        TourPackageDTO paquete = tourPackageService.findById(id);
        if (paquete != null) {
            model.addAttribute("paquete", paquete);
            return "detalle_paquete";
        } else {
            return "redirect:/inicio?error=notfound";
        }
    }
}
