package hello.itemservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data //DTO 같은 클래스에선 @Data 사용 고려
public class ItemParamDto {

    private String itemName;
    private Integer price;
    private Integer quantity;
}
