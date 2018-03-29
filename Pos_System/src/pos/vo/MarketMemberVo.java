package pos.vo;

import java.sql.Date;

public class MarketMemberVo {
	private int mNum;
	private String mName;
	private String mPhone;
	private String mAddr;
	private int mPoint;
	private Date mHiredate;
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmAddr() {
		return mAddr;
	}
	public void setmAddr(String mAddr) {
		this.mAddr = mAddr;
	}
	public int getmPoint() {
		return mPoint;
	}
	public void setmPoint(int mPoint) {
		this.mPoint = mPoint;
	}
	public Date getmHiredate() {
		return mHiredate;
	}
	public void setmHiredate(Date mHiredate) {
		this.mHiredate = mHiredate;
	}
	public MarketMemberVo(int mNum, String mName, String mPhone, String mAddr, int mPoint, Date mHiredate) {
		super();
		this.mNum = mNum;
		this.mName = mName;
		this.mPhone = mPhone;
		this.mAddr = mAddr;
		this.mPoint = mPoint;
		this.mHiredate = mHiredate;
	}
}
