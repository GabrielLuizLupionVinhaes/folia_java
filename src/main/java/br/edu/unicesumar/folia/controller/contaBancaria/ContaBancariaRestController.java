package br.edu.unicesumar.folia.controller.contaBancaria;

import br.edu.unicesumar.folia.domain.contaBancaria.ContaBancaria;
import br.edu.unicesumar.folia.domain.contaBancaria.ContaBancariaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contas-bancarias")
public class ContaBancariaRestController {
    @Autowired
    private ContaBancariaService contaBancariaService;

    //nova conta
    @PostMapping
    public ResponseEntity<ContaBancaria>cadastrarContaBancaria(@RequestBody ContaBancaria contaBancaria) {
        ContaBancaria novaConta = contaBancariaService.cadastrarContaBancaria(contaBancaria);
        return new ResponseEntity<>(novaConta, HttpStatus.CREATED);

    }



    @GetMapping
    public ResponseEntity<List<ContaBancariaListaDTO>> listarTodas(){
        List<ContaBancariaListaDTO> contas = contaBancariaService.listarTodas();
        return new ResponseEntity<>(contas,HttpStatus.OK);


    }


    @GetMapping("/{uuid}")
    public ResponseEntity<ContaBancaria> buscarPorId (@PathVariable UUID uuid){

        Optional<ContaBancaria> contaBancaria = contaBancariaService.buscarPorId(uuid);
        return contaBancaria.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<ContaBancaria> atualizarConta(@PathVariable UUID uuid, @RequestBody ContaBancaria contaAtualizada){
        try {
            ContaBancaria contaBancaria = contaBancariaService.atualizarConta(uuid, contaAtualizada);
            return new ResponseEntity<>(contaBancaria, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // detetar conta bancaria
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletarConta(@PathVariable UUID uuid){
        try {
            contaBancariaService.detelarContaBancaria(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


















}