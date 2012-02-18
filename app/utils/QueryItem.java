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
  //工作行业
  public String industry;
  //消费能力
  public String consumptionLevel;
  //爱好
  public String hobby;

  public Map<String, String> generateQueryItems(){
    Map<String, String> queryItems = new HashMap<String, String>();
    queryItems.put("gender", this.gender);
    queryItems.put("age", this.age);
  }
}
