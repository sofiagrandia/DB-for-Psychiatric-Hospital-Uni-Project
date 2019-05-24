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

import Project.Contract;
import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import psychiatrichospital.db.DBManager;
import psychiatrichospital.db.JPAManager;

public class UtilitiesUI {

	public void menuPpal() {
		System.out.println("Choose the entity you wish to manage:");
		System.out.println("1)Contract");
		System.out.println("2)Doctor");
		System.out.println("3)Nurse");
		System.out.println("4)Patient");
		System.out.println("5)Room");
		System.out.println("6)Treatement");
		System.out.println("7)Exit");

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

	// CONTRACT
	public void updateContract(DBManager db, BufferedReader reader, DateTimeFormatter formatter) throws NumberFormatException, IOException, SQLException {
		List<Contract> lisCon = new ArrayList<Contract>();
		lisCon = db.selectContract();
		for (int i = 0; i < lisCon.size(); i++) {
			System.out.println(lisCon.get(i));
		}
		System.out.println("Choose a contract, type its Id: ");
		int idCon = Integer.parseInt(reader.readLine());
		Contract contract = db.getContractbyId(idCon);
		System.out.print("Amount: " + contract.getMoney());
		System.out.println("Introduce the new amount");
		String newAmount = (reader.readLine());
		if (!newAmount.equals("")) {
			float newA=Float.parseFloat(reader.readLine());
			contract.setMoney(newA);
		}
		System.out.print("Holidays: " + contract.getHolidays());
		System.out.println("Introduce the new holidays");
		String newHolidays = (reader.readLine());
		if (!newHolidays.equals("")) {
			int newH=Integer.parseInt(reader.readLine());
			contract.setHolidays(newH);
		}
		System.out.print("End date: " + contract.getDob());
		System.out.println("Introduce the new end date");
		String newDate = (reader.readLine());
		if (!newDate.equals("")) {
			LocalDate newDateContract = LocalDate.parse(newDate, formatter);
			Date newDD = Date.valueOf(newDateContract);
			contract.setDob(newDD);
		}
		db.updateContract(contract);
		System.out.println("Update finished.");
		
	}
	
	public Contract insertContractSimple(DBManager db, JPAManager jpa,BufferedReader reader, DateTimeFormatter formatter) throws NumberFormatException, IOException {
		System.out.println("Please, input the contract info:");
		System.out.print("Amount: ");
		Float amount = Float.parseFloat(reader.readLine());
		System.out.print("Contract valid until (yyyy-MM-dd): ");
		String date = reader.readLine();
		LocalDate dDate = LocalDate.parse(date, formatter);
		Date d = Date.valueOf(dDate);
		System.out.print("Holidays: ");
		Integer holidays = Integer.parseInt(reader.readLine());
		
		Contract contract = new Contract(amount, holidays, d);
		return contract;
	}
	
	public Contract assignContractToNurse(JPAManager jpa, BufferedReader reader, DBManager db, Nurse nurse)
			throws NumberFormatException, IOException, SQLException {
		System.out.println(db.selectContract());
		System.out.println("Select  id");
		int chosenId = Integer.parseInt(reader.readLine());
		Contract c = (Contract) db.getContractId(chosenId);
		return c;
	}
	
	
	public void selectContract(DBManager db,  BufferedReader reader) throws NumberFormatException, IOException, SQLException {
		List<Contract> lista = new ArrayList<Contract>();
		lista = db.selectContract();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert Contract id you wish to see");
		int idChosen = Integer.parseInt(reader.readLine());
		Contract c = db.getContractbyId(idChosen);
		System.out.println(c);
	}

	// DOCTOR
	public Doctor insertDoctorSimple(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws IOException {
		System.out.println("Please, input the doctor info:");
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
		Doctor doctor = new Doctor(name, gender, d, hours);
		return doctor;
	}

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

	public void assignDoctorToPatient(JPAManager jpa, BufferedReader reader, DBManager db, Patient patient)
			throws NumberFormatException, IOException {
		System.out.println(db.selectDoctor());
		do {
			System.out.println("Select  id");
			String s=reader.readLine();
			if(s.equals("")) {
				break;
			}else {
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipPD(chosenId, patient.getId());}
		} while (reader.readLine() != "");

	}
	// NURSE

	public Nurse insertNurseSimple(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
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
		return nurse;
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
		String leido;
		System.out.println("Do you want to introduce a patient? (yes / no )");
		leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your patient created already?(yes/no)");
			while (true) {
				String respuesta = reader.readLine();
				if (respuesta.equalsIgnoreCase("yes")) {
					assignPatientToNurse(jpa, reader, db, nurse);
					break;
				}
				if (respuesta.equalsIgnoreCase("no")) {
					Patient p = insertPatientSimple(db, reader, jpa, formatter);
					db.createRelationshipNP(nurse.getId(), p.getId());
					break;
				} else
					System.out.println("Oh no! You didn´t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn´t have a nurse");
		}

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
		System.out.println(db.selectNurse());

		do {
			System.out.println("Select  id");
			String s=reader.readLine();
			if(s.equals("")) {
				break;
			}else {
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipNP(chosenId, patient.getId());}
		} while (reader.readLine() != "");

	}

	// ROOM

	public Room insertRoomSimple(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException {
		System.out.println("Please, input the room info:");
		System.out.print("FLoor: ");
		int floor = Integer.parseInt(reader.readLine());
		Room room = new Room(floor);
		return room;
	}
	// PATIENT

	public Patient insertPatientSimple(DBManager db, BufferedReader reader, JPAManager jpa, DateTimeFormatter formatter)
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
		return patient;
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
		System.out.println("Patient created correctly");
		jpa.insertPatient(patient);
		String leido;
		System.out.println("Do you want to introduce a nurse? (yes / no )");
		leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your nurse created already?(yes/no)");
			while (true) {
				String respuesta = reader.readLine();
				if (respuesta.equalsIgnoreCase("yes")) {
					System.out.println(db.selectNurse());
					assignNurseToPatient(jpa, reader, db, patient);
					break;
				}
				if (respuesta.equalsIgnoreCase("no")) {
					Nurse n = insertNurseSimple(db, jpa, reader, formatter);
					db.createRelationshipNP(n.getId(), patient.getId());
					break;
				} else
					System.out.println("Oh no! You didn´t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn´t have a nurse");
		}

		System.out.println("Do you want to introduce a doctor? (yes / no )");
		leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your doctor created already?(yes/no)");
			while (true) {
				String respuesta = reader.readLine();
				if (respuesta.equalsIgnoreCase("yes")) {
					System.out.println(db.selectDoctor());
					assignDoctorToPatient(jpa, reader, db, patient);
					break;
				}
				if (respuesta.equalsIgnoreCase("no")) {
					Doctor d = insertDoctorSimple(db, jpa, reader, formatter);
					db.createRelationshipPD(d.getId(), patient.getId());
					break;
				} else
					System.out.println("Oh no! You didn´t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn´t have a doctor");
		}

		System.out.println("Do you want to introduce a treatment? (yes / no )");
		leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your treatment created already?(yes/no)");
			while (true) {
				String respuesta = reader.readLine();
				if (respuesta.equalsIgnoreCase("yes")) {
					System.out.println(db.selectTreatment());
					assignTreatmentToPatient(jpa, reader, db, patient);
					break;
				}
				if (respuesta.equalsIgnoreCase("no")) {
					Treatment t = insertTreatmentSimple(db, jpa, reader, formatter);
					db.createRelationshipPT(t.getId(), patient.getId());
					break;
				} else
					System.out.println("Oh no! You didn´t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn´t have a nurse");
		}

		System.out.println("Do you want to introduce a room?");
		leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your room created already? (yes/no)");
			while (true) {
				String answer = reader.readLine();
				if (answer.equalsIgnoreCase("yes")) {
					System.out.println(jpa.selectRoom());
					int read = Integer.parseInt(reader.readLine());
					Room r = jpa.selectRoomById(read);
					jpa.assignPatientRoom(patient, r);
					break;
				}
				if (answer.equalsIgnoreCase("no")) {
					Room r = insertRoomSimple(db, jpa, reader, formatter);
					jpa.assignPatientRoom(patient, r);
					break;
				} else
					System.out.println("Oh no! You didn't choose a valid option! :( Try again ");

			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn't have a room.");

		}
		jpa.insertPatient(patient);
		System.out.println("Patient created correctly");

	}

	public void selectPatientMenu(DBManager db, BufferedReader reader, JPAManager jpa)
			throws NumberFormatException, IOException {
		List<Patient> listaP = new ArrayList<Patient>();
		
		listaP = jpa.selectPatient();
		if(!listaP.isEmpty()) {
		for (int i = 0; i < listaP.size(); i++) {
			System.out.println(listaP.get(i));
		}
		
		System.out.println("Choose the id of the patient you wish to see.");
		int patient_id = Integer.parseInt(reader.readLine());
		Patient p = jpa.selectPatientByid(patient_id);
		System.out.println(p);

	}else System.out.println("No patients");
		}

	public void updatePatientMenu(DBManager db, BufferedReader reader, JPAManager jpa, DateTimeFormatter formatter)
			throws NumberFormatException, IOException {
		List<Patient> listPat = new ArrayList<Patient>();
		listPat = jpa.selectPatient();
		for (int i = 0; i < listPat.size(); i++) {
			System.out.println(listPat.get(i));
		}
		System.out.println("Choose a patient, type its Id: ");
		int idPat = Integer.parseInt(reader.readLine());
		Patient pat = jpa.selectPatientByid(idPat);
		System.out.print("Name: " + pat.getName());
		System.out.println("Type new name:");
		String newNamePat = reader.readLine();
		if (!newNamePat.equals("")) {
			pat.setName(newNamePat);
		}
		System.out.print("Gender: " + pat.getGender());
		System.out.println("Type new gender:");
		String newGenderPat = reader.readLine();
		if (!newGenderPat.equals("")) {
			pat.setGender(newGenderPat);
		}
		System.out.print("Dob: " + pat.getDob());
		System.out.println("Type new dob:");
		String newDobPat = reader.readLine();
		if (!newDobPat.equals("")) {
			LocalDate newDobDatePat = LocalDate.parse(newDobPat, formatter);
			Date newDPat = Date.valueOf(newDobDatePat);
			pat.setDob(newDPat);
		}
		
		if(pat.getRoom_id()==-1) {
			System.out.println("No room");
			System.out.println("Create a new room in main menu.");
		}else {
		System.out.print("Room id: " + pat.getRoom_id());
		System.out.println("Type new room:");
		
		System.out.println(jpa.selectRoom());
		String RoomidPat = (reader.readLine());
		if (!RoomidPat.equals("")) {
			int newRoomidPat = Integer.parseInt(reader.readLine());
			Room newRoom = jpa.selectRoomById(newRoomidPat);
			jpa.assignPatientRoom(pat, newRoom);
		}}
		
		if(pat.getDoctorsId()==null) {
			System.out.println("No doctor.");
			System.out.println("To update create a new doctor in main menu.");
		}else {
	
		System.out.print("Doctors id: " + pat.getDoctorsId());
		System.out.println("New doctor");
		
		assignDoctorToPatient(jpa, reader, db, pat);}
		
		if(pat.getNursesId()==null) {
			System.out.println("No nurse.");
		}
			
		System.out.print("Nurses id: " + pat.getNursesId());
		System.out.println("New nurse:");
		assignNurseToPatient(jpa, reader, db, pat);
		if(pat.getTreatmentsId()==null) {
			System.out.println("No doctor.");
		}
		System.out.print("Treatments id: " + pat.getTreatmentsId());
		System.out.println("New treatment:");
		assignTreatmentToPatient(jpa, reader, db, pat);
		
		db.updatePatient(pat);
		System.out.println("Update finished.");

	}

	public void deletePatientMenu(JPAManager jpa, BufferedReader reader) throws NumberFormatException, IOException, SQLException {
		List<Patient> lista = new ArrayList<Patient>();
		lista = jpa.selectPatient();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert patient id you wish to remove");
		int idChosen = Integer.parseInt(reader.readLine());
		jpa.deletePatient(idChosen);
		System.out.println("Patient deleted");
		
	}

	public void assignPatientToNurse(JPAManager jpa, BufferedReader reader, DBManager db, Nurse nurse)
			throws NumberFormatException, IOException {
		System.out.println(jpa.selectPatient());

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipNP(nurse.getId(), chosenId);
		} while (reader.readLine() != "");

	}

	// TREATMENT

	public Treatment insertTreatmentSimple(DBManager db, JPAManager jpa, BufferedReader reader,
			DateTimeFormatter formatter) throws IOException {
		System.out.println("Please, input the treatment info:");
		System.out.print("Type: ");
		String type = reader.readLine();
		System.out.print("Number: ");
		Integer number = Integer.parseInt(reader.readLine());
		Treatment t = new Treatment(type, number);
		return t;
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

	public void assignTreatmentToPatient(JPAManager jpa, BufferedReader reader, DBManager db, Patient patient)
			throws NumberFormatException, IOException {
		System.out.println(db.selectTreatment());

		do {
			System.out.println("Select  id");
			String s=reader.readLine();
			if(s.equals("")) {
				break;
			}else {
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipPT(chosenId, patient.getId());}
		} while (reader.readLine() != "");

	}
}
