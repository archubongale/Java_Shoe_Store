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

  @Test
  public void update_ChangesStoreFromDatabase() {
    Store myStore = new Store("Walkers","185 SE Portland");
    myStore.save();
    myStore.update("Macys");
    Store savedStore = Store.find(myStore.getId());
    assertEquals("Macys",savedStore.getName());
  }
  @Test
  public void delete_deletesFromDatabase() {
    Store myStore = new Store("Walkers","185 SE Portland");
    myStore.save();
    myStore.delete();
    assertEquals(Store.all().size(), 0);
  }
}
