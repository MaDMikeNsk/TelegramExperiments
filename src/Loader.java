import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader
{
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.91:443", 638162, "29b943a4106e48a5f69e8803089fdae9");
//
//        System.out.println("Please, type phone number:");
//        AuthCheckedPhone checkedPhone = bridge.authCheckPhone(reader.readLine().trim());
//        System.out.println(checkedPhone.isRegistered());
//    }

    public static void main(String[] args) throws IOException
    {
        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.91:443", 638162, "29b943a4106e48a5f69e8803089fdae9");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please, type phone number:");
        String phoneNumber = reader.readLine().trim();            //Записываем номер в переменную phoneNumber

        AuthSentCode checkedPhone = bridge.authSendCode(phoneNumber); //Высылаем код подтверждения
        String phoneCodeHash = checkedPhone.getPhoneCodeHash();  //Возвращаем phone code hash

        System.out.println("Please, type sms code:");
        String smsCode = reader.readLine().trim();
        AuthAuthorization authorizatedUser = bridge.authSignIn(smsCode);

        User me = authorizatedUser.getUser();                    //Выводим данные авторизованного юзера
        System.out.println("First name: " + me.getFirstName());
        System.out.println("Last name: " + me.getLastName());
        System.out.println("Phone nubmer: " + me.getPhone());
    }
}
