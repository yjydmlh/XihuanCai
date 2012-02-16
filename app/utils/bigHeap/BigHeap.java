package utils.bigHeap;

import java.util.Iterator;
import java.util.TreeSet;
/**
 * This data structure is used for sort objects and keep a specified size.
 * When push in an object, the heap will pop out the.
 * @author royguo1988@gmail.com
 *
 */
public class BigHeap {
  private int heapSize = 8;
  private TreeSet<Object> treeSet = new TreeSet<Object>();
  public BigHeap(){}
  public BigHeap(int heapSize){
    this.heapSize = heapSize;
  }
  public void push(Object o){
    treeSet.add(o);
    if(this.treeSet.size() > this.heapSize){
      treeSet.pollFirst();
    }
  }
  
  
}
