package org.androidpn.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * ApnUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "apn_user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1995380469365662981L;
	private Long id;
	private Date createdDate;
	private String email;
	private String name;
	private String password;
	private Date updatedDate;
	private String username;

	@Transient
	private boolean online;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username) {
		this.username = username;
	}

	/** full constructor */
	public User(Date createdDate, String email, String name,
			String password, Date updatedDate, String username) {
		this.createdDate = createdDate;
		this.email = email;
		this.name = name;
		this.password = password;
		this.updatedDate = updatedDate;
		this.username = username;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "email", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "username", unique = true, nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "online", length = 3)
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User)) {
			return false;
		}

		final User obj = (User) o;
		if (username != null ? !username.equals(obj.username)
				: obj.username != null) {
			return false;
		}
		if (!(createdDate.getTime() == obj.createdDate.getTime())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 29 * result + (username != null ? username.hashCode() : 0);
		result = 29 * result
				+ (createdDate != null ? createdDate.hashCode() : 0);
		return result;
	}

}