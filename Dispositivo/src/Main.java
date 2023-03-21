import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String idMeter = "none";
    public static int interval = 3000;
    public static boolean pause = false;
    public static boolean setMeter = false;
    public static boolean fixed = false;
    public static int fixedMeter = 0;
    public static int definedMeter = 0;

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o numero de registro deste medidor");
        idMeter = scanner.nextLine();
        System.out.println("MEDIDOR: " + idMeter + " INICIADO ENVIANDO VALORES ALEATÒRIOS EM "+ interval/1000 +" SEG");
        new PostMessage().start();

        System.out.println(idMeter);
        while (!idMeter.equals("none")){
            String consumotionOption = ((fixed) ? "Enviar consumos aleatórios" : "Definir consumo fixo");
            System.out.println("\n[1]Alterar intervalo das medições\n[2]Pausar medições\n[3]" + consumotionOption);
            int menu = scanner.nextInt();

            if (menu == 1) {
                System.out.println("Novo intervalo (em segundos)");
                int intervalLocal = scanner.nextInt();
                System.out.println("Novo intervalo definido");
                interval = intervalLocal * 1000;

            } else if (menu == 2) {
                pause = true;
                while (pause) {
                    System.out.println("Medições pausadas\n[1]Continuar medições\n[2]Enviar uma medição definida");
                    int option = scanner.nextInt();

                    if (option == 1) {
                        pause = false;
                    }

                    else if (option == 2) {
                        System.out.println("informe o valor a ser enviado");
                        int value = scanner.nextInt();
                        definedMeter = value;
                        setMeter = true;
                    }
                }
            }

            else if (menu == 3) {
                if (fixed) {
                    fixed = false;
                    System.out.println("Medidor configurado para enviar valores aleatórios");
                } else {
                    System.out.println("informe o valor fixo que será enviado");
                    int value = scanner.nextInt();
                    fixedMeter = value;
                    fixed = true;
                }
            }
        }
    }
}
