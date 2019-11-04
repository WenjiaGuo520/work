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

import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.Purchasedetails;
import edu.learn.bms.projo.Stock;
import edu.learn.bms.service.PurDetailService;
import edu.learn.bms.service.PurchaseService;
import edu.learn.bms.service.SaleDetailService;
import edu.learn.bms.service.SaleService;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PurDetailsFrame extends JFrame {

	private JPanel contentPane;
	private JTable detailsTable;
	private PurchaseService purService = new PurchaseService();
	private PurDetailService detailService = new PurDetailService();
	public static JButton btnSale;
	private JTextField textid;
	private JTextField textnum;
	

	/**
	 * Create the frame.
	 */
	public PurDetailsFrame(List<Vector<Object>> list) {
		setTitle("\u8FDB\u8D27\u660E\u7EC6");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 10, 414, 200);
		contentPane.add(scrollPane);
		
		detailsTable = new JTable();
		detailsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//表格点击事件
				detailTableClickedEvent(e);
			}
		});
		
		detailsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u660E\u7EC6\u7F16\u53F7", "\u6240\u5C5E\u8BA2\u5355", "\u4E66\u7C4D\u540D\u79F0", "\u6570\u91CF", "\u5B9E\u9645\u8FDB\u8D27\u6570", "\u5C0F\u8BA1", "\u4E66\u7C4Did"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		detailsTable.getColumnModel().getColumn(6).setPreferredWidth(0);
		detailsTable.getColumnModel().getColumn(6).setMinWidth(0);
		detailsTable.getColumnModel().getColumn(6).setMaxWidth(0);
		scrollPane.setViewportView(detailsTable);
		
		btnSale = new JButton("\u5165\u5E93");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//入庫
				inStockClickedEvent(e);
			}
		});
		btnSale.setBounds(382, 305, 71, 23);
		//btnSale.setVisible(false);
		contentPane.add(btnSale);
		
		JLabel label = new JLabel("\u7F16\u53F7\uFF1A");
		label.setBounds(39, 236, 54, 15);
		contentPane.add(label);
		
		textid = new JTextField();
		textid.setEditable(false);
		textid.setBounds(92, 233, 66, 21);
		contentPane.add(textid);
		textid.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5B9E\u9645\u6570\u91CF\uFF1A");
		label_1.setBounds(185, 236, 71, 15);
		contentPane.add(label_1);
		
		textnum = new JTextField();
		textnum.setBounds(252, 233, 66, 21);
		contentPane.add(textnum);
		textnum.setColumns(10);
		
		JButton btnEdit = new JButton("\u4FEE\u6539\u6570\u91CF");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//数量修改按钮
				numEditClickedEvent(e);
			}
		});
		btnEdit.setBounds(360, 232, 93, 23);
		contentPane.add(btnEdit);
		
		fillDetailsTable(list);
	}
	/**
	 * 点击修改进货数量
	 * @param e
	 */
	private void numEditClickedEvent(ActionEvent e) {
		Integer num = Integer.parseInt(textnum.getText().toString());
		int row = detailsTable.getSelectedRow();
		detailsTable.setValueAt(num, row, 4);
		/*DefaultTableModel dtm = (DefaultTableModel) detailsTable.getModel();
		int rowCount = detailsTable.getRowCount();*/
	}
	/**
	 * 点击表格，将编号和数量显示到修改框
	 * @param e
	 */
	private void detailTableClickedEvent(MouseEvent e) {
		int row = detailsTable.getSelectedRow();
		textid.setText(detailsTable.getValueAt(row, 0).toString());
		textnum.setText(detailsTable.getValueAt(row, 4).toString());
	}
	/**
	 * 入庫
	 * @param e
	 */
	private void inStockClickedEvent(ActionEvent e) {
	
		
		int rowCount = detailsTable.getRowCount();
		List<Stock> stocks = new ArrayList<>();
		for(int i=0;i<rowCount;i++) {
			String bookid=detailsTable.getValueAt(i, 6).toString();
			String bookname=detailsTable.getValueAt(i, 2).toString();
			Integer stockNum = Integer.parseInt(detailsTable.getValueAt(i, 4).toString());
			if(stockNum==null || stockNum==0) {
				JOptionPane.showMessageDialog(null, "进货数量不能为空");
				return;
			}
			Date stockdate = new Date();
			String stockreason="入库";
			Stock stock = new Stock(UUID.randomUUID().toString(),bookid,stockNum,stockdate,stockreason);
			Double total = Double.parseDouble(detailsTable.getValueAt(i, 5).toString());
			Book book = new Book(bookid,stockNum,bookname,total/stockNum);
			stock.setBook(book);
			stocks.add(stock);
		}
		
		boolean isSuccess = detailService.operStore(stocks);
		if(isSuccess) {
			JOptionPane.showMessageDialog(null, "入库成功！");
			//修改订单状态
			purService.updateState(detailsTable.getValueAt(0, 1).toString(),1);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "发生了未知错误，入库失败！");
		}
	}
	/**
	 * 填充表格
	 * @param list
	 */
	private void fillDetailsTable(List<Vector<Object>> list) {
		DefaultTableModel dtm = (DefaultTableModel) detailsTable.getModel();
		for(Vector<Object> v:list) {
			dtm.addRow(v);
		}
	}
}
