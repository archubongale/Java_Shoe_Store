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

  @Test
  public void save_savesObjectIntoDatabase() {
    Store myStore = new Store ("Walkers","185 SE Portland");
    myStore.save();
    assertEquals(myStore.all().get(0),myStore);
  }

  @Test
  public void getId_returnsCorrectId() {
    Store myStore = new Store("Walkers","185 SE Portland");
    myStore.save();
    assertEquals(Store.all().get(0).getId(),myStore.getId());
  }

  @Test
  public void getName_returnsCorrectName() {
    Store myStore = new Store("Walkers","185 SE Portland");
    myStore.save();
    assertEquals(Store.all().get(0).getName(),myStore.getName());
  }
@Test
public void addBrands_StoreToTheJointTable() {
  Store myStore = new Store("Walkers","185 SE Portland");
  myStore.save();
  Brand myBrand = new Brand("Nike");
  myBrand.save();
  myStore.addBrand(myBrand);
  Brand savedBrand = myStore.getBrands().get(0);
  assertEquals(savedBrand,myBrand);
}

@Test
public void find_returnsCorrectStore() {
  Store myStore = new Store ("Walkers","185 SE Portland");
  myStore.save();
  Store savedStore = Store.find(myStore.getId());
  assertEquals(myStore,savedStore);
}

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
