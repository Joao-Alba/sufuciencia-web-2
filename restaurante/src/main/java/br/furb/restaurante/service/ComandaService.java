package br.furb.restaurante.service;

import br.furb.restaurante.entity.Comanda;
import br.furb.restaurante.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    public List<Comanda> getAllComandas(){
        return comandaRepository.findAll();
    }

    public Comanda getComandaById(Long id){
        return comandaRepository.findById(id).orElse(null);
    }

    public Comanda saveComanda(Comanda comanda){
        return comandaRepository.save(comanda);
    }

    public boolean deleteComanda(Long id){
        Optional<Comanda> comandaToDelete = comandaRepository.findById(id);

        if(comandaToDelete.isPresent()){
            comandaRepository.delete(comandaToDelete.get());
            return true;
        }else{
            return false;
        }
    }
}
