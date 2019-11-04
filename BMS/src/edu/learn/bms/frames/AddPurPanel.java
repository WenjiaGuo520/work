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
import edu.learn.bms.projo.Purchase;
import edu.learn.bms.projo.Purchasedetails;
import edu.learn.bms.projo.Sale;
import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.service.BookService;
import edu.learn.bms.service.PurDetailService;
import edu.learn.bms.service.PurchaseService;
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

public class AddPurPanel extends JPanel {
	private JTextField textbookid;
	private JTextField textnum;
	private JTable detailTable;
	
	private BookService bookService = new BookService();
	
	private PurchaseService purService = new PurchaseService();
	private PurDetailService purDetailService = new PurDetailService();
	
	
	private static AddPurPanel addSalePanel = new AddPurPanel(); 
	private JButton btnSubmit;
	private JTextField textbookname;
	private JTextField textbookprice;
	public static AddPurPanel createInstance() {
		return addSalePanel;
	}
	/**
	 * Create the panel.
	 */
	private AddPurPanel() {
		setLayout(null);
		
		JLabel label_1 = new JLabel("\u4E66\u7C4D\u7F16\u53F7\uFF1A");
		label_1.setBounds(22, 20, 67, 15);
		add(label_1);
		
		textbookid = new JTextField();
		textbookid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//�س�ʱ��
				enterClickedEvent(e);
			}
		});
		
		textbookid.setBounds(83, 17, 66, 21);
		add(textbookid);
		textbookid.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6570\u91CF\uFF1A");
		label_2.setBounds(413, 20, 41, 15);
		add(label_2);
		
		textnum = new JTextField();
		textnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//�س�ʱ��
				numenterClickedEvent(e);
			}
		});
		
		textnum.setBounds(459, 17, 66, 21);
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
				"\u660E\u7EC6\u7F16\u53F7", "\u4E66\u7C4D\u540D\u79F0", "\u6570\u91CF", "\u5355\u4EF7", "\u5C0F\u8BA1", "bookid"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		detailTable.getColumnModel().getColumn(5).setPreferredWidth(0);
		detailTable.getColumnModel().getColumn(5).setMinWidth(0);
		detailTable.getColumnModel().getColumn(5).setMaxWidth(0);
		scrollPane.setViewportView(detailTable);
		
		btnSubmit = new JButton("\u521B\u5EFA\u8BA2\u5355");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�ύ�����Ķ���
				submitClickedEvent(e);
			}
		});
		btnSubmit.setBounds(509, 328, 93, 23);
		add(btnSubmit);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����ϸ������
				addClickedEvent();
			}
		});
		button.setBounds(535, 16, 67, 23);
		add(button);
		
		JLabel label = new JLabel("\u4E66\u7C4D\u540D\uFF1A");
		label.setBounds(159, 20, 54, 15);
		add(label);
		
		textbookname = new JTextField();
		textbookname.setBounds(208, 17, 66, 21);
		add(textbookname);
		textbookname.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5355\u4EF7\uFF1A");
		label_3.setBounds(282, 20, 54, 15);
		add(label_3);
		
		textbookprice = new JTextField();
		textbookprice.setBounds(325, 17, 66, 21);
		add(textbookprice);
		textbookprice.setColumns(10);
		
		
	}
	/**
	 * �ύ���룬��䵽���
	 * @param e
	 */
	private void numenterClickedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			addClickedEvent();
			
		}
	}
	/**
	 * ���text��ֵ��������ָ��id�����
	 */
	private void clearText() {
		textbookid.setText("");
		textbookname.setText("");
		textbookprice.setText("");
		textnum.setText("");
		textbookid.grabFocus();
	}
	/**
	 * ����Ƿ�������ı�ŵ��鼮
	 * @param e
	 */
	private void enterClickedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			Book book = bookService.getBookById(textbookid.getText());
			if(book!=null) {
				textbookname.setEditable(false);
				textbookprice.setEditable(false);
				textbookname.setText(book.getBookname());
				textbookprice.setText(book.getBookprice().toString());
				textnum.grabFocus();
			}else {
				int flag = JOptionPane.showConfirmDialog(null, "��Ҫ��������һ�����飬\r\n��Ҫ���鼮��������������͵��飬��ע���ֶ������鼮���ͣ�\r\n�Ƿ�ȷ����");
				if(flag==JOptionPane.YES_OPTION) {
					textbookname.setEditable(true);
					textbookprice.setEditable(true);
					textbookname.grabFocus();
					
				}else {
					textbookid.setText("");
					textbookid.grabFocus();
				}
			}
		}
	}
	/**
	 * �������ֵ��ӵ�������ϸ����
	 * @param e
	 */
	private void addClickedEvent() {
		DefaultTableModel dtm = (DefaultTableModel) detailTable.getModel();
		dtm.setRowCount(detailTable.getRowCount());
		
		Vector<Object> v = new Vector<>();
		v.add(UUID.randomUUID().toString());
		v.add(textbookname.getText());
		Integer num = Integer.parseInt((textnum.getText()));
		v.add(num);
		Double price = Double.parseDouble(textbookprice.getText());
		v.add(price);
		v.add(num*price);
		v.add(textbookid.getText());
		dtm.addRow(v);
		clearText();
	}
	/**
	 * 	�ύ�����Ķ�����ϸ
	 * @param e
	 */
	private void submitClickedEvent(ActionEvent e) {
		//0.����һ������
		String purid = UUID.randomUUID().toString();
		String userid = MainMenu.getCurrentuserid();
		Date date = Calendar.getInstance().getTime();
		Integer state = 0;
		Purchase pur = new Purchase(purid,userid,date,state);
		//1.������ϸ��Ϣ
		int rowCount = detailTable.getRowCount();
		List<Purchasedetails> list = new ArrayList<>();
		for(int i=0;i<rowCount;i++) {
			Purchasedetails details = new Purchasedetails();
			details.setPdid(detailTable.getValueAt(i, 0).toString());
			details.setPurid(purid);
			details.setBookid(detailTable.getValueAt(i, 5).toString());
			details.setPdplannum(((Integer.parseInt(detailTable.getValueAt(i, 2).toString()))));
			details.setPdprice((Double.parseDouble(detailTable.getValueAt(i, 4).toString())));
			details.setBookname(detailTable.getValueAt(i, 1).toString());
			
			
			//details.setBook(new Book(detailTable.getValueAt(i, 5).toString(),detailTable.getValueAt(i, 1).toString()));
			list.add(details);
		}
		
		boolean isSuccess = purService.addPurForPurdetails(pur,list);
		if(isSuccess) {
			JOptionPane.showMessageDialog(null, "��ӳɹ�");
			DefaultTableModel dtm  = (DefaultTableModel) detailTable.getModel();
			dtm.setRowCount(0);
			
		}else {
			JOptionPane.showMessageDialog(null, "���ʧ��");
			DefaultTableModel dtm  = (DefaultTableModel) detailTable.getModel();
			dtm.setRowCount(0);
			purService.deletePur(purid);
			for(Purchasedetails pd : list) {
				bookService.deletBook(pd.getBookid());
			}
		}
		
	}
	
	
}
