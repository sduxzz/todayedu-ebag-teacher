package ebag.pojo;


import java.util.HashSet;
import java.util.Set;

/**
 * Eproblem entity. @author MyEclipse Persistence Tools
 */

public class Eproblem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String problem;
	private String hint;
	private String ans;
	private String analysis;
	private String point;
	private String difficulty;
	private String aspect;
	private String requests;
	private Integer type;
	private Set eexams = new HashSet(0);

	// Constructors

	/** default constructor */
	public Eproblem() {
	}

	/** minimal constructor */
	public Eproblem(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Eproblem(Integer id, String problem, String hint, String ans,
			String analysis, String point, String difficulty, String aspect,
			String requests, Integer type, Set eexams) {
		this.id = id;
		this.problem = problem;
		this.hint = hint;
		this.ans = ans;
		this.analysis = analysis;
		this.point = point;
		this.difficulty = difficulty;
		this.aspect = aspect;
		this.requests = requests;
		this.type = type;
		this.eexams = eexams;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getHint() {
		return this.hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getAns() {
		return this.ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getAspect() {
		return this.aspect;
	}

	public void setAspect(String aspect) {
		this.aspect = aspect;
	}

	public String getRequests() {
		return this.requests;
	}

	public void setRequests(String requests) {
		this.requests = requests;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set getEexams() {
		return this.eexams;
	}

	public void setEexams(Set eexams) {
		this.eexams = eexams;
	}

}