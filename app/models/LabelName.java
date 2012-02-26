package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.score.ItemLabelNameValueScore;
import play.db.jpa.Model;
/**
 * 查询选项的field名称，如gender、industry等.
 * @author MrROY
 *
 */
@Entity
@Table(name="T_LABEL_NAME")
public class LabelName extends Model{
  public String name;
  @OneToMany(mappedBy="labelName")
  public List<LabelItem> labelItems;
  @OneToMany(mappedBy="labelName")
  public Set<ItemLabelNameValueScore> itemLabelNameValueScores;

  public LabelName(){}
  public LabelName(String name){
    this.name = name;
  }
  @Override
  public String toString() {
    return this.id + " " + this.name;
  }
}
