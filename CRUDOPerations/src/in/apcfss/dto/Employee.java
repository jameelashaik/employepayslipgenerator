 package in.apcfss.dto;

import java.io.InputStream;

public class Employee {
	private int id;
	private String name;
	private String qualification;
	private String gender;
	private String doj;
	private String pancard;
	private String dob;
	private String salary;
	private String dayspresent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDayspresent() {
		return dayspresent;
	}
	public void setDayspresent(String dayspresent) {
		this.dayspresent = dayspresent;
	}
	
}