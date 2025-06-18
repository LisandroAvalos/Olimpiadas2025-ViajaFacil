package viajafacil.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import viajafacil.dto.TourPackageDTO;
import viajafacil.service.TourPackageService;

import java.util.List;

@Controller
@RequestMapping("/jefe-ventas/paquetes")
public class JefeVentasPaqueteController {

    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping("/buscar")
    public String buscarPaquetes(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            try {
                TourPackageDTO paquete = tourPackageService.findById(id);
                if (paquete != null) {
                    model.addAttribute("paquetes", List.of(paquete));
                } else {
                    model.addAttribute("paquetes", List.of());
                    model.addAttribute("error", "No se encontró el paquete con id " + id);
                }
            } catch (IllegalArgumentException ex) {
                model.addAttribute("paquetes", List.of());
                model.addAttribute("error", ex.getMessage());
            }
        } else {
            model.addAttribute("paquetes", tourPackageService.findAll());
        }
        return "buscar_paquete";
    }



    // Formulario para crear nuevo paquete
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("paquete", new TourPackageDTO());
        return "nuevo_paquete";
    }

    // Guardar nuevo paquete
    @PostMapping("/nuevo")
    public String guardarPaquete(@Valid @ModelAttribute("paquete") TourPackageDTO paqueteDTO,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "nuevo_paquete";
        }

        tourPackageService.save(paqueteDTO);
        return "redirect:/jefe-ventas/paquetes/buscar?success";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        try {
            TourPackageDTO paquete = tourPackageService.findById(id);
            model.addAttribute("paquete", paquete);
            return "editar_paquete";
        } catch (IllegalArgumentException ex) {
            return "redirect:/jefe-ventas/paquetes/buscar?error=notfound";
        }
    }


    @PostMapping("/editar/{id}")
    public String actualizarPaquete(@PathVariable Long id,
                                    @Valid @ModelAttribute("paquete") TourPackageDTO paqueteDTO,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "editar_paquete";
        }

        paqueteDTO.setId(id);
        tourPackageService.updateById(id, paqueteDTO);
        return "redirect:/jefe-ventas/paquetes/buscar?updated";
    }
    
    @GetMapping("/eliminar/{id}")
    public String mostrarConfirmacionEliminar(@PathVariable Long id, Model model) {
        try {
            TourPackageDTO paquete = tourPackageService.findById(id);
            model.addAttribute("paquete", paquete);
            return "eliminar_paquete";
        } catch (IllegalArgumentException ex) {
            return "redirect:/jefe-ventas/paquetes/buscar?error=notfound";
        }
    }

    
    @PostMapping("/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        tourPackageService.deleteById(id);
        return "redirect:/jefe-ventas/paquetes/buscar?deleted";
    }
}