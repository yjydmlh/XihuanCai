package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Item;
import models.LabelItem;
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

  public static void specialday(int pageNo) {
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
//      Logger.info(e.getKey() + " --> " + e.getValue());
      ItemLabelNameValueScore s = new ItemLabelNameValueScore();
      LabelName labelName = LabelName.find("name = ?", e.getKey()).first();
      LabelValue labelValue = LabelValue.find("value = ?", e.getValue()).first();
      LabelItem labelItem = LabelItem.find("labelName.name = ?", e.getKey()).first();
      if(labelName==null){
        labelName = new LabelName(e.getKey());
        labelName.save();
      }
      if(labelValue == null){
        labelValue = new LabelValue(e.getValue());
        labelValue.save();
      }
      if(labelItem == null && labelName !=null){
        labelItem = new LabelItem(labelName,labelValue);
      }
      s.labelName = labelName;
      s.labelValue = labelValue;
      s.score++;
      s.item = item;
      s.save();
    }
    item.baseScore++;
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
  /**
   * 从页面参数中生成数据库查询键值对
   * @param params
   * @return
   */
  private static Map<String,String> generateQueryItems(Map<String,String[]> params){
    Map<String,String> queryItems = new HashMap<String, String>();
    for (Entry<String, String[]> e : params.entrySet()) {
      String key = e.getKey();
      String[] value = e.getValue()[0].split(",");
      if(key.indexOf("guess_")>-1 && value.length > 0){
        for (int i = 0; i < value.length && !value[i].equals(""); i++) {
//          Logger.info(key.replace("guess_", "") + " " + value[i]);
          queryItems.put(key.replace("guess_", ""), value[i]);
        }
      }
    }
    return queryItems;
  }
}
