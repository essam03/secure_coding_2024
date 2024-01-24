package final_sc;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {

	public static void main(String[] args) {
		

		System.out.println("HI welcom to HealthClinicPro hope you go throw shiny day "
				+ "please chose number one of the following \n" + "1. recoerd keeper\n" + "2. Doctors\n"
				+ "3. Patients\n"+"4. exit");
		boolean loop = true;

		while (loop) {
		choosingAndClassify();
		}
		}

	public static void choosingAndClassify() {
		try {
		Scanner scan = new Scanner(System.in);
		int choosing = scan.nextInt();
		
		switch (choosing) {
		case 1:
			RecordKeeper recordKeeper = new RecordKeeper();
	        recordKeeper.login();
	        break;

		case 2:
			 Doctor doctor = new Doctor();
		     doctor.login();
			break;

		case 3:
			Patient patient = new Patient();
	        patient.login();
			   
		case 4:
			System.exit(0);
			break;
			
			default:
				System.out.println("Invaild input");
		 }
		
		
		}catch(InputMismatchException e){
            MyLogger.writeToLog("Exception:: ",e);
			System.out.println("please enter a number");
		}
			}
}