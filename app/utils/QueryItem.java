package utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 页面类别查询集合.
 * @author Royguo1988@gmail.com
 */
public class QueryItem {
  public String gender;
  public int age;
  // 工作行业
  public String industry;
  // 消费能力
  public String consumptionLevel;
  // 爱好
  public String hobby;

  public Map<String, String> generateQueryItemsMap() {
    Map<String, String> queryItems = new HashMap<String, String>();
    if (this.gender != null && !this.gender.trim().equals("")) {
      queryItems.put("gender", this.gender);
    }
    if (this.age > 0) {
      queryItems.put("age", this.age + "");
    }
    if (this.industry != null && !this.industry.trim().equals("")) {
      queryItems.put("industry", this.industry);
    }
    if (this.consumptionLevel != null && !this.consumptionLevel.trim().equals("")) {
      queryItems.put("consumptionLevel", this.consumptionLevel);
    }
    if (this.hobby != null && !this.hobby.trim().equals("")) {
      queryItems.put("hobby", this.hobby);
    }
    return queryItems;
  }
}
