package final_sc;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

class RecordKeeper {
	private static String username;
	private String password;

	public RecordKeeper() {
		// Constructor to initialize the RecordKeeper object
	}

	public void login() {
		System.out.println("Welcome back hardworking record keeper.");
		int maxAttempts = 3;

		for (int i = 0; i < maxAttempts; i++) {
			setCredentials();
			MyLogger.writeToLog("logging in attempt");
			if (authenticate()) {
				System.out.println("Login successful! Let's continue.");
				MyLogger.writeToLog("logging in attempt and it's successfully done by: " + username);
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

	private void setCredentials() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter your username:");
			this.username = scanner.next();
			System.out.println("Please enter your password:");
			this.password = scanner.next();
		} catch (InputMismatchException e) {
			MyLogger.writeToLog("Exception:: ", e);
			System.out.println("there's error exceted so please try again");
		}

	}

	public boolean authenticate() {

		BufferedReader br = null;
		String[] parts;
		String dbusername = "";
		String dbpassword = "";

		try {
			br = new BufferedReader(new FileReader("recored keeperlogs.txt"));
			String line;
			String hashedInputPassword = hash.GetHash(password);

			while ((line = br.readLine()) != null) {
				parts = line.split(",");
				dbusername = parts[0].trim();
				dbpassword = parts[1].trim();

				if (username.equals(dbusername) && hashedInputPassword.equals(dbpassword)) {
					System.out.println("Login successful! Hi hard worker, let's start working.");
					return true;
				}
			}
			System.out.println("Error occurred. The username or password is wrong. Please try again.");
			return false;
		} catch (IOException e) {
			MyLogger.writeToLog("Exception:: ", e);

			System.out.println("there's an error occurred  " + e.getMessage());
			return false;
		} catch(ArrayIndexOutOfBoundsException e){ 
			System.out.println("there's an error occured");
			return false;
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					MyLogger.writeToLog("Exception:: ", e);
					System.out.println("Error while closing BufferedReader: " + e.getMessage());
				}
			}
		}

	}

	private void performActions() {
		boolean loop = true;

		while (loop) {
			try {
				System.out.println("Choose one of the following:\n1. Register doctors\n2. Register patients\n3.exit");
				Scanner scanner = new Scanner(System.in);
				int actionChoice = scanner.nextInt();

				switch (actionChoice) {
				case 1:
					registerDoctor();
					break;
				case 2:
					registerPatient();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice.");
				}
			} catch (InputMismatchException e) {
				MyLogger.writeToLog("Exception:: ", e);
				System.out.println("there's error please try again");
			}
		}
	}

	public static void registerDoctor() {
		String docuser;
		String password;
		int doctor_age;
		char doctor_gendar;
		String phone;
		String doctor_name;

		System.out.println("Please enter the Doctor's user and Password");
		System.out.println("be awre of these policies for the password");
		System.out.println("Password must be at least 8 characters long.");
		System.out.println("Password must contain both uppercase and lowercase letters.");
		System.out.println("Password must contain at least one digit.");
		System.out.println("Password must contain at least one special character.");

		Scanner scanner = new Scanner(System.in);
		docuser = scanner.next();
		password = scanner.next();
		System.out.println(password.length());
		if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")
				|| !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()-_+=].*")) {
			System.out.println("Please follow all password policies.");
		}

		else {
			System.out.println("plese enter name ");
			doctor_name = scanner.next();
			System.out.println("plese enter the age as number");
			doctor_age = scanner.nextInt();
			System.out.println("plese enter the gender as letter and Uppercase");
			doctor_gendar = scanner.next().charAt(0);
			if (doctor_gendar == 'F' || doctor_gendar == 'M') {
			System.out.println("plese enter the phone number but be careful it's 10 digits");
			phone = scanner.next();
			if (phone.length() != 10 && doctor_age % 1 != 0 && Character.SIZE / 8 != 1) {
				System.out.println("please follw the role");
			}

			BufferedWriter bw = null;
			BufferedWriter bw1 = null;

			try {
				bw = new BufferedWriter(new FileWriter("Doctors.txt", true));
				bw1 = new BufferedWriter(new FileWriter("Doctorslog.txt", true));
				try {
					bw1.write("\n" + docuser + ",");
					bw1.write(hash.GetHash(password) + ",");
					bw.write(doctor_name + ",");
					bw.write(doctor_age + ",");
					bw.write(doctor_gendar + ",");
					bw.write(phone + "\n");
					MyLogger myLogger = new MyLogger();
					myLogger.writeToLog("a new docotor added and done by" + username);

				} finally {
					bw.close();
					bw1.close();
				}
			} catch (IOException e) {
				MyLogger.writeToLog("Exception:: ", e);
				e.printStackTrace();
			}
		}
		}
	}

	private void registerPatient() {
		String patuser;
		String patient_name;
		String password;
		int patient_age;
		char patient_gendar;
		String phone;

		System.out.println("Please enter the Patient's user and Password");
		System.out.println("be awre of these policies for the password");
		System.out.println("Password must be at least 8 characters long.");
		System.out.println("Password must contain both uppercase and lowercase letters.");
		System.out.println("Password must contain at least one digit.");
		System.out.println("Password must contain at least one special character.");
		Scanner scanner = new Scanner(System.in);
		patuser = scanner.next();
		password = scanner.next();
		if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")
				|| !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()-_+=].*")) {
			System.out.println("Please follow all password policies.");
		}

		else {
			System.out.println("enter the name");
			patient_name = scanner.next();
			System.out.println("plese enter age as number");
			patient_age = scanner.nextInt();
			System.out.println("plese enter gendar as letter");
			patient_gendar = scanner.next().charAt(0);
			if (patient_gendar == 'F' || patient_gendar == 'M') {
			System.out.println("plese enter phone number but be careful it's 10 digits ");
			phone = scanner.next();
			if (phone.length() != 10 && patient_age % 1 != 0 && Character.SIZE / 8 != 1) {
				System.out.println("please follw the role");
			} else {

				BufferedWriter bw = null;
				BufferedWriter bw1 = null;

				try {
					bw = new BufferedWriter(new FileWriter("Patients.txt", true));
					bw1 = new BufferedWriter(new FileWriter("Patientslogs.txt", true));

					try {
						bw1.write("\n" + patuser + ",");
						bw1.write(hash.GetHash(password));
						bw.write(patient_name + ",");
						bw.write(patient_age + ",");
						bw.write(patient_gendar + ",");
						bw.write(phone + "\n");
						MyLogger.writeToLog("new parient added by: " + username);

					} finally {
						bw.close();
						bw1.close();
					}
				} catch (IOException e) {
					MyLogger.writeToLog("Exception:: ", e);
					e.printStackTrace();
				}
			}
		}
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	// ******  testing    ******//
	
	
	 public void setInputForTesting(String testUsername, String testPassword) {
	        setUsername(testUsername);
	        setPassword(testPassword);
	    }
	
	
	
	
	
	
}
