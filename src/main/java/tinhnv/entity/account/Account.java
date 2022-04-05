package tinhnv.entity.account;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "tai_khoan")
//@Where(clause="da_xoa=false")
@SQLDelete(sql = "UPDATE tai_khoan SET da_xoa = true WHERE id=?")
@FilterDef(name = "deletedAccountFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedAccountFilter", condition = "da_xoa = :isDeleted")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_tai_khoan")
	private Long id;

	@Column(name = "ten_dang_nhap")
	private String loginName;

	@Column(name = "mat_khau")
	private String password;

	@Column(name = "ho_va_ten")
	private String fullName;

	@Column(name = "dia_chi")
	private String address;

//	@Column(name = "khoa_truy_cap")
//	private String token;
	
//	@Column(name = "tao_khoa")
//	private Timestamp timeToken;

	@Column(name = "hoat_dong")
	private boolean active;

	@Column(name = "quyen_han")
	private String role;

	@Column(name = "da_xoa")
	private boolean deleted;

	public Account() {
		super();
	}

	public Account(String loginName, String fullName,
			String address, boolean active,
			String role) {
		super();
		this.loginName = loginName;
		this.fullName = fullName;
		this.address = address;
		this.active = active;
		this.role = role;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getToken() {
//		return token;
		return null;
	}

	public void setToken(String token) {
//		this.token = token;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public Timestamp getTimeToken() {
//		return timeToken;
		return null;
	}

	public void setTimeToken(Timestamp timeToken) {
//		this.timeToken = timeToken;
	}
}
