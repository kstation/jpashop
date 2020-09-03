package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    
    private String name;

    //다대다이기때문에 중간 테이블 매핑을 위핸 JoinTable 설정이 필요
    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<Item> items = new ArrayList<>();
    
    //self 양방향 관게 
    // 내 부모의 카테고리 지정 내 자식의 카테고리 지정
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    
    @OneToMany(mappedBy = "parent")    
    private List<Category> child = new ArrayList<>();
}
