package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.Logger;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import utils.Constants;
/**
 * @author MrROY
 */
@Entity
@Table(name="T_QUERY_ITEM")
public class QueryItem extends Model{

}
