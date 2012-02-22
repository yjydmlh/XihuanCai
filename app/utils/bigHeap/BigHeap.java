package utils.bigHeap;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * This data structure is used for sort objects and keep a specified size.
 * When push in an object, the heap will pop out the.
 * @author royguo1988@gmail.com
 *
 */
public class BigHeap<T> {
  private TreeSet<T> treeSet;
  public BigHeap(Comparator<T> comparator){
    this.treeSet = new TreeSet<T>(comparator);
  }
  public void push(T o){
    treeSet.add(o);
  }
  public TreeSet<T> getTreeSet(){
    return this.treeSet;
  }
  /**
   * Test case.
   * @param args
   */
  public static void main(String[] args) {
    BigHeap bigHeap = new BigHeap(new ItemCacheNodeComprator());
    for (int i = 0; i < 3; i++) {
      bigHeap.push(new ItemCacheNode(1+i,22+i));
    }
    for (Object o : bigHeap.getTreeSet()) {
      System.out.println(((ItemCacheNode)o).getScore());
    }
  }
}
