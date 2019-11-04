package edu.learn.bms.frames;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.BookType;
import edu.learn.bms.service.BookService;
import edu.learn.bms.service.BookTypeService;
import javafx.scene.text.TextBoundsType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class BookPanel extends JPanel {
	private JTextField textSearchBname;
	private JTable bookTable;
	private JTextField textBookName;
	private JTextField textPrice;
	private JTextField textId;
	private JComboBox cbSearchBookType;
	private JComboBox cbBookType;
	private JRadioButton radioUp;
	private JRadioButton radioDown;
	//单例模式
	private static BookPanel bookPanel = new BookPanel();
	public static BookPanel createInstance() {
		return bookPanel;
	}
	//创建业务逻辑层对象
	private BookService bookService = new BookService();
	private BookTypeService typeService = new BookTypeService();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textstore;
	/**
	 * Create the panel.
	 */
	private BookPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\uFF1A");
		label.setBounds(40, 34, 54, 15);
		add(label);
		
		textSearchBname = new JTextField();
		textSearchBname.setBounds(84, 31, 91, 21);
		add(textSearchBname);
		textSearchBname.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u578B\uFF1A");
		label_1.setBounds(185, 34, 73, 15);
		add(label_1);
		
		cbSearchBookType = new JComboBox();
		cbSearchBookType.setBounds(250, 30, 94, 23);
		add(cbSearchBookType);
		
		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.addActionListener(new ActionListener() {
			//查询按钮点击时间
			public void actionPerformed(ActionEvent evt) {
				searchBtnEvent(evt);
			}
		});
		btnSearch.setBounds(370, 30, 67, 23);
		add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 76, 422, 139);
		add(scrollPane);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				//表格点击事件
				bookTableClickedEvent(evt);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u56FE\u4E66ID", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u7C7B\u578B", "\u72B6\u6001", "\u5E93\u5B58", "\u4EF7\u683C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		bookTable.getColumnModel().getColumn(0).setMinWidth(0);
		bookTable.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(bookTable);
		
		JLabel lbname = new JLabel("\u540D\u79F0\uFF1A");
		lbname.setBounds(40, 236, 54, 15);
		add(lbname);
		
		textBookName = new JTextField();
		textBookName.setBounds(74, 233, 66, 21);
		add(textBookName);
		textBookName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u578B\uFF1A");
		label_2.setBounds(164, 236, 73, 15);
		add(label_2);
		
		cbBookType = new JComboBox();
		cbBookType.setBounds(230, 232, 91, 23);
		add(cbBookType);
		
		JLabel lblIsbn = new JLabel("\u72B6\u6001\uFF1A");
		lblIsbn.setBounds(334, 236, 54, 15);
		add(lblIsbn);
		
		JLabel lblNewLabel = new JLabel("\u5E93\u5B58\uFF1A");
		lblNewLabel.setBounds(41, 280, 54, 15);
		add(lblNewLabel);
		
		JLabel label_3 = new JLabel("\u4EF7\u683C\uFF1A");
		label_3.setBounds(165, 280, 54, 15);
		add(label_3);
		
		textPrice = new JTextField();
		textPrice.setBounds(231, 277, 90, 21);
		add(textPrice);
		textPrice.setColumns(10);
		
		
		JButton btnEdite = new JButton("\u4FEE\u6539");
		btnEdite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//修改按钮事件
				editClickedEvent(evt);
				
			}
		});
		btnEdite.setBounds(369, 276, 93, 23);
		add(btnEdite);
		
		textId = new JTextField();
		textId.setEnabled(false);
		textId.setEditable(false);
		textId.setBounds(74, 325, 0, 0);
		add(textId);
		
		radioUp = new JRadioButton("\u4E0A\u67B6");
		buttonGroup.add(radioUp);
		radioUp.setBounds(370, 232, 54, 23);
		add(radioUp);
		
		radioDown = new JRadioButton("\u4E0B\u67B6");
		buttonGroup.add(radioDown);
		radioDown.setBounds(426, 232, 67, 23);
		add(radioDown);
		
		textstore = new JTextField();
		textstore.setEditable(false);
		textstore.setBounds(74, 277, 66, 21);
		add(textstore);
		textstore.setColumns(10);
		//填充书籍表（参数为查询条件）
		fillBookTable(new Book());
		//初始化cbSearchBookType
		initBookTypeForSearch();

	}
	/**
	 * 修改按钮，提交修改表单
	 * @param evt
	 */
	private void editClickedEvent(ActionEvent evt) {
		String bookid = textId.getText();
		String bookname = textBookName.getText();
		Object booktype = cbBookType.getSelectedItem();
		String btid="";
		if(booktype instanceof BookType) {
			btid=((BookType) booktype).getBtid();
		}
		String state="";
		if(radioUp.isSelected()) {
			state="1";
		}else if(radioDown.isSelected()) {
			state="0";
		}
		Integer bookstate = Integer.parseInt(state);
		Integer bookstore = Integer.parseInt(textstore.getText());
		Double bookprice = Double.parseDouble((textPrice.getText()));
		Book book = new Book(bookid,btid,bookstate,bookstore,bookname,bookprice);
		//checkParameter(book);
		boolean isSuccess = bookService.updateBook(book);
		if(isSuccess) {
			//弹出提示
			JOptionPane.showMessageDialog(null, "修改成功！");
			//刷新表格
			fillBookTable(new Book());
		}else {
			//弹出提示
			JOptionPane.showMessageDialog(null, "修改参数错误，修改失败！请重新输入！");
		}
		
	}
	/**
	 * 	表格点击事件，将选中的行的数据显示到修改表单中
	 * @param evt
	 */
	private void bookTableClickedEvent(MouseEvent evt) {
		//1.填充类型下拉框
		initBookTypeForEdit();
		//2.获得选中的行
		int row = bookTable.getSelectedRow();
		//填充数据
		
		textId.setText(bookTable.getValueAt(row, 0).toString());
		textBookName.setText(bookTable.getValueAt(row, 1).toString());
		cbBookType.setSelectedItem(bookTable.getValueAt(row, 2));
		String state = bookTable.getValueAt(row, 3).toString();
		if("1".equals(state)) {
			radioUp.setSelected(true);
		}
		if("0".equals(state)) {
			radioDown.setSelected(true);
		}
		textstore.setText(bookTable.getValueAt(row, 4).toString());
		
		textPrice.setText(bookTable.getValueAt(row, 5).toString());
		
	}
	/**
	 * 查询按钮点击事件，提交查询表单
	 * @param evt
	 */
	private void searchBtnEvent(ActionEvent evt) {
		//1.获得查询表单信息
		String searchName = textSearchBname.getText();
		//注意此处是一个BookType对象，只是重写了toString让其显示为类型名称
		Object bookType = cbSearchBookType.getSelectedItem();
		String searchBtid = "";
		if(bookType instanceof BookType) {
			bookType = (BookType) bookType;
			searchBtid = ((BookType) bookType).getBtid();
		}
		//2. 根据查询条件 刷新表格
		fillBookTable(new Book(searchName,searchBtid));
	}
	/**
	 * 初始化图书类型查询下拉选择框
	 *  	如果是查询的类型选择框，需要在第一行显示  请选择
	 */
	private void initBookTypeForSearch() {
		cbSearchBookType.removeAllItems();
		BookType type_1 = new BookType();
		type_1.setBtname("-请选择-");
		type_1.setBtid("-1");
		cbSearchBookType.addItem(type_1);
		List<BookType> typeList = typeService.getBookTypeList(new BookType());
		for (BookType bt : typeList) {
			
			cbSearchBookType.addItem(bt);
		}
	}
	/**
	 * 	初始化修改表单的booktype下拉框
	 */
	private void initBookTypeForEdit() {
		cbBookType.removeAllItems();
		List<BookType> typeList = typeService.getBookTypeList(new BookType());
		
		for (BookType bt : typeList) {
			cbBookType.addItem(bt);
		}
		
		
	}
	

	/**
	 *	 填充Book表格
	 * @param book
	 */
	private void fillBookTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		//先格式化表格
		dtm.setRowCount(0);
		List<Book> books = bookService.getBookList(book);
		
		for(Book b:books) {
			Vector<Object> rows = new Vector<>();
			rows.add(b.getBookid());
			rows.add(b.getBookname());
			rows.add(b.getType());
			rows.add(b.getBookstate().toString());
			rows.add(b.getBookstore().toString());
			rows.add(b.getBookprice().toString());
			//添加一行
			dtm.addRow(rows);
		}
		
	}
}
