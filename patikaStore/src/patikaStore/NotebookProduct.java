package patikaStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotebookProduct extends Product {
	public NotebookProduct(int id, double price, double discountRate, int amount, String name, String brand, int memory,
			double screenSize, int RAM) {
		super(id, price, discountRate, amount, name, brand, memory, screenSize, RAM);
	}

	public NotebookProduct() {
		notebookList.add(new NotebookProduct(1, 7000, 10, 5, "Huawei Matebook 14", "Huawei", 512, 14, 16));
		notebookList.add(new NotebookProduct(2, 3699, 10, 5, "Lenovo V14 IGL", "Lenovo", 1024, 14, 8));
		notebookList.add(new NotebookProduct(3, 8199, 10, 5, "Asus Tuf Gaming", "Asus", 2048, 15.6, 32));
	}

	public static void menu() {
		System.out.println("\n===== Notebook Management Panel =====\n" + "1- Add A New Notebook\n"
				+ "2- Print Notebook List\n" + "3- Delete Notebook\n" + "4- Sort Notebooks By ID Number\n"
				+ "5- Filter Notebooks By Brands");
		System.out.print("Select Your Choice: ");
		int select = scan.nextInt();

		while (select < 0 || select > 5) {
			System.out.print("Invalid selection, Try Again: ");
			select = scan.nextInt();
		}

		switchCaseNotebookProduct(select);
	}

	public static void switchCaseNotebookProduct(int select) {
		switch (select) {
		case 1:
			add("Notebook");
			break;
		case 2:
			printTable(notebookList);
			break;
		case 3:
			remove();
			break;
		case 4:
			sortById(notebookList);
			break;
		case 5:
			filterByBrand(notebookList);
			break;
		default:
			System.out.println();
			System.out.println("There is no such an option. Please enter your choice again.");
			System.out.println();
		}
	}

	public static void printTable(List<NotebookProduct> notebooks) {
		String leftAlignFormat = "| %-2d | %-29s | %-9.1f | %-9s | %-9d | %-11.1f | %-9d | %-9d | %-9d | %-10s |%n";
		System.out.println(
				"| ID | Name of Product               | Price     | Brand     | Memory    | Screen Size | Camera    | Battery   | RAM       | Color      | ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");
		for (NotebookProduct notebook : notebooks) {
			System.out.format(leftAlignFormat, notebook.getId(), notebook.getName(), notebook.getPrice(),
					notebook.getBrand(), notebook.getId(), notebook.getName(), notebook.getPrice(), notebook.getBrand(),
					notebook.getMemory(), notebook.getScreenSize(), notebook.getRAM());
		}
	}

	public static void sortById(List<NotebookProduct> notebookList) {
		notebookList.sort(Comparator.comparingInt(NotebookProduct::getId));
		printTable(notebookList);
	}

	public static void filterByBrand(List<NotebookProduct> notebookList) {
		Map<String, List<NotebookProduct>> notebookByBrand = new HashMap<>();

		for (NotebookProduct notebook : notebookList) {
			String brand = notebook.getBrand();
			if (!notebookByBrand.containsKey(brand)) {
				notebookByBrand.put(brand, new ArrayList<>());
			}
			notebookByBrand.get(brand).add(notebook);
		}

		for (Map.Entry<String, List<NotebookProduct>> entry : notebookByBrand.entrySet()) {
			System.out.println("Brand: " + entry.getKey());
			printTable(entry.getValue());
		}
	}

	public static void remove() {
		printTable(notebookList);
		System.out.print("Enter the id of the notebook you want to delete: ");
		int select = scan.nextInt();

		int removeId = -1;
		for (NotebookProduct notebook : notebookList) {
			if (notebook.getId() == select) {
				removeId = mobilePhoneList.indexOf(notebook);
			}
		}

		if (removeId != -1) {
			notebookList.remove(removeId);
			System.out.println("Notebook is deleted");
		}
	}

}
