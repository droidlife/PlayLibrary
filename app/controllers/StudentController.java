package controllers;


import Utility.Hash;
import Utility.ResponseManager;
import Utility.StudentAuthenticator;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Student;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import java.io.IOException;

@Security.Authenticated(StudentAuthenticator.class)
public class StudentController extends ResponseManager {

    public Result regitserStudent() {
        try {
            JsonNode values = request().body().asJson();
            Student student = new ObjectMapper().readValue(values.toString(), Student.class);
            Student checkStudentRollNumber = Ebean.find(Student.class).where().eq("student_rollnumber",student.studentRollnumber)
                    .findUnique();

            if(checkStudentRollNumber != null) {

                return Results.ok(resultBuilder(true,"Sorry the Student Already Exists"));

            }

            Student checkStudentEmail = Ebean.find(Student.class).where().eq("student_email",student.studentEmail)
                    .findUnique();

            if(checkStudentEmail != null) {

                return Results.ok(resultBuilder(true,"Sorry the Email Already Exists"));

            }

            student.setPassword(Hash.generateHash(student.password));
            student.save();
            return Results.ok(resultBuilder(true, student));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Results.badRequest(Json.toJson(serverError()));
    }


}
