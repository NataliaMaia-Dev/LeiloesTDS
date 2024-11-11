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
    PreparedStatement stmt;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            conn = new conectaDAO().connectDB();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getStatus());

            stmt.execute();
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
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return listagem;
    }

    public void venderProduto(int idProduto) {

        try {
            // Estabelecendo conexão com o banco de dados
            conn = new conectaDAO().connectDB();

            // Verificar se o produto já está vendido
            String sqlCheck = "SELECT status FROM produtos WHERE id = ?";
            stmt = conn.prepareStatement(sqlCheck);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                if ("Vendido".equalsIgnoreCase(status)) {
                    System.out.println("Produto já vendido.");
                    JOptionPane.showMessageDialog(null, "O produto já está vendido e não pode ser vendido novamente.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                return;
            }

            // Atualizar o status do produto para "Vendido"
            String sqlUpdate = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            stmt = conn.prepareStatement(sqlUpdate);
            stmt.setInt(1, idProduto);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao vender o produto. Verifique o ID.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar status do produto: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarVendidos() {
        try {
            conn = new conectaDAO().connectDB();

            String sql = "SELECT id, nome, valor, status FROM produtos where status = 'Vendido'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));

                listagemVendidos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return listagemVendidos;
    }

}
