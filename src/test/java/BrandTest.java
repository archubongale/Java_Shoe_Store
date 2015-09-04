import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  //Test whether the array is empty or nor
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  // Test for override objects
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Brand firstBrand = new Brand("Nike");
    Brand secondBrand = new Brand("Nike");
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Brand myBrand = new Brand ("Nike");
    myBrand.save();
    assertEquals(myBrand.all().get(0),myBrand);
  }

  @Test
  public void getId_returnsCorrectId() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    assertEquals(Brand.all().get(0).getId(),myBrand.getId());
  }

  @Test
  public void getName_returnsCorrectName() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    assertEquals(Brand.all().get(0).getName(),myBrand.getName());
  }
  @Test
  public void getStores_returnsStoresList(){
    Brand myBrand = new Brand ("Addidas");
    myBrand.save();
    Brand savedBrand = Brand.all().get(0);
    assertEquals(savedBrand.getId(),myBrand.getId());
  }

  @Test
  public void find_returnsCorrectBrand() {
    Brand myBrand = new Brand ("Nike");
    myBrand.save();
    Brand savedBrand = Brand.find(myBrand.getId());
    assertEquals(myBrand,savedBrand);
  }

  @Test
  public void update_ChangesBrandFromDatabase() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    myBrand.update("Addidas");
    Brand savedBrand = Brand.find(myBrand.getId());
    assertEquals("Addidas",savedBrand.getName());
  }
  @Test
  public void delete_deletesFromDatabase() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    myBrand.delete();
    Brand savedBrand = Brand.find(myBrand.getId());
    assertEquals(false,myBrand.equals(savedBrand));
  }
}
