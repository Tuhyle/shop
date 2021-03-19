package response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;

    private Double subTotal;

    private Double itemDiscount;

    private Double tax;

    private Double shipping;

    private Double total;

    private String promo;

    private Double discount;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String line1;

    private String province;

    private String country;

    private String content;
}
