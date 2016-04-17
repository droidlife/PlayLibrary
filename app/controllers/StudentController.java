package controllers;


import Utility.Hash;
import Utility.ResponseManager;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Books;
import models.Student;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.io.IOException;
import java.util.List;

//@Security.Authenticated(StudentAuthenticator.class)
public class StudentController extends ResponseManager {

    public Result regitserStudent() {
        try {
            JsonNode values = request().body().asJson();
            Student student = new ObjectMapper().readValue(values.toString(), Student.class);
            Student checkStudentRollNumber = Ebean.find(Student.class).where().eq("student_rollnumber",student.studentRollnumber)
                    .findUnique();

            if(checkStudentRollNumber != null) {

                return Results.ok(resultBuilder(false,"Sorry the Student RollNumber Already Exists"));

            }

            Student checkStudentEmail = Ebean.find(Student.class).where().eq("student_email",student.studentEmail)
                    .findUnique();

            if(checkStudentEmail != null) {

                return Results.ok(resultBuilder(false,"Sorry the Email Already Exists"));

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

        try{

            List<Books> books = Ebean.find(Books.class).where().like("name",key+"%").findList();
            if (books == null) {

                return Results.ok(resultBuilder(false,books));

            }
            return Results.ok(resultBuilder(true,books));
        }catch (Exception e) {
            e.printStackTrace();
        }
            return Results.ok(Json.toJson(serverError()));
    }

}
