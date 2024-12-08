package Springbootcrud.Nagpro.Controller;

import Springbootcrud.Nagpro.model.Item;
import Springbootcrud.Nagpro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/addItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item){

       return itemService.createItem(item);
    }

    @GetMapping()
        public List<Item> getAllItems(){

            return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id){

        return itemService.getById(id);

    }
}
