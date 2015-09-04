import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

//Test whether the array is empty or nor
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

// Test for override objects
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Store firstStore = new Store("Walkers", "185 SE Portland");
    Store secondStore = new Store("Walkers", "185 SE Portland");
    assertTrue(firstStore.equals(secondStore));
  }
  //
  // @Test
  // public void save_savesObjectIntoDatabase() {
  //   Store myStore = new Store("Sally", "2015-09-01");
  //   myStore.save();
  //   Store savedStore = Store.all().get(0);
  //   assertTrue(savedStore.equals(myStore));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Store myStore = new Store("Sally", "2015-09-01");
  //   myStore.save();
  //   Store savedStore = Store.all().get(0);
  //   assertEquals(myStore.getId(), savedStore.getId());
  // }

//   @Test
//   public void find_findsStoreInDatabase_true() {
//     Store myStore = new Store("Sally");
//     myStore.save();
//     Store savedStore = Store.find(myStore.getId());
//     assertTrue(myStore.equals(savedStore));
//   }
//
//   @Test
//   public void addCourse_addsCourseToStore() {
//     Course myCourse = new Course("Household chores");
//     myCourse.save();
//
//     Store myStore = new Store("Sally");
//     myStore.save();
//
//     myStore.addCourse(myCourse);
//     Course savedCourse = myStore.getCategories().get(0);
//     assertTrue(myCourse.equals(savedCourse));
//   }
//
//   @Test
//   public void getCategories_returnsAllCategories_ArrayList() {
//     Course myCourse = new Course("Household chores");
//     myCourse.save();
//
//     Store myStore = new Store("Sally");
//     myStore.save();
//
//     myStore.addCourse(myCourse);
//     List savedCategories = myStore.getCategories();
//     assertEquals(savedCategories.size(), 1);
//   }
//
//   @Test
//   public void delete_deletesAllStoresAndListsAssoicationes() {
//     Course myCourse = new Course("Household chores");
//     myCourse.save();
//
//     Store myStore = new Store("Sally");
//     myStore.save();
//
//     myStore.addCourse(myCourse);
//     myStore.delete();
//     assertEquals(myCourse.getStores().size(), 0);
//   }
 }
