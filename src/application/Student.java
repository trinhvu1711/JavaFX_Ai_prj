package application;

public class Student {
	private String id;
	private String name;
	private double point;
	
	public Student(String id, String name, double point) {
		super();
		this.id = id;
		this.name = name;
		this.point = point;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", point=" + point + "]";
	} 
	
}	
