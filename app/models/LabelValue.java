package models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.score.ItemLabelNameValueScore;
import play.db.jpa.Model;
/**
 * 标签-值表示单个item对应的某个label(如年龄)的值(如23岁).
 * @author royguo1988@gmail.com.
 *
 */
@Entity
@Table(name="T_LABEL_VALUE")
public class LabelValue extends Model{
  public String value;
  @ManyToOne
  public LabelItem labelItem;
  @OneToMany(mappedBy="labelValue")
  public Set<ItemLabelNameValueScore> itemLabelNameValueScores;

  public LabelValue(){}
  public LabelValue(String value){
    this.value = value;
  }
  public String toString(){
    return this.id + " " + this.value;
  }
}
