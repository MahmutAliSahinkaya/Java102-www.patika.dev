package patikaStore;

import java.util.Scanner;

public class PatikaStore {
	Scanner scan = new Scanner(System.in);

	public void run() {
		new NotebookProduct();
		new MobilePhoneProduct();
		new Brand();

		boolean isExit = false;

		while (true) {
			System.out.println("\n===== PatikaStore Product Management Panel =====");
			System.out.println("1- Notebook Operations");
			System.out.println("2- Phone Operations");
			System.out.println("3- List of Brands");
			System.out.println("0- Exit");
			System.out.print("Select Your Choice: ");

			int select = scan.nextInt();
			while (select < 0 || select > 3) {
				System.out.print("Invalid selection, Try Again: ");
				select = scan.nextInt();
			}

			switch (select) {
			case 1:
				NotebookProduct.menu();
				break;
			case 2:
				MobilePhoneProduct.menu();
				break;
			case 3:
				Brand.printBrands();
				break;
			case 0:
				isExit = true;
				break;
			default:
				System.out.println();
				System.out.println("There is no such an option. Please enter your choice again.");
				System.out.println();
			}

			if (isExit) {
				System.out.println("\nExiting the program...");
				break;
			}
		}
	}

}
