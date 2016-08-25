
import javax.json.JsonObject;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 09.08.2016.
 */
public class ConsolApp {

    private String timeOfRequest;
    private String mailFromSend;
    private String mailToSend;
    private String host;
    private String port;
    private String password;
    private String username;
    private String calendarId;
    private String path;
    private JSONWork jsonWork = new JSONWork();
    private Updating updating = new Updating();
    private JsonObject jsonObjectToSettings;

    public void ConsolApp() throws IOException {
        System.out.println("ConsolApp for working whith KatSend, Hello :) ");
        System.out.println("----  help  ---- for list of commands,\n----  quit  ---- to quit.");
        inputPreWrite();
    }

    private void inputPreWrite() {
        try (Scanner in = new Scanner(System.in)) {
            do {
                System.out.print("KatSend> ");
            } while (isCommand(in.nextLine(), in));
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
        } finally {
            System.out.println("End of KatSend");
        }
    }

    private boolean isCommand(String input, Scanner in) throws IOException, MessagingException, InterruptedException {
        String[] command = input.split(" ");
        System.out.print("KatSend> ");
        switch (command[0]) {
            case "help":
                helper();
                return true;
            case "settings":
                jsonObjectToSettings = null;
                System.out.println("\n----  help  ---- for list of commands,\n----  quit  ---- to quit.");
                do {
                    System.out.print("KatSend> ");
                } while (isCommandForSettings(in.nextLine(), in));
                return true;
            case "start":
                updating.updating(jsonWork);  //it's working
                return true;
            case "mkFile":
                jsonWork.resetSettings();
                jsonWork.jsonWriteEvent("new text","new text","new text","new text");
                return true;
            case "quit":
            case "exit":
                return false;
            default:
                System.out.println("Unknown command, try again ;)");
                return true;
        }
    }

    private boolean isCommandForSettings(String input, Scanner in) throws IOException {
        Settings settings = new Settings();
        String[] command = input.split(" ");
        System.out.print("KatSend> ");
        switch (command[0]) {

            case "help":
                helpForSettings();
                return true;
            case "chHost":
                host = settings.host(in);
                if (!host.equals("")) {
                    jsonObjectToSettings = jsonWork.toChHost(host);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chTime":
                timeOfRequest = settings.time(in);
                if (!timeOfRequest.equals("")) {
                    jsonObjectToSettings = jsonWork.toChTime(timeOfRequest);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chMailTo":
                mailToSend = settings.mailToSend(in);
                if (!mailToSend.equals("")) {
                    jsonObjectToSettings = jsonWork.toChMailTo(mailToSend);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chMailFrom":
                mailFromSend = settings.mailFromSend(in);
                if (!mailFromSend.equals("")) {
                    jsonObjectToSettings = jsonWork.toChMailFrom(mailFromSend);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chPassword":
                password = settings.e_MailPassword(in);
                if (!password.equals("")) {
                    jsonObjectToSettings = jsonWork.toChPassword(password);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chUsername":
                username = settings.e_MailUsername(in);
                if (!username.equals("")) {
                    jsonObjectToSettings = jsonWork.toChUsername(username);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "extend":
                helpExtend();
                return true;
            case "chPort":
                port = settings.port(in);
                if (!port.equals("")) {
                    jsonObjectToSettings = jsonWork.toChPort(port);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chCalenderID":
                calendarId = settings.calendarID(in);
                if (!calendarId.equals("")) {
                    jsonObjectToSettings = jsonWork.toChClendarID(calendarId);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chPath":
                path = settings.path(in);
                if (!path.equals("")) {
                    jsonObjectToSettings = jsonWork.toChClendarID(path);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
            case "reset":
                jsonWork.resetSettings();
                return true;
            case "exit":
                return false;
            default:
                System.out.println("Unknown command, try again ;)");
                return true;
        }
    }

    private void helper() {
        System.out.println("\n----  start  ----  for STARTING the program KatSender");
        System.out.println("----  settings  ----  for chang settings");
        System.out.println("----  mkFile  ----  build File for settings.IT'S IMPORTANT DO IN THE FIRST TIME OF THE WORK");
        System.out.println("----  quit  ----  or  ----  exit  ----  for ENDING the program KatSender");
    }

    private void helpForSettings() {
        System.out.println("\n----  chTime  ----  for chang TIME of updating");
        System.out.println("----  chCalenderID  ----  for chang CALENDERID of updating");
        System.out.println("----  chMailTo  ----  for chang address of Mail ON which will sending Mails");
        System.out.println("----  extend  ----  for view other  settings");
        System.out.println("----  exit  ----  for EXIT from the settings");
    }

    private void helpExtend() {
        System.out.println("\n----  chMailFrom  ----  for chang address of Mail FROM which will sending Mails");
        System.out.println("----  chPassword  ----  for chang PASSWORD of E-Mail from witch sending mails");
        System.out.println("----  chUsername  ----  for chang USERNAME of E-Mail from witch sending mails");
        System.out.println("----  chHost  ----  for chang HOST of E-Mail connection");
        System.out.println("----  chPort  ----  for chang PORT of E-Mail connection");
        System.out.println("----  chPath  ----  for chang PORT of E-Mail connection");
        System.out.println("----  reset  ----  for RESET Settings");
    }

}
