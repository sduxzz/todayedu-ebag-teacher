package ebag.pojo;

/**
 * Eanswer entity. @author MyEclipse Persistence Tools
 */

public class Eanswer implements java.io.Serializable {

	// Fields

	private Integer id;
	private Euser euser;
	private Integer problemId;
	private String textanswer;
	private String picanswer;
	private String point;
	private String textofteacher;
	private String picofteacher;
	private Integer state;

	// Constructors

	/** default constructor */
	public Eanswer() {
	}

	/** minimal constructor */
	public Eanswer(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Eanswer(Integer id, Euser euser, Integer problemId,
			String textanswer, String picanswer, String point,
			String textofteacher, String picofteacher, Integer state) {
		this.id = id;
		this.euser = euser;
		this.problemId = problemId;
		this.textanswer = textanswer;
		this.picanswer = picanswer;
		this.point = point;
		this.textofteacher = textofteacher;
		this.picofteacher = picofteacher;
		this.state = state;
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

	public Integer getProblemId() {
		return this.problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public String getTextanswer() {
		return this.textanswer;
	}

	public void setTextanswer(String textanswer) {
		this.textanswer = textanswer;
	}

	public String getPicanswer() {
		return this.picanswer;
	}

	public void setPicanswer(String picanswer) {
		this.picanswer = picanswer;
	}

	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getTextofteacher() {
		return this.textofteacher;
	}

	public void setTextofteacher(String textofteacher) {
		this.textofteacher = textofteacher;
	}

	public String getPicofteacher() {
		return this.picofteacher;
	}

	public void setPicofteacher(String picofteacher) {
		this.picofteacher = picofteacher;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}