package Springbootcrud.Nagpro.Repository;

import Springbootcrud.Nagpro.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
