import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Brand {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Brand(String name) {
    this.name = name;
  }

  public static List<Brand> all() {
    String sql = "SELECT * FROM brands";
    try(Connection con =DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Brand.class);
    }
  }
  @Override
  public boolean equals(Object otherBrand) {
    if(!(otherBrand instanceof Brand)) {
      return false;
    }else {
      Brand newBrand = (Brand) otherBrand;
      return this.getId() == newBrand.getId() &&
              this.getName().equals(newBrand.getName());
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO brands(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("name",this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM brands WHERE id=:id";
      Brand brand = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Brand.class);
      return brand;
    }
  }
  public void addStore(Store store) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id,brand_id) VALUES (:store_id,:brand_id)";
      con.createQuery(sql)
      .addParameter("store_id",store.getId())
      .addParameter("brand_id",this.getId())
      .executeUpdate();
    }
  }
  public List<Store> getStores() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT store.* FROM brands JOIN stores_brands ON (brands.id = stores_brands.brand_id) JOIN stores ON (stores_brands.store_id = stores.id) WHERE brands.id =:id ORDER BY name";
      return con.createQuery(sql)
      .addParameter("id",id)
      .executeAndFetch(Store.class);
    }
  }

  public void update(String name) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE brands SET name = :name WHERE id =:id";
      con.createQuery(sql)
      .addParameter("name",name)
      .addParameter("id",id)
      .executeUpdate();
    }
  }
  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM brands WHERE id =:id";
      con.createQuery(sql)
      .addParameter("id",id)
      .executeUpdate();

      String joinDeleteQuery = "DELETE FROM stores_brands WHERE brand_id = :brand_id";
      con.createQuery(joinDeleteQuery)
      .addParameter("brandId", this.getId())
      .executeUpdate();

    }
  }
}
