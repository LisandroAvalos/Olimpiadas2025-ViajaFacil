package viajafacil.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartItemDTO {

	private Long id;

	@NotNull
	private Long cartId;

	@NotNull
	private Long tourPackageId;

}

