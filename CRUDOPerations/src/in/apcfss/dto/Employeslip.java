package in.apcfss.dto;

public class Employeslip {
	private int id=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int dayspresent=0;
	private int basicpay=0;
	private int da=0;
	private int hra=0;
	private int grosstotal=0;
	private int pf=0;
	private int pt=0;
	private int netsalary=0;
	public int getBasicpay() {
		return basicpay;
	}
	public void setBasicpay(int basicpay) {
		this.basicpay = basicpay;
	}
	public int getDa() {
		return da;
	}
	public void setDa(int da) {
		this.da = da;
	}
	public int getHra() {
		return hra;
	}
	public void setHra(int hra) {
		this.hra = hra;
	}
	public int getGrosstotal() {
		return grosstotal;
	}
	public void setGrosstotal(int grosstotal) {
		this.grosstotal = grosstotal;
	}
	public int getPf() {
		return pf;
	}
	public void setPf(int pf) {
		this.pf = pf;
	}
	public int getPt() {
		return pt;
	}
	public void setPt(int pt) {
		this.pt = pt;
	}
	public int getNetsalary() {
		return netsalary;
	}
	public void setNetsalary(int netsalary) {
		this.netsalary = netsalary;
	}
	public int getDayspresent() {
		return dayspresent;
	}
	public void setDayspresent(int dayspresent) {
		this.dayspresent = dayspresent;
	}	
}
