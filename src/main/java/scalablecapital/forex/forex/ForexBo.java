package scalablecapital.forex.forex;

public class ForexBo {
	
	public ForexBo(String forextCombination, int totalNumbers) {
		super();
		this.forextCombination = forextCombination;
		this.totalNumbers = totalNumbers;
	}
	private String forextCombination;
	public int getTotalNumbers() {
		return totalNumbers;
	}
	public void setTotalNumbers(int totalNumbers) {
		this.totalNumbers = totalNumbers;
	}
	public String getForextCombination() {
		return forextCombination;
	}
	public void setForextCombination(String forextCombination) {
		this.forextCombination = forextCombination;
	}
	private int totalNumbers;
	 
}
