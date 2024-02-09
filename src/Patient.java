class Patient {
    private int id;
    private String nombreCompleto;

    public Patient(int id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setId(int id) {
        this.id = id;
    }
}