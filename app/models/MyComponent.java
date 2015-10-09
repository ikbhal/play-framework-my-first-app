package models;

public class MyComponent {
	String name;
	
	public MyComponent(){
		name = "Ikbhal";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyComponent [name=" + name + "]";
	}
	
	
}
