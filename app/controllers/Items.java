package controllers;

import java.util.List;

import models.Item;
import play.Logger;


public class Items extends CRUD {
  public static void guess(){
    List items = Item.findAll();
    render(items);
  }
  
  public static void specialday(){
    List items = Item.findAll();
    render(items);
  }
  
  public static void edit(){
    render();
  }
  
  public static void save(Item item){
    item.save();
  }
  
  public static void getImg(Long itemId){
    Item item = Item.findById(itemId);
    response.setContentTypeIfNotSet(item.img.type());
    Logger.info(item.img.getFile().getName());
    renderBinary(item.img.get());
   }
}