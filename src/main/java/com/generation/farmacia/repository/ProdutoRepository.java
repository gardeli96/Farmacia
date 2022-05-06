package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long>{

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	public List<Produto> findByNomeAndLaboratorio( String nome, String laboratorio);
	public List<Produto > findByNomeOrLaboratorio(String nome, String laboratorio);
	
	@Query(value = "select*from tb_produtos where preco between :inicio and :fim", nativeQuery =  true)
	public List <Produto> buscarProdutoEntre(@Param ("inicio") BigDecimal inicio,@Param ("fim") BigDecimal fim);
	
	public List <Produto> findByPrecoIn(List<BigDecimal> preco); 
}
