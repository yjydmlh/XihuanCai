package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

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
  @Override
  public String toString() {
    return this.id + " " + this.labelName.name + "=" + this.labelValues;
  }
  
}
