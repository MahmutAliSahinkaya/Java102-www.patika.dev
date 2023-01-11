package patikaStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MobilePhoneProduct extends Product {

	private int battery;
	private String color;
	private int camera;

	public MobilePhoneProduct(int id, double price, double discountRate, int amount, String name, String brand,
			int memory, double screenSize, int RAM, int battery, String color, int camera) {
		super(id, price, discountRate, amount, name, brand, memory, screenSize, RAM);
		this.battery = battery;
		this.color = color;
		this.camera = camera;
	}

	public MobilePhoneProduct() {
		mobilePhoneList.add(new MobilePhoneProduct(1, 3199, 10, 5, "Samsung Galaxy A51", "Samsung", 128, 6.5, 6, 4000,
				"Siyah", 32));
		mobilePhoneList
				.add(new MobilePhoneProduct(2, 7379, 10, 5, "Iphone 11 64 GB", "Apple", 64, 6.1, 6, 3046, "Mavi", 5));
		mobilePhoneList.add(new MobilePhoneProduct(3, 4012, 10, 5, "Redmi Note 10 Pro 8 GB", "Xiaomi", 128, 6.5, 12,
				4000, "Beyaz", 35));
	}

	public static void menu() {
		System.out.println("\n===== Phone Management Panel =====\n" + "1- Add A New Phone\n" + "2- Print Phone List\n"
				+ "3- Delete Phone\n" + "4- Sort Phones By ID Number\n" + "5- Filter Phones By Brands");
		System.out.print("Select Your Choice: ");
		int select = scan.nextInt();
		while (select < 0 || select > 5) {
			System.out.print("Invalid selection, Try Again: ");
			select = scan.nextInt();
		}

		switchCaseMobilePhoneProduct(select);
	}

	public static void switchCaseMobilePhoneProduct(int select) {
		switch (select) {
		case 1:
			add("MobilePhone");
			break;
		case 2:
			printTable(mobilePhoneList);
			break;
		case 3:
			remove();
			break;
		case 4:
			sortById(mobilePhoneList);
			break;
		case 5:
			filterByBrand(mobilePhoneList);
			break;
		default:
			System.out.println();
			System.out.println("There is no such an option. Please enter your choice again.");
			System.out.println();
		}
	}

	public static void printTable(List<MobilePhoneProduct> phones) {
		String leftAlignFormat = "| %-2d | %-29s | %-9.1f | %-9s | %-9d | %-11.1f | %-9d | %-9d | %-9d | %-10s |%n";
		System.out.println(
				"| ID | Name of Product               | Price     | Brand     | Memory    | Screen Size | Camera    | Battery   | RAM       | Color      | ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");
		for (MobilePhoneProduct phone : phones) {
			System.out.format(leftAlignFormat, phone.getId(), phone.getName(), phone.getPrice(), phone.getBrand(),
					phone.getMemory(), phone.getScreenSize(), phone.getCamera(), phone.getBattery(), phone.getRAM(),
					phone.getColor());
		}
	}

	public static void sortById(List<MobilePhoneProduct> mobilePhoneList) {
		mobilePhoneList.sort(Comparator.comparingInt(MobilePhoneProduct::getId));
		printTable(mobilePhoneList);
	}

	public static void filterByBrand(List<MobilePhoneProduct> mobilePhoneList) {
		Map<String, List<MobilePhoneProduct>> phoneByBrand = new HashMap<>();

		for (MobilePhoneProduct phone : mobilePhoneList) {
			String brand = phone.getBrand();
			if (!phoneByBrand.containsKey(brand)) {
				phoneByBrand.put(brand, new ArrayList<>());
			}
			phoneByBrand.get(brand).add(phone);
		}

		for (Map.Entry<String, List<MobilePhoneProduct>> entry : phoneByBrand.entrySet()) {
			System.out.println("Brand: " + entry.getKey());
			printTable(entry.getValue());
		}
	}

	public static void remove() {
		printTable(mobilePhoneList);
		System.out.print("Enter the id of the phone you want to delete: ");
		int select = scan.nextInt();

		int removeId = -1;
		for (MobilePhoneProduct phone : mobilePhoneList) {
			if (phone.getId() == select) {
				removeId = mobilePhoneList.indexOf(phone);
			}
		}

		if (removeId != -1) {
			mobilePhoneList.remove(removeId);
			System.out.println("Phone is deleted");
		}
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCamera() {
		return camera;
	}

	public void setCamera(int camera) {
		this.camera = camera;
	}
}