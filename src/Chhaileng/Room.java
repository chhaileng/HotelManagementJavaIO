package Chhaileng;
import java.util.Scanner;

public class Room {
	private String hotel[][];
	private static Scanner s = new Scanner(System.in);
	Room(int floor, int room){
		hotel = new String[floor][room];
	}
	
	public String getGName(int f, int r) {
		r--;
		return hotel[f][r];
	}
	public String [][]getAllRooms(){
		return hotel;
	}
	
	public void CheckIn(int floor, int room){
		room--;
		try {
			String n = "";
			do {
				System.out.print("Customer name: ");
				n = s.nextLine();
				if (!MyValidator.allowedName(n) || n.replaceAll("\\s+", " ").trim().equals(""))
					System.out.println("Invalid Name");
			} while (!MyValidator.allowedName(n) || n.replaceAll("\\s+", " ").trim().equals(""));
			n = n.replaceAll("\\s+", " ").trim();
			hotel[floor][room] = n;
//			System.out.println("\'"+ n +"\'");
		} catch (Exception e) {
			//System.out.println("Index out of bound");
		}
	}
	public void CheckOut(int floor, int room) {
		room--;
		try {
			hotel[floor][room] = null;
		} catch (Exception e) {
			System.out.println("Index out of bound");
		}
	}
	
	public void Display() {
		int floor = hotel.length;
		int room = hotel[0].length;
		System.out.print("\t");
		for (int i=0 ; i<room ; i++)
			System.out.print("Room " + (i+1) + "\t\t");
		System.out.println();
		System.out.print("\t");
		for (int i=0 ; i<room ; i++)
			System.out.print("-------\t\t");
		System.out.println();
		for (int i=0 ; i<floor ; i++){
			System.out.print("Floor" + i + ": ");
			for (int j=0 ; j<room ; j++) {
				if (hotel[i][j] == null)
					System.out.print("[" + i + "-" + (j+1) + "]\t\t");
				else
					System.out.print(hotel[i][j] + "\t\t");
			}
			System.out.println();
		}
	}
}
