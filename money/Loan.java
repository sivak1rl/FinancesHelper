package money;

public class Loan extends Investment
{
	private Integer	term;

	public Loan(Double annualPercentageRate, Double principle, Integer term)
	{
		super(annualPercentageRate, principle);
		this.term = term;
	}

	/**
	 * Assumes that standard payment is made
	 */
	@Override
	public Double amountAfterMonths(Integer months)
	{
		double temp = getPrinciple();
		for (int i = 0; i < months; i++)
		{
			temp *= (1 + (0.01 * getMonthlyPercentage()));
			temp -= getPayment();
		}
		return temp;
	}

	/**
	 * Assumes standard payment is made, then removes extra contribution
	 * 
	 * @param months
	 *            number of months to let it sit
	 * @param extraContribution
	 *            extra amount contributed to loan payments
	 * @return amount after the number of months have gone by, reduced by extra
	 *         contribution, standard payment, and increased by interest
	 *         monthly.
	 */
	@Override
	public Double amountAfterMonths(Integer months, Double extraContribution)
	{
		double temp = getPrinciple();
		for (int i = 0; i < months; i++)
		{
			temp *= (1 + (0.01 * getMonthlyPercentage()));
			temp -= getPayment();
			temp -= extraContribution;
		}
		return temp;
	}

	/**
	 * @param extraPayment
	 *            the amount extra to contribute every month
	 * @return How many months it will take to pay off this loan if you pay
	 *         extra
	 */
	public Integer getAmountOfMonthsWithExtraPayment(Double extraPayment)
	{
		Integer i;
		for (i = 0; amountAfterMonths(i, extraPayment) > 0; i++)
		{
		}
		return i;
	}

	@Override
	public Double getAnnualPercentageChange()
	{
		return super.getAnnualPercentageChange();
	}

	public Double getDiscountFactor()
	{
		Double top = Math.pow(1 + getDoubleMonthlyPercentage(), getTerm());
		return (top - 1) / (getDoubleMonthlyPercentage() * top);
	}

	public Double getPayment()
	{
		return getAnnualPercentageChange() > 0 ? getPrinciple()
				/ getDiscountFactor() : getPrinciple() / term;
	}

	@Override
	public Double getPrinciple()
	{
		return super.getPrinciple();
	}

	public Integer getTerm()
	{
		return term;
	}

	public Double getTotalInterestPaidOnTerm()
	{
		if (getAnnualPercentageChange() == 0)
		{
			return 0.0;
		}
		return getPayment() * term - getPrinciple();
	}

	/**
	 * @param extraPayment
	 *            Extra amount to pay each month
	 * @return Total amount of interest paid after factoring in extra payments
	 *         each month
	 */
	public Double getTotalInterestPaidWithExtraPayments(Double extraPayment)
	{
		if (getAnnualPercentageChange() == 0)
		{
			return 0.0;
		}
		Double amountAtEnd = amountAfterMonths(
				getAmountOfMonthsWithExtraPayment(extraPayment) - 1,
				extraPayment);
		Double lastInterest = amountAtEnd * getDoubleMonthlyPercentage();
		return (extraPayment + getPayment())
				* (getAmountOfMonthsWithExtraPayment(extraPayment) - 1)
				- getPrinciple() + lastInterest;

	}

	@Override
	public String toString()
	{
		return String
				.format("Loan [term="
						+ term
						+ ", getDiscountFactor()=%.4f, getPayment()=%.2f, getAnnualPercentageChange()="
						+ getAnnualPercentageChange()
						+ ", getPrinciple()=%.2f, getTerm()=" + getTerm()
						+ ", getMonthlyPercentage()=" + getMonthlyPercentage()
						+ ", getDoubleMonthlyPercentage()="
						+ getDoubleMonthlyPercentage() + "]",
						getDiscountFactor(), getPayment(), getPrinciple());
	}

	public static Double getInterestPaid(Double interestRate, Double payment,
			Double principle)
	{
		interestRate *= .01;
		interestRate /= 12;
		Double interestPaid = 0.0, interest;
		while (principle > payment)
		{
			interest = principle * interestRate;
			principle += interest;
			interestPaid += interest;
			principle -= payment;
		}
		interest = principle * interestRate;
		principle += interest;
		interestPaid += interest;

		return interestPaid;
	}

	public static Integer getMonthsPaying(Double interestRate, Double payment,
			Double principle)
	{
		interestRate *= .01;
		interestRate /= 12;
		Integer months = 0;
		Double interest;
		while (principle > payment)
		{
			months++;
			interest = principle * interestRate;
			principle += interest;
			principle -= payment;
		}
		months++;
		interest = principle * interestRate;
		principle += interest;

		return months;
	}
}
