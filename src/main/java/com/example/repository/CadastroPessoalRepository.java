package com.example.repository;

import com.example.entity.CadastroPessoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadastroPessoalRepository extends JpaRepository<CadastroPessoal, Long> {

    public List<CadastroPessoal> findAllByNomeContainingIgnoreCase(String nome);
    public List<CadastroPessoal> findAllBySobrenomeContainingIgnoreCase(String sobrenome);
    public List<CadastroPessoal> findAllByPaisContainingIgnoreCase(String pais);
    
}
