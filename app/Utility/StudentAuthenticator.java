package Utility;


import com.avaje.ebean.Ebean;
import models.Session;
import models.Student;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Arrays;

public class StudentAuthenticator extends Security.Authenticator {


    @Override
    public String getUsername(Http.Context ctx) {
        String Route = ctx.request().path();
        if (Arrays.asList(Constants.anonymousRoutes.studentRoutesArray).contains(Route))
            return "";

        String token = getTokenFromHeader(ctx);
        if (token != null) {
            Session user = Ebean.find(Session.class).where().conjunction().eq("session_id", token).findUnique();
            ctx.args.put(Constants.values.sessionKey, user);
            if (user != null) {

                Student student = Ebean.find(Student.class).where().idEq(user.userId).findUnique();

                ctx.args.put(Constants.values.student, student);
                return "";
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return unauthorized(Json.toJson(ResponseManager.unauthorize()));
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("sessionId");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}
