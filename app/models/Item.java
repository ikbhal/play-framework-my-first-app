package models;

import org.bson.types.ObjectId;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.Reference;
import de.caluga.morphium.annotations.caching.Cache;

@Entity(translateCamelCase = true)
@Cache
public class Item {
	@Id
	private ObjectId id;

	private String name;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}

	// private EmbeddedObject emb;

	// @Reference
	// private MyEntity otherEntity;

	
}
