package br.furb.restaurante.service;

import br.furb.restaurante.entity.Produto;
import br.furb.restaurante.entity.Produto;
import br.furb.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto saveProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public boolean deleteProduto(Long id){
        Optional<Produto> produtoToDelete = produtoRepository.findById(id);

        if(produtoToDelete.isPresent()){
            produtoRepository.delete(produtoToDelete.get());
            return true;
        }else{
            return false;
        }
    }
}
