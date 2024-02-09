import java.util.Date;

public class Appointment {
    private int id;
    private Date fechaHora;
    private String motivo;
    private int doctorId;
    private int patientId;

    public Appointment(int id, Date fechaHora, String motivo, int doctorId, int patientId) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

}