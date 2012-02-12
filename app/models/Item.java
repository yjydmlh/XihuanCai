package models;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
 @OneToMany(mappedBy="item")
 public List<LabelItem> labelItems;
 
 @Override
 public String toString() {
   return this.id + " " + this.description;
 }

/**
  * Get total page number.
  * @return
  */
 public static int getTotalPageNo(){
   Number totalPage = Item.count()/Constants.PAGE_SIZE_GUESS 
                 + (Item.count()%Constants.PAGE_SIZE_GUESS > 0 ? 1 : 0);
   return totalPage.intValue();
 }
 public static List<Item> guessItem(int pageNo, Map<String,String> queryItems){
   return null;
 }
 public static List<Item> guessSpecialDay(int pageNo, String specialDay){
   return null;
 }
}
