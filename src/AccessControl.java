import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccessControl {
    private Map<String, String> administrators;

    public AccessControl() {
        administrators = new HashMap<>();
    }

    public void addAdministrator(String username, String password) {
        administrators.put(username, password);
    }

    public boolean authenticate(String username, String password) {
        return administrators.containsKey(username) && administrators.get(username).equals(password);
    }


    public static void main(String[] args) {
        AccessControl accessControl = new AccessControl();

        accessControl.addAdministrator("admin", "1234");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario:");
        String username = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        if (accessControl.authenticate(username, password)) {
            System.out.println("Acceso concedido. Bienvenido, " + username + "!");

        } else {
            System.out.println("Acceso denegado. Usuario o contraseña incorrectos.");
        }
    }
}
