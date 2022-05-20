/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DishShop;

import java.util.ArrayList;
import java.lang.String;
/**
 *
 * @author Jim_Tzel
 */
public class Shop {
    public ArrayList<Dish> items;
    private int item_count;
    
    public Shop(){
        
        items= new ArrayList<>();
        items.add(new Dish("Golden dish",12.5,0.2));
        items.add(new Dish("Silver dish",10,0.4));
        items.add(new Dish("Metal dish",11,0.1));
        items.add(new Dish("Wooden dish",5,0.9));
        items.add(new Dish("Fat dish",5,0.5));
        items.add(new Dish("Thick dish",11,0.3));
        items.add(new Dish("Big dish",13,0.2));
        items.add(new Dish("Giant dish",20,0.4));
        items.add(new Dish("Pink dish",66,0.7));
        items.add(new Dish("Orange dish",4,0.6));
        items.add(new Dish("Red dish",23,0.2));
        item_count=items.size();
        System.out.println(this);
    
    }
    public int get_item_count(){
        return item_count;    
    }
    public String toString(){
        String t="We have"+item_count+"items\n";
        for(Dish d:items)
            t+="Item: "+d.get_name()+"\nPrice: "+d.get_cost()+"\nWeight: "+d.get_weight();
        return t;
    }
    public Dish get_item(int i) {
    	return items.get(i);
    }
     
}
