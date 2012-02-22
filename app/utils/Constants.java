package utils;

public class Constants {
  //“猜”页面分页大小
  public static final int PAGE_SIZE_GUESS = 8;
  public static final String SESSION_QUERY_ITEMS = "SESSION_QUERY_ITEMS";
  //标签分值权重
  public static final float LABEL_SCORE_WEIGHT = 0.9f;
  //基础分值权重
  public static final float BASE_SCORE_WEIGHT = 0.1f;
  public static final long ITEM_CACHE_TIMEOUT_HOURS = 6l;
  public static final int ITEM_CACHE_TIMEOUT_MINUTES = 10;
}
