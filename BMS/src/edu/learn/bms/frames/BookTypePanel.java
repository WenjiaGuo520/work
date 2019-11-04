package edu.learn.bms.frames;

import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.learn.bms.projo.BookType;
import edu.learn.bms.service.BookTypeService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.BorderLayout;

public class BookTypePanel extends JPanel {
	private JTable typeTable;
	private JTextField textSearchName;
	private JTextField textTypeName;
	private JRadioButton radioYes;
	private JRadioButton radioNo;
	private BookTypeService typeService = new BookTypeService();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textId;
	private static BookTypePanel bookTypePanel = new BookTypePanel();
	public static BookTypePanel createInstance() {
		return bookTypePanel;
	}
	/**
	 * Create the panel.
	 */
	private BookTypePanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 94, 390, 106);
		add(scrollPane);
		
		typeTable = new JTable();
		typeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				//������¼�
				tableClickedEvent(evt);
			}
		});
		typeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"btid", "\u7C7B\u578B\u540D\u79F0", "\u7C7B\u578B\u72B6\u6001"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		typeTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		typeTable.getColumnModel().getColumn(0).setMinWidth(0);
		typeTable.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(typeTable);
		
		JLabel label = new JLabel("\u7C7B\u578B\u540D\u79F0\uFF1A");
		label.setBounds(78, 69, 65, 15);
		add(label);
		
		textSearchName = new JTextField();
		textSearchName.setBounds(137, 66, 87, 21);
		add(textSearchName);
		textSearchName.setColumns(10);
		
		JButton btnSeach = new JButton("\u67E5\u8BE2");
		btnSeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//��ѯ��ť
				searchCileckEvent(evt);
			}
		});
		btnSeach.setBounds(234, 65, 73, 23);
		add(btnSeach);
		
		JLabel label_1 = new JLabel("\u7C7B\u578B\u540D\u79F0\uFF1A");
		label_1.setBounds(78, 231, 65, 15);
		add(label_1);
		
		textTypeName = new JTextField();
		textTypeName.setBounds(137, 228, 87, 21);
		add(textTypeName);
		textTypeName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7C7B\u578B\u72B6\u6001\uFF1A");
		label_2.setBounds(228, 231, 65, 15);
		add(label_2);
		
		radioYes = new JRadioButton("\u53EF\u7528");
		buttonGroup.add(radioYes);
		radioYes.setBounds(284, 227, 57, 23);
		add(radioYes);
		
		radioNo = new JRadioButton("\u4E0D\u53EF\u7528");
		buttonGroup.add(radioNo);
		radioNo.setBounds(338, 227, 65, 23);
		add(radioNo);
		
		JButton btnEdit = new JButton("\u4FEE\u6539");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//�޸İ�ť�¼�
				editClickedEvent(evt);
			}
		});
		btnEdit.setBounds(403, 227, 65, 23);
		add(btnEdit);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(77, 256, 0, 0);
		add(textId);
		textId.setColumns(10);
		
		JButton btnAdd = new JButton("\u589E\u52A0\u7C7B\u578B");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����ͼ������
				AddBookType inner = new AddBookType();
				inner.setLocationRelativeTo(null);
				inner.setVisible(true);
				
			}
		});
		btnAdd.setBounds(375, 65, 93, 23);
		add(btnAdd);
		
		//�����
		fillTypeTable(new BookType());
		
	}
	/**
	 * �޸İ�ť����ύ�޸���Ϣ
	 * @param evt
	 */
	private void editClickedEvent(ActionEvent evt) {
		String id = textId.getText();
		String name = textTypeName.getText();
		Integer state=null;
		if(radioYes.isSelected()) {
			state=1;
		}else if(radioNo.isSelected()) {
			state=0;
		}
		boolean flag = typeService.updateBookType(new BookType(id,name,state));
		if(flag) {
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
			//ˢ�±�
			fillTypeTable(new BookType());
		}else {
			JOptionPane.showMessageDialog(null, "�޸�ʧ��");
		}
	}

	/**
	 * 	�����Ӧ���У���ʾ��Ϣ���޸ı�
	 * @param evt
	 */
	private void tableClickedEvent(MouseEvent evt) {
		//���ѡ�е���
		int row = typeTable.getSelectedRow();
		//������Ϣ
		textId.setText(typeTable.getValueAt(row, 0).toString());
		textTypeName.setText(typeTable.getValueAt(row, 1).toString());
		String state = typeTable.getValueAt(row, 2).toString();
		if("����".equals(state)) {
			radioYes.setSelected(true);
		}else if("������".equals(state)) {
			radioNo.setSelected(true);
		}
		
	}
	/**
	 * 	��ѯ
	 * @param evt
	 */
	private void searchCileckEvent(ActionEvent evt) {
		String typeName = textSearchName.getText();
		//��ò�ѯ����� �����
		fillTypeTable(new BookType(typeName));
		
	}

	private void fillTypeTable(BookType bookType) {
		List<BookType> types = typeService.getBookTypeList(bookType);
		DefaultTableModel dtm = (DefaultTableModel) typeTable.getModel();
		//���
		dtm.setRowCount(0);
		for(BookType type:types) {
			Vector<Object> v = new Vector<>();
			v.add(type.getBtid());
			v.add(type.getBtname());
			if(type.getBtstate()==1) {
				v.add("����");
			}else if(type.getBtstate()==0) {
				v.add("������");
			}
			v.add(type.getBtstate());
			//���һ��
			dtm.addRow(v);
		}
		
	}
}
