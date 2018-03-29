package pos.vo;

import java.sql.Date;

public class WarehouseVo {
	private int wNum;
	private int pNum;
	private int wCnt;
	private int wPrice;
	private Date wDate;
	
	public int getwNum() {
		return wNum;
	}
	public void setwNum(int wNum) {
		this.wNum = wNum;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getwCnt() {
		return wCnt;
	}
	public void setwCnt(int wCnt) {
		this.wCnt = wCnt;
	}
	public int getwPrice() {
		return wPrice;
	}
	public void setwPrice(int wPrice) {
		this.wPrice = wPrice;
	}
	public Date getwDate() {
		return wDate;
	}
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}
	public WarehouseVo(int wNum, int pNum, int wCnt, int wPrice, Date wDate) {
		super();
		this.wNum = wNum;
		this.pNum = pNum;
		this.wCnt = wCnt;
		this.wPrice = wPrice;
		this.wDate = wDate;
	}
}
