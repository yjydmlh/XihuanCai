package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;
/**
 * 一个Item由一个具体的field(如gender)和多个value(如男、女)组成.
 * @author Administrator
 *
 */
@Entity
@Table(name="T_LABEL_ITEM")
public class LabelItem extends Model{
  //Related shopping item.
  @ManyToOne
  public Item item;
  @ManyToOne
  public LabelName labelName;
  @OneToMany(mappedBy="labelItem")
  public List<LabelValue> labelValues;

  public LabelItem(){}
  /**
   * 初始化构造的时候可以选择只push一个value.
   * @param labelName
   * @param labelValue
   */
  public LabelItem(LabelName labelName,LabelValue labelValue){
    this.labelName = labelName;
    this.labelValues = new ArrayList<LabelValue>();
    this.labelValues.add(labelValue);
  }
  public LabelItem(LabelName labelName,List<LabelValue> labelValues){
    this.labelName = labelName;
    this.labelValues = labelValues;
  }
  @Override
  public String toString() {
    return this.id + " " + this.labelName.name + "=" + this.labelValues;
  }

}
