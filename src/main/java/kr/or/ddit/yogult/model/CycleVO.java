package kr.or.ddit.yogult.model;

public class CycleVO {
	private int cid; // 고객 번호
	private int pid; // 제품 번호
	private int day; // 애음 요일 (1-일, 2-월, 3-화....7-토)
	private int cnt; // 애음 수량
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "CycleVO [cid=" + cid + ", pid=" + pid + ", day=" + day + ", cnt=" + cnt + "]";
	}
	
	
}
