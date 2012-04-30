/*
 * Eric Sare
 * IGN Code Foo Challenge #3
 * 
 * This program takes the number of population and generate the best plates combination with digits and letters
 * with the least number of excess plates.
 * Ex. 1) a population of 999 will have the best combination of 3 spaces for digits, 10^3 = 1000, with 1 excess plate.
 * Ex. 2) a population of 500 will have the best combination of 1 space for letters and 1 space for digits, 26*10 = 260
 * and we multiply 260 plates by 2 since digits and letters can switch for total of 520 plates with 20 plates excess.
 */

import java.util.Scanner;

public class CodeFoo3 
{
	public static void main(String[] args)
	{
		double digit = 10, alpha = 26, digitPlatesCount = 0, alphaPlatesCount = 0, digitPowerCount = 0, alphaPowerCount = 0;
		double population, bestPlatesCount, bestExcess;
		double bestOuterPowerCount, bestInnerPowerCount, maxOuterPowerCount, maxInnerPowerCount, bestInnerBase, bestOuterBase;
		double outerPowerCount, innerPowerCount;
		Scanner scan = new Scanner(System.in);
		char respond = 'y';
		
		while(respond == 'y')
		{
			System.out.print("Please enter the population: ");
			population = scan.nextDouble();
			scan.nextLine();
			
			while(digitPlatesCount < population)
			{
				digitPowerCount++;
				digitPlatesCount = Math.pow(digit, digitPowerCount);
			}
			
			bestPlatesCount = digitPlatesCount;
			bestExcess = digitPlatesCount - population;
			bestOuterBase = digit;
			bestInnerBase = alpha;
			bestOuterPowerCount = digitPowerCount;
			bestInnerPowerCount = 0;
			maxOuterPowerCount = digitPowerCount;
			
			while(alphaPlatesCount < population)
			{
				alphaPowerCount++;
				alphaPlatesCount = Math.pow(alpha, alphaPowerCount);
			}
			
			if(bestExcess > (alphaPlatesCount - population))
			{
				bestPlatesCount = alphaPlatesCount;
				bestExcess = alphaPlatesCount - population;
				bestOuterBase = alpha;
				bestInnerBase = digit;
				bestOuterPowerCount = alphaPowerCount;
				bestInnerPowerCount = 0;
				maxOuterPowerCount = alphaPowerCount;
				maxInnerPowerCount = digitPowerCount;
			}
			else
			{
				maxInnerPowerCount = alphaPowerCount;
			}
			
			for(outerPowerCount = 1; (outerPowerCount < maxOuterPowerCount) ; outerPowerCount++)
			{
				double tempPlateCount = Math.pow(bestOuterBase, outerPowerCount);
				
				for(innerPowerCount = 1; (innerPowerCount < maxInnerPowerCount) && (tempPlateCount <= bestPlatesCount);innerPowerCount++)
				{
					int combo = (int) (outerPowerCount + innerPowerCount);
					tempPlateCount = Math.pow(bestOuterBase, outerPowerCount) * Math.pow(bestInnerBase, innerPowerCount) * combo;
					
					double tempExcess = tempPlateCount - population;
					
					if(tempExcess > 0 && tempExcess < bestExcess)
					{
						bestPlatesCount = tempPlateCount;
						bestExcess = tempExcess;
						bestOuterPowerCount = outerPowerCount;
						bestInnerPowerCount = innerPowerCount;
					}
				}
			}
			
			System.out.println("The best combination is composed of: ");
			System.out.println((int) bestPlatesCount + " plates total");
			if((int)bestOuterBase == 26)
			{
				System.out.printf("%.0f spaces for alphabets\n", bestOuterPowerCount);
				System.out.printf("%.0f spaces for digits\n", bestInnerPowerCount);
			}
			else
			{
				System.out.printf("%.0f spaces for digits\n", bestOuterPowerCount);
				System.out.printf("%.0f spaces for letters\n", bestInnerPowerCount);
			}
			System.out.println((int)bestExcess + " excess plates");
			System.out.println("\n ====================================== \n");
			
			System.out.println("Would you like to run the program again? y or n : ");
			respond = scan.nextLine().toLowerCase().charAt(0);
			
			while(respond != 'y' && respond != 'n')
			{
				System.out.println("Please enter 'y' or 'n' only: ");
				respond = scan.nextLine().toLowerCase().charAt(0);
			}
		}
		
	}
}
