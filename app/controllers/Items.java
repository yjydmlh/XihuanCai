package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Item;
import models.LabelName;
import models.LabelValue;
import models.score.ItemLabelNameValueScore;
import play.Logger;


public class Items extends CRUD {
  /**
   * Guess what you like.
   * @param pageNo
   */
  public static void guess(int pageNo) {
    Map<String,String> queryItems = Items.generateQueryItems(params.all());
    int totalPage = Item.getTotalPageNo();
    if (pageNo <= 0) {
      pageNo = 1;
    } else if (pageNo > totalPage) {
      pageNo = totalPage;
    }
    List items = Item.guessItem(pageNo, queryItems);
    params.flash();
    render(items, totalPage, pageNo);
  }

  /**
   * 前台打开单个链接，后台进行分值修改
   * @param itemId
   */
  public static void openItem(Long itemId){
    Map<String,String> queryItems = Items.generateQueryItems(params.all());
    Item item = Item.findById(itemId);
    for (Entry<String, String> e : queryItems.entrySet()) {
      ItemLabelNameValueScore s = new ItemLabelNameValueScore();
      s.labelName = LabelName.find("name = ?", e.getKey()).first();
      s.labelValue = LabelValue.find("value = ?", e.getValue()).first();
      s.score++;
      s.item = item;
      s.save();
    }
    item.baseScore++;
    item.save();
  }

  public static void specialday(int pageNo) {
    int totalPage = Item.getTotalPageNo();
    if (pageNo <= 0) {
      pageNo = 1;
    } else if (pageNo > totalPage) {
      pageNo = totalPage;
    }
    List items = Item.findAll();
    render(items, pageNo, totalPage);
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
  /**
   * 从页面参数中生成数据库查询键值对
   * @param params
   * @return
   */
  private static Map<String,String> generateQueryItems(Map<String,String[]> params){
    Map<String,String> queryItems = new HashMap<String, String>();
    for (Entry<String, String[]> e : params.entrySet()) {
      String key = e.getKey();
      String[] value = e.getValue();
      if(key.indexOf("guess_")>-1 && value.length > 0){
        for (int i = 0; i < value.length && !value[i].equals(""); i++) {
          queryItems.put(key.replace("guess_", ""), value[0]);
        }
      }
    }
    return queryItems;
  }
}
