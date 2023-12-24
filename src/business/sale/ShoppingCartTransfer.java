package business.sale;

import java.util.ArrayList;
import java.util.List;

import business.saleLine.SaleLineTransfer;

public class ShoppingCartTransfer {
	
	private SaleTransfer sale;
	List<SaleLineTransfer> lines;
	
	public ShoppingCartTransfer(SaleTransfer sale) {
		super();
		this.sale = sale;
		this.lines = new ArrayList<SaleLineTransfer>();
	}
	public ShoppingCartTransfer(SaleTransfer sale, List<SaleLineTransfer> lines) {
		super();
		this.sale = sale;
		this.lines = lines;
	}
	public SaleTransfer getSale() {
		return sale;
	}
	public void setSale(SaleTransfer sale) {
		this.sale = sale;
	}
	public List<SaleLineTransfer> getLines() {
		return lines;
	}
	public void setLines(List<SaleLineTransfer> lines) {
		this.lines = lines;
	}
	@Override
	public String toString() {
		String s = this.sale.toString();
		for (SaleLineTransfer line : this.lines) {
			s += line.toString();
		}
		return s;
	}

}
