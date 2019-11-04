package edu.learn.bms.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import edu.learn.bms.projo.Stock;
import edu.learn.bms.service.SaleDetailService;
import edu.learn.bms.service.SaleService;

public class SaleDetailsFrame extends JFrame {

	private JPanel contentPane;
	private JTable saleDetailsTable;
	private SaleService saleService = new SaleService();
	private SaleDetailService detailService = new SaleDetailService();
	public static JButton btnSale;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleDetailsFrame frame = new SaleDetailsFrame(List<Vector<Object>> list);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public SaleDetailsFrame(List<Vector<Object>> list) {
		setTitle("\u9500\u552E\u660E\u7EC6");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 414, 200);
		contentPane.add(scrollPane);
		
		saleDetailsTable = new JTable();
		saleDetailsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u660E\u7EC6\u7F16\u53F7", "\u6240\u5C5E\u8BA2\u5355", "\u4E66\u7C4D\u540D\u79F0", "\u6570\u91CF", "\u5C0F\u8BA1"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(saleDetailsTable);
		
		btnSale = new JButton("\u51FA\u5E93");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����
				outStockClickedEvent(e);
			}
		});
		btnSale.setBounds(340, 220, 71, 23);
		//btnSale.setVisible(false);
		contentPane.add(btnSale);
		
		fillDetailsTable(list);
	}
	/**
	 * ����
	 * @param e
	 */
	private void outStockClickedEvent(ActionEvent e) {
		int rowCount = saleDetailsTable.getRowCount();
		List<Stock> stocks = new ArrayList<>();
		for(int i=0;i<rowCount;i++) {
			String bookid=saleDetailsTable.getValueAt(i, 2).toString();
			Integer stockNum = Integer.parseInt(saleDetailsTable.getValueAt(i, 3).toString())*-1;
			Date stockdate = new Date();
			String stockreason="����";
			stocks.add(new Stock(UUID.randomUUID().toString(),bookid,stockNum,stockdate,stockreason));
		}
		
		boolean isSuccess = detailService.operStore(stocks);
		if(isSuccess) {
			JOptionPane.showMessageDialog(null, "����ɹ���");
			//�޸Ķ���״̬
			saleService.updateState(saleDetailsTable.getValueAt(0, 1).toString(),1);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "��治�㣬������δ��ʶ���ԭ�򣬳���ʧ�ܣ�");
		}
	}
	/**
	 * �����
	 * @param list
	 */
	private void fillDetailsTable(List<Vector<Object>> list) {
		DefaultTableModel dtm = (DefaultTableModel) saleDetailsTable.getModel();
		for(Vector<Object> v:list) {
			dtm.addRow(v);
		}
	}

}
