package Utility;


import com.avaje.ebean.Ebean;
import models.Session;

import java.math.BigInteger;
import java.security.SecureRandom;

public class LoginUtil {

    public static String generateSessionId() {

        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }


    public static String createSession(int userId) {

        Session session = new Session();

        session.sessionId = generateSessionId();
        session.isActive = true;
        session.userId = userId;

        Ebean.save(session);

        return session.sessionId;
    }

    public static boolean clearSession(String sessionId) {
        Session session = Ebean.find(Session.class).where().conjunction().eq("session_id", sessionId)
                .eq("is_active", true).findUnique();

        if (session != null) {

            session.setActive(false);
            session.update();
            return true;
        } else
            return false;
    }


    public static boolean isValidStudentUser(String password,String mainPassword) {

        System.out.println(ValidateLogin.validate(password, mainPassword));
        if (ValidateLogin.validate(password, mainPassword))
            return true;
        else
            return false;
    }


}
