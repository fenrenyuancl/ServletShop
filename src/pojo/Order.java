package pojo;

import java.util.Date;

public class Order {
	private Integer rid;
	private String uid;
	private String oid;
	private String sta;
	private Integer aid;
	private Double payment;
	private Date placed;
	private Date receipt;
	private Date deliver;
	private Date handover;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Date getPlaced() {
		return placed;
	}
	public void setPlaced(Date placed) {
		this.placed = placed;
	}
	public Date getReceipt() {
		return receipt;
	}
	public void setReceipt(Date receipt) {
		this.receipt = receipt;
	}
	public Date getDeliver() {
		return deliver;
	}
	public void setDeliver(Date deliver) {
		this.deliver = deliver;
	}
	public Date getHandover() {
		return handover;
	}
	public void setHandover(Date handover) {
		this.handover = handover;
	}
}
