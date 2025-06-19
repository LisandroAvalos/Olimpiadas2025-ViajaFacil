package viajafacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import viajafacil.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
	List<Venta> findAllByUser_Id(Long idUser);
}
