package models;

import java.util.List;

import javax.persistence.AttributeOverrides;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
/**
 * 
 * @author MrROY
 *
 */
@Entity
@Table(name="T_LABEL_ITEM")
public class LabelItem extends Model{
  //Related shopping item.
  @ManyToOne
  public Item item;
  @ManyToOne
  public Label label;
  @ElementCollection
  public List<String> values;
}
