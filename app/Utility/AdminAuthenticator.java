package Utility;


import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Arrays;

public class AdminAuthenticator extends Security.Authenticator {


    @Override
    public String getUsername(Http.Context ctx) {

        String Route = ctx.request().path();
        if (Arrays.asList(Constants.anonymousRoutes.booksRoutesArray).contains(Route))
            return "";


        String token = getTokenFromHeader(ctx);
        if (token != null && token.equals("admin")) {

            return "";

        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return unauthorized(Json.toJson(ResponseManager.unauthorize()));
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("token");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}
