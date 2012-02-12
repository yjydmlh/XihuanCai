package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="T_LABEL_NAME")
public class LabelName extends Model{
  @OneToMany(mappedBy="labelName")
  public List<LabelItem> labelItems;
  public String name;
  
  @Override
  public String toString() {
    return this.id + " " + this.name;
  }
}
