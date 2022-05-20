/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DishShop;
import java.lang.String;

/**
 *
 * @author Jim_Tzel
 */
public class Dish {
    private double cost;
    private double weight;
    private static int dish_count=0;
    private String name;
    
    public Dish(String name,double c,double w){
        this.name=name;
        cost=c;
        weight=w;
        dish_count++;
    }
    public double get_weight(){
        return weight;
    }
    public double get_cost(){
        return cost;
    }
    public void set_price(float c){
        cost=c;
    }
    public String get_name(){
        return name;
    }
    public void set_name(String n){
        name=n;
    }
    public String toString() {
    	return "Item Name: "+name+" Item cost: "+cost+" Item weight: "+weight;
    }
    public int get_dish_count(){
    	return dish_count;
    	
    }
}
