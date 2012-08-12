package ebag.pojo;


/**
 * Examactivity entity. @author MyEclipse Persistence Tools
 */

public class Examactivity implements java.io.Serializable {

	// Fields

	private ExamactivityId id;
	private Eexam eexam;
	private Euser euser;

	// Constructors

	/** default constructor */
	public Examactivity() {
	}

	/** minimal constructor */
	public Examactivity(ExamactivityId id) {
		this.id = id;
	}

	/** full constructor */
	public Examactivity(ExamactivityId id, Eexam eexam, Euser euser) {
		this.id = id;
		this.eexam = eexam;
		this.euser = euser;
	}

	// Property accessors

	public ExamactivityId getId() {
		return this.id;
	}

	public void setId(ExamactivityId id) {
		this.id = id;
	}

	public Eexam getEexam() {
		return this.eexam;
	}

	public void setEexam(Eexam eexam) {
		this.eexam = eexam;
	}

	public Euser getEuser() {
		return this.euser;
	}

	public void setEuser(Euser euser) {
		this.euser = euser;
	}

}