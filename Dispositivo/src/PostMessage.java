import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PostMessage extends Thread{
    private static String generateConsumption(){
        Random random = new Random();
        int numero = random.nextInt(101);
        return Integer.toString(numero);
    }

    private static String getDate(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateF = dateFormat.format(date);
        return dateF;
    }

    private static void postMessage(String consumotion, String idMeter) throws IOException {
        String message = idMeter + " " +consumotion+ " " +getDate();
        int porta = 9800;
        DatagramSocket clientSocket = new DatagramSocket();
        byte[] sendData = new byte[200];
        sendData = message.getBytes();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
        clientSocket.send(sendPacket);
        clientSocket.close();
    }

    @Override
    public void run() {
        try {
            while(!Main.idMeter.equals("none")) {
                String consumotion = "";
                if (!Main.pause) {
                    Thread.sleep(Main.interval);
                    consumotion = ((Main.fixed) ? "" + Main.fixedMeter : generateConsumption());
                    postMessage(consumotion, Main.idMeter);
                }else {
                    Thread.sleep(1000);
                    if (Main.setMeter) { // Definir taxa fixa
                        postMessage("" + Main.definedMeter, Main.idMeter);
                        Main.setMeter = false;

                    }
                }

            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


