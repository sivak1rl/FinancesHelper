package money;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MonthlyPercentageCalc
{

	public static void main(String[] args)
	{

		// Loan L;

		// L = retrieveInput();
		//
		// System.out.println(L);
		// System.out
		// .println("If you pay $100 extra per month, it will only take "
		// + L.getAmountOfMonthsWithExtraPayment(100.00)
		// + " months");
		// System.out
		// .println("If you pay $200 extra per month, it will only take "
		// + L.getAmountOfMonthsWithExtraPayment(200.00)
		// + " months");
		// System.out
		// .println("If you pay $400 extra per month, it will only take "
		// + L.getAmountOfMonthsWithExtraPayment(400.00)
		// + " months");
		// System.out.printf("Paying for the full term, you will pay $" + "%.2f"
		// + " in only interest\n", L.getTotalInterestPaidOnTerm());
		//
		// System.out.printf("Paying $100 extra per payment, you will pay $"
		// + "%.2f" + " in only interest\n",
		// L.getTotalInterestPaidWithExtraPayments(100.00));
		// System.out.printf("Paying $200 extra per payment, you will pay $"
		// + "%.2f in only interest\n",
		// L.getTotalInterestPaidWithExtraPayments(200.00));
		// System.out.printf("Paying $400 extra per payment, you will pay $"
		// + "%.2f in only interest\n",
		// L.getTotalInterestPaidWithExtraPayments(400.00));
		// 17773.51 5.99
		// 21578.79 5.99 84mo
		System.out.println("Interest on my current loan... "
				+ Loan.getInterestPaid(5.99, 315.13, 14785.29));
		System.out.println("Total interest percentage I will be paying: "
				+ (Loan.getInterestPaid(5.99, 315.13, 14785.29)/21578.79 ));
		System.out.println("Months paying on my current loan... "
				+ Loan.getMonthsPaying(5.99, 315.13, 14785.29));

		System.out.println("Interest on my original loan... "
				+ Loan.getInterestPaid(5.99, 315.13, 21578.79));
		System.out.println("Total interest percentage I would be paying: "
				+ (Loan.getInterestPaid(5.99, 315.13, 21578.79)/21578.79 ));
		System.out.println("Months paying on my original loan... "
				+ Loan.getMonthsPaying(5.99, 315.13, 21578.79));
	}

	/**
	 * @return A loan with details determined in the method.
	 */
	private static Loan retrieveInput()
	{
		Double annualPercentageChange = null, principle = null;
		Integer term = null;

		try (Scanner sc = new Scanner(System.in))
		{
			while (annualPercentageChange == null)
			{
				try
				{
					System.out.println("Provide APY/APR");
					annualPercentageChange = sc.nextDouble();
					if (annualPercentageChange < 0)
					{
						throw new NotTheInputIWantException();
					}
				} catch (InputMismatchException e1)
				{
					annualPercentageChange = null;
				} catch (NotTheInputIWantException e)
				{
					System.out
							.println("The annual percentage change cannot be a negative number. Try again.");
					annualPercentageChange = null;
				}
			}
			while (principle == null)
			{
				try
				{
					System.out.println("Provide Principle");
					principle = sc.nextDouble();
					if (principle <= 0)
					{
						throw new NotTheInputIWantException();
					}
				} catch (InputMismatchException e1)
				{
					principle = null;
				} catch (NotTheInputIWantException e)
				{
					System.out
							.println("The principle can't be zero or negative. Put in a positive number.");
					principle = null;
				}
			}
			while (term == null)
			{
				System.out.println("Provide Term in whole months");
				try
				{
					term = sc.nextInt();
				} catch (InputMismatchException e)
				{
					term = null;
					System.out
							.println("Term was not a whole number, please provide a whole number.");
				}
			}

		}
		return new Loan(annualPercentageChange, principle, term);
	}

}
