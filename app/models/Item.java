package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jobs.ItemCacheJobs;
import models.score.ItemLabelNameValueScore;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import utils.Constants;
import utils.DateUtils;

/**
 * @author MrROY
 */
@Entity
@Table(name = "T_ITEM")
public class Item extends Model {
  public String description;
  public Blob img;
  public String imgName;
  public String url;
  public Long baseScore = 0l;
  @OneToMany(mappedBy = "item")
  public List<LabelItem> labelItems;
  @OneToMany(mappedBy = "item")
  public Set<ItemLabelNameValueScore> itemLabelNameValueScores;

  // ~~~~~~~~~~~~~~~~~ instance methods
  @Override
  public String toString() {
    return this.id + " " + this.description;
  }
  public void _save(){
    this.imgName = img.getFile().getName();
    super._save();
  }
  // ~~~~~~~~~~~~~~~~~~ static methods
  /**
   * Get total page number.
   * @return
   */
  public static int getTotalPageNo() {
    Number totalPage =
        Item.count() / Constants.PAGE_SIZE_GUESS
            + (Item.count() % Constants.PAGE_SIZE_GUESS > 0 ? 1 : 0);
    return totalPage.intValue();
  }

  public Long getScoreByQueryItems(Map<String, String> queryItems) {
    Float score = 0f;
    for (Entry<String, String> e : queryItems.entrySet()) {
      ItemLabelNameValueScore itemLabelNameValueScore =
          ItemLabelNameValueScore.find(
              "item.id = ? and labelName.name = ? and labelValue.value = ?", this.id, e.getKey(),
              e.getValue()).first();
      if (itemLabelNameValueScore != null) {
        score =
            score
                + (itemLabelNameValueScore.score * Constants.LABEL_SCORE_WEIGHT + this.baseScore
                    * Constants.BASE_SCORE_WEIGHT);
      }
    }
    if(score == 0f){
      score = Constants.BASE_SCORE_WEIGHT * this.baseScore;
    }
    return score.longValue();
  }

  /**
   * The main algorithm of guess shopping items.
   * @param pageNo
   * @param queryItems
   * @return
   */
  public static List<Item> guessItem(int pageNo, Map<String, String> queryItems) {
    // 先检查倒排索引中是否已经存在
    String key = ItemCache.generateQueryKeyMD5(queryItems);
    Long cacheCount =
        ItemCache.count("queryKey = ? and pageNo = ? and pageSize = ?", key, pageNo,
            Constants.PAGE_SIZE_GUESS);
    if (cacheCount == 0) {
      ItemCacheJobs.cache(queryItems);
    }
    ItemCache itemCache =
        ItemCache.find("queryKey = ? and pageNo = ? and pageSize = ?", key, pageNo,
            Constants.PAGE_SIZE_GUESS).first();
    if (itemCache != null) {
      //超过一定时间就重新生成索引(分钟数和小时数，以最小的为准)
      if(DateUtils.getDiffHours(itemCache.date, new Date()) > Constants.ITEM_CACHE_TIMEOUT_HOURS
          ||DateUtils.getDiffMinutes(itemCache.date, new Date()) > Constants.ITEM_CACHE_TIMEOUT_MINUTES){
        ItemCacheJobs.cache(queryItems);
      }
      return itemCache.getItems();
    } else {
      return new ArrayList<Item>();
    }
  }

  /**
   * The main algorithm of special day guessing.
   * @param pageNo
   * @param specialDay
   * @return
   */
  public static List<Item> guessSpecialDay(int pageNo, String specialDay) {
    return null;
  }
}
