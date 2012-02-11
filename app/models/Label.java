package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;
/**
 * The label of query items
 * For example, "age"、"salary" etc.
 * @author MrROY
 *
 */
@Entity
@Table(name="T_LABEL")
public class Label extends Model{
  public static String name;
}
