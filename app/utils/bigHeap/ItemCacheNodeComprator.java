package utils.bigHeap;

import java.util.Comparator;


public class ItemCacheNodeComprator implements Comparator<ItemCacheNode> {

  @Override
  public int compare(ItemCacheNode o1, ItemCacheNode o2) {
    if(o1.getScore() >= o2.getScore()){
      return -1;
    }else{
      return 1;
    }
  }

}
