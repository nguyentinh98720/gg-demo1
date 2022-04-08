package tinhnv.entity.account;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor()
@Entity
@Table(name = "tai_khoan")
//@Where(clause="da_xoa=false")
@SQLDelete(sql = "UPDATE tai_khoan SET da_xoa = true WHERE ma_tai_khoan=?")
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
}
