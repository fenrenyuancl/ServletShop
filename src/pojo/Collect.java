package pojo;

public class Collect {
	private Integer rid;			//自增主键
	private String uid;				//用户主键（phone）
	private String book;			//商品主键（isbn）
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
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
}
