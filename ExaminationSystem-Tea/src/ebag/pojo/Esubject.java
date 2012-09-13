package ebag.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Esubject entity. @author MyEclipse Persistence Tools
 */

public class Esubject implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String grade;
	private String verson;
	private Set eclasses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Esubject() {
	}

	/** minimal constructor */
	public Esubject(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Esubject(Integer id, String name, String grade, String verson,
			Set eclasses) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.verson = verson;
		this.eclasses = eclasses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getVerson() {
		return this.verson;
	}

	public void setVerson(String verson) {
		this.verson = verson;
	}

	public Set getEclasses() {
		return this.eclasses;
	}

	public void setEclasses(Set eclasses) {
		this.eclasses = eclasses;
	}

}