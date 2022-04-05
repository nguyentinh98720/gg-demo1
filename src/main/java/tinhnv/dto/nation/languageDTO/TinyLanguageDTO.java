package tinhnv.dto.nation.languageDTO;

public class TinyLanguageDTO {

	private Integer languageId;
	private boolean official;

	public TinyLanguageDTO() {
		super();
	}

	public TinyLanguageDTO(Integer id, boolean official) {
		this.languageId = id;
		this.official = official;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}
}
