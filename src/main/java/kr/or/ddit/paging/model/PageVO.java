package kr.or.ddit.paging.model;

public class PageVO {
	private int page; //페이지 번호
	private int pageSize; // 페이지당 건수
	
	public PageVO() {
		
	}
	public PageVO(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page == 0 ? 1 : page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize == 0 ? 10 : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getPage();
		result = prime * result + getPageSize();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageVO other = (PageVO) obj;
		if (getPage() != other.getPage())
			return false;
		if (getPageSize() != other.getPageSize())
			return false;
		return true;
	}
	
	
}
