package models;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.score.ItemLabelNameValueScore;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import utils.Constants;
/**
 * @author MrROY
 */
@Entity
@Table(name="T_ITEM")
public class Item extends Model{
	public String description;
 public Blob img;
 public String url;
 public Long baseScore = 0l;
 @OneToMany(mappedBy="item")
 public List<LabelItem> labelItems;
 @OneToMany(mappedBy="item")
 public Set<ItemLabelNameValueScore> itemLabelNameValueScores;
 // ~~~~~~~~~~~~~~~~~ instance methods
 @Override
 public String toString() {
   return this.id + " " + this.description;
 }
// ~~~~~~~~~~~~~~~~~~ static methods
/**
  * Get total page number.
  * @return
  */
 public static int getTotalPageNo(){
   Number totalPage = Item.count()/Constants.PAGE_SIZE_GUESS 
                 + (Item.count()%Constants.PAGE_SIZE_GUESS > 0 ? 1 : 0);
   return totalPage.intValue();
 }
 /**
  * The main algorithm of guess shopping items.
  * @param pageNo
  * @param queryItems
  * @return
  */
 public static List<Item> guessItem(int pageNo, Map<String,String> queryItems){
   //先检查倒排索引中是否已经存在
   String key = ItemCache.generateQueryKeyMD5(queryItems);
   ItemCache itemCache = ItemCache.find("key = ? and pageNo = ?", key, pageNo).first();
   if (itemCache.pageSize == Constants.PAGE_SIZE_GUESS
       && itemCache.pageNo == pageNo ) {
     //TODO 当上次cache超过一天的时候重建索引
     return itemCache.getItemsByArarry();
   }
   //倒排索引中不存在则进行查询并索引
   for (Entry<String, String> e : queryItems.entrySet()) {
     
   }
   return null;
 }
 /**
  * The main algorithm of special day guessing.
  * @param pageNo
  * @param specialDay
  * @return
  */
 public static List<Item> guessSpecialDay(int pageNo, String specialDay){
   return null;
 }
}
