package edu.learn.bms.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.learn.bms.projo.User;
import edu.learn.bms.service.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.UUID;
import java.awt.event.ActionEvent;

public class AddUserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textusername;
	private JTextField textpwd;
	private JTextField texttruename;
	private UserService userService = new UserService();

	/**
	 * Create the frame.
	 */
	public AddUserFrame() {
		setTitle("\u6DFB\u52A0\u7BA1\u7406\u5458");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(96, 60, 54, 15);
		contentPane.add(label);
		
		textusername = new JTextField();
		textusername.setBounds(164, 57, 106, 21);
		contentPane.add(textusername);
		textusername.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(96, 101, 54, 15);
		contentPane.add(label_1);
		
		textpwd = new JTextField();
		textpwd.setBounds(164, 98, 106, 21);
		contentPane.add(textpwd);
		textpwd.setColumns(10);
		
		JLabel label_2 = new JLabel("\u771F\u5B9E\u59D3\u540D\uFF1A");
		label_2.setBounds(96, 142, 65, 15);
		contentPane.add(label_2);
		
		texttruename = new JTextField();
		texttruename.setBounds(164, 139, 106, 21);
		contentPane.add(texttruename);
		texttruename.setColumns(10);
		
		JButton btnSubmit = new JButton("\u6DFB\u52A0");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//管理员添加
				addClickedEvent(evt);
			}
		});
		btnSubmit.setBounds(199, 187, 71, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(96, 187, 65, 23);
		contentPane.add(btnCancel);
	}
	/**
	 * 添加管理员
	 * @param evt
	 */
	private void addClickedEvent(ActionEvent evt) {
		String username = textusername.getText();
		String pwd = textpwd.getText();
		String truename = texttruename.getText();
		boolean isSuccess = userService.addUser(new User(UUID.randomUUID().toString(),username,pwd,truename,1));
		if(isSuccess) {
			JOptionPane.showMessageDialog(null, "添加成功");
			//退出添加
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "添加失败");
		}
		
	}
}
