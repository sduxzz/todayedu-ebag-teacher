package ebag.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Eexam entity. @author MyEclipse Persistence Tools
 */

public class Eexam implements java.io.Serializable {

	// Fields

	private Integer id;
	private Euser euser;
	private Integer type;
	private String name;
	private Long time;
	private Long createTime;
	private Set eproblems = new HashSet(0);
	private Set examactivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public Eexam() {
	}

	/** minimal constructor */
	public Eexam(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Eexam(Integer id, Euser euser, Integer type, String name, Long time,
			Long createTime, Set eproblems, Set examactivities) {
		this.id = id;
		this.euser = euser;
		this.type = type;
		this.name = name;
		this.time = time;
		this.createTime = createTime;
		this.eproblems = eproblems;
		this.examactivities = examactivities;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Euser getEuser() {
		return this.euser;
	}

	public void setEuser(Euser euser) {
		this.euser = euser;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Set getEproblems() {
		return this.eproblems;
	}

	public void setEproblems(Set eproblems) {
		this.eproblems = eproblems;
	}

	public Set getExamactivities() {
		return this.examactivities;
	}

	public void setExamactivities(Set examactivities) {
		this.examactivities = examactivities;
	}

}