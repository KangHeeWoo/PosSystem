package pos.vo;

import java.sql.Date;

public class SaleVo {
	private int saNum;
	private int mNum;
	private int saPrice;
	private int saPoint;
	private Date saDate;
	
	public int getSaNum() {
		return saNum;
	}
	public void setSaNum(int saNum) {
		this.saNum = saNum;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public int getSaPrice() {
		return saPrice;
	}
	public void setSaPrice(int saPrice) {
		this.saPrice = saPrice;
	}
	public int getSaPoint() {
		return saPoint;
	}
	public void setSaPoint(int saPoint) {
		this.saPoint = saPoint;
	}
	public Date getSaDate() {
		return saDate;
	}
	public void setSaDate(Date saDate) {
		this.saDate = saDate;
	}
	public SaleVo(int saNum, int mNum, int saPrice, int saPoint, Date saDate) {
		super();
		this.saNum = saNum;
		this.mNum = mNum;
		this.saPrice = saPrice;
		this.saPoint = saPoint;
		this.saDate = saDate;
	}
}
