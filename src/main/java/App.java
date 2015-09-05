import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Store> stores = Store.all();
      model.put("stores", stores);
      model.put("allBrands", Brand.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      Store newStore = new Store(name, address);
      newStore.save();
      response.redirect("/stores");
      return null;
    });

    get("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Brand> brands = Brand.all();
      model.put("brands", brands);
      model.put("template", "/templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Brand newBrand = new Brand(name);
      newBrand.save();
      response.redirect("/brands");
      return null;
    });

    get("/stores/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Store store = Store.find(id);
      model.put("store", store);
      model.put("allBrands", Brand.all());
      model.put("template", "templates/store.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Brand brand = Brand.find(id);
      model.put("brand", brand);
      model.put("allStores", Store.all());
      model.put("template", "templates/brand.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add_stores", (request, response) -> {
      int storeId = Integer.parseInt(request.queryParams("store_id"));
      int brandId = Integer.parseInt(request.queryParams("brand_id"));
      Brand brand = Brand.find(brandId);
      Store store = Store.find(storeId);
      brand.addStore(store);
      response.redirect("/brands/" + brandId);
      return null;
    });

    post("/add_brands", (request, response) -> {
      int storeId = Integer.parseInt(request.queryParams("store_id"));
      int brandId = Integer.parseInt(request.queryParams("brand_id"));
      Brand brand = Brand.find(brandId);
      Store store = Store.find(storeId);
      store.addBrand(brand);
      response.redirect("/stores/" + storeId);
      return null;
    });

    get("/stores/:id/edit", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Store editStore = Store.find(id);
      model.put("editStore", editStore);
      List<Store> stores = Store.all();
      model.put("stores", stores);
      model.put("allBrands", Brand.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stores/:id/delete", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Store deleteStore = Store.find(id);
      deleteStore.delete();
      List<Store> stores = Store.all();
      model.put("stores", stores);
      model.put("allBrands", Brand.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/:id/edit", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Store editStore = Store.find(id);
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      editStore.update(name);
      List<Store> stores = Store.all();
      model.put("stores", stores);
      model.put("allBrands", Brand.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands/:id/edit", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Brand editBrand= Brand.find(id);
      model.put("editBrand", editBrand);
      List<Brand> brands = Brand.all();
      model.put("brands", brands);
      model.put("allStores", Store.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands/:id/edit", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Brand editBrand= Brand.find(id);
      String name = request.queryParams("name");
      editBrand.update(name);
      List<Brand> brands = Brand.all();
      model.put("brands", brands);
      model.put("allStores", Store.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
