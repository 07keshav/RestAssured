package pOJO;

// inner JSon must though of as another POJo Class
// every array consider just for creation purpose as single json the n you will realize first create one more class 
// and then do that as list of class name as return or data type

public class CourseDetails {
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkdein;
	
	
	
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkdein() {
		return linkdein;
	}
	public void setLinkdein(String linkdein) {
		this.linkdein = linkdein;
	}
	
	
	
	

}
