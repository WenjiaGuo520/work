package edu.learn.bms.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import edu.learn.bms.utils.MyMouseListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	
	private static String currentuserid = "1";
	public static void setCurrentUser(String id) {
		currentuserid=id;
	}
	
	public static String getCurrentuserid() {
		return currentuserid;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private BookPanel bookPanel;
	private BookTypePanel btPanel;
	private SalePanel salePanel;
	private AddSalePanel addSalePanel;
	private PurchasePanel purPanel;
	private AddPurPanel addpurPanel;
	private UserPanel userPanel;
	private JPopupMenu popup;
	private JMenuItem item;
	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("\u4E66\u5E97\u7BA1\u7406\u7CFB\u7EDFv1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 574);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u56FE\u4E66\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem_9 = new JMenuItem("\u56FE\u4E66\u7BA1\u7406");
		menuItem_9.addActionListener(new ActionListener() {
			//图书管理界面
			public void actionPerformed(ActionEvent e) {
				bookPanel = BookPanel.createInstance();
				tabbedPane.add("图书信息维护",bookPanel);
				tabbedPane.setSelectedComponent(bookPanel);
				
				addMouseListenerToPanel(bookPanel);
			}
		});
		menu.add(menuItem_9);
		
		JMenuItem menuItem_10 = new JMenuItem("\u7C7B\u578B\u7BA1\u7406");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开类型管理界面
				btPanel = BookTypePanel.createInstance();
				tabbedPane.add("图书类型维护",btPanel);
				tabbedPane.setSelectedComponent(btPanel);
				
				addMouseListenerToPanel(btPanel);
			}
		});
		menu.add(menuItem_10);
		
		JMenu menu_1 = new JMenu("\u501F\u9605\u5F52\u8FD8");
		menu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "此功能未实现！！！！");
			}
		});
		
		menuBar.add(menu_1);
		
		JMenu menu_4 = new JMenu("\u501F\u4E66");
		menu_1.add(menu_4);
		
		JMenuItem menuItem_4 = new JMenuItem("\u501F\u4E66\u8BB0\u5F55");
		menu_4.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u501F\u4E66\u767B\u8BB0");
		menu_4.add(menuItem_5);
		
		JMenu menu_5 = new JMenu("\u5F52\u8FD8");
		menu_1.add(menu_5);
		
		JMenuItem menuItem_6 = new JMenuItem("\u5F52\u8FD8\u8BB0\u5F55");
		menu_5.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("\u5F52\u8FD8\u767B\u8BB0");
		menu_5.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("\u501F\u9605\u8BB0\u5F55");
		menu_1.add(menuItem_8);
		
		JMenu menu_2 = new JMenu("\u9500\u552E");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9500\u552E\u7BA1\u7406");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//销售管理
				salePanel = SalePanel.createInstance();
				tabbedPane.add("订单管理",salePanel);
				tabbedPane.setSelectedComponent(salePanel);
				
				addMouseListenerToPanel(salePanel);
			}
		});
		menu_2.add(menuItem_1);
		
		JMenuItem menuItem = new JMenuItem("\u521B\u5EFA\u8BA2\u5355");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建订单
				addSalePanel = AddSalePanel.createInstance();
				tabbedPane.add("创建销售订单",addSalePanel);
				tabbedPane.setSelectedComponent(addSalePanel);
				
				addMouseListenerToPanel(addSalePanel);
			}
		});
		menu_2.add(menuItem);
		
		JMenu menu_3 = new JMenu("\u8FDB\u8D27");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_12 = new JMenuItem("\u8FDB\u8D27\u7BA1\u7406");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//订货单
				purPanel = PurchasePanel.createInstance();
				tabbedPane.add("订货单维护",purPanel);
				tabbedPane.setSelectedComponent(purPanel);
				
				addMouseListenerToPanel(purPanel);
			}
		});
		menu_3.add(menuItem_12);
		
		JMenuItem menuItem_2 = new JMenuItem("\u521B\u5EFA\u8FDB\u8D27\u5355");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建订货单
				addpurPanel = AddPurPanel.createInstance();
				tabbedPane.add("创建进货单",addpurPanel);
				tabbedPane.setSelectedComponent(addpurPanel);
				
				addMouseListenerToPanel(addpurPanel);
			}
		});
		menu_3.add(menuItem_2);
		
		JMenu menu_6 = new JMenu("\u7BA1\u7406\u5458");
		menuBar.add(menu_6);
		
		JMenuItem menuItem_13 = new JMenuItem("\u7BA1\u7406\u5458\u7EF4\u62A4");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//管理员维护
				userPanel = UserPanel.createInstance();
				tabbedPane.add("管理员维护",userPanel);
				tabbedPane.setSelectedComponent(userPanel);
				
				addMouseListenerToPanel(userPanel);
			}
		});
		menu_6.add(menuItem_13);
		
		JMenu menu_7 = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		menuBar.add(menu_7);
		
		JMenuItem menuItem_14 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"感谢使用");
			}
		});
		menu_7.add(menuItem_14);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		popup = new JPopupMenu();
	    item = new JMenuItem("关闭当前窗口");
	     JMenuItem item2 = new JMenuItem("关闭所有窗口");
	    //addActionListener(item, tabbedPane ,tabbedPane.getSelectedComponent());
	    addActionListenerRemoveAll(item2);
	     
	     popup.add(item);
	     popup.add(item2);
	     
	}
	
	private void addActionListenerRemoveAll(JMenuItem item2) {
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				tabbedPane.removeAll();
			}
     });
	}

	/**
	 * 为item添加点击事件，从tabbedpane中删除panel
	 * @param item
	 * @param tabbedPane
	 */
	private void addActionListener(JMenuItem item,JTabbedPane tabbedPane,Component...component) {
		
		
		 item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					for(Component p : component) {
						close(p);
					}
	             }
				private void close(Component panel) {
					tabbedPane.remove(panel);
					item.removeActionListener(this);
				}
	     });
	}
	/**
	 * 给控件添加关闭事件
	 * @param panel
	 * @param item
	 */
	private void addMouseListenerToPanel(JPanel panel) {
		panel.addMouseListener(new MyMouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isMetaDown()) {
					addActionListener(item, tabbedPane, panel);
					popup.add(item);
					popup.show(e.getComponent(),
		                     e.getX(), e.getY());
				}
			}
		});
	}
}
