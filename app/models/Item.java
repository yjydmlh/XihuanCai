package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.Logger;
import play.data.validation.MinSize;
import play.data.validation.Required;
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
	//商品图片
 public Blob img;
 public String url;
 public Item(){}
 public Item(String description, String url){
   this.description = description;
   this.url = url;
 }
 /**
  * Get total page number.
  * @return
  */
 public static int getTotalPageNo(){
   Number totalPage = Item.count()/Constants.PAGE_SIZE_GUESS 
                 + (Item.count()%Constants.PAGE_SIZE_GUESS > 0 ? 1 : 0);
//   Logger.info(Item.count()/Constants.PAGE_SIZE_GUESS+"");
//   Logger.info((Item.count()%Constants.PAGE_SIZE_GUESS > 0 ? 1 : 0) +"");
   return totalPage.intValue();
 }
}
