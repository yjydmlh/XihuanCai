package models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.score.ItemLabelNameValueScore;
import play.data.validation.Required;
import play.db.jpa.Model;
/**
 * 标签-值表示单个item对应的某个label(如年龄)的值(如23岁).
 * @author royguo1988@gmail.com.
 *
 */
@Entity
@Table(name="T_LABEL_VALUE")
public class LabelValue extends Model{
  @Required
  public String value;
  @Required
  @ManyToOne
  public LabelItem labelItem;
  @OneToMany(mappedBy="labelValue")
  public Set<ItemLabelNameValueScore> itemLabelNameValueScores;

  public LabelValue(){}
  public LabelValue(String value,LabelItem labelItem){
    this.value = value;
    this.labelItem = labelItem;
  }
  public String toString(){
    return this.id + " " + this.value;
  }
}
