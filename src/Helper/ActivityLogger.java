package Helper;

import Model.User;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class ActivityLogger {
    /**
     * Logs login activity to a text file.
     * @param userName prints the username provided by the lates login attempt.
     * @param successfulLogin Prints the value of the login
     * @throws IOException
     */
    public static void logActivity(String userName, boolean successfulLogin) throws IOException {
        File file = new File("src/Activity_Log.txt");
       if(file.exists()){
           FileWriter writer = new FileWriter(file, true);
           writer.append( "\n" + java.time.LocalDate.now() +  " | " + java.time.LocalTime.now() + " | User: " + userName + " | ");
           if(successfulLogin){
               writer.append("Login Succesful");
           } else{
               writer. append("Login Failed");
           }
           writer.close();
       }
       else if(file.createNewFile()){
           FileWriter writer = new FileWriter(file, true);
           writer.append(java.time.LocalDate.now() +  " | " + java.time.LocalTime.now() + " | User: " + userName + " | ");
           if(successfulLogin){
               writer.append("Login Succesful");
           } else{
               writer. append("Login Failed");
           }
           writer.close();
        }

    }
}
