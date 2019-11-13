package edu.eci.arsw.didiApp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.didiApp.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Integer> {

    List<Servicio> findAll();

    Servicio save(Servicio service);

    
}
