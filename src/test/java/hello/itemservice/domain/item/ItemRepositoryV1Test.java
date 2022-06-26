package hello.itemservice.domain.item;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemParamDto;
import hello.itemservice.domain.ItemRepositoryV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryV1Test {

    ItemRepositoryV1 itemRepositoryV1 = new ItemRepositoryV1();

    //TEST 끝날 때마다 실행되는 어노테이션
    @AfterEach
    void afterEach() {
        itemRepositoryV1.clearStore();
    }

    @Test
    public void save() throws Exception {
        //given: 이런 게 주어졌을 때
        Item item = new Item("itemA", 10000, 10);

        //when: 이렇게 하면
        Item savedItem = itemRepositoryV1.save(item);

        //then: 이렇게 된다
        Item findItem = itemRepositoryV1.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    public void findAll() throws Exception {
        //given: 이런 게 주어졌을 때
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepositoryV1.save(item1);
        itemRepositoryV1.save(item2);

        //when: 이렇게 하면
        List<Item> result = itemRepositoryV1.findAll();

        //then: 이렇게 된다
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    public void updateItem() throws Exception {
        //given: 이런 게 주어졌을 때
        Item item = new Item("item1", 10000, 10);
        itemRepositoryV1.save(item);
        ItemParamDto itemParamDto = new ItemParamDto("updateItem1", 20000, 20);
        Long itemId = item.getId();

        //when: 이렇게 하면
        itemRepositoryV1.update(itemId, itemParamDto);

        //then: 이렇게 된다
        assertThat(item.getItemName()).isEqualTo("updateItem1");
        assertThat(item.getPrice()).isEqualTo(20000);
        assertThat(item.getQuantity()).isEqualTo(20);

        Item findItem = itemRepositoryV1.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(itemParamDto.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(itemParamDto.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemParamDto.getQuantity());
    }
}
