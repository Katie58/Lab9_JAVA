package lab9;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShoppingList {
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean retry = true;
		
		while(retry) {
			greeting();
			ArrayList<ArrayList> cart = new ArrayList();
			printMenu();	
			shop(cart);
			retry = retry(cartTotal(cart));
		}
		exit();
	}
	
	private static void greeting() {
		System.out.println("Welcome to JAVA's Market!\n");
	}

	private static TreeMap<String, Double> menu() {
		TreeMap<String, Double> menu = new TreeMap<>();
		menu.put("apple", 0.99);
		menu.put("banana", 0.59);
		menu.put("cauliflower", 1.59);
		menu.put("dragonfruit", 2.19);
		menu.put("Elderberry", 1.79);
		menu.put("figs", 2.09);
		menu.put("grapefruit", 1.99);
		menu.put("honeydew", 3.49);
		
		return menu;
	}
					
	private static int padding() {
		Iterator i = menu().entrySet().iterator();
		int maxLength = 0;
		while (i.hasNext()) {
			Map.Entry pair = (Map.Entry)i.next();
			String item = pair.getKey().toString();
			if (item.length() > maxLength) {
				maxLength = item.length();
			}
		}
		return maxLength;
	}
	
	private static String padding(int padding, String rep) {
		String reps = rep;
		for (int i = -5; i < padding; i++) {
			reps += rep;
		}
		return reps;
	}
	
	private static void printMenu() {
		System.out.println("Item" + padding(padding() - 4, " ") + "Price");
		System.out.println(padding(padding(), "=") + padding(5, "="));
		for (String key : menu().keySet()) {
			System.out.println(key + padding(padding() - key.length(), " ") + "$" + menu().get(key));
		}
	}
	
	private static void shop(ArrayList<ArrayList> cart) {
		String order;
		boolean retry = true;

		while(retry) {
			System.out.print("\nWhat would you like to order? ");
			order = scnr.nextLine();
			if (menu().containsKey(order)) {
				cart.add(cartAdd(order,menu().get(order)));
				//cart.add(order, menu().get(order));	
				System.out.println(order + " has been added to your cart at $" + menu().get(order));
			} else {
				System.out.println("Sorry, we do not currently carry that item...");
			}
			retry = retry();
		}
	}
	
	private static ArrayList cartAdd(String item, double price) {
		ArrayList itemPair = new ArrayList();
		itemPair.add(item);
		itemPair.add(price);
		return itemPair;		
	}
	
	private static double cartTotal(ArrayList<ArrayList> cart) {
		double total = 0;
		for (ArrayList itemPair : cart) {
			double price = (double) itemPair.get(1);
			total += price;
		}
		return total;
	}
	
	private static boolean retry() {
		System.out.print("Would you like to order anything else? (y/n) ");
		return retry(scnr.nextLine().charAt(0));
	}
	
	private static boolean retry(double cartTotal) {
		System.out.print("\nYour cart contains $" + cartTotal + " empty cart and shop again? (y/n) ");
		return retry(scnr.nextLine().charAt(0));
	}
	
	private static boolean retry(char input) {
		while (input != 'y' && input != 'Y' && input != 'n' && input != 'N') {
			System.out.println("This is a simple yes or no question, try again...");
			input = scnr.nextLine().charAt(0);
		}
		return (input == 'y' || input == 'Y');
	}
	
	private static void exit() {
		System.out.println("GOODBYE!");
	}
	
}
