package models.score;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
import models.Item;
import models.LabelName;
import models.LabelValue;

/**
 * 商品标签名值对的分值
 * @author MrROY
 */
@Entity
@Table(name="T_ITEM_LABEL_NAME_VALUE_SCORE")
public class ItemLabelNameValueScore extends Model{
  @ManyToOne
  public Item item;
  @ManyToOne
  public LabelName labelName;
  @ManyToOne
  public LabelValue labelValue;
}
