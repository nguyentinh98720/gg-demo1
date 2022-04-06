package tinhnv.transfer;

import java.util.List;

import org.springframework.data.domain.Page;

public class Paging<T> {

	private boolean isFirstPage;
	private boolean isLastPage;
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalPage;
	private List<T> data;

	public Integer getPageNo() {
		return pageNo;
	}

	public Integer getPageNumber() {
		return pageSize;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageSize = pageNumber;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

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
