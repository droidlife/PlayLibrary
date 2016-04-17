package controllers;


import Utility.Hash;
import Utility.ResponseManager;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.BookIssued;
import models.Books;
import models.Session;
import models.Student;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//@Security.Authenticated(StudentAuthenticator.class)
public class StudentController extends ResponseManager {

    public Result regitserStudent() {
        try {
            JsonNode values = request().body().asJson();
            Student student = new ObjectMapper().readValue(values.toString(), Student.class);
            Student checkStudentRollNumber = Ebean.find(Student.class).where().eq("student_rollnumber", student.studentRollnumber)
                    .findUnique();

            if (checkStudentRollNumber != null) {

                return Results.ok(resultBuilder(false, "Sorry the Student RollNumber Already Exists"));

            }

            Student checkStudentEmail = Ebean.find(Student.class).where().eq("student_email", student.studentEmail)
                    .findUnique();

            if (checkStudentEmail != null) {

                return Results.ok(resultBuilder(false, "Sorry the Email Already Exists"));

            }

            student.setPassword(Hash.generateHash(student.password));
            student.save();
            return Results.ok(resultBuilder(true, student));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Results.badRequest(Json.toJson(serverError()));
    }


    public Result getSuggestionBooks(String key) {

        try {

            List<Books> books = Ebean.find(Books.class).where().ilike("name", key + "%").findList();
            if (books.size() == 0) {
                return Results.ok(resultBuilder(false, books));

            }
            return Results.ok(resultBuilder(true, books));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.ok(Json.toJson(serverError()));
    }

    public Result studentDetails() {

        HashMap<String,Object> hashMap = new HashMap<>();

        try {
            String token = request().getHeader("sessionId");
            if (token.length() == 0) {
                return Results.unauthorized(resultBuilder(false, "Please provide the token"));
            }

            Session user = Ebean.find(Session.class).where().conjunction().eq("session_id", token).findUnique();

            if (user == null) {
                return Results.unauthorized(resultBuilder(false, "Please login to continue"));
            }

            Student student = Ebean.find(Student.class).where().idEq(user.userId).findUnique();

            if (student != null) {

                List<BookIssued> details = Ebean.find(BookIssued.class).fetch("books").where().eq("student_id", student.id).
                        findList();


                hashMap.put("status",true);
                hashMap.put("books",details);

                return Results.ok(Ebean.json().toJson(hashMap));
            } else {
                return Results.unauthorized(resultBuilder(false, "Provided Credentials are wrong." + "\n"
                        + "Please Login again to continue"));
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return Results.badRequest(Json.toJson(serverError()));
    }
}
