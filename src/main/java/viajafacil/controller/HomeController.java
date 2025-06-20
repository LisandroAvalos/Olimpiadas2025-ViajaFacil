package viajafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import viajafacil.dto.TourPackageDTO;
import viajafacil.service.TourPackageService;


@Controller
public class HomeController {

    @Autowired
    private TourPackageService tourPackageService;
    
    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        model.addAttribute("paquetes", tourPackageService.findAll());
        return "index";
    }
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
