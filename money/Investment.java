package money;

public abstract class Investment {

	private Double annualPercentageChange;
	private Double principle;

	/**
	 * Sets properties to null!
	 */
	protected Investment() {
		this.annualPercentageChange = null;
		this.principle = null;
	}

	/**
	 * 
	 * @param annualPercentageChange
	 *            Rate of growth in investment. Should be the PERCENTAGE, so if
	 *            your rate is 5.99%, put 5.99
	 * @param principle The initial value of the Investment, i.e. for a Loan, the total value of the Loan, for a Savings, how much money is in it.
	 */
	protected Investment(Double annualPercentageChange, Double principle) {
		this.annualPercentageChange = annualPercentageChange;
		this.principle = principle;
	}

	public Double getAnnualPercentageChange() {
		return annualPercentageChange;
	}

	public Double getPrinciple() {
		return principle;
	}

	/**
	 * @param months
	 *            number of months to grow.
	 * @return the principle after left untouched for <code>months</code>
	 *         months.
	 */
	public Double amountAfterMonths(Integer months) {
		double temp = getPrinciple();
		for (int i = 0; i < months; i++) {
			temp *= (1 + (0.01 * getMonthlyPercentage()));
		}
		return temp;
	}

	/**
	 * @param months
	 *            number of months to grow.
	 * @param extraContribution
	 *            extra money contributed to growth.
	 * @return the principle after left untouched for <code>months</code>
	 *         months.
	 */
	public Double amountAfterMonths(Integer months, Double extraContribution) {
		double temp = getPrinciple();
		for (int i = 0; i < months; i++) {
			temp *= (1 + (0.01 * getMonthlyPercentage()));
			temp += extraContribution;
		}
		return temp;
	}

	/**
	 * @return annual percentage divided by 12
	 */
	public Double getMonthlyPercentage() {
		return getAnnualPercentageChange() / 12.0;
	}

	/**
	 * Multiplies Monthly percentage by .01
	 * 
	 * @return Monthly percentage *.01
	 */
	public Double getDoubleMonthlyPercentage() {
		return getMonthlyPercentage() * 0.01;
	}

	public void setPrinciple(Double principle) {
		this.principle = principle;
	}

	public void setAnnualPercentageChange(Double annualPercentageChange) {
		this.annualPercentageChange = annualPercentageChange;
	}

}
