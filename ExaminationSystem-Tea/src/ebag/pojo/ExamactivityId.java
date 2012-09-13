package ebag.pojo;

/**
 * ExamactivityId entity. @author MyEclipse Persistence Tools
 */

public class ExamactivityId implements java.io.Serializable {

	// Fields

	private Eexam eexam;
	private Euser euser;
	private Integer state;

	// Constructors

	/** default constructor */
	public ExamactivityId() {
	}

	/** full constructor */
	public ExamactivityId(Eexam eexam, Euser euser, Integer state) {
		this.eexam = eexam;
		this.euser = euser;
		this.state = state;
	}

	// Property accessors

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

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ExamactivityId))
			return false;
		ExamactivityId castOther = (ExamactivityId) other;

		return ((this.getEexam() == castOther.getEexam()) || (this.getEexam() != null
				&& castOther.getEexam() != null && this.getEexam().equals(
				castOther.getEexam())))
				&& ((this.getEuser() == castOther.getEuser()) || (this
						.getEuser() != null
						&& castOther.getEuser() != null && this.getEuser()
						.equals(castOther.getEuser())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null
						&& castOther.getState() != null && this.getState()
						.equals(castOther.getState())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEexam() == null ? 0 : this.getEexam().hashCode());
		result = 37 * result
				+ (getEuser() == null ? 0 : this.getEuser().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		return result;
	}

}