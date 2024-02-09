class Doctor {
    private int id;
    private String nombreCompleto;
    private String especialidad;

    public Doctor(int id, String nombreCompleto, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setId(int id) {
        this.id = id;
    }
}