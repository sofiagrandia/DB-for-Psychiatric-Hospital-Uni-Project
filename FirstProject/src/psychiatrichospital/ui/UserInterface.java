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

import javax.xml.bind.JAXBException;

import Project.Patient;
import Project.Room;
import Project.Treatment;
import psychiatrichospital.db.*;

public class UserInterface {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// It is going to have a main
	public static void main(String[] args) throws ClassNotFoundException, JAXBException, SQLException {

		DBManager db = new DBManager();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		JPAManager jpa = new JPAManager();
		UtilitiesUI uui = new UtilitiesUI();

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
					uui.menuContract();
					int optionContract = Integer.parseInt(reader.readLine());
					switch (optionContract) {
					case 1:
					case 2:
					case 3:
					case 4:
					}

					break;
				case 2:
					uui.menuDoctor();
					int optionDoctor = Integer.parseInt(reader.readLine());
					switch (optionDoctor) {
					case 1:
						uui.selectDoctorMenu(db, reader);
						break;
					case 2:
						uui.insertDoctorMenu(db, jpa, reader, formatter);
						break;
					case 3:
						uui.deleteDoctorMenu(db, reader);
						break;
					case 4:
						uui.updateDoctorMenu(db, reader, formatter);
						break;
					}

					break;

				case 3:
					uui.menuNurse();
					int optionNurse = Integer.parseInt(reader.readLine());
					switch (optionNurse) {
					case 1:
						uui.selectNurseMenu(db, reader);
						break;
					case 2:
						uui.insertNurseMenu(db, jpa, reader, formatter);
						break;
					case 3:
						uui.deleteNurseMenu(db, reader);
						break;
					case 4:
						uui.updateNurseMenu(db, reader, formatter);
						break;
					}

					break;

				case 4:
					uui.menuPatient();
					int optionPatient = Integer.parseInt(reader.readLine());
					switch (optionPatient) {
					case 1: uui.selectPatientMenu(db,reader,jpa);break;
					case 2: uui.insertPatientMenu(db,reader,jpa,formatter);break;
					case 3: uui.updatePatientMenu(db, reader, jpa, formatter);break;
					case 4:
					}

					break;

				case 5:
					uui.menuRoom();
					int optionRoom = Integer.parseInt(reader.readLine());
					switch (optionRoom) {
					case 1:
					case 2:
					case 3:
					case 4:
					}

					break;

				case 6:
					uui.menuTreatment();
					int optionTreatment = Integer.parseInt(reader.readLine());
					switch (optionTreatment) {
					case 1:
						//uui.selectTreatmentMenu(db, reader);
						break;
					case 2:
						uui.insertTreatmentMenu(db, jpa, reader, formatter);
						break;
					case 3:
						//uui.deleteTreatmentMenu(db, reader);
						break;
					case 4:
						//uui.updateTreatmentMenu(db, reader, formatter);
						break;
					case 5:
						uui.marshall(db, reader);
					}

					break;
					
					
				
				case 7: System.exit(0);
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
