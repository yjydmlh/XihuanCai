package controllers;

import java.util.List;

import models.Item;
import play.Logger;
import utils.Constants;


public class Items extends CRUD {
  /**
   * Guess what you like.
   * @param pageNo
   */
  public static void guess(int pageNo){
    int totalPage = Item.getTotalPageNo();
    if(pageNo<=0){
      pageNo = 1;
    }else if(pageNo > totalPage){
      pageNo = totalPage;
    }
    List items = Item.find("order by id desc").fetch(pageNo, Constants.PAGE_SIZE_GUESS);
    render(items, totalPage, pageNo);
  }
  
  public static void specialday(){
    //List items = Item.findAll();
    //render(items);
				guess(1);
  }
  
  public static void edit(){
    render();
  }
  
  public static void save(Item item){
    item.save();
  }
  
  public static void getImg(Long itemId){
				try{
    Item item = Item.findById(itemId);
    if(item.img!=null && item.img.getFile()!=null){
      response.setContentTypeIfNotSet(item.img.type());
      Logger.info(item.img.getFile().getName());
      renderBinary(item.img.get());
    }
				}catch(Exception e){
				  
				}
   }
}
