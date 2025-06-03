package br.com.locaweb.relatorioclientes.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.locaweb.relatorioclientes.model.Maquina;
import br.com.locaweb.relatorioclientes.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquinas")
@CrossOrigin(origins = "*")
public class MaquinaController {

    @Autowired
    private MaquinaRepository maquinaRepository;

    @GetMapping("/por-cliente/{codCliente}")
    public List<Maquina> listarPorCliente(@PathVariable Integer codCliente) {
        return maquinaRepository.findByCodCliente(codCliente);
    }
    
    

} 
