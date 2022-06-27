package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepositoryV1 {
    //멀티 쓰레드 환경에선 HashMap 사용하지 않고 ConcurrentHashMap 사용
    private static final Map<Long, Item> store = new HashMap<>(); //static 키워드와 해시맵 주의
    private static long sequence = 0l; //static
    //static 키워드 사용 안하면 new로 ItemRepository 생성시 새로운 객체가 만들어져 위험 (싱글톤 사용)

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        Item findItem = store.get(id);
        return findItem;
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, ItemParamDto updateParam) {
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

    public void delete(Long itemId) {
        store.remove(itemId);
    }
}
