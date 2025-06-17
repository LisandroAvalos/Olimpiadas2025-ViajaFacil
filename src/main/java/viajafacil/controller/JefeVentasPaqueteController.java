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
import java.util.Optional;

@Controller
@RequestMapping("/jefe-ventas/paquetes")
public class JefeVentasPaqueteController {

    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping("/buscar")
    public String buscarPaquetes(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            Optional<TourPackageDTO> paquete = tourPackageService.findById(id);
            if (paquete.isPresent()) {
                model.addAttribute("paquetes", List.of(paquete.get()));
            } else {
                model.addAttribute("paquetes", List.of());
                model.addAttribute("error", "No se encontró un paquete con el ID: " + id);
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
        Optional<TourPackageDTO> paqueteOptional = tourPackageService.findById(id);
        if (paqueteOptional.isPresent()) {
            model.addAttribute("paquete", paqueteOptional.get());
            return "editar_paquete";
        } else {
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
        Optional<TourPackageDTO> paqueteOpt = tourPackageService.findById(id);
        if (paqueteOpt.isPresent()) {
            model.addAttribute("paquete", paqueteOpt.get());
            return "eliminar_paquete"; // nombre del html de confirmación
        } else {
            return "redirect:/jefe-ventas/paquetes/buscar?error=notfound";
        }
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        tourPackageService.deleteById(id);
        return "redirect:/jefe-ventas/paquetes/buscar?deleted";
    }
}