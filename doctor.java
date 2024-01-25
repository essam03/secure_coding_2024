package final_sc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Doctor {
	private static final Object[] String = null;
	private static String username;
	private static String password;
	static String dbusername;


	public void login() {
		System.out.println("Welcome back, professional doctor!");
		int maxAttempts = 3;

		for (int i = 0; i < maxAttempts; i++) {
			setCredentials(); // Get username and password from user input
			MyLogger.writeToLog("logging in attempt and the user is:"+username);
			
			if (authenticate()) {
				System.out.println("Login successful! Let's continue.");
				MyLogger.writeToLog("logging success"+"the name of the user is: "+username);
				performActions();
				return;
			} else {
				int attemptsLeft = maxAttempts - i - 1;
				System.out.println("Login failed. " + attemptsLeft + " attempts left.");
			}
		}

		System.out.println("Login failed. Exiting the program.");
		System.exit(0);
	}

	public void setCredentials() {
		try {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username:");
		this.username = scanner.next();
		System.out.println("Please enter your password:");
		this.password = scanner.next();
		}catch(InputMismatchException e) {
            MyLogger.writeToLog("Exception:: ",e);
			System.out.println("there's an error please try again");
		}
	}

	private static boolean authenticate() {
		BufferedReader br = null;
		String[] parts;
		String dbpassword;

		try {
			br = new BufferedReader(new FileReader("Doctorslog.txt"));
			String line;
			while ((line = br.readLine()) != null) {

				parts = line.split(",");
				dbusername = parts[0].trim();
				dbpassword = parts[1].trim();
				if (username.equals(dbusername) && hash.GetHash(password).equals(dbpassword)) {
					System.out.println("Login successful! Hi hard worker, let's start working.");
					return true;
				}
			}
			System.out.println("Error occurred. The username or password is wrong. Please try again.");
			return false;
		} catch (IOException e){
            MyLogger.writeToLog("Exception:: ",e);
			System.out.println("there's an error occured  " + e.getMessage());
			return false;
		}catch(ArrayIndexOutOfBoundsException e){ 
			System.out.println("there's an error occured");
			return false;
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
		            MyLogger.writeToLog("Exception:: ",e);
					System.out.println("Error while closing BufferedReader: " + e.getMessage());
				}
			}
		}
	}

	private void performActions() {
		boolean loop = true;

		while (loop) {
			System.out.println(
					"Choose one of the following:\n1. View my information\n2. Enter patient's medical information\n3. exit");
			Scanner scanner = new Scanner(System.in);
			int actionChoice = scanner.nextInt();

			switch (actionChoice) {
			case 1:
				displayDoctorInfo();
				break;
			case 2:
				enterPatientMedicalInfo();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	private void displayDoctorInfo() {
		String[] parts2;
		String docuser;
		String password;
		String doctor_name;
		String doctor_age;
		String doctor_gendar;
		String phone;
			System.out.println("please write your name please");
			Scanner name = new Scanner(System.in);
			String inputname = name.next();
			try {

				BufferedReader br = new BufferedReader(new FileReader("Doctors.txt"));
				String line;
				while ((line = br.readLine()) != null) {
					parts2 = line.split(",");
					doctor_name = parts2[0].trim();
					doctor_age = parts2[1].trim();
					doctor_gendar = parts2[2].trim();
					phone = parts2[3].trim();

					if (inputname.equals(doctor_name)) {
						System.out.println("Doctor Info:");
						System.out.println("Name: " + doctor_name);
						System.out.println("Doctor age: " + doctor_age);
						System.out.println("Doctor gendar: " + doctor_gendar);
						System.out.println("Phone: " + phone);
						MyLogger.writeToLog("display the Doctor's crediantials done by: "+username);

						break;

					}
				}

			} catch (IOException e) {
	            MyLogger.writeToLog("Exception:: ",e);
				System.out.println("error with reading the data");
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("an error occured");
			}
		}

	private void enterPatientMedicalInfo() {
		System.out.println("Please enter the patient's name,medical situation, and medical treatment in order:");
		Scanner scanner = new Scanner(System.in);
		String patientName = readStringWithMaxLength(scanner, 50);
		String medicalSituation =  readStringWithMaxLength(scanner, 100);
		String treatment = readStringWithMaxLength(scanner, 200);

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("medical info.txt", true));
			try {
				bw.write(patientName + ",");
				bw.write(medicalSituation + ",");
				bw.write(treatment + ",\n");
				MyLogger.writeToLog("a medical patinet's info record add by: "+username);

			} finally {
				bw.close();
			}
		} catch (IOException e) {
            MyLogger.writeToLog("Exception:: ",e);
			e.printStackTrace();
		}
	}
	  private static String readStringWithMaxLength(Scanner scanner, int maxLength) {
	        String input;
	        do {
	            System.out.print("Enter text (up to " + maxLength + " characters): ");
	            input = scanner.nextLine().trim();
	            if (input.length() > maxLength) {
	                System.out.println("Input exceeds maximum length. Please try again.");
	            }
	        } while (input.length() > maxLength);

	        return input;
	        
	    }
	  
	  
		// ****** getter and sitter  ******//

	  
	  
	  public static String getUsername() {
			return username;
		}

		public static void setUsername(String username) {
			Doctor.username = username;
		}

		public static String getPassword() {
			return password;
		}

		public static void setPassword(String password) {
			Doctor.password = password;
		}

		public static String getDbusername() {
			return dbusername;
		}

		public static void setDbusername(String dbusername) {
			Doctor.dbusername = dbusername;
		}
		
	  
	  
	  
	  
	  
	  
	// ******  testing    ******//
		
		
		 public void setInputForTesting(String username, String password) {
		        setUsername(username);
		        setPassword(password);
		    }

		
}
