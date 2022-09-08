package com.mevlut.onlineshop.controller;

import com.mevlut.onlineshop.dao.ItemDAO;
import com.mevlut.onlineshop.exception.CustomException;
import com.mevlut.onlineshop.exception.ExceptionController;
import com.mevlut.onlineshop.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "item")
public class ItemController {

    private ItemDAO itemDAO;

    @Autowired
    public ItemController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @GetMapping(produces = "application/json")
    public List<Item> getAllItems(){
        List<Item> items = itemDAO.getAllItems();

        if (items.isEmpty()){
            throw new CustomException("There are no items currently.", HttpStatus.NOT_FOUND);
        }
        return itemDAO.getAllItems();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Item getItemById(@PathVariable int id){
        Item item = itemDAO.getItemById(id);

        if (item == null) {
            throw new CustomException("Item doesn't exist.", HttpStatus.NOT_FOUND);
        }

        return itemDAO.getItemById(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable int id){

        Boolean isUpdated = itemDAO.updateItem(item, id);

        if (isUpdated == false) {
            throw new CustomException("Couldn't update. Maybe doesn't exist.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable int id){
        Boolean isDeleted = itemDAO.deleteItemById(id);

        if (isDeleted == false){
            throw new CustomException("Couldn't delete. Maybe doesn't exist.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        try{
            itemDAO.deleteAll();
        }catch (Exception e){
            throw new CustomException("Couldn't delete.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Deleted All", HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addItem(@RequestBody Item item){

        try{
            Boolean didAdd = itemDAO.addItem(item);
            if (didAdd == false){
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            }
        }catch (Exception e){
            throw new CustomException("Couldn't add.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

}
