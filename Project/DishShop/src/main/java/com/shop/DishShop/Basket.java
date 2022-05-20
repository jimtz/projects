/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DishShop;

import java.util.HashMap;
import java.lang.String;
/**
 *
 * @author Jim_Tzel
 */
public class Basket {
    private HashMap<Dish, Integer> list;  //Dish and quantity
    private int items_count;
    private double basket_price, basket_final_price;
    
    public Basket(){
        items_count=0;   
        basket_price=0;
        basket_final_price=0;
        list=new HashMap<Dish,Integer>();    
    }
    
   public void add_Dish(Dish d){
      if(list.containsKey(d)){
          list.replace(d,list.get(d)+1);
      }else{
          list.put(d,1);
      }
      items_count++;
      basket_final_price= calculate(); 
   }
   public void remove_Dish(Dish d){
       if(list.containsKey(d)){
           if(list.get(d)==1)
               list.remove(d);
           else
               list.replace(d,list.get(d)-1);
           
           items_count--;
           basket_final_price=calculate();           
           
       }}
     
   private double calculate(){
       double sum=0;
       for (HashMap.Entry<Dish,Integer> pair:list.entrySet()){
           sum+=pair.getValue()*pair.getKey().get_cost();
       }
       if(sum>100){
           sum*=0.9;
           System.out.println("Hey, you have 10% discound because you 've exceeded 100$, your sum is "+ sum+"  before shipping");         
       } 
       basket_price=sum;
       return calculate_final(sum);
   }
   private double calculate_final(double sum){  // +shipping
       for (HashMap.Entry<Dish,Integer> pair:list.entrySet())
           sum+=pair.getValue()*pair.getKey().get_weight()*5; // calculate shipping
       return sum;
    }
   public String toString(){
    String t= "You have " +items_count+" items in your basket.\n The cost is "+basket_price+"\nThe final cost,after shipping is "+basket_final_price+"\n";
     for (HashMap.Entry<Dish,Integer> pair:list.entrySet())
          t+="Item: "+pair.getKey().get_name()+"\nQuantity: "+pair.getValue()+"\n Cost per item "+pair.getKey().get_cost()+"\n";
    return t;
   }
}
    
