package it.lucadario.serviceapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lucadario on 16/01/18.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class HttpUnauthorizedException extends RuntimeException {
}
