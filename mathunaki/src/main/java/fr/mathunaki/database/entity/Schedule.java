package fr.mathunaki.database.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table
public final class Schedule implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "schedule_id", nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "tuition_id", nullable = false)
	@NotNull
	private Tuition tuition;

	@Column(name = "start_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startDate;

	@Column(nullable = false)
	@Min(0)
	@Max(120)
	@NotNull
	private int duration;

	@Column
	private String description;

	@Override
	public Long getId() {
		return id;
	}

	public Tuition getTuition() {
		return tuition;
	}

	public void setTuition(Tuition tuition) {
		this.tuition = tuition;
	}

	public Date getStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		return calendar.getTime();
	}

	public void setStartDate(Date startDate) {
		// store a copy of the incoming date to avoid referencing a mutable
		// object.
		this.startDate = (Date) startDate.clone();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
