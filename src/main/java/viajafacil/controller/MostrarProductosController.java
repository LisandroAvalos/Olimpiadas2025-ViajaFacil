package viajafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import viajafacil.service.TourPackageService;
import viajafacil.dto.TourPackageDTO;

import java.util.List;

@Controller
public class MostrarProductosController {

    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping("/productos")
    public String mostrarListaPaquetes(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            TourPackageDTO paquete = tourPackageService.findById(id);
            if (paquete != null) {
                model.addAttribute("paquetes", List.of(paquete));
            } else {
                model.addAttribute("paquetes", List.of());
                model.addAttribute("error", "No se encontró el paquete con ID " + id);
            }
        } else {
            model.addAttribute("paquetes", tourPackageService.findAll());
        }
        return "lista_paquete";
    }
}
