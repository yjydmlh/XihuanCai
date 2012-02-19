package utils.bigHeap;
/**
 * The node structure in ItemCache BigHeap.
 * @author Royguo1988@gmail.com
 *
 */
public class ItemCacheNode {
  private Long itemId;
  private Long score;
  public ItemCacheNode(){}
  public ItemCacheNode(long itemId,long score){
    this.itemId = itemId;
    this.score = score;
  }
  public Long getItemId() {
    return itemId;
  }
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
  public Long getScore() {
    return score;
  }
  public void setScore(Long score) {
    this.score = score;
  }

}
