package ebag.pojo;


import java.util.HashSet;
import java.util.Set;

/**
 * Eclass entity. @author MyEclipse Persistence Tools
 */

public class Eclass implements java.io.Serializable {

	// Fields

	private Integer id;
	private String classname;
	private String tag;
	private String grade;
	private Set eusers = new HashSet(0);
	private Set esubjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Eclass() {
	}

	/** minimal constructor */
	public Eclass(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Eclass(Integer id, String classname, String tag, String grade,
			Set eusers, Set esubjects) {
		this.id = id;
		this.classname = classname;
		this.tag = tag;
		this.grade = grade;
		this.eusers = eusers;
		this.esubjects = esubjects;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Set getEusers() {
		return this.eusers;
	}

	public void setEusers(Set eusers) {
		this.eusers = eusers;
	}

	public Set getEsubjects() {
		return this.esubjects;
	}

	public void setEsubjects(Set esubjects) {
		this.esubjects = esubjects;
	}

}