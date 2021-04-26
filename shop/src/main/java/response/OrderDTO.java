package response;

import com.shop.shop.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;

    private Double shipping;

    private Double grandTotal;

    private String promo;

    private Double discount;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String address;

    private String content;

    private Integer status;

    List<OrderItem>  orderItems;
}
