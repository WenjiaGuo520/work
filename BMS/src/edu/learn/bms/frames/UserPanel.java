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

import edu.learn.bms.projo.User;
import edu.learn.bms.service.UserService;
import javafx.scene.control.TextInputDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserPanel extends JPanel {
	private JTable userTable;
	private JTextField textsearchName;
	private JTextField texttrueName;
	private JTextField textusername;
	private JTextField textpwd;
	private JRadioButton radioYes;
	private JRadioButton radioNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private UserService userService = new UserService();
	private static UserPanel userPanel = new UserPanel();
	private JTextField textId;
	public static UserPanel createInstance() {
		return userPanel;
	}
	/**
	 * Create the panel.
	 */
	private UserPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 100, 414, 186);
		add(scrollPane);
		
		userTable = new JTable();
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				//table点击事件
				userTableClickedEvent(evt);
			}
		});
		userTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"userid", "\u8D26\u53F7", "\u5BC6\u7801", "\u59D3\u540D", "\u8D26\u53F7\u72B6\u6001"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		userTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		userTable.getColumnModel().getColumn(0).setMinWidth(0);
		userTable.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(userTable);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(108, 74, 54, 15);
		add(label);
		
		textsearchName = new JTextField();
		textsearchName.setBounds(143, 69, 97, 21);
		add(textsearchName);
		textsearchName.setColumns(10);
		
		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//查询
				searchClickedEvent(evt);
			}
		});
		btnSearch.setBounds(250, 67, 66, 23);
		add(btnSearch);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setBounds(108, 317, 54, 15);
		add(label_1);
		
		texttrueName = new JTextField();
		texttrueName.setBounds(154, 314, 66, 21);
		add(texttrueName);
		texttrueName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u8D26\u53F7\uFF1A");
		label_2.setBounds(250, 317, 54, 15);
		add(label_2);
		
		textusername = new JTextField();
		textusername.setBounds(292, 314, 66, 21);
		add(textusername);
		textusername.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5BC6\u7801\uFF1A");
		label_3.setBounds(379, 317, 54, 15);
		add(label_3);
		
		textpwd = new JTextField();
		textpwd.setBounds(430, 314, 77, 21);
		add(textpwd);
		textpwd.setColumns(10);
		
		JLabel label_4 = new JLabel("\u8D26\u53F7\u72B6\u6001\uFF1A");
		label_4.setBounds(108, 365, 77, 15);
		add(label_4);
		
		radioYes = new JRadioButton("\u53EF\u7528");
		buttonGroup.add(radioYes);
		radioYes.setBounds(191, 361, 54, 23);
		add(radioYes);
		
		radioNo = new JRadioButton("\u4E0D\u53EF\u7528");
		buttonGroup.add(radioNo);
		radioNo.setBounds(252, 361, 66, 23);
		add(radioNo);
		
		JButton btnEdit = new JButton("\u4FEE\u6539");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改账户信息
				editClieckedEvent(e);
			}
		});
		btnEdit.setBounds(441, 361, 66, 23);
		add(btnEdit);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(333, 362, 0, 0);
		add(textId);
		textId.setColumns(10);
		
		JButton btnAdd = new JButton("\u589E\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AddUserFrame addUserFrame = new AddUserFrame();
				addUserFrame.setLocationRelativeTo(null);
				addUserFrame.setVisible(true);
			}
		});
		btnAdd.setBounds(448, 67, 59, 23);
		add(btnAdd);
		//初始化表格
		fillUserTable(new User());
	}
	
	/**
	 * 修改用户信息
	 * @param e
	 */
	private void editClieckedEvent(ActionEvent e) {
		String id = textId.getText();
		String username = textusername.getText();
		String pwd = textpwd.getText();
		String truename = texttrueName.getText();
		Integer state=0;
		if(radioYes.isSelected()) {
			state=1;
		}
		boolean flag = userService.updateUser(new User(id, username, pwd,truename, state));
		if(flag) {
			JOptionPane.showMessageDialog(null, "修改成功");
			fillUserTable(new User());
		}else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
	}
	/**
	 * 查询
	 * @param evt
	 */
	protected void searchClickedEvent(ActionEvent evt) {
		String truename = textsearchName.getText();
		User user = new User();
		user.setUsertruename(truename);
		fillUserTable(user);
	}
	/**
	 * 点击一行，将这一行的数据写到编辑表单
	 * @param evt
	 */
	protected void userTableClickedEvent(MouseEvent evt) {
		int row = userTable.getSelectedRow();
		textId.setText(userTable.getValueAt(row, 0).toString());
		textusername.setText(userTable.getValueAt(row, 1).toString());
		textpwd.setText(userTable.getValueAt(row, 2).toString());
		texttrueName.setText(userTable.getValueAt(row, 3).toString());
		String state = userTable.getValueAt(row, 4).toString();
		if("可用".equals(state)) {
			radioYes.setSelected(true);
		}else if("不可用".equals(state)) {
			radioNo.setSelected(true);
		}
		
	}
	/**
	 * 初始化表格
	 * @param user
	 */
	private void fillUserTable(User user) {
		List<User> list = userService.getUserList(user);
		
		DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
		dtm.setRowCount(0);
		for(User u:list) {
			Vector<Object> v = new Vector<>();
			v.add(u.getUserid());
			v.add(u.getUsername());
			v.add(u.getUserpwd());
			v.add(u.getUsertruename());
			if(u.getUserstate().equals(1)) {
				v.add("可用");
			}else if(u.getUserstate().equals(0)){
				v.add("不可用");
			}
			dtm.addRow(v);
		}
	}

}
