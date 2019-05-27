package psychiatrichospital.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Project.Contract;
import Project.CustomErrorHandler;
import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import Project.TreatmentList;
import psychiatrichospital.db.DBManager;
import psychiatrichospital.db.JPAManager;
import psychiatrichospital.db.XMLManager;

public class UtilitiesUI {

	public void menuPpal() {
		System.out.println("Choose the entity you wish to manage:");
		System.out.println("1)Contract");
		System.out.println("2)Doctor");
		System.out.println("3)Nurse");
		System.out.println("4)Patient");
		System.out.println("5)Room");
		System.out.println("6)Treatment");
		System.out.println("7)Exit");

	}

	public void menuContract() {
		System.out.println("----------CONTRACT MENU----------");
		System.out.println("1)Select contract");
		System.out.println("2)Update contract");
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
	}

	public void menuTreatment() {
		System.out.println("----------TREATMENT MENU----------");
		System.out.println("1)Select treatment");
		System.out.println("2)Create treatment");
		System.out.println("3)Delete treatment");
		System.out.println("4)Update treatment");
		System.out.println("5)Marshall treatment");
		System.out.println("6)Unmarshall treatment");
		System.out.println("7)Checker");
		System.out.println("8)HTML");
	}

	// CONTRACT
	public void updateContractMenu(DBManager db, BufferedReader reader, DateTimeFormatter formatter) throws NumberFormatException, IOException, SQLException {
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
		String newAmount = reader.readLine();
		if (!newAmount.equals("")) {
			int newA=Integer.parseInt(newAmount);
			contract.setMoney(newA);
		}
		System.out.print("Holidays: " + contract.getHolidays());
		System.out.println("Introduce the new holidays");
		String newHolidays = (reader.readLine());
		if (!newHolidays.equals("")) {
			int newH=Integer.parseInt(newHolidays);
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
	
	public Contract insertContractSimple(DBManager db, JPAManager jpa,BufferedReader reader, DateTimeFormatter formatter) throws NumberFormatException, IOException, SQLException {
		System.out.println("Please, input the contract info:");
		System.out.print("Amount: ");
		int amount = Integer.parseInt(reader.readLine());
		System.out.print("Contract valid until (yyyy-MM-dd): ");
		String date = reader.readLine();
		LocalDate dDate = LocalDate.parse(date, formatter);
		Date d = Date.valueOf(dDate);
		System.out.print("Holidays: ");
		Integer holidays = Integer.parseInt(reader.readLine());
		
		Contract contract = new Contract(amount, holidays, d);
		db.insertContract(contract);
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		contract.setId(lastId);
		
		return contract;
	}
	

	public void assignContractToNurse(JPAManager jpa, BufferedReader reader, DBManager db, Nurse nurse,Contract contract)
			throws NumberFormatException, IOException, SQLException {
		String sql = "UPDATE nurse SET contract_id=? WHERE id=?";
		PreparedStatement prep = db.getConnection().prepareStatement(sql);
		prep.setInt(1, contract.getId());
		prep.setInt(2, nurse.getId());
		
		prep.executeUpdate();
	
	}
	
	public void assignContractToDoctor(JPAManager jpa, BufferedReader reader, DBManager db, Doctor doctor,Contract contract)
			throws NumberFormatException, IOException, SQLException {
		String sql = "UPDATE doctor SET contract_id=? WHERE id=?";
		PreparedStatement prep = db.getConnection().prepareStatement(sql);
		prep.setInt(1, contract.getId());
		prep.setInt(2, doctor.getId());
		
		prep.executeUpdate();
	
	}
	
	public void selectContractMenu(DBManager db,  BufferedReader reader) throws NumberFormatException, IOException, SQLException {
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
			throws NumberFormatException, IOException, SQLException {
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
		
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		doctor.setId(lastId);
		return doctor;
	}
	

	public void insertDoctorMenu(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {
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
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		doctor.setId(lastId);
		
		
		System.out.println("Do you want to introduce a patient? (yes / no )");
		String leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your patient created already?(yes/no)");
			while (true) {
				String answer = reader.readLine();
				if (answer.equalsIgnoreCase("yes")) {
					System.out.println(jpa.selectPatient());
					assignPatientToDoctor(jpa, reader, db, doctor);
					break;
				}
				if (answer.equalsIgnoreCase("no")) {
					///noooooooooo vaaaaaaaaaaaa
					Patient p = insertPatientSimple(db, reader, jpa, formatter);
					db.createRelationshipPD(doctor.getId(), p.getId());
					break;
				} else
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new doctor doesn�t have a patient");
		}
		/*System.out.println("Introduce a contract");
		Contract c=insertContractSimple(db, jpa, reader, formatter);
		doctor.setContract(c);*/
		Contract contract= insertContractSimple(db,jpa,reader,formatter);
		System.out.println(contract);
		assignContractToDoctor(jpa,reader,db,doctor,contract);
		
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

	public Nurse insertNurseSimple(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
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
		db.insertNurse(nurse);
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		nurse.setId(lastId);
		return nurse;
	}

	public void insertNurseMenu(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws IOException, SQLException {

		System.out.println("Please, input the nurse info:");
		System.out.print("Name: ");
		String nameNu = reader.readLine();
		System.out.print("Gender: ");
		String genderNu = reader.readLine();
		System.out.print("Hours: ");
		int hoursNu = Integer.parseInt(reader.readLine());
		System.out.print("Date of Birth (yyyy-MM-dd): ");
		String dobNu = reader.readLine();
		LocalDate dobDateNu = LocalDate.parse(dobNu, formatter);
		Date dNu = Date.valueOf(dobDateNu);
		Nurse nurse = new Nurse(nameNu, genderNu, dNu, hoursNu);
		
		db.insertNurse(nurse);
		System.out.println(nurse);
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		nurse.setId(lastId);
		System.out.println(lastId);
		
	
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
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");
			}
		


		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new nurse doesn�t have a patient");
		}
		Contract contract= insertContractSimple(db,jpa,reader,formatter);
		System.out.println(contract);
		assignContractToNurse(jpa,reader,db,nurse,contract);
		
		System.out.println("Patient(s) selected correctly");
		System.out.println("Nurse inserted");
		
		
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
	public void selectRoomMenu(JPAManager jpa, BufferedReader reader) throws NumberFormatException, IOException {
	List<Room> listaR = new ArrayList<Room>();
	
	listaR = jpa.selectRoom();
	if(!listaR.isEmpty()) {
	for (int i = 0; i < listaR.size(); i++) {
		System.out.println(listaR.get(i));
	}
	
	System.out.println("Choose the id of the room you wish to see.");
	int room_id = Integer.parseInt(reader.readLine());
	Room r = jpa.selectRoomById(room_id);
	System.out.println(r);

}else System.out.println("No patients");}

	public Room insertRoomSimple(DBManager db, JPAManager jpa, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {
		System.out.println("Please, input the room info:");
		System.out.print("FLoor: ");
		int floor = Integer.parseInt(reader.readLine());
		Room room = new Room(floor);
		jpa.insertRoom(room);
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		room.setId(lastId);
		return room;
	}
	
	public void insertRoomMenu(DBManager db, BufferedReader reader, JPAManager jpa, DateTimeFormatter formatter)
			throws IOException, SQLException {
		System.out.println("Please, input the room info:");
		System.out.print("Floor: ");
		int floor = Integer.parseInt(reader.readLine());
		Room room = new Room(floor);
		String query= "SELECT last_insert_rowid() AS lastId";
		Connection c=db.getConnection();
		PreparedStatement pr= c.prepareStatement(query);
		ResultSet rs =pr.executeQuery();
		Integer lastId =rs.getInt("lastId");
		room.setId(lastId);
		String leido;
		System.out.println("Room created correctly");
		Patient p=null;
		System.out.println("Do you want to introduce a patient?");
		leido = reader.readLine();
		if (leido.equalsIgnoreCase("yes")) {

			System.out.println("Is your patient created already? (yes/no)");
			while (true) {
				String answer = reader.readLine();
				if (answer.equalsIgnoreCase("yes")) {
					System.out.println(jpa.selectPatient());
					int read = Integer.parseInt(reader.readLine());
					p = jpa.selectPatientByid(read);
					jpa.assignPatientRoom(p, room);
					break;
				}
				if (answer.equalsIgnoreCase("no")) {
					p = insertPatientSimple(db,  reader,jpa, formatter);
					
					System.out.println(p);
					System.out.println(room);
					jpa.assignPatientRoom(p, room);
					break;
				} else
					System.out.println("Oh no! You didn't choose a valid option! :( Try again ");

			}
		}
		jpa.insertRoom(room);
		System.out.println("Patient created correctly");

	}
	
	// PATIENT

	public Patient insertPatientSimple(DBManager db, BufferedReader reader, JPAManager jpa, DateTimeFormatter formatter)
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
		jpa.insertPatient(patient);
		return patient;
	}
     
	
	public void updateTreatmentMenu(DBManager db, BufferedReader reader, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {

		List<Treatment> lt = new ArrayList<Treatment>();
		lt = db.selectTreatment();
		for (int i = 0; i < lt.size(); i++) {
			System.out.println(lt.get(i));
		}
		System.out.println("\nChoose a Treatment, type its Id: ");
		int id = Integer.parseInt(reader.readLine());
		Treatment t = db.getTreatmentId(id);
		System.out.print("Type: " + t.getType());
		System.out.println("\nIntroduce the new type");
		String newType = reader.readLine();
		if (!newType.equals("")) {
			t.setType(newType);
		}
		System.out.print("Number: " + t.getNumber());
		System.out.println("\nIntroduce the new number");
	   String newNumber = reader.readLine();
	   
		if (!newNumber.equals("")) {
			 int number=Integer.parseInt(newNumber);
			t.setNumber(number);
		}
		
		db.updateTreatment(t);
		System.out.println("Update finished.");
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
		jpa.insertPatient(patient);
		System.out.println("Patient created correctly");
		
		
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
					System.out.println(n);
					System.out.println(patient);
					db.createRelationshipNP(n.getId(), patient.getId());
					break;
				} else
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn�t have a nurse");
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
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn�t have a doctor");
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
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");
			}
		}
		if (leido.equalsIgnoreCase("no")) {
			System.out.println("Your new patient doesn�t have a nurse");
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
		
		jpa.updatePatient(pat);
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
	
	public void assignPatientToTreatment(JPAManager jpa, BufferedReader reader, DBManager db, Treatment treatment)
			throws NumberFormatException, IOException {
		jpa.selectPatient();

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipPT(treatment.getId(), chosenId);
		} while (reader.readLine() == "");

	}
	
	public void assignDoctorToTreatment(JPAManager jpa, BufferedReader reader, DBManager db, Treatment treatment)
			throws NumberFormatException, IOException {
		jpa.selectDoctor();

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipDT(treatment.getId(), chosenId);
		} while (reader.readLine() == "");

	}
	

	
	
	public void assignPatientToDoctor(JPAManager jpa, BufferedReader reader, DBManager db, Doctor doctor)
			throws NumberFormatException, IOException {
		System.out.println(jpa.selectPatient());

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipPD(doctor.getId(), chosenId);
		} while (reader.readLine() != "");

	}

	// TREATMENT
 
	public Treatment insertTreatmentSimple(DBManager db, JPAManager jpa, BufferedReader reader,
			DateTimeFormatter formatter) throws IOException, SQLException {
		System.out.println("Please, input the treatment info:");
		System.out.print("Type: ");
		String type = reader.readLine();
		System.out.print("Number: ");
		Integer number = Integer.parseInt(reader.readLine());
		Treatment t = new Treatment(type, number);
		db.insertTreatment(t);
		String query = "SELECT last_insert_rowid() AS lastId FROM treatment";
		PreparedStatement ps = db.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Integer lastId = rs.getInt("lastId");
		t.setId(lastId);
		
		return t;
	}
	
	public void selectTreatmentMenu(DBManager db, BufferedReader reader)
			throws NumberFormatException, IOException, SQLException {
		List<Treatment> lista = new ArrayList<Treatment>();
		lista = db.selectTreatment();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert Treatment id you wish to see");
		int idChosen = Integer.parseInt(reader.readLine());
		Treatment t = db.getTreatmentId(idChosen);
		System.out.println(t);
	}
	
	public void deleteTreatmentMenu(DBManager db,JPAManager jpa, BufferedReader reader) throws NumberFormatException, IOException, SQLException {
		List<Treatment> lista = new ArrayList<Treatment>();
		lista = db.selectTreatment();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}

		System.out.println("Insert treatment id you wish to remove");
		int idChosen = Integer.parseInt(reader.readLine());
		db.deleteTreatment(idChosen);
		System.out.println("Treatment deleted");
		
	}


	public void insertTreatmentMenu(DBManager db,  BufferedReader reader,JPAManager jpa, DateTimeFormatter formatter)
			throws NumberFormatException, IOException, SQLException {

		System.out.println("Please, input the treatment info:");
		System.out.print("Name of treatment: ");
		String nameTreatment = reader.readLine();
		System.out.print("Number: ");
		int number = Integer.parseInt(reader.readLine());
		Treatment treatment = new Treatment(nameTreatment, number);
		System.out.println("Treatment created correctly");
		db.insertTreatment(treatment);
		String query = "SELECT last_insert_rowid() AS lastId FROM treatment";
		PreparedStatement ps = db.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Integer lastId = rs.getInt("lastId");
		treatment.setId(lastId);
		
		System.out.println("Do you want to introduce a patient?(yes/no)");
		String leido = reader.readLine();

		//while (true) {
			if (leido.equalsIgnoreCase("yes")) {

				System.out.println("Is your patient created already?(yes/no)");
				while (true) {
					String respuesta = reader.readLine();
					if (respuesta.equalsIgnoreCase("yes")) {
						System.out.println(jpa.selectPatient());
						assignPatientToTreatment(jpa, reader, db, treatment);

						break;

					} if (respuesta.equalsIgnoreCase("no")) {
						Patient p=insertPatientSimple(db, reader, jpa, formatter);
						System.out.println(p);
						System.out.println(treatment);
						db.createRelationshipPT(treatment.getId(), p.getId());
						break;
					}
					else System.out.println("Oh no! You didn't choose a valid option! :( Try again");

				}

			}
			if (leido.equalsIgnoreCase("no")) {
				System.out.println("Your new treatment doesn't have a patient");
				//break;
			}
		//	System.out.println("Oh no! You didn't choose a valid option! :( Try again");
		//}
		
       
		System.out.println("Patient introduced in treatment correctly");
		
		System.out.println("Do you want to introduce a doctor?(yes/no)");
			 leido = reader.readLine();
			if (leido.equalsIgnoreCase("yes")) {

				System.out.println("Is your doctor created already?(yes/no)");
				while (true) {
					String respuesta = reader.readLine();
					if (respuesta.equalsIgnoreCase("yes")) {
						System.out.println(db.selectDoctor());
						assignDoctorToTreatment(jpa, reader, db, treatment);

						break;

					}
					 if (respuesta.equalsIgnoreCase("no")) {
						Doctor d=insertDoctorSimple(db, jpa, reader, formatter);
						System.out.println(d);
						System.out.println(treatment);
						db.createRelationshipDT(treatment.getId(), d.getId());
						
						break;
					}
					System.out.println("Oh no! You didn�t choose a valid option! :( Try again");

				}

			}
			if (leido.equalsIgnoreCase("no")) {
				System.out.println("Your new treatment doesn�t have a doctor");
			}
			//System.out.println("Oh no! You didn't choose a valid option! :( Try again");
		

		

		System.out.println("Doctor introduced in treatment correctly");
		System.out.println("Treatment inserted");
}
	
	
	
	public void marshall(DBManager db, BufferedReader reader) throws IOException, JAXBException, SQLException {
		System.out.println("Introduce the name of the document you want to marshall (always ending with .xml)");
		//BufferedReader consola = new BufferedReader (new InputStreamReader (System.in));
		String leido = reader.readLine();
		List<Treatment> lista = db.selectTreatment();
	//	for (Treatment treatment : lista) {
		//	treatment.setP(db.selectPatientByTreatment(treatment.getId()));
		//}
		TreatmentList treatments= new TreatmentList(lista);
		XMLManager.marshaller(treatments, leido);
		
		
	}
	
	public void unmarshall(DBManager db, BufferedReader reader) throws IOException, JAXBException, SQLException{
		System.out.println("Introduce the name of the document you want to unmarshall ");
		String leido= reader.readLine();
		TreatmentList tl= XMLManager.unmarshallerTreatment(leido);
		for(Treatment t: tl.getListTreatment()) {
			db.insertTreatment(t);
		}
	}


	public void assignTreatmentToPatient(JPAManager jpa, BufferedReader reader, DBManager db, Patient patient)
			throws NumberFormatException, IOException {
		System.out.println(db.selectTreatment());

		do {
			System.out.println("Select  id");
			int chosenId = Integer.parseInt(reader.readLine());
			db.createRelationshipPT(chosenId, patient.getId());
		} while (reader.readLine() != "");

	}
	
	public static void simpleTransform(String sourcePath, String xsltPath, String resultDir) {
		TransformerFactory tf= TransformerFactory.newInstance();
		try {
			Transformer t= tf.newTransformer(new StreamSource(new File(xsltPath)));
			t.transform(new StreamSource(new File(sourcePath)), new StreamResult (new File (resultDir)));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 public static void DTDChecker() {
	        File xmlFile = new File("./xmls/treatment.xml"); 
	        try {
	        	// Create a DocumentBuilderFactory
	            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
	            // Set it up so it validates XML documents
	            dBF.setValidating(true);
	            // Create a DocumentBuilder and an ErrorHandler (to check validity)
	            DocumentBuilder builder = dBF.newDocumentBuilder();
	            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
	            builder.setErrorHandler(customErrorHandler);
	            // Parse the XML file and print out the result
	            Document doc = builder.parse(xmlFile);
	            if (customErrorHandler.isValid()) {
	                System.out.println(xmlFile + " was valid!");
	            }
	        } catch (ParserConfigurationException ex) {
	            System.out.println(xmlFile + " error while parsing!");
	        } catch (SAXException ex) {
	            System.out.println(xmlFile + " was not well-formed!");
	        } catch (IOException ex) {
	            System.out.println(xmlFile + " was not accesible!");
	        }

	    }

}




