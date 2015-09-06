import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Store {
  private int id;
  private String name;
  private String address;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  public String getAddress() {
    return address;
  }

  public Store(String name,String address) {
    this.name = name;
    this.address = address;
  }

  public static List<Store> all() {
    String sql = "SELECT * FROM stores ORDER BY name ASC";
    try(Connection con =DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Store.class);
    }
  }
  @Override
  public boolean equals(Object otherStore) {
    if(!(otherStore instanceof Store)) {
      return false;
    }else {
      Store newStore = (Store) otherStore;
      return this.getId() == newStore.getId() &&
      this.getName().equals(newStore.getName()) &&
      this.getAddress().equals(newStore.getAddress());
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO stores(name,address) VALUES (:name,:address)";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("name",this.name)
      .addParameter("address",this.address)
      .executeUpdate()
      .getKey();
    }
  }

  public static Store find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stores WHERE id=:id";
      Store store = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Store.class);
      return store;
    }
  }
  public void addBrand(Brand brand) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id,brand_id) VALUES (:store_id,:brand_id)";
      con.createQuery(sql)
      .addParameter("store_id",this.getId())
      .addParameter("brand_id",brand.getId())
      .executeUpdate();
    }
  }


  public List<Brand> getBrands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT brands.* FROM stores JOIN stores_brands ON (stores_brands.store_id = stores.id) JOIN brands ON (stores_brands.brand_id = brands.id) WHERE store_id=:id ";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Brand.class);
    }
  }

  public void update(String name) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stores SET (name,address) = (:name,:address) WHERE id =:id";
      con.createQuery(sql)
      .addParameter("address",address)
      .addParameter("name",name)
      .addParameter("id",id)
      .executeUpdate();
    }
  }
  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM stores WHERE id =:id";
      con.createQuery(deleteQuery)
      .addParameter("id",id)
      .executeUpdate();

      String joinDeleteQuery = "DELETE FROM stores_brands WHERE store_id = :storeId";
      con.createQuery(joinDeleteQuery)
      .addParameter("storeId", this.getId())
      .executeUpdate();

    }
  }
}
