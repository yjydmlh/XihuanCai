package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import play.db.jpa.Model;
import play.libs.Codec;
/**
 * 查询缓存
 * @author Royguo1988@gmail.com.
 *
 */
public class ItemCache extends Model {
  //前端查询参数生成的key(MD5)
  public String key;
  public int pageNo;
  public int pageSize;
  //Array形式的数组,如1,2,3.
  public String itemIds;
  public Date date = new Date();
  //~~~~~~~~~~~~~~~ instance methods
  /**
   * 根据数组索引获取Item
   */
  public List<Item> getItems(){
    List<Item> items = new ArrayList<Item>();
    String[] ids = this.itemIds.split(",");
    for (int i = 0; i < ids.length; i++) {
      Item item = Item.findById(ids[i]);
      items.add(item);
    }
    return items;
  }
  //~~~~~~~~~~~~~~~ class methods 
  public static String generateArrayItemIds(List<Item> items){
    StringBuilder builder = new StringBuilder();
    for (Item item : items) {
      builder.append(item.id+",");
    }
    return builder.toString();
  }
  public static String generateQueryKeyMD5(Map<String,String> queryItems){
    if(queryItems.isEmpty()){
      return null;
    }
    StringBuilder builder = new StringBuilder();
    for (Entry<String, String> e : queryItems.entrySet()) {
      builder.append(e.getKey()+e.getValue());
    }
    return Codec.hexMD5(builder.toString());
  }
}
