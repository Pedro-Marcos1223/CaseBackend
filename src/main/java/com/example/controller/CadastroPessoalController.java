package com.example.controller;

import com.example.entity.CadastroPessoal;
import com.example.repository.CadastroPessoalRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cadastro")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CadastroPessoalController {

    @Autowired
    private CadastroPessoalRepository cadastroPessoalRepository;

    @GetMapping
    public ResponseEntity<List<CadastroPessoal>> getAllCadastros(){
        if(cadastroPessoalRepository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foi encontrado nenhum cadastro");
        }else{
            return ResponseEntity.ok(cadastroPessoalRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroPessoal> getCadastroById(@PathVariable Long id){
        return cadastroPessoalRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CadastroPessoal>> getAllCadastrosByNome(@PathVariable String nome){

        if(cadastroPessoalRepository.findAllByNomeContainingIgnoreCase(nome).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foram encontrados cadastros com esse nome.");
        }else{
            return ResponseEntity.ok(cadastroPessoalRepository.findAllByNomeContainingIgnoreCase(nome));
        }
    }

    @GetMapping("/sobrenome/{sobrenome}")
    public ResponseEntity<List<CadastroPessoal>> getAllCadastrosBySobrenome(@PathVariable String sobrenome){
        if(cadastroPessoalRepository.findAllBySobrenomeContainingIgnoreCase(sobrenome).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foram encontrados cadastros com esse sobrenome.");
        }else{
            return ResponseEntity.ok(cadastroPessoalRepository.findAllBySobrenomeContainingIgnoreCase(sobrenome));
        }
    }

    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<CadastroPessoal>> getAllCadastrosByPais(@PathVariable String pais){
        if(cadastroPessoalRepository.findAllByPaisContainingIgnoreCase(pais).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foram encontrados cadastros com esse país.");
        }else{
            return ResponseEntity.ok(cadastroPessoalRepository.findAllByPaisContainingIgnoreCase(pais));
        }
    }

    @PostMapping
    public ResponseEntity<CadastroPessoal> postCadastro(@RequestBody CadastroPessoal cadastroPessoal){
        if(cadastroPessoal.getNome().length() < 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome precisa possuir mais de 3 caracteres.");
        }else if(cadastroPessoal.getPais().length() < 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O pais precisa possuir mais de 3 caracteres.");
        }else if(cadastroPessoal.getIdade() < 18){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cadastro pessoal só permite maiores de 18 anos.");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(cadastroPessoalRepository.save(cadastroPessoal));
        }
    }

    @PutMapping
    public ResponseEntity<CadastroPessoal> putCadastro(@RequestBody CadastroPessoal cadastroPessoal){
        if(!cadastroPessoalRepository.existsById(cadastroPessoal.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse cadastro não existe. Logo, não pode ser alterado.");
        }else if(cadastroPessoal.getNome().length() < 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome precisa possuir mais de 3 caracteres.");
        }else if(cadastroPessoal.getPais().length() < 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O pais precisa possuir mais de 3 caracteres.");
        }else if(cadastroPessoal.getIdade() < 18){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cadastro pessoal só permite maiores de 18 anos.");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(cadastroPessoalRepository.save(cadastroPessoal));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCadastro(@PathVariable long id){
        cadastroPessoalRepository.deleteById(id);
    }


}
