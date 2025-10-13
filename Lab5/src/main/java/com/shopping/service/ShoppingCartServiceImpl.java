package com.shopping.service;

import com.shopping.db.DB;
import com.shopping.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private Map<Integer, Item> cart = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item dbItem = DB.items.get(id);
        if (dbItem == null) return null;

        Item cartItem = cart.get(id);
        if (cartItem != null) {
            cartItem.setQty(cartItem.getQty() + 1);
        } else {
            cartItem = new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 1);
            cart.put(id, cartItem);
        }
        return cartItem;
    }

    @Override
    public void remove (Integer id){
        cart.remove(id);
    }

    @Override
    public Item update(Integer id, int qty){
        Item item = cart.get(id);
        item.setQty(qty);
        return item;
    }

    @Override
    public void clear(){
        cart.clear();
    }

    @Override
    public Collection<Item> getItems(){
        return cart.values();
    }

    @Override
    public int getCount(){
        return cart.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount(){
        return cart.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
    }
}
