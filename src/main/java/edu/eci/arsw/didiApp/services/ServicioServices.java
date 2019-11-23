package edu.eci.arsw.didiApp.services;

import java.util.List;

import edu.eci.arsw.didiApp.model.Coordinate;
import edu.eci.arsw.didiApp.model.Servicio;

public interface ServicioServices {

    List<Servicio> getAllServices(Coordinate coordinate);

    void saveService(Servicio service);

    Servicio getGenerateService(Servicio service);

    Double getPriceDestino(String destino);

}
