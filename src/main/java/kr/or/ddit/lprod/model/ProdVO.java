package kr.or.ddit.lprod.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ProdVO {
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private int prod_sale;
	private String prod_buyer;
	@DateTimeFormat(pattern =  "yy/MM/dd")
	private Date prod_insdate;
	
	public Date getProd_insdate() {
		return prod_insdate;
	}
	public void setProd_insdate(Date prod_insdate) {
		this.prod_insdate = prod_insdate;
	}
	public int getProd_sale() {
		return prod_sale;
	}
	public void setProd_sale(int prod_sale) {
		this.prod_sale = prod_sale;
	}
	public String getProd_buyer() {
		return prod_buyer;
	}
	public void setProd_buyer(String prod_buyer) {
		this.prod_buyer = prod_buyer;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_lgu() {
		return prod_lgu == null ? "P101" : prod_lgu;
	}
	public void setProd_lgu(String prod_lgu) {
		this.prod_lgu = prod_lgu;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	
}
