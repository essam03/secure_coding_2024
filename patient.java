package final_sc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Patient {
	private String username;
	private String password;

	public void login() {
		System.out.println("Welcome, dear patient! We hope you get well soon.");
		Scanner scanner = new Scanner(System.in);
		int maxAttempts = 3;

		for (int i = 0; i < maxAttempts; i++) {
			setCredentials();
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
			System.out.println(" there's an error please try again");
		}
	}

	private boolean authenticate() {
		BufferedReader br1 = null;
		String[] parts;
		String dbusername;
		String dbpassword;

		try {
			br1 = new BufferedReader(new FileReader("Patientslogs.txt"));
			String line;
			while ((line = br1.readLine()) != null) {

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
		} catch (IOException e) {
            MyLogger.writeToLog("Exception:: ",e);
			System.out.println("there's an error occured  " + e.getMessage());
			return false;
		} catch(ArrayIndexOutOfBoundsException e){ 
			System.out.println("there's an error occured");
			return false;
		}finally {
			if (br1 != null) {
				try {
					br1.close();
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
			System.out.println("Choose one of the following:\n1. View your info\n2. View medical records\n3.exit");
			Scanner scanner = new Scanner(System.in);
			int actionChoice = scanner.nextInt();

			switch (actionChoice) {
			case 1:
				displayPatientInfo();
				break;
			case 2:
				displayMedicalRecords();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	private void displayPatientInfo() {
		String[] parts2;
		String patuser;
		String patient_name;
		String password;
		String patient_age;
		String patient_gendar;
		String phone;
		try {
				System.out.println("please write your name");
				Scanner name = new Scanner(System.in);
				String inputname = name.next();

				BufferedReader br = new BufferedReader(new FileReader("Patients.txt"));
				String line;
				while ((line = br.readLine()) != null) {
					parts2 = line.split(",");
					patient_name = parts2[0].trim();
					patient_age = parts2[1].trim();
					patient_gendar = parts2[2].trim();
					phone = parts2[3].trim();

					if (inputname.equals(patient_name)) {
						System.out.println("ptient Info:");
						System.out.println("Name: " + patient_name);
						System.out.println("patient age: " + patient_age);
						System.out.println("patient gendar: " + patient_gendar);
						System.out.println("Phone: " + phone);
						MyLogger.writeToLog("display the patient's crediantials done by: "+username);

					}
				}

			} catch (IOException e) {
	            MyLogger.writeToLog("Exception:: ",e);
				System.out.println("error with reading the data");
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("an error occured");
			}

		}

	private void displayMedicalRecords() {
		String[] parts2;
		String patient_name;
		String medicl_sutiation;
		String medical_treatment;
		System.out.println("please enter your name");
		Scanner patname=new Scanner(System.in);
		String inputname=patname.next();
		try {
			BufferedReader br = new BufferedReader(new FileReader("medical info.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				parts2 = line.split(",");
				patient_name = parts2[0].trim();
				medicl_sutiation = parts2[1].trim();
				medical_treatment = parts2[2].trim();

				if (inputname.equals(patient_name)) {
					System.out.println("medical record Info:");
					System.out.println("Name: " + patient_name);
					System.out.println("medicl_sutiation: " + medicl_sutiation);
					System.out.println("medical_treatment: " + medical_treatment);
					MyLogger.writeToLog("the medical record is shown to: "+username);


				}
				else
					System.out.println("there's no medical info");
				break;
			}

		} catch (IOException e) {
            MyLogger.writeToLog("Exception:: ",e);
			System.out.println("error with reading the data");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("an error occured");
		}
	}

}