package psychiatrichospital.ui;

import Project.Nurse;
import Project.Doctor;
import Project.Contract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import psychiatrichospital.db.*;


public class UserInterface  {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// It is going to have a main
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Nurse nurse;
		Doctor doctor;
		Patient patient;
		DBManager db = new DBManager();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		JPAManager jpa=new JPAManager();
		UtilitiesUI uui= new UtilitiesUI();

		// Shows several options to the user and lest her choose one
		// One option includes reading from the keyboard the information of the nurse
		// and creating a nurse object
		// Call the nurse method with the information received
		try {

			int option;
			do {
				uui.menuPpal();
				String stringLeido = reader.readLine();
				option = Integer.parseInt(stringLeido);
				db.connection();
				jpa.connection();
				db.createTables();
				
				switch (option) {
				case 1:
					// create table
					break;

				case 2:
					// Insert nurse
					System.out.println("Please, input the nurse info:");
					System.out.print("Name: ");
					String name = reader.readLine();
					System.out.print("Gender: ");
					String gender = reader.readLine();
					System.out.print("Hours: ");
					stringLeido = reader.readLine();
					int hours = Integer.parseInt(stringLeido);
					System.out.print("Date of Birth (yyyy-MM-dd): ");
					String dob = reader.readLine();
					LocalDate dobDate = LocalDate.parse(dob, formatter);
					Date d = Date.valueOf(dobDate);
					nurse = new Nurse(name, gender, d, hours);
					System.out.println("Nurse created correctly");
					db.insertNurse(nurse);
					break;

				case 3:
					// Select nurse(shows you the nurses)
					List<Nurse> lista = new ArrayList<Nurse>();
					lista = db.selectNurse();
					for (int i = 0; i < lista.size(); i++) {
						System.out.println(lista.get(i));
					}
					break;
				case 4:
					// Update nurse
					List<Nurse> lis = new ArrayList<Nurse>();
					lis = db.selectNurse();
					for (int i = 0; i < lis.size(); i++) {
						System.out.println(lis.get(i));
					}
					System.out.println("Choose a nurse, type its Id: ");
					int id = Integer.parseInt(reader.readLine());
					Nurse n = db.getNurseId(id);
					System.out.print("Name: " + n.getName());
					String newName = reader.readLine();
					if (!newName.equals("")) {
						n.setName(newName);
					}
					System.out.print("Gender: " + n.getGender());
					String newGender = reader.readLine();
					if (!newGender.equals("")) {
						n.setGender(newGender);
					}
					System.out.print("Dob: " + n.getDob());
					String newDob = reader.readLine();
					if (!newDob.equals("")) {
						LocalDate newDobDate = LocalDate.parse(newDob, formatter);
						Date newD = Date.valueOf(newDobDate);
						n.setDob(newD);
					}
					System.out.print("Hours: " + n.getHours());
					String newHours = reader.readLine();
					if (!newHours.equals("")) {
						int newH = Integer.parseInt(newHours);
						n.setHours(newH);
					}
					db.updateNurse(n);
					System.out.println("Update finished.");
					break;
				
				case 5:
					// Insert patient
					System.out.println("Please, input the patient info:");
					System.out.print("Name: ");
					String nameP = reader.readLine();
					System.out.print("Gender: ");
					String genderP = reader.readLine();
					System.out.print("Hours: ");
					stringLeido = reader.readLine();
					int room_idP = Integer.parseInt(stringLeido);
					System.out.print("Date of Birth (yyyy-MM-dd): ");
					String dobP = reader.readLine();
					LocalDate dobDateP = LocalDate.parse(dobP, formatter);
					Date dP = Date.valueOf(dobDateP);
					System.out.print("Photo: ");
					System.out.print("Type the file name as it appears in folder /photos, including extension: ");
					String fileName = reader.readLine();
					File photo = new File("./photos/" + fileName);
					/*InputStream streamBlob = new FileInputStream(photo);
					byte[] bytesBlob = new byte[streamBlob.available()];
					streamBlob.read(bytesBlob);
					streamBlob.close();*/
					patient = new Patient (nameP, genderP, dP, room_idP, bytesBlob);
					System.out.println(jpa.selectRoom());
					int rid=Integer.parseInt(reader.readLine());
					Room r = jpa.selectRoomById(rid);
					System.out.println(db.selectNurse());
					int nid=Integer.parseInt(reader.readLine());
					Nurse nu= db.getNurseId(nid);
					System.out.println(db.selectDoctor());
					int did=Integer.parseInt(reader.readLine());
					Doctor doc= db.getDoctorId(did);
					System.out.println(db.selectTreatment());
					int tid=Integer.parseInt(reader.readLine());
					Treatment t= db.getTreatmentId(tid);
					db.insertPatient(patient,r,nu,doc,t);
					System.out.println("Patient created correctly");
					
					
					break;
				case 6:
					// Select patient(shows you the patients)
					List<Patient> listaP = new ArrayList<Patient>();
					listaP = db.selectPatient();
					for (int i = 0; i < listaP.size(); i++) {
						System.out.println(listaP.get(i));
					}
					break;
				case 7:
					// Update patient
					List<Patient> listPat = new ArrayList<Patient>();
					listPat = db.selectPatient();
					for (int i = 0; i < listPat.size(); i++) {
						System.out.println(listPat.get(i));
					}
					System.out.println("Choose a nurse, type its Id: ");
					int idPat = Integer.parseInt(reader.readLine());
					Patient pat = db.selectPatientByid(idPat);
					System.out.print("Name: " + pat.getName());
					String newNamePat = reader.readLine();
					if (!newNamePat.equals("")) {
						pat.setName(newNamePat);
					}
					System.out.print("Gender: " + pat.getGender());
					String newGenderPat = reader.readLine();
					if (!newGenderPat.equals("")) {
						pat.setGender(newGenderPat);
					}
					System.out.print("Dob: " + pat.getDob());
					String newDobPat = reader.readLine();
					if (!newDobPat.equals("")) {
						LocalDate newDobDatePat = LocalDate.parse(newDobPat, formatter);
						Date newDPat = Date.valueOf(newDobDatePat);
						pat.setDob(newDPat);
					}
					System.out.print("Room id: " + pat.getRoom_id());
					int newRoomidPat = Integer.parseInt(reader.readLine());
					if (newRoomidPat!=0) {
						pat.setRoom_id(newRoomidPat);
					}
					
					db.updatePatient(pat);
					System.out.println("Update finished.");
					break;
				case 8: 
					//insert doctor
					System.out.println("Please, input the doctor info:");
					System.out.print("Name: ");
					String nameDoc = reader.readLine();
					System.out.print("Gender: ");
					String genderDoc = reader.readLine();
					System.out.print("Hours: ");
					stringLeido = reader.readLine();
					int hoursDoc = Integer.parseInt(stringLeido);
					System.out.print("Date of Birth (yyyy-MM-dd): ");
					String dobDoc = reader.readLine();
					LocalDate dobDateDoc = LocalDate.parse(dobDoc, formatter);
					Date dDoc = Date.valueOf(dobDateDoc);
					doctor = new Doctor (nameDoc, genderDoc, dDoc, hoursDoc);
					System.out.println("Doctor created correctly");
					db.insertDoctor(doctor);
					break;
					
				case 9:
						// Select doctor(shows you the doctors)
						List<Doctor> listaDoc = new ArrayList<Doctor>();
						listaDoc = db.selectDoctor();
						for (int i = 0; i < listaDoc.size(); i++) {
							System.out.println(listaDoc.get(i));
						}
						break;
						
				case 10:
						//update doctor
						
						List<Doctor> lisDoc = new ArrayList<Doctor>();
						lisDoc = db.selectDoctor();
						for (int i = 0; i < lisDoc.size(); i++) {
							System.out.println(lisDoc.get(i));
						}
						System.out.println("Choose a doctor, type its Id: ");
						int idDoc = Integer.parseInt(reader.readLine());
						Doctor doctor1 = db.getDoctorId(idDoc);
						System.out.print("Name: " + doctor1.getName());
						String newNameDoc = reader.readLine();
						if (!newNameDoc.equals("")) {
							doctor1.setName(newNameDoc);
						}
						System.out.print("Gender: " + doctor1.getGender());
						String newGenderDoc = reader.readLine();
						if (!newGenderDoc.equals("")) {
							doctor1.setGender(newGenderDoc);
						}
						System.out.print("Dob: " + doctor1.getDob());
						String newDobDoc = reader.readLine();
						if (!newDobDoc.equals("")) {
							LocalDate newDobDateDoc = LocalDate.parse(newDobDoc, formatter);
							Date newDDoc = Date.valueOf(newDobDateDoc);
							doctor1.setDob(newDDoc);
						}
						System.out.print("Hours: " + doctor1.getHours());
						String newHoursDoc = reader.readLine();
						if (!newHoursDoc.equals("")) {
							int newHDoc = Integer.parseInt(newHoursDoc);
							doctor1.setHours(newHDoc);
						}
						db.updateDoctor(doctor1);
						System.out.println("Update finished.");
				}
				

			} while (option != 0);
			// Close database connection!!!!! IMPORTANT
			db.closeConnection();
			jpa.closeConnection();
			System.out.println("Database connection closed.");

		}

		catch (IOException ex) {
			System.out.println("ERROR");
		}

	}
}

