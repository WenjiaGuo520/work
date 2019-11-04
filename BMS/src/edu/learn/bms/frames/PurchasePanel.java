package edu.learn.bms.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.learn.bms.dao.SaleDao;
import edu.learn.bms.projo.Purchase;
import edu.learn.bms.projo.Purchasedetails;
import edu.learn.bms.projo.Sale;
import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.service.PurDetailService;
import edu.learn.bms.service.PurchaseService;
import edu.learn.bms.service.SaleService;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchasePanel extends JPanel {
	private JTable purTable;
	private JComboBox cbPurState;
	
	private PurchaseService purService = new PurchaseService();
	private PurDetailService detailService = new PurDetailService();
	private static PurchasePanel salePanel = new PurchasePanel();
	public static PurchasePanel createInstance() {
		return salePanel;
	}
			
	/**
	 * Create the panel.
	 */
	private PurchasePanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 74, 617, 311);
		add(scrollPane);
		
		purTable = new JTable();
		purTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//表格点击
				purTableClickedEvent(e);
			}
		});
		
		purTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BA2\u5355\u7F16\u53F7", "\u521B\u5EFA\u4EBA", "\u521B\u5EFA\u65F6\u95F4", "\u8BA2\u5355\u72B6\u6001"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(purTable);
		
		JLabel label = new JLabel("\u72B6\u6001");
		label.setBounds(40, 45, 54, 15);
		add(label);
		
		cbPurState = new JComboBox();
		cbPurState.setModel(new DefaultComboBoxModel(new String[] {"-\u8BF7\u9009\u62E9-", "\u672A\u5165\u5E93", "\u5DF2\u5165\u5E93", "\u653E   \u5F03"}));
		cbPurState.setBounds(75, 41, 99, 23);
		add(cbPurState);
		
		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//订单查询
				searchClickedEvent(e);
			}
		});
		btnSearch.setBounds(184, 41, 66, 23);
		add(btnSearch);
		//填充表格
		fillSaleTable(new Purchase());
	}
	/**
	 * 	订单查询
	 * @param e
	 */
	private void searchClickedEvent(ActionEvent e) {
		String click = cbPurState.getSelectedItem().toString();
		Integer state = 0;
		if("未入库".equals(click)) {
			state=0;
		}else if("已入库".equals(click)) {
			state=1;
		}else if("放   弃".equals(click)){
			state=2;
		}else {
			state=-1;
		}
		fillSaleTable(new Purchase(state));
	}

	/**
	 * 	如果状态是已创建，显示一个入库按钮，否则只显示销售明细
	 * @param e
	 */
	private void purTableClickedEvent(MouseEvent e) {
		//构建明细列表
		int row = purTable.getSelectedRow();
		String purid = purTable.getValueAt(row, 0).toString();
		List<Purchasedetails> details = detailService.getListByPurid(purid);
		List<Vector<Object>> list = new ArrayList<>();
		for(Purchasedetails pd : details) {
			Vector<Object> v = new Vector<>();
			v.add(pd.getPdid());
			v.add(pd.getPurid());
			v.add(pd.getBookname());
			v.add(pd.getPdplannum());
			v.add(0);
			v.add(pd.getPdprice());
			v.add(pd.getBookid());
			list.add(v);
		}
		
		PurDetailsFrame detailFrame = new PurDetailsFrame(list);
		detailFrame.setLocationRelativeTo(null);
		
		String state = purTable.getValueAt(row, 3).toString();
		if("未入库".equals(state)) {
			PurDetailsFrame.btnSale.setVisible(true);
		}else {
			PurDetailsFrame.btnSale.setVisible(false);
		}
		detailFrame.setVisible(true);
	}

	/**
	 * 	表格填充
	 * @param sale
	 */
	private void fillSaleTable(Purchase pur) {
		DefaultTableModel dtm = (DefaultTableModel) purTable.getModel();
		dtm.setRowCount(0);
		List<Purchase> list = purService.getList(pur);
		for(Purchase p:list) {
			Vector<Object> v = new Vector<>();
			v.add(p.getPurid());
			v.add(p.getUserid());
			v.add(p.getPurdate());
			Integer state = p.getPurstate();
			if(state==0) {
				v.add("未入库");
			}else if(state==1) {
				v.add("已入库");
			}else if(state==2) {
				v.add("已放弃");
			}
			dtm.addRow(v);
		}
		
	}
}
