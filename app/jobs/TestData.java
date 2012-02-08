package jobs;

import models.Item;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class TestData extends Job{

  @Override
  public void doJob() throws Exception {
    new Item("desc1","url1").save();
    new Item("desc2","url1").save();
    new Item("desc3","url1").save();
    new Item("desc4","url1").save();
    new Item("desc5","url1").save();
    new Item("desc6","url1").save();
    new Item("desc7","url1").save();
    new Item("desc8","url1").save();
    new Item("desc9","url1").save();
    new Item("desc10","url1").save();
    new Item("desc11","url1").save();
    new Item("desc12","url1").save();
    new Item("desc13","url1").save();
    new Item("desc14","url1").save();
    new Item("desc15","url1").save(); 
  }
  
}
