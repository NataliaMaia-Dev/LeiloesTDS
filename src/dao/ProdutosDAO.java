package dao;

import dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        
        try {
            conn = new conectaDAO().connectDB();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
            PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, produto.getNome());
            query.setDouble(2, produto.getValor());
            query.setString(3, produto.getStatus());

            query.execute();
            // Adiciona o produto à lista após o cadastro
            listagem.add(produto);

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());

        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
        conn = new conectaDAO().connectDB();

        String sql = "SELECT id, nome, valor, status FROM produtos";
        PreparedStatement query = conn.prepareStatement(sql);
        ResultSet resultset = query.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getDouble("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar produtos: " + e.getMessage());
    }

    return listagem;
}
}
