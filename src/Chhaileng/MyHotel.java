package Chhaileng;
import java.util.Scanner;

public class MyHotel {
	static Scanner s = new Scanner(System.in);
	static int menu() {
		System.out.println("--------------Welcome To Hotel Management System--------------");
		System.out.println("1. Input number of Room and Floor");
		System.out.println("2. Show Menu");
		System.out.println("3. Exit\n");
		String m = "";
		while (!MyValidator.numFormat(m)) {
			System.out.print("Choose Option(1-3): ");
			m = s.nextLine();
			if(!MyValidator.numFormat(m)){
				System.out.println("\nWarning: Please Enter number 1 to 3!\n");
			}
		}
		return Integer.valueOf(m);
	}
	static int sub_menu() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("1.Check In\t2.Check Out\t3.Display\t4.Exit\n");
		String m = "";
		while(!MyValidator.numFormat(m)) {
			System.out.print("Choose Option(1-4):");
			m = s.nextLine();
			if(!MyValidator.numFormat(m)){
				System.out.println("\nWarning: Please Enter number 1 to 4!\n");
			}
		}
		return Integer.valueOf(m);
	}
	MyHotel() {
		Room room = null;
		int F=0,R=0,f,r;
		String tmp = "";
		
		int m_menu,s_menu;
		do{
			m_menu = menu();
			switch(m_menu) {
			case 1:
				if (room == null) {
					do {
						tmp = "";
						while (!MyValidator.numFormat(tmp)) {
							System.out.print("Enter Amount of Floor: ");
							tmp = s.nextLine();
							if (!MyValidator.numFormat(tmp))
								System.out.println("\nWarning: Enter number (minimum: 1)\n");
						}
						F = Integer.valueOf(tmp);
						if (F<=0)
							System.out.println("\nWarning: Enter number (minimum: 1)\n");
					} while (F<=0);
					
					do {
						tmp = "";
						while (!MyValidator.numFormat(tmp)) {
							System.out.print("Enter Amount of Room for Each Floor: ");
							tmp = s.nextLine();
							if (!MyValidator.numFormat(tmp))
								System.out.println("\nWarning: Enter number (minimum: 1)\n");
						}
						R = Integer.valueOf(tmp);
						if (R<=0)
							System.out.println("\nWarning: Enter number (minimum: 1)\n");
					} while (R<=0);
					
					room = new Room(F,R);
					System.out.println("Floors and Rooms have been set up!");
					System.out.println("You have " + F + " floors and " + (R*F) + " rooms...\n");
				}
				else {
					System.out.println("Floor and Room are already set up!");
					System.out.println("You have " + F + " floors and " + (R*F) + " rooms...\n");
				}
				break;
			case 2:
				if (room != null) {
					do {
						s_menu = sub_menu();
						switch(s_menu) {
							case 1:
								if (!MyValidator.allBusy(room.getAllRooms())) {
									String ask = "";
									do {
										do {
											do {
												tmp = "";
												while (!MyValidator.numFormat(tmp)){
													System.out.print("CheckIN-Enter Floor (0-"+(F-1)+"):");
													tmp = s.nextLine();
													if (!MyValidator.numFormat(tmp))
														System.out.println("\nWarning: Please input number!\n");
												}
												f = Integer.valueOf(tmp);
												if (f<0 || f>F-1)
													System.out.println("\nWarning: Enter number (0-" + (F-1) +")\n");
											} while (f<0 || f>F-1);
											if (MyValidator.floorFull(room.getAllRooms(), f)){
												System.out.println("\nWarning: This floor is full!\n");
											}
										} while (MyValidator.floorFull(room.getAllRooms(), f));
										
										do {
											tmp = "";
											while (!MyValidator.numFormat(tmp)){
												System.out.print("CheckIN-Enter Room (1-"+R+"):");
												tmp = s.nextLine();
												if (!MyValidator.numFormat(tmp))
													System.out.println("\nWarning: Please input number!\n");
											}
											r = Integer.valueOf(tmp);
											if (r<0 || r>R)
												System.out.println("\nWarning: Enter number (1-" + R +")\n");
										} while (r<=0 || r>R);
										
										if (room.getGName(f, r)==null) {
											room.CheckIn(f, r); // input name from method
											System.out.println("\nThis room has been checked-in successfully!\n");
											break;
										}
										else {
											System.out.println("\nWarning: This room is already checked-in!\n");
											do {
												System.out.print("Do you want to check in again? (y/n): ");
												ask = s.nextLine();	
											} while (!ask.equals("y") && !ask.equals("n"));
											if (ask.equals("n")) {
												System.out.println();
												break;
											}
										}
									} while (true);
								}
								else {
									System.out.println("\nAll rooms are full!!!\n");									
								}
								break;
							case 2:
								if (!MyValidator.allFree(room.getAllRooms())) {
									String ask = "";
									do {
										do {
											tmp = "";
											while (!MyValidator.numFormat(tmp)){
												System.out.print("CheckOUT-Enter Floor (0-"+(F-1)+"):");
												tmp = s.nextLine();
												if (!MyValidator.numFormat(tmp))
													System.out.println("\nWarning: Please input number!\n");
											}
											f = Integer.valueOf(tmp);
											if (f<0 || f>F-1)
												System.out.println("\nWarning: Enter number (0-" + (F-1) +")\n");
										} while (f<0 || f>F-1);
										
										do {
											tmp = "";
											while (!MyValidator.numFormat(tmp)){
												System.out.print("CheckOUT-Enter Room (1-"+R+"):");
												tmp = s.nextLine();
												if (!MyValidator.numFormat(tmp))
													System.out.println("\nWarning: Please input number!\n");
											}
											r = Integer.valueOf(tmp);
											if (r<0 || r>R)
												System.out.println("\nWarning: Enter number (1-" + R +")\n");
										} while (r<=0 || r>R);
										
										if (room.getGName(f, r) != null) {
											System.out.println("Guest name: " + room.getGName(f, r));
											int confirm = 2;
											do {
												tmp = "";
												while (!MyValidator.numFormat(tmp)){
													System.out.print("Press 1 to confirm or 0 to cancel: ");
													tmp = s.nextLine();
													if (!MyValidator.numFormat(tmp))
														System.out.println("\nWarning: Enter 1 or 0\n");
												}
												confirm = Integer.valueOf(tmp);
												if (confirm!=1 && confirm!=0)
													System.out.println("\nWarning: Enter 1 or 0\n");
											} while (confirm!=1 && confirm!=0);
											if (confirm ==1) {
												room.CheckOut(f, r);
												System.out.println("\nThis room has been checked-out successfully!\n");
												break;
											}
											else {
												System.out.println("\nCancelled\n");
												break;
											}
										}
										else {
											System.out.println("\nThis room is not checked-in!\n");
											do {
												System.out.print("Do you want to check out again? (y/n): ");
												ask = s.nextLine();	
											} while (!ask.equals("y") && !ask.equals("n"));
											if (ask.equals("n")) {
												System.out.println();
												break;
											}
										}
									}while(true);
								}
								else {
									System.out.println("\nAll rooms are not yet checked-in!!\n");
								}
								break;
							case 3:
								System.out.println("Display All Rooms Detail\n");
								room.Display();
								break;
							case 4: 
								break;
							default:
								System.out.println("\nWarning: Please Enter number 1 to 3!\n");
						}
					} while (s_menu != 4);
				}
				else 
					System.out.println("Floor and Room are not set up!");
				break;
			case 3:
				System.out.println("Bye bro");
				break;
			default:
				System.out.println("\nWarning: Please Enter number 1 to 3!\n");
			}
		} while (m_menu != 3);
	}

}
