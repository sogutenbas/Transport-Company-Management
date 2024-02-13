// IDandPasswords.java
package authentication;

import java.util.HashMap;

public class IDandPasswords {
    
    private HashMap<String, String> logininfo = new HashMap<String, String>();

    // Private constructor
    private IDandPasswords() {
        logininfo.put("test", "pizza");
        logininfo.put("Test", "Passord");
        logininfo.put("Elias", "Test");
        logininfo.put("Mari-Al", "Test");
        logininfo.put("Elias>Migle", "Test");
        logininfo.put("Student", "Student");
        logininfo.put("student", "student");


    }

    // Static method to get a single instance of the class
    private static IDandPasswords instance;

    public static IDandPasswords getInstance() {
        if (instance == null) {
            instance = new IDandPasswords();
        }
        return instance;
    }

    public HashMap<String, String> getLoginInfo() {
        return logininfo;
    }
}
