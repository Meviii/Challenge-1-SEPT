package com.mevlut.onlineshop.dao;

import com.mevlut.onlineshop.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDAO {

    private static List<Item> items = new ArrayList<>();

    static
    {
        items.add(new Item("PC", "A computer", 3900.0));
        items.add(new Item("Laptop", "A laptop", 1900.0));
        items.add(new Item("Job", "A job", 10000.0));
    }

    private Item findById(int id){
        Item toReturn = null;

        for (Item i : items){
            if (i.getId() == id){
                toReturn = i;
            }
        }
        return toReturn;
    }

    public List<Item> getAllItems(){
        return items;
    }

    public Boolean addItem(Item item){
        Item checkIfExist = this.getItemById(item.getId());
        Boolean didAdd = true;
        // If id exists in POST request, check if exists
        if (checkIfExist != null) {
            if (checkIfExist.getId() == item.getId()) {
                this.updateItem(item, item.getId());
                didAdd = false;
            }
        }else {
            items.add(item);
        }
        return didAdd;
    }

    public Boolean updateItem(Item item, int id){
        Boolean isUpdated = false;

        Item toUpdate = this.findById(id);
        if (toUpdate != null) {
            toUpdate.setDesc(item.getDesc());
            toUpdate.setName(item.getName());
            toUpdate.setPrice(item.getPrice());
            isUpdated = true;
        }
        return isUpdated;
    }

    public Boolean deleteItemById(int id){
        Boolean toReturn = false;
        Item itemToDelete = this.findById(id);
        if (itemToDelete != null){
            items.remove(itemToDelete);
            toReturn = true;
        }

        return toReturn;
    }

    public Item getItemById(int id){
        return this.findById(id);
    }

    public void deleteAll() {
        items.clear();
    }
}
