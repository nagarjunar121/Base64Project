package Springbootcrud.Nagpro.service;

import Springbootcrud.Nagpro.Repository.ItemRepository;
import Springbootcrud.Nagpro.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
   // private ItemRepository itemRepository;

    private final ItemRepository itemRepository;
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item){
       String base64price = encodeToBase64(item.getPrice());
       item.setBase64Price(base64price);
        return itemRepository.save(item);
    }

    public List<Item> getAllItems(){

        List<Item> items = itemRepository.findAll();
        for(Item item: items){
            item.setPrice(decodeFromBase64(item.getBase64Price()));
        }
        return items;
    }

    public Item getById(Long id){

        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
           Item presentItem = item.get();
           presentItem.setPrice(decodeFromBase64(presentItem.getBase64Price()));
           return presentItem;
        }
        else{
            throw new RuntimeException("Item not found with id" + id);
        }

    }

    public String encodeToBase64(Double price){
        if(price == null){
            return null;
        }
        byte[] encodedPrice = Base64.getEncoder().encode(price.toString().getBytes());
        return new String(encodedPrice);
    }

    public Double decodeFromBase64(String base64Price){
        if(base64Price == null){
            return null;
        }
        byte[] decodedPrice = Base64.getDecoder().decode(base64Price);
        String decodedString = new String(decodedPrice);
        return Double.parseDouble(decodedString);
    }
}
