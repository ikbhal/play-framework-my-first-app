package controllers;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

//import java.net.UnknownHostException;

//import org.jongo.Jongo;
//import org.jongo.MongoCollection;

import com.fasterxml.jackson.databind.JsonNode;
//import com.mongodb.DB;
//import com.mongodb.MongoClient;

import de.caluga.morphium.Morphium;
import de.caluga.morphium.MorphiumConfig;
import de.caluga.morphium.query.Query;
import models.Friend;
import models.Item;
import models.MyComponent;
import models.Product;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import util.MyMongoDBSingleton;
import play.mvc.Result;

public class ProductController extends Controller {

	@Inject
	MyComponent component;

	@Inject
	MyMongoDBSingleton myMongoDB;

	public Result injectTest() {
		if (component == null) {
			System.out.println("component is null");
		} else {
			System.out.println("component is not null is " + component.toString());
		}
		return ok("inject test");
	}

	public Result index() {
		System.out.println("Inside product controller index");
		Product product = new Product();
		product.setId(123);
		product.setName("sdasda");
		// return product;
		// return ok(product);
		return ok(Json.toJson(product));

	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result create() {
		System.out.println("Inside product controller create");
		RequestBody requestBody = request().body();
		JsonNode json = requestBody.asJson();
		int id = json.findPath("id").asInt();
		String name = json.findPath("name").textValue();

		Product product = new Product();
		product.setId(id);
		product.setName(name);
		// return product;
		// return ok(product);
		return ok(Json.toJson(product));
	}

	public Result get(Long id) {
		System.out.println("Inside product controller get id:" + id);
		return ok("product get id : " + id);

	}

	public Result getJson(Long id) {
		System.out.println("Inside product controller get json id:" + id);
		Product product = new Product();
		// product.setId(33L);
		product.setName("Mango");
		// JsonNode json = Json.toJson(product);
		// Result jsonResult = ok(json);
		;

		return ok("product get json id : " + id);
	}

	public Result update() {
		System.out.println("Inside product controller update");
		return ok("product update");
	}

	public Result mongo() {
		System.out.println("Inside play productcontroller mongo action");
		Friend one = null;
		// try {
		// DB db = new MongoClient().getDB("play");

		// Jongo jongo = new Jongo(db);
		// MongoCollection friends = jongo.getCollection("friends");
		// MongoCursor mc;
		// MongoCursor<Friend> all = friends.find("{name:
		// 'Joe'}").as(Friend.class);
		// one = friends.findOne("{name: 'Joe'}").as(Friend.class);
		// } catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return ok("test mongo");
		// return ok(Json.toJson(one));
	}

	public Result morphium() {
		System.out.println("Inside play productcontroller morphium action");
		StringBuffer sb = new StringBuffer();
		try {
			MorphiumConfig cfg = new MorphiumConfig();
			cfg.setDatabase("play");// db name is play
			cfg.addHost("localhost", 27017);
			Morphium m = new Morphium(cfg);

			Query<Item> q = m.createQueryFor(Item.class).f("name").eq("mac");
			List<Item> lst = q.asList();

			for (Item item : lst) {
				System.out.println(item);
				sb.append(item.toString() + "<br>\n");
			}
		} catch (UnknownHostException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok("test morphium <br>\n" + sb.toString());
	}

	public Result morphiumInject() {
		System.out.println("Inside play productcontroller morphium  inject action");
		StringBuffer sb = new StringBuffer();

		Query<Item> q = myMongoDB.getMorphium().createQueryFor(Item.class).f("name").eq("mac");
		List<Item> lst = q.asList();

		for (Item item : lst) {
			System.out.println(item);
			sb.append(item.toString() + "<br>\n");
		}

		return ok("test morphium <br>\n" + sb.toString());
	}

}
