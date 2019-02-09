import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader
{
    //Будем выводить цветной текст в консоль
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException
    {
        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.91:443", 638162, "29b943a4106e48a5f69e8803089fdae9");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println( ANSI_RED + "Please, type phone number:" + ANSI_RESET);
        //Записываем номер в переменную phoneNumber
        String phoneNumber = reader.readLine().trim();
        phoneNumber = phoneNumber.replaceAll("[-()+]+", "");

        //Высылаем код подтверждения
        bridge.authSendCode(phoneNumber);
        System.out.println(ANSI_RED + "Please, type sms code:" + ANSI_RESET);
        String code = reader.readLine().trim();
        code = code.replaceAll("[-()+]+", "");
        AuthAuthorization authorizatedUser = bridge.authSignIn(code);

        //Выводим данные авторизованного User'a
        User me = authorizatedUser.getUser();
        System.out.println(ANSI_RED + "First name: " + ANSI_RESET + me.getFirstName());
        System.out.println(ANSI_RED + "Last name: " + ANSI_RESET + me.getLastName());
        System.out.println(ANSI_RED + "Phone number: " + ANSI_RESET + me.getPhone());

        //Выводим контакты User'а
        ArrayList<UserContact> contactsList = bridge.contactsGetContacts();
        for (UserContact item : contactsList) {
            System.out.println(item + " - " + item.getPhone());
        }
    }
}