package org.serratec.avaliacao.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.serratec.avaliacao.model.Funcionario;
import org.serratec.avaliacao.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // GET para listar TODOS os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    // GET para listar um funcionário(ID) específico
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID inexistente" + id)));
    }

    // POST para inserir um funcionário(ID)
    @PostMapping
    public ResponseEntity<Funcionario> inserir(@Valid @RequestBody Funcionario funcionario) {
        funcionario.setId(null);
        Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    // PUT para atualizar um funcionário(ID)
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("ID inexistente" + id);
        }

        funcionario.setId(id);
        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    // DELETE para remover um funcionário(ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("ID inexistente: " + id);
        }

        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}