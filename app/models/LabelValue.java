package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="T_LABEL_VALUE")
public class LabelValue extends Model{
  @ManyToOne
  public LabelItem labelItem;
  public String value;
  public String toString(){
    return this.id + " " + this.value;
  }
}
