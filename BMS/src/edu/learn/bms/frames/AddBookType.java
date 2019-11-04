package edu.learn.bms.frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.learn.bms.projo.BookType;
import edu.learn.bms.service.BookTypeService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class AddBookType extends JFrame {
	private JTextField texttypeId;
	private JTextField texttypeName;
	
	private BookTypeService typeService = new BookTypeService();

	/**
	 * Create the frame.
	 */
	public AddBookType() {
		/*setResizable(true);
		setIconifiable(true);
		setClosable(true)*/;
		setTitle("\u589E\u52A0\u56FE\u4E66\u7C7B\u578B");
		setBounds(90, 100, 389, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7C7B\u578B\u7F16\u53F7");
		label.setBounds(84, 44, 72, 15);
		getContentPane().add(label);
		
		texttypeId = new JTextField();
		texttypeId.setBounds(176, 41, 104, 21);
		getContentPane().add(texttypeId);
		texttypeId.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7C7B\u578B\u540D\u79F0");
		label_1.setBounds(84, 99, 72, 15);
		getContentPane().add(label_1);
		
		texttypeName = new JTextField();
		texttypeName.setBounds(176, 96, 104, 21);
		getContentPane().add(texttypeName);
		texttypeName.setColumns(10);
		
		JButton btnSubmit = new JButton("\u63D0\u4EA4");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//增加表单提交事件
				submitClickedEvent(evt);
			}
		});
		btnSubmit.setBounds(217, 158, 64, 23);
		getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("\u91CD\u7F6E");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//重置
				texttypeId.setText("");
				texttypeName.setText("");
			}
		});
		btnCancel.setBounds(84, 158, 57, 23);
		getContentPane().add(btnCancel);
	}
	
	/**
	 *	 增加图书类型
	 * @param evt
	 */
	private void submitClickedEvent(ActionEvent evt) {
		String id = texttypeId.getText();
		String name = texttypeName.getText();
		boolean isSuccess = typeService.add(new BookType(id,name,1));
		if(isSuccess) {
			JOptionPane.showMessageDialog(null, "增加成功！");
		}else {
			JOptionPane.showMessageDialog(null, "参数错误，增加失败！");
		}
		//重置
		texttypeId.setText("");
		texttypeName.setText("");
	}

}
