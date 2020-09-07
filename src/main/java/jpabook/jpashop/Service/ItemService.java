package jpabook.jpashop.Service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경감지를 이용한 업데이트
    //예시에서는 merge를 이용하여 모든 속성을 변경하나
    //실제 실무에서는 필요한 값만 변경감지기능을 이용해야한다. 아래처럼 필요한 값만 변경감지에 추가
    //param이 많다 싶으면 dto 객체를 생성하여 담아서 param으로
    @Transactional
    public void updateItem(Long itemID, String bookName, String boodAuthor, int bookPrice, int quantity){
        Item findItem = itemRepository.findOne(itemID);
        findItem.setPrice(bookPrice);
        findItem.setName(bookName);
        findItem.setStockQuantity(quantity);

        //그리고 실제 settter를 사용하기 보단 명확하게 method를 생성하여 호출하는것이 좋음
        //change()메소드는 직접 생성성
       //ex findItem.chage(itemId, bookName, bookAuthor...)
    }

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
