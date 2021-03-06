package hello.itemservice.domain;

import lombok.*;

@NoArgsConstructor
@Getter @Setter //@Data는 핵심 도메인 모델에서 사용 X
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
