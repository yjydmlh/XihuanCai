package controllers;

import models.Item;
import play.db.jpa.Blob;


public class Items extends Application {
  public static void guess(){
    render();
  }
  
  public static void edit(){
    render();
  }
  
  public static void save(Item item){
    item.save();
  }
  
}