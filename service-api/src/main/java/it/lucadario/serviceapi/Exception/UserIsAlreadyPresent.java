package it.lucadario.serviceapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lucadario on 16/01/18.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserIsAlreadyPresent extends RuntimeException {
}
