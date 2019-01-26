import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthCheckedPhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.50:443", 638162, "29b943a4106e48a5f69e8803089fdae9");

        System.out.println("Please, type phone number:");
        AuthCheckedPhone checkedPhone = bridge.authCheckPhone(reader.readLine().trim());
        System.out.println(checkedPhone.isRegistered());
    }
}
