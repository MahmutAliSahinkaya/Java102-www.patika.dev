package patikaStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Product {
	private int id;

	private static int phoneCount = 3;
	private static int notebookCount = 3;
	private double price;
	private double discountRate;
	private int amount;
	private String name;
	private String brand;
	private int memory;
	private double screenSize;
	private int RAM;
	public static List<NotebookProduct> notebookList = new ArrayList<>();
	public static List<MobilePhoneProduct> mobilePhoneList = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);

	public Product(int id, double price, double discountRate, int amount, String name, String brand, int memory,
			double screenSize, int RAM) {
		this.id = id;
		this.price = price;
		this.discountRate = discountRate;
		this.amount = amount;
		this.name = name;
		this.brand = brand;
		this.memory = memory;
		this.screenSize = screenSize;
		this.RAM = RAM;
	}

	public Product() {

	}

	public static void add(String nameClass) {
		try {
			System.out.print("Name: ");
			String name = scan.next();
			scan.nextLine();
			System.out.print("Brand: ");
			String brand = scan.nextLine();
			brand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
			System.out.print("Memory: ");
			int memory = scan.nextInt();
			System.out.print("Screen Size: ");
			double screenSize = scan.nextDouble();
			System.out.print("RAM: ");
			int RAM = scan.nextInt();
			System.out.print("Unit Price: ");
			double price = scan.nextDouble();
			System.out.print("Discount Rate: ");
			double discountRate = scan.nextDouble();
			System.out.print("Stock Amount: ");
			int amount = scan.nextInt();

			if (!Brand.isThere(brand)) {
				System.out.println("This brand is not on the list");
			} else if (nameClass.equals("CellPhone")) {
				System.out.print("Battery: ");
				int battery = scan.nextInt();
				System.out.print("Color: ");
				String color = scan.next();
				color += scan.nextLine();
				System.out.print("Camera: ");
				int camera = scan.nextInt();

				setPhoneCount(getPhoneCount() + 1);
				mobilePhoneList.add(new MobilePhoneProduct(getPhoneCount(), price, discountRate, amount, name, brand,
						memory, screenSize, RAM, battery, color, camera));
				System.out.println("Phone is added the list");
			} else if (nameClass.equals("Notebook")) {
				setNotebookCount(getNotebookCount() + 1);
				notebookList.add(new NotebookProduct(getNotebookCount(), price, discountRate, amount, name, brand,
						memory, screenSize, RAM));
				System.out.println("Notebook is added the list");
			}
		} catch (Exception e) {
			System.out.println("\nSomething went wrong !!!");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getRAM() {
		return RAM;
	}

	public void setRAM(int RAM) {
		this.RAM = RAM;
	}

	public static int getPhoneCount() {
		return phoneCount;
	}

	public static void setPhoneCount(int phoneCount) {
		Product.phoneCount = phoneCount;
	}

	public static int getNotebookCount() {
		return notebookCount;
	}

	public static void setNotebookCount(int notebookCount) {
		Product.notebookCount = notebookCount;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}
}