package viajafacil.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartItemDTO {
    private Long cartItemId;
    private String destination;
    private BigDecimal price;
    private int passangers;
}
