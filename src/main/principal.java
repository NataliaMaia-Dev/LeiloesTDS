
package main;

import dto.ProdutosDTO;
import view.cadastroVIEW;
import dao.ProdutosDAO;

public class principal {
    public static void main(String args[]) {
        cadastroVIEW tela = new cadastroVIEW();
        tela.setVisible(true);
        /*        ProdutosDTO p = new ProdutosDTO();
        p.setNome ("Teste");
        p.setValor (350.00);
        p.setStatus ("A Venda");
        
        ProdutosDAO dao = new ProdutosDAO();
        dao.cadastrarProduto (p);*/
    }
}
