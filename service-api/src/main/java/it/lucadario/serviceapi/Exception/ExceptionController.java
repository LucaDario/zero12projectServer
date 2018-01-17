package it.lucadario.serviceapi.Exception;

import it.lucadario.serviceapi.Exception.HttpUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucadario on 16/01/18.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({HttpUnauthorizedException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    Map<String, String> unauthorizedAccess(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();
        exception.put("timestamp",String.valueOf(System.currentTimeMillis()));
        exception.put("status", "401");
        exception.put("error", "Unauthorized");
        exception.put("message", "Bad credentials for chosen user");

        return exception;
    }

    @ExceptionHandler({IdLightNotUnique.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    Map<String, String> idLightNotUnique(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();
        exception.put("timestamp",String.valueOf(System.currentTimeMillis()));
        exception.put("status", "500");
        exception.put("error", "idLight not Unique");
        exception.put("message", "Id Light is not unique");

        return exception;
    }
    @ExceptionHandler({UserIsNotPresent.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    Map<String, String> userIsNotPresent(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();
        exception.put("timestamp",String.valueOf(System.currentTimeMillis()));
        exception.put("status", "500");
        exception.put("error", "user not present");
        exception.put("message", "User is not present");

        return exception;
    }
    @ExceptionHandler({UserIsAlreadyPresent.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    Map<String, String> userAlreadyPresent(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();
        exception.put("timestamp",String.valueOf(System.currentTimeMillis()));
        exception.put("status", "500");
        exception.put("error", "user is already present");
        exception.put("message", "User is already present");

        return exception;
    }

}
