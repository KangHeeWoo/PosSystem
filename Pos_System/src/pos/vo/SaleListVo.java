package pos.vo;

public class SaleListVo {
	private int slNum;
	private int saNum;
	private int pNum;
	private int slCnt;
	
	public int getSlNum() {
		return slNum;
	}
	public void setSlNum(int slNum) {
		this.slNum = slNum;
	}
	public int getSaNum() {
		return saNum;
	}
	public void setSaNum(int saNum) {
		this.saNum = saNum;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getSlCnt() {
		return slCnt;
	}
	public void setSlCnt(int slCnt) {
		this.slCnt = slCnt;
	}
	
	public SaleListVo(int slNum, int saNum, int pNum, int slCnt) {
		super();
		this.slNum = slNum;
		this.saNum = saNum;
		this.pNum = pNum;
		this.slCnt = slCnt;
	}	
}
