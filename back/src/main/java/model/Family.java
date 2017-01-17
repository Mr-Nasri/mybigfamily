package model;

public class Family {
	private String id;
	private String name;
	private Person creator;
	private int nbMembers;
	
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
	public Person getCreator() {
		return creator;
	}
	public void setCreator(Person creator) {
		this.creator = creator;
	}
	public int getNbMembers() {
		return nbMembers;
	}
	public void setNbMembers(int nbMembers) {
		this.nbMembers = nbMembers;
	}
	
	
}
