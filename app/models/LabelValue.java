package models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.score.ItemLabelNameValueScore;

import play.db.jpa.Model;

@Entity
@Table(name="T_LABEL_VALUE")
public class LabelValue extends Model{
  public String value;
  @ManyToOne
  public LabelItem labelItem;
  @OneToMany(mappedBy="labelValue")
  public Set<ItemLabelNameValueScore> itemLabelNameValueScores;
  
  public String toString(){
    return this.id + " " + this.value;
  }
}
