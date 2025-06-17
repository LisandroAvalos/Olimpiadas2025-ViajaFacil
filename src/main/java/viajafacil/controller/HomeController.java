package viajafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return tourPackageService.findById(id)
                .map(paquete -> {
                    model.addAttribute("paquete", paquete);
                    return "detalle_paquete";
                })
                .orElse("redirect:/inicio?error=notfound");
    }
}