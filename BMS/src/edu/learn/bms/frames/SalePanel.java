package edu.learn.bms.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.learn.bms.dao.SaleDao;
import edu.learn.bms.projo.Sale;
import edu.learn.bms.projo.SaleDetails;
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

public class SalePanel extends JPanel {
	private JTable saleTable;
	private JComboBox cbSaleState;
	
	private SaleService saleService = new SaleService();
	private static SalePanel salePanel = new SalePanel();
	public static SalePanel createInstance() {
		return salePanel;
	}
			
	/**
	 * Create the panel.
	 */
	private SalePanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 74, 617, 311);
		add(scrollPane);
		
		saleTable = new JTable();
		saleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//表格点击
				saleTableClickedEvent(e);
			}
		});
		
		saleTable.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(saleTable);
		
		JLabel label = new JLabel("\u72B6\u6001");
		label.setBounds(40, 45, 54, 15);
		add(label);
		
		cbSaleState = new JComboBox();
		cbSaleState.setModel(new DefaultComboBoxModel(new String[] {"--\u8BF7\u9009\u62E9--", "\u5DF2\u521B\u5EFA", "\u5DF2\u51FA\u5E93"}));
		cbSaleState.setBounds(75, 41, 99, 23);
		add(cbSaleState);
		
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
		fillSaleTable(new Sale());
	}
	/**
	 * 	订单查询
	 * @param e
	 */
	private void searchClickedEvent(ActionEvent e) {
		String click = cbSaleState.getSelectedItem().toString();
		Integer state = 0;
		if("已创建".equals(click)) {
			state=0;
		}else if("已出库".equals(click)) {
			state=1;
		}else {
			state=-1;
		}
		fillSaleTable(new Sale(state));
	}

	/**
	 * 	如果状态是已创建，显示一个入库按钮，否则只显示销售明细
	 * @param e
	 */
	private void saleTableClickedEvent(MouseEvent e) {
		//构建明细列表
		int row = saleTable.getSelectedRow();
		String saleid = saleTable.getValueAt(row, 0).toString();
		List<SaleDetails> details = saleService.getSaleDetailsBySaleid(saleid);
		List<Vector<Object>> list = new ArrayList<>();
		for(SaleDetails sd : details) {
			Vector<Object> v = new Vector<>();
			v.add(sd.getSdid());
			v.add(sd.getSaleid());
			v.add(sd.getBookid());
			v.add(sd.getSdnum());
			v.add(sd.getSdprice());
			list.add(v);
		}
		
		SaleDetailsFrame detailFrame = new SaleDetailsFrame(list);
		detailFrame.setLocationRelativeTo(null);
		
		String state = saleTable.getValueAt(row, 3).toString();
		if("已创建".equals(state)) {
			SaleDetailsFrame.btnSale.setVisible(true);
		}else {
			SaleDetailsFrame.btnSale.setVisible(false);
		}
		detailFrame.setVisible(true);
	}

	/**
	 * 	表格填充
	 * @param sale
	 */
	private void fillSaleTable(Sale sale) {
		DefaultTableModel dtm = (DefaultTableModel) saleTable.getModel();
		dtm.setRowCount(0);
		List<Sale> list = saleService.getList(sale);
		for(Sale s:list) {
			Vector<Object> v = new Vector<>();
			v.add(s.getSaleid());
			v.add(s.getUser());
			v.add(s.getSaletime());
			Integer salestate = s.getSalestate();
			if(salestate==0) {
				v.add("已创建");
			}else if(salestate==1) {
				v.add("已出库");
			}
			dtm.addRow(v);
		}
		
	}
}
