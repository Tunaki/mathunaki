package fr.mathunaki.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import fr.mathunaki.database.service.user.UserLevel;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "user_level" }) })
public final class Tuition implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tuition_id", nullable = false)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_level", nullable = false, length = 20)
	@NotNull
	private UserLevel userLevel;

	@Column
	private String description;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] resource;

	@Override
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getResource() {
		return resource;
	}

	public void setResource(byte[] resource) {
		this.resource = resource;
	}

}
