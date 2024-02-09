import com.opencsv.CSVWriter;
import java.util.InputMismatchException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.text.ParseException;

public class DoctorManager {
    private int nextDoctorId = 1;
    private int nextAppointmentId;
    private int nextPatientId=0;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public DoctorManager() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }


    private void saveDoctorsToCSV(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario
        }
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            int lastDoctorId = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                try {
                    int doctorId = Integer.parseInt(nextLine[0]);

                    if (doctorId > lastDoctorId) {
                        lastDoctorId = doctorId;
                    }
                } catch (NumberFormatException ignored) {
                    // Ignorar líneas que no contienen un ID numérico
                }
            }

            nextDoctorId = lastDoctorId + 1; // Establecer el próximo ID disponible

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true))) {
                if (new File(fileName).length() == 0) {
                    String[] header = {"ID", "Nombre Completo", "Especialidad"};
                    csvWriter.writeNext(header);
                }

                for (Doctor doctor : doctors) {
                    if (doctor.getId() == 0) {
                        doctor.setId(nextDoctorId++);
                    }

                    String[] data = {String.valueOf(nextDoctorId), doctor.getNombreCompleto(), doctor.getEspecialidad()};
                    csvWriter.writeNext(data);
                }

                System.out.println("Doctores guardados en el archivo CSV.");
                doctors = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void savePatientsToCSV(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            int lastPatientId = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                try {
                    int patientId = Integer.parseInt(nextLine[0]);

                    if (patientId > lastPatientId) {
                        lastPatientId = patientId;
                    }
                } catch (NumberFormatException ignored) {
                }
            }

            nextPatientId = lastPatientId + 1;

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true))) {
                if (new File(fileName).length() == 0) {
                    String[] header = {"ID", "Nombre Completo"};
                    csvWriter.writeNext(header);
                }

                for (Patient patient : patients) {
                    if (patient.getId() == 0) {
                        patient.setId(nextPatientId++);
                    }

                    String[] data = {String.valueOf(nextPatientId), patient.getNombreCompleto()};
                    csvWriter.writeNext(data);
                }

                System.out.println("Pacientes guardados en el archivo CSV.");
                patients = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void saveAppointmentsToCSV(String fileName) {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            int lastAppointmentId = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                try {
                    int appointmentId = Integer.parseInt(nextLine[0]);

                    if (appointmentId > lastAppointmentId) {
                        lastAppointmentId = appointmentId;
                    }
                } catch (NumberFormatException ignored) {
                }
            }

            nextAppointmentId = lastAppointmentId + 1;

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true))) {
                if (new File(fileName).length() == 0) {
                    String[] header = {"ID", "Fecha y Hora", "Motivo", "ID Doctor", "ID Paciente"};
                    csvWriter.writeNext(header);
                }

                for (Appointment appointment : appointments) {
                    if (appointment.getId() == 0) {
                        appointment.setId(nextAppointmentId++);
                    }

                    String[] data = {
                            String.valueOf(nextAppointmentId),
                            String.valueOf(appointment.getFechaHora()),
                            appointment.getMotivo(),
                            String.valueOf(appointment.getDoctorId()),
                            String.valueOf(appointment.getPatientId())
                    };
                    csvWriter.writeNext(data);
                }

                System.out.println("Citas guardadas en el archivo CSV.");
                appointments = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void createDoctor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del doctor: ");
        String doctorName = scanner.nextLine();

        System.out.print("Ingrese la especialidad del doctor: ");
        String doctorSpecialty = scanner.nextLine();

        Doctor newDoctor = new Doctor(doctors.size() + 1, doctorName, doctorSpecialty);
        addDoctor(newDoctor);
        saveDoctorsToCSV("doctores.csv");
    }
    private void createPatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del paciente: ");
        String patientName = scanner.nextLine();

        Patient newPatient = new Patient(patients.size() + 1, patientName);
        addPatient(newPatient);

        // Guardar la información en un archivo CSV
        savePatientsToCSV("pacientes.csv");
    }
    private void createAppointment() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del doctor: ");
        int doctorId = scanner.nextInt();
        Doctor doctor = findDoctorById(doctorId);

        System.out.print("Ingrese el ID del paciente: ");
        int patientId = scanner.nextInt();
        Patient patient = findPatientById(patientId);

        System.out.print("Ingrese la fecha y hora de la cita (formato: yyyy-MM-dd HH:mm:ss): ");
        scanner.nextLine();
        String dateString = scanner.nextLine();
        Date appointmentDate = parseDate(dateString);

        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        Appointment newAppointment = new Appointment(appointments.size() + 1,  appointmentDate, motivo, doctorId, patientId);
        appointments.add(newAppointment);

        saveAppointmentsToCSV("citas.csv");
    }

    private Doctor findDoctorById(int doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                return doctor;
            }
        }
        return null;
    }

    private Patient findPatientById(int patientId) {
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                return patient;
            }
        }
        return null;
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Formato de fecha y hora no válido. Utilice el formato yyyy-MM-dd HH:mm:ss");
            return null;
        }
    }
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menú Principal");
            System.out.println("1. Crear Doctor");
            System.out.println("2. Crear Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("0. Salir");
            System.out.print("Ingrese su elección: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createDoctor();
                        break;
                    case 2:
                        createPatient();
                        break;
                    case 3:
                        createAppointment();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número entero válido.");
                scanner.nextLine();
                choice = -1;
            }

        } while (choice != 0);
    }

    public static void main(String[] args) {
        DoctorManager doctorManager = new DoctorManager();

        doctorManager.mainMenu();
    }
}