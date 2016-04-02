package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Student;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.io.IOException;

public class StudentController extends Controller {

    public Result addStudent() {
        try {
            JsonNode values = request().body().asJson();
            Student student = new ObjectMapper().readValue(values.toString(), Student.class);
            student.save();
            return Results.ok();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return Results.badRequest();
    }
}
