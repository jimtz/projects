package com.shop.DishShop;

public class Session {
	Shop shop;
	Basket basket;
	
	public Session() {
		shop=new Shop();
		basket=new Basket();
	}
	public Shop get_shop() {
		return shop;
	}
	public Basket get_basket() {
		return basket;
	}

}
