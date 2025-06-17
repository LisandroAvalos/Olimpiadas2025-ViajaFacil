package viajafacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jefe-ventas")
public class JefeVentasController {

    @GetMapping
    public String mostrarHome() {
        return "jefe_ventas_home";  // nombre de la vista para la página principal del jefe de ventas
    }
}

