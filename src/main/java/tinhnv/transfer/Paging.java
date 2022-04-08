package tinhnv.transfer;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Paging<T> {

	private boolean isFirstPage;
	private boolean isLastPage;
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalPage;
	private List<T> data;

	public static <T> Paging<T> paging(Page<T> page) {
		Paging<T> result = new Paging<>();
		result.isFirstPage = page.isFirst();
		result.isLastPage = page.isLast();
		result.pageNo = page.getNumber();
		result.pageSize = page.getSize();
		result.totalPage = page.getTotalPages();
		result.data = page.getContent();
		return result;
	}
}
