package com.jszybisty.marketcheckout.repository;

import com.jszybisty.marketcheckout.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 30.08.2017.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByIdAndName(Long id, String name);
}

