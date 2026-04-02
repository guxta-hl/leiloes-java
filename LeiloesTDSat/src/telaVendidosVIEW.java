import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class telaVendidosVIEW extends JFrame {

    private JTable tabelaVendas;

    public telaVendidosVIEW() {
        setTitle("Produtos Vendidos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabelaVendas = new JTable();
        tabelaVendas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));

        JScrollPane scroll = new JScrollPane(tabelaVendas);

        add(scroll);

        carregarTabela();
    }

    public void carregarTabela() {
        ProdutosDAO dao = new ProdutosDAO();

        DefaultTableModel modelo = (DefaultTableModel) tabelaVendas.getModel();
        modelo.setRowCount(0);

        for (ProdutosDTO p : dao.listarProdutosVendidos()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor(),
                p.getStatus()
            });
        }
    }

}
