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
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textusername;
	private UserService userService = new UserService();
	private JPasswordField textpwd;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = new LogInFrame();
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
	public LogInFrame() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(109, 52, 54, 15);
		contentPane.add(label);
		
		textusername = new JTextField();
		textusername.setBounds(160, 49, 122, 21);
		contentPane.add(textusername);
		textusername.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setBounds(109, 108, 54, 15);
		contentPane.add(label_1);
		
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//登录
				loginClickedEvent();
			}
		});
		btnLogin.setBounds(219, 170, 63, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("\u8FD4\u56DE");
		btnCancel.setBounds(103, 170, 60, 23);
		contentPane.add(btnCancel);
		
		textpwd = new JPasswordField();
		textpwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//回车事件
				enterClickedEvent(e);
			}
		});
		textpwd.setBounds(160, 105, 122, 21);
		contentPane.add(textpwd);
	}
	/**
	 * 回车登陆
	 * @param e
	 */
	private void enterClickedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			loginClickedEvent();
		}
	}

	/**
	 * 登录
	 * @param evt
	 */
	private void loginClickedEvent() {
		String username = textusername.getText();
		String pwd = textpwd.getText();
		if(username.equals("") || pwd.equals("")) {
			JOptionPane.showMessageDialog(null, "账户名或密码不能为空，请重新输入！");
			return;
		}
		int loginState = userService.checkLogin(new User(username,pwd));
		if(loginState==0) {
			JOptionPane.showMessageDialog(null, "账户名不存在，请核对后再试！");
		}else if(loginState==1) {
			JOptionPane.showMessageDialog(null, "密码错误，请核对后再试！");
			textpwd.setText("");
			textpwd.grabFocus();
		}else {
			MainMenu.setCurrentUser(userService.getUser(username, pwd).getUserid());
			MainMenu mainMenu = new MainMenu();
			mainMenu.setLocationRelativeTo(null);
			mainMenu.setVisible(true);
			this.dispose();
		}
	}
}
