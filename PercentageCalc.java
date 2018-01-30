package money;
import java.util.Scanner;

/**
 * This calculator will calculate percentage change over <i>n</i> years. There
 * will be no reduction or increase calculate, i.e., you cannot add to it year
 * by year.
 * 
 * @author RSIVAK
 */
public class PercentageCalc
{

	public static void main(String[] args)
	{
		Integer initAmount, years;
		Double yearPerc, newTotal, percChange;
		try (Scanner sc = new Scanner(System.in))
		{
			System.out.println("Please provide an initial amount: ");
			initAmount = sc.nextInt();

			System.out.println("Please give a yearly percentage reduction. ");
			System.out
					.println("This value will be reduced to a decimal percentage. Please provide the PERCENTAGE, e.g. 5% will be changed BY ME to 0.05");
			yearPerc = sc.nextDouble();

			System.out
					.println("Please give the number of years (a whole number): ");
			years = sc.nextInt();
		}
		yearPerc *= .01;
		newTotal = yearPerc < 1 ? initAmount - (initAmount * yearPerc)
				: initAmount * yearPerc;
		percChange = yearPerc < 1 ? 1 - (newTotal / initAmount) : newTotal
				/ initAmount;
		System.out
				.printf("After %d year it is: %6.2f \nThis is a %4.4f percent change overall\n",
						1, newTotal, percChange < 1 ? percChange * 100
								: percChange * 100 - 100);
		for (int i = 2; i <= years; i++)
		{
			newTotal = yearPerc < 1 ? newTotal - (newTotal * yearPerc)
					: newTotal * yearPerc;
			percChange = yearPerc < 1 ? 1 - (newTotal / initAmount) : newTotal
					/ initAmount;

			System.out
					.printf("After %d years it is: %6.2f \nThis is a %4.4f percent change overall\n",
							i, newTotal, percChange < 1 ? percChange * 100
									: percChange * 100 - 100);
		}
	}

}
