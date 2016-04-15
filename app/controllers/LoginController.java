package controllers;


import Utility.LoginUtil;
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

import java.util.HashMap;

@Security.Authenticated(StudentAuthenticator.class)
public class LoginController extends ResponseManager {

    public Result logout() {
        try {

            String sessionId = request().getHeader("sessionId") ;

            if (LoginUtil.clearSession(sessionId)) {
                return Results.ok(resultBuilder(true,"Successfully Logout"));
            } else {
                return Results.ok(resultBuilder(true,"User Already Logged out"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            return Results.badRequest(Json.toJson(serverError()));
        }

    }

    public Result studentLogin() {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();

            JsonNode values = request().body().asJson();
            Student student = new ObjectMapper().readValue(values.toString(), Student.class);
            Student checkStudent = Ebean.find(Student.class).where().eq("student_email",student.studentEmail)
                    .findUnique();

            if(checkStudent == null) {
                return Results.ok(resultBuilder(false,"Student Doesn't Exists"));
            }

            if (LoginUtil.isValidStudentUser(student.password,checkStudent.password)) {
                hashMap.put("result",true);
                hashMap.put("sessionId", LoginUtil.createSession(checkStudent.id));
                hashMap.put("user",student);
                return Results.ok(Ebean.json().toJson(hashMap));
            } else {
                return Results.unauthorized(resultBuilder(false,"Password Doesn't Match"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Results.badRequest(Json.toJson(serverError()));
        }

    }



}
