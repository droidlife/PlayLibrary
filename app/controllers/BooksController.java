package controllers;

import Utility.AdminAuthenticator;
import Utility.ResponseManager;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Books;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import java.io.IOException;
import java.util.List;

@Security.Authenticated(AdminAuthenticator.class)
public class BooksController extends ResponseManager{

    public Result addBook() {
        JsonNode values = request().body().asJson();
        try {
            Books books = new ObjectMapper().readValue(values.toString(), Books.class);
            books.save();
            return Results.ok(resultBuilder(true,books));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Results.badRequest(Json.toJson(serverError()));
    }


    public Result getBooks() {

        List<Books> books = Ebean.find(Books.class).findList();

        return Results.ok(Ebean.json().toJson(books));
    }
}
