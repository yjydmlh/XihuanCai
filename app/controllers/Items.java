package controllers;

import java.util.List;
import java.util.Map;

import models.Item;
import play.Logger;
import utils.QueryItem;


public class Items extends CRUD {
  /**
   * Guess what you like.
   * @param pageNo
   */
  public static void guess(int pageNo, QueryItem query) {
    int totalPage = Item.getTotalPageNo();
    if (pageNo <= 0) {
      pageNo = 1;
    } else if (pageNo > totalPage) {
      pageNo = totalPage;
    }
    Map<String, String> queryItems = query == null ? null : query.generateQueryItemsMap();
    List items = Item.guessItem(pageNo, queryItems);
    render(items, totalPage, pageNo);
  }

  public static void specialday(int pageNo) {
    int totalPage = Item.getTotalPageNo();
    List items = Item.findAll();
    render(items, pageNo, totalPage);
  }

  public static void edit() {
    render();
  }

  public static void save(Item item) {
    item.save();
  }

  public static void getImg(Long itemId) {
    try {
      Item item = Item.findById(itemId);
      if (item.img != null && item.img.getFile() != null) {
        response.setContentTypeIfNotSet(item.img.type());
        renderBinary(item.img.get());
      }
    } catch (Exception e) {
      Logger.error("无法获取图片,itemId = " + itemId);
    }
  }
}
