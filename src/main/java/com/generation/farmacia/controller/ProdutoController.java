package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto") 
@CrossOrigin(origins = "*", allowedHeaders = "*") // receber requisição fora do dominio da URL do insominia
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
    public ResponseEntity<List<Produto>> GetAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> GetById(@PathVariable long id){
        return produtoRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Produto> post (@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> put (@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        produtoRepository.deleteById(id);
    }

    @GetMapping("/nome/{nome}/oulaboratorio/{laboratorio}")
    public ResponseEntity<List<Produto>> getByNomeOuLaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
        return ResponseEntity.ok(produtoRepository.findByNomeOrLaboratorio(nome, laboratorio));
    }
    
    // Consulta por nome e laboratório
    
    @GetMapping("/nome/{nome}/elaboratorio/{laboratorio}")
    public ResponseEntity<List<Produto>> getByNomeELaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
        return ResponseEntity.ok(produtoRepository.findByNomeAndLaboratorio(nome, laboratorio));
    }
    
    // Consulta por preço entre dois valores (Between com Native Query)
    
    @GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
    public ResponseEntity<List<Produto>> getByPrecoEntreNatve(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
        return ResponseEntity.ok(produtoRepository.buscarProdutoEntre(inicio, fim));
    }

    @GetMapping("/lista_preco/{p1}/{p2}/{p3}")
    public ResponseEntity<List<Produto>> getByListaPreco(@PathVariable BigDecimal p1, @PathVariable BigDecimal p2, @PathVariable BigDecimal p3){
        List<BigDecimal> listaPreco = List.of(p1, p2, p3);
        return ResponseEntity.ok(produtoRepository.findByPrecoIn(listaPreco));
    }
    
}
