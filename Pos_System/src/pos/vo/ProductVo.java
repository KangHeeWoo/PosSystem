package pos.vo;

public class ProductVo {
	private int pNum;
	private String pName;
	private int pPrice;
	private int pStock;
	
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpStock() {
		return pStock;
	}
	public void setpStock(int pStock) {
		this.pStock = pStock;
	}
	public ProductVo(int pNum, String pName, int pPrice, int pStock) {
		super();
		this.pNum = pNum;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pStock = pStock;
	}
}
