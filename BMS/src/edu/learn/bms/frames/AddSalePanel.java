package edu.learn.bms.frames;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.Sale;
import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.service.BookService;
import edu.learn.bms.service.SaleDetailService;
import edu.learn.bms.service.SaleService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class AddSalePanel extends JPanel {
	private JTextField textbookid;
	private JTextField textnum;
	private JTable detailTable;
	private JTextField textbookprice;
	private JTextField textbookname;
	
	private BookService bookService = new BookService();
	private SaleService saleService = new SaleService();
	private SaleDetailService detailService = new SaleDetailService();
	
	private static AddSalePanel addSalePanel = new AddSalePanel(); 
	private JButton btnSubmit;
	public static AddSalePanel createInstance() {
		return addSalePanel;
	}
	/**
	 * Create the panel.
	 */
	private AddSalePanel() {
		setLayout(null);
		
		JLabel label_1 = new JLabel("\u4E66\u7C4D\u7F16\u53F7\uFF1A");
		label_1.setBounds(22, 20, 67, 15);
		add(label_1);
		
		textbookid = new JTextField();
		textbookid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//回车事件
				bookenterPressedEvent(e);
			}
		});
		textbookid.setBounds(83, 17, 66, 21);
		add(textbookid);
		textbookid.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6570\u91CF\uFF1A");
		label_2.setBounds(183, 20, 54, 15);
		add(label_2);
		
		textnum = new JTextField();
		textnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//回车事件
				numenterPressedEvent(e);
			}
		});
		textnum.setBounds(226, 17, 66, 21);
		add(textnum);
		textnum.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 59, 580, 259);
		add(scrollPane);
		
		detailTable = new JTable();
		detailTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u660E\u7EC6\u7F16\u53F7", "\u4E66\u7C4D\u540D\u79F0", "\u6570\u91CF", "\u5C0F\u8BA1", "bookid"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		detailTable.getColumnModel().getColumn(4).setPreferredWidth(0);
		detailTable.getColumnModel().getColumn(4).setMinWidth(0);
		detailTable.getColumnModel().getColumn(4).setMaxWidth(0);
		scrollPane.setViewportView(detailTable);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClickedEvent();
			}
		});
		btnAdd.setBounds(326, 16, 78, 23);
		add(btnAdd);
		
		textbookprice = new JTextField();
		textbookprice.setEditable(false);
		textbookprice.setBounds(414, 17, 0, 0);
		add(textbookprice);
		textbookprice.setColumns(10);
		
		textbookname = new JTextField();
		textbookname.setEditable(false);
		textbookname.setBounds(490, 17, 0, 0);
		add(textbookname);
		textbookname.setColumns(10);
		
		btnSubmit = new JButton("\u521B\u5EFA\u8BA2\u5355");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//提交创建的订单
				submitClickedEvent(e);
			}
		});
		btnSubmit.setBounds(500, 16, 93, 23);
		add(btnSubmit);
		
		
	}
	/**
	 * 添加明细
	 */
	private void addClickedEvent() {
		fillDetailTable();
		//清空信息
		textbookid.setText("");
		textbookname.setText("");
		textbookprice.setText("");
		textnum.setText("");
		textbookid.grabFocus();
	}
	/**
	 * 	提交创建的订单明细
	 * @param e
	 */
	private void submitClickedEvent(ActionEvent e) {
		//0.生成一个订单
		String saleid = UUID.randomUUID().toString();
		String userid = MainMenu.getCurrentuserid();
		Date date = Calendar.getInstance().getTime();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date saletime = sdf.format(date);
		Integer salestate = 0;
		Sale sale = new Sale(saleid,userid,date,salestate);
		//1.保存明细信息
		int rowCount = detailTable.getRowCount();
		List<SaleDetails> list = new ArrayList<>();
		for(int i=0;i<rowCount;i++) {
			SaleDetails details = new SaleDetails();
			details.setSdid(detailTable.getValueAt(i, 0).toString());
			details.setSaleid(saleid);
			details.setBookid(detailTable.getValueAt(i, 4).toString());
			details.setSdnum(Integer.parseInt(detailTable.getValueAt(i, 2).toString()));
			details.setSdprice(Double.parseDouble(detailTable.getValueAt(i, 3).toString()));
			list.add(details);
		}
		
		boolean isSuccess = saleService.addSaleDetailsForSale(sale,list);
		if(isSuccess) {
			JOptionPane.showMessageDialog(null, "添加成功");
			DefaultTableModel dtm  = (DefaultTableModel) detailTable.getModel();
			dtm.setRowCount(0);
			
		}else {
			JOptionPane.showMessageDialog(null, "添加失败");
			DefaultTableModel dtm  = (DefaultTableModel) detailTable.getModel();
			dtm.setRowCount(0);
			saleService.deleteSale(sale);
		}
		
	}
	
	/**
	 * 明细数量回车事件
	 * @param e
	 */
	private void numenterPressedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			String num = textnum.getText();
			if(num.equals("")) {
				JOptionPane.showMessageDialog(null, "书籍数量不能为空！");
				return;
			}
			
			//填充表格
			fillDetailTable();
			//清空信息
			textbookid.setText("");
			textbookname.setText("");
			textbookprice.setText("");
			textnum.setText("");
			textbookid.grabFocus();
		}
		
	}
	
	/**
	 * 点击回车，提交根据书籍id查询相关书籍信息
	 * @param e
	 */
	private void bookenterPressedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			String bookid = textbookid.getText();
			if(bookid.equals("")) {
				JOptionPane.showMessageDialog(null, "书籍编号不能为空！");
				return;
			}
			Book book = bookService.getBookById(bookid);
			if(book!=null) {
				textbookname.setText(book.getBookname());
				textbookprice.setText(book.getBookprice().toString());
			}else {
				JOptionPane.showMessageDialog(null, "书籍编号不存在！");
				textbookid.setText("");
				return;
			}
			//获得焦点
			textnum.grabFocus();
		}
	}
	
	/**
	 * 根据输入信息填充表格
	 */
	private void fillDetailTable() {
		DefaultTableModel dtm = (DefaultTableModel) detailTable.getModel();
		int rowCount = detailTable.getRowCount();
		dtm.setRowCount(rowCount);
		String detailID =UUID.randomUUID().toString();
		String bookid = textbookid.getText();
		String bookname = textbookname.getText();
		String num = textnum.getText();
		Double total = Double.parseDouble(textbookprice.getText())*Integer.parseInt(num);
		//填充
		Vector<Object> v = new Vector<>();
		v.add(detailID);
		v.add(bookname);
		v.add(num);
		v.add(total);
		v.add(bookid);
		dtm.addRow(v);
	}
}
