package psychiatrichospital.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import psychiatrichospital.db.DBManager;
import psychiatrichospital.db.JPAManager;

public class UtilitiesUI {

	public void menuPpal() {
		System.out.println("Choose entity you wish to manage:");
		System.out.println("1)Contract");
		System.out.println("2)Doctor");
		System.out.println("3)Nurse");
		System.out.println("4)Patient");
		System.out.println("5)Room");
		System.out.println("6)Treatement");

	}

	public void menuContract() {
		System.out.println("----------CONTRACT MENU----------");
		System.out.println("1)Select contract");
		System.out.println("2)Create contract");
		System.out.println("3)Delete contract");
		System.out.println("4)Update contract");
	}

	public void menuDoctor() {
		System.out.println("----------DOCTOR MENU----------");
		System.out.println("1)Select doctor");
		System.out.println("2)Create doctor");
		System.out.println("3)Delete doctor");
		System.out.println("4)Update doctor");
	}

	public void menuNurse() {
		System.out.println("----------NURSE MENU----------");
		System.out.println("1)Select nurse");
		System.out.println("2)Create nurse");
		System.out.println("3)Delete nurse");
		System.out.println("4)Update nurse");
	}

	public void menuPatient() {
		System.out.println("----------PATIENT MENU----------");
		System.out.println("1)Select patient");
		System.out.println("2)Create patient");
		System.out.println("3)Delete patient");
		System.out.println("4)Update patient");
	}

	public void menuRoom() {
		System.out.println("----------ROOM MENU----------");
		System.out.println("1)Select room");
		System.out.println("2)Create room");
		System.out.println("3)Delete room");
		System.out.println("4)Update room");
	}

	public void menuTreatment() {
		System.out.println("----------TREATMENT MENU----------");
		System.out.println("1)Select treatment");
		System.out.println("2)Create treatment");
		System.out.println("3)Delete treatment");
		System.out.println("4)Update treatment");
	}

	// DOCTOR

	public void insertDoctorMenu(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException {
		System.out.println("Please, input the doctor info:");
		System.out.print("Name: ");
		String nameDoc = reader.readLine();
		System.out.print("Gender: ");
		String genderDoc = reader.readLine();
		System.out.print("Hours: ");
		int hoursDoc = Integer.parseInt(reader.readLine());
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dobDoc = reader.readLine();
		LocalDate dobDateDoc = LocalDate.parse(dobDoc, formatter);
		Date dDoc = Date.valueOf(dobDateDoc);
		Doctor doctor = new Doctor(nameDoc, genderDoc, dDoc, hoursDoc);
		db.insertDoctor(doctor);
		jpa.selectPatient();
		do {
			System.out.println("Select patient id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipNP(doctor.getId(), chosenId);
		} while (reader.readLine() == "");

		System.out.println("Patient(s) selected correctly");
		System.out.println("Doctor inserted");

	}

	public void selectDoctorMenu(DBManager db, BufferedReader reader)
			throws NumberFormatException, IOException, SQLException {
		List<Doctor> lista = new ArrayList<Doctor>();
		lista = db.selectDoctor();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert doctor id you wish to see");
		int idChosen = Integer.parseInt(reader.readLine());
		Doctor d = db.getDoctorId(idChosen);
		System.out.println(d);
	}

	public void deleteDoctorMenu(DBManager db, BufferedReader reader)
			throws NumberFormatException, IOException, SQLException {
		List<Doctor> lista = new ArrayList<Doctor>();
		lista = db.selectDoctor();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert doctor id you wish to remove");
		int idChosen = Integer.parseInt(reader.readLine());
		db.deleteDoctor(idChosen);
		System.out.println("Doctor deleted");
	}

	public void updateDoctorMenu(DBManager db, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {

		List<Doctor> lisDoc = new ArrayList<Doctor>();
		lisDoc = db.selectDoctor();
		for (int i = 0; i < lisDoc.size(); i++) {
			System.out.println(lisDoc.get(i));
		}
		System.out.println("Choose a doctor, type its Id: ");
		int idDoc = Integer.parseInt(reader.readLine());
		Doctor doctor1 = db.getDoctorId(idDoc);
		System.out.print("Name: " + doctor1.getName());
		System.out.println("Introduce the new name");
		String newNameDoc = reader.readLine();
		if (!newNameDoc.equals("")) {
			doctor1.setName(newNameDoc);
		}
		System.out.print("Gender: " + doctor1.getGender());
		System.out.println("Introduce the new gender");
		String newGenderDoc = reader.readLine();
		if (!newGenderDoc.equals("")) {
			doctor1.setGender(newGenderDoc);
		}
		System.out.print("Dob: " + doctor1.getDob());
		System.out.println("Introduce the new date of birth");
		String newDobDoc = reader.readLine();
		if (!newDobDoc.equals("")) {
			LocalDate newDobDateDoc = LocalDate.parse(newDobDoc, formatter);
			Date newDDoc = Date.valueOf(newDobDateDoc);
			doctor1.setDob(newDDoc);
		}
		System.out.print("Hours: " + doctor1.getHours());
		System.out.println("Introduce the new hours");
		String newHoursDoc = reader.readLine();
		if (!newHoursDoc.equals("")) {
			int newHDoc = Integer.parseInt(newHoursDoc);
			doctor1.setHours(newHDoc);
		}
		db.updateDoctor(doctor1);
		System.out.println("Update finished.");
	}

	// NURSE

	public void insertNurseSimple(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws IOException {
		System.out.println("Please, input the nurse info:");
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Gender: ");
		String gender = reader.readLine();
		System.out.print("Hours: ");
		int hours = Integer.parseInt(reader.readLine());
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dob = reader.readLine();
		LocalDate dobDate = LocalDate.parse(dob, formatter);
		Date d = Date.valueOf(dobDate);
		Nurse nurse = new Nurse(name, gender, d, hours);

	
	}

	public void insertNurseMenu(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws IOException, SQLException {

		System.out.println("Please, input the nurse info:");
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Gender: ");
		String gender = reader.readLine();
		System.out.print("Hours: ");
		int hours = Integer.parseInt(reader.readLine());
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dob = reader.readLine();
		LocalDate dobDate = LocalDate.parse(dob, formatter);
		Date d = Date.valueOf(dobDate);
		Nurse nurse = new Nurse(name, gender, d, hours);
		System.out.println("Nurse created correctly");
		db.insertNurse(nurse);

		System.out.println("Do you want to introduce a patient? (yes / no )");

			String leido = reader.readLine();
			if (leido.equalsIgnoreCase("yes")) {

				System.out.println("Is your patient created already?(yes/no)");
				while (true) {
					String respuesta = reader.readLine();
					if (respuesta.equalsIgnoreCase("yes")) {
						assignPatientToNurse(jpa, reader, db, nurse);

						break;

					} else if (respuesta.equalsIgnoreCase("no")) {
						insertPatientSimple(db, reader, jpa, formatter);
						break;
					} else
						System.out.println("Oh no! You didn�t choose a valid option! :( Try again");

				}

			}
			if (leido.equalsIgnoreCase("no")) {
				System.out.println("Your new nurse doesn�t have a patient");
				
			}
		

		// System.out.println(db.selectNurse());
		// int nid = Integer.parseInt(reader.readLine());
		// Nurse nu = db.getNurseId(nid);

		// System.out.println(db.selectDoctor());
		// int did = Integer.parseInt(reader.readLine());
		// Doctor doc = db.getDoctorId(did);

		db.insertNurse(nurse);
		System.out.println("Nurse created correctly");

		// System.out.println("Nurse(s) selected correctly");
		System.out.println("Nurse inserted");
	}

	public void selectNurseMenu(DBManager db, BufferedReader reader)
			throws NumberFormatException, IOException, SQLException {
		List<Nurse> lista = new ArrayList<Nurse>();
		lista = db.selectNurse();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert nurse id you wish to see");
		int idChosen = Integer.parseInt(reader.readLine());
		Nurse n = db.getNurseId(idChosen);
		System.out.println(n);

	}

	public void deleteNurseMenu(DBManager db, BufferedReader reader)
			throws NumberFormatException, IOException, SQLException {
		List<Nurse> lista = new ArrayList<Nurse>();
		lista = db.selectNurse();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert nurse id you wish to remove");
		int idChosen = Integer.parseInt(reader.readLine());
		db.deleteNurse(idChosen);
		System.out.println("Nurse deleted");
	}

	public void updateNurseMenu(DBManager db, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {
		List<Nurse> lis = new ArrayList<Nurse>();
		lis = db.selectNurse();
		for (int i = 0; i < lis.size(); i++) {
			System.out.println(lis.get(i));
		}
		System.out.println("Choose a nurse, type its Id: ");
		int id = Integer.parseInt(reader.readLine());
		Nurse n = db.getNurseId(id);
		System.out.print("Name: " + n.getName());
		System.out.println("Introduce the new name");
		String newName = reader.readLine();
		if (!newName.equals("")) {
			n.setName(newName);
		}
		System.out.print("Gender: " + n.getGender());
		System.out.println("Introduce the new gender");
		String newGender = reader.readLine();
		if (!newGender.equals("")) {
			n.setGender(newGender);
		}
		System.out.print("Dob: " + n.getDob());
		System.out.println("Introduce the new date of birth");
		String newDob = reader.readLine();
		if (!newDob.equals("")) {
			LocalDate newDobDate = LocalDate.parse(newDob, formatter);
			Date newD = Date.valueOf(newDobDate);
			n.setDob(newD);
		}
		System.out.print("Hours: " + n.getHours());
		System.out.println("Introduce the new hours");
		String newHours = reader.readLine();
		if (!newHours.equals("")) {
			int newH = Integer.parseInt(newHours);
			n.setHours(newH);
		}
		db.updateNurse(n);
		System.out.println("Update finished.");
	}

	public void assignNurseToPatient(JPAManager jpa, BufferedReader reader, DBManager db, Patient patient)
			throws NumberFormatException, IOException {
		jpa.selectNurse();

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipNP(chosenId, patient.getId());
		} while (reader.readLine() == "");

	}

	// PATIENT
	public void insertPatientSimple(DBManager db, BufferedReader reader, JPAManager jpa, DateTimeFormatter formatter)
			throws IOException {
		System.out.println("Please, input the patient info:");
		System.out.print("Name: ");
		String nameP = reader.readLine();
		System.out.print("Gender: ");
		String genderP = reader.readLine();
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dobP = reader.readLine();
		LocalDate dobDateP = LocalDate.parse(dobP, formatter);
		Date dP = Date.valueOf(dobDateP);
		Patient patient = new Patient(nameP, genderP, dP);
	

	}

	public void insertPatientMenu(DBManager db, BufferedReader reader, JPAManager jpa, DateTimeFormatter formatter)
			throws IOException, SQLException {
		System.out.println("Please, input the patient info:");
		System.out.print("Name: ");
		String nameP = reader.readLine();
		System.out.print("Gender: ");
		String genderP = reader.readLine();
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dobP = reader.readLine();
		LocalDate dobDateP = LocalDate.parse(dobP, formatter);
		Date dP = Date.valueOf(dobDateP);
		Patient patient = new Patient(nameP, genderP, dP);

		System.out.println("Do you want to introduce a nurse? (yes / no )");
	
			String leido = reader.readLine();
			if (leido.equalsIgnoreCase("yes")) {

				System.out.println("Is your nurse created already?(yes/no)");
				while (true) {
					String respuesta = reader.readLine();
					if (respuesta.equalsIgnoreCase("yes")) {
						assignNurseToPatient(jpa, reader, db, patient);

						break;

					}
					if (respuesta.equalsIgnoreCase("no")) {
						insertNurseSimple(db, jpa, reader, formatter);
						break;
					}else
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");

				}

			}
			if (leido.equalsIgnoreCase("no")) {
				System.out.println("Your new patient doesn�t have a nurse");
			}


		

		// System.out.println(db.selectNurse());
		// int nid = Integer.parseInt(reader.readLine());
		// Nurse nu = db.getNurseId(nid);

		// System.out.println(db.selectDoctor());
		// int did = Integer.parseInt(reader.readLine());
		// Doctor doc = db.getDoctorId(did);

		jpa.insertPatient(patient);
		System.out.println("Patient created correctly");

	}

	public void selectPatientMenu(DBManager db) {
		List<Patient> listaP = new ArrayList<Patient>();
		listaP = db.selectPatient();
		for (int i = 0; i < listaP.size(); i++) {
			System.out.println(listaP.get(i));
		}
	}

	public void updatePatientMenu(DBManager db, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException {
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
		if (newRoomidPat != 0) {
			pat.setRoom_id(newRoomidPat);
		}

		db.updatePatient(pat);
		System.out.println("Update finished.");

	}

	public void assignPatientToNurse(JPAManager jpa, BufferedReader reader, DBManager db, Nurse nurse)
			throws NumberFormatException, IOException {
		jpa.selectPatient();

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipNP(nurse.getId(), chosenId);
		} while (reader.readLine() == "");

	}

	public void insertTreatmentMenu(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {

		System.out.println("Please, input the treatment info:");
		System.out.print("Name of treatment: ");
		String nameTreatment = reader.readLine();
		System.out.print("Number: ");
		int number = Integer.parseInt(reader.readLine());

		Treatment treatment = new Treatment(nameTreatment, number);
		db.insertTreatment(treatment);
		System.out.println(jpa.selectPatient());
		do {
			System.out.println("Select patient id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipPT(treatment.getId(), chosenId);
		} while (reader.readLine() == "");

		System.out.println("Patient(s) selected correctly");

		System.out.println(db.selectDoctor());
		System.out.println("Select doctor id");
		int chosenId = Integer.parseInt(reader.readLine());
		String sql = "UPDATE treatment SET id=? ";
		Connection c = db.getConnection();
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, chosenId);

		System.out.println("Doctor selected correctly");
		System.out.println("Treatment inserted");

	}

}
