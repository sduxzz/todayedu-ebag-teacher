package ebag.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Euser entity. @author MyEclipse Persistence Tools
 */

public class Euser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String pwd;
	private String userType;
	private Set examactivities = new HashSet(0);
	private Set eexams = new HashSet(0);
	private Set eanswers = new HashSet(0);
	private Set eclasses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Euser() {
	}

	/** minimal constructor */
	public Euser(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Euser(Integer id, String name, String pwd, String userType,
			Set examactivities, Set eexams, Set eanswers, Set eclasses) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.userType = userType;
		this.examactivities = examactivities;
		this.eexams = eexams;
		this.eanswers = eanswers;
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

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set getExamactivities() {
		return this.examactivities;
	}

	public void setExamactivities(Set examactivities) {
		this.examactivities = examactivities;
	}

	public Set getEexams() {
		return this.eexams;
	}

	public void setEexams(Set eexams) {
		this.eexams = eexams;
	}

	public Set getEanswers() {
		return this.eanswers;
	}

	public void setEanswers(Set eanswers) {
		this.eanswers = eanswers;
	}

	public Set getEclasses() {
		return this.eclasses;
	}

	public void setEclasses(Set eclasses) {
		this.eclasses = eclasses;
	}

}