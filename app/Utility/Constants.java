package Utility;

public class Constants {


    public interface adminkeys {
        String adminInitials = "admin@";
    }

    public interface anonymousRoutes {

        String[] studentRoutesArray = {"/api/student/login", "/api/student/register"};
        String[] booksRoutesArray = {"/api/books/list"};
    }

    public interface values {

        String student = "student";
        String sessionKey = "session";
    }
}
