package patikaStore;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Brand {
	private int id;
	private String name;
	private static String[] brands = new String[9];
	public static LinkedHashMap<Integer, String> brandList = new LinkedHashMap<>();

	public Brand() {
		brandList.put(1, "Samsung");
		brandList.put(2, "Lenovo");
		brandList.put(3, "Apple");
		brandList.put(4, "Huawei");
		brandList.put(5, "Casper");
		brandList.put(6, "Asus");
		brandList.put(7, "HP");
		brandList.put(8, "Xiaomi");
		brandList.put(9, "Monster");

		brands = brandList.values().toArray(new String[0]);
	}

	public static boolean isThere(String enteredBrand) {
		return Arrays.asList(brands).contains(enteredBrand);
	}

	public static void printBrands() {
		Arrays.sort(brands);
		System.out.println("\nBRANDS\n----------");
		for (String brand : brands) {
			System.out.println("- " + brand);
		}
	}

	public static void printWithId() {
		int id = 1;
		for (String brand : brands) {
			System.out.println(id + "- " + brand);
			id++;
		}
	}

	private static void sortBrands(String[] brands) {
		Arrays.sort(brands);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String[] getBrandArray() {
		return brands;
	}

	public static void setBrandArray(String[] brandArray) {
		Brand.brands = brandArray;
	}
}