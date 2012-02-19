package jobs;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import models.Item;
import models.ItemCache;
import play.jobs.Job;
import utils.Constants;
import utils.bigHeap.BigHeap;
import utils.bigHeap.ItemCacheNode;
import utils.bigHeap.ItemCacheNodeComprator;

public class ItemCacheJobs extends Job {

  @Override
  public void doJob() throws Exception {
    // TODO Auto-generated method stub
    super.doJob();
  }
  /**
   * 具体的Item索引算法.
   * 采用大堆进行数据排序
   * @param queryItems
   */
  public static void cache(Map<String,String> queryItems){
    if(queryItems.size()==0){
      return;
    }
    String key = ItemCache.generateQueryKeyMD5(queryItems);
    BigHeap<ItemCacheNode> bigHeap = new BigHeap(new ItemCacheNodeComprator());
    //遍历所有Item，进行索引、排序
    List<Item> items = Item.findAll();
    for (Item item : items) {
      Long score = item.getScoreByQueryItems(queryItems);
      ItemCacheNode node = new ItemCacheNode(item.id,score);
      bigHeap.push(node);
    }
    //将索引后的Item进行分页，持久化存储
    TreeSet<ItemCacheNode> nodes = bigHeap.getTreeSet();
    StringBuilder builder = new StringBuilder();
    int counter = 0;
    int pageNo = 1;
    //遍历索引
    for (ItemCacheNode n : nodes) {
      counter++;
      builder.append(n.getItemId());
      //收集满一页进行持久化
      if(counter%Constants.PAGE_SIZE_GUESS == 0){
        ItemCache cache = new ItemCache();
        cache.date = new Date();
        cache.itemIds = builder.toString();
        cache.key = key;
        cache.pageNo = pageNo++;
        cache.pageSize = Constants.PAGE_SIZE_GUESS;
        cache.save();
      }else{
        builder.append(",");
      }
    }
  }
  /**
   * 清除无用的ItemCache
   * @param key
   * @param pageSize
   * @param date
   */
  public static void clearCache(int pageSize){
    ItemCache.delete("pageSize = ?", pageSize);
  }
}
