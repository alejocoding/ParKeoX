package com.ParkeoX.ParkeoX.exceptions;

public class NotFoundException extends RuntimeException
{

    public NotFoundException(String mensaje)
    {
        super(mensaje);
    }
}
