package com.redbee.io.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by martin on 21/10/16.
 */
@ControllerAdvice
public class ControllerAdviceGlobal {

    @ExceptionHandler(value = EntityNotFoundException.class )
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="No se ha econtrado lo que esta Buscando,Tomese una birra")
    public void notFound(){
    }

    @ExceptionHandler(value = EntityCantBeSave.class )
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason ="No se puede Guardar , te falta algo...,fijate")
    public void cantBeSave(){
    }

    @ExceptionHandler(value = EntityCantBeUpdate.class )
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason ="No se puede Actualizar")
    public void cantBeUpdate(){
    }

    @ExceptionHandler(value = EntityCantBeDelete.class )
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason ="No se puede Borrar!!!")
    public void cantBeDelete(){
    }
}
