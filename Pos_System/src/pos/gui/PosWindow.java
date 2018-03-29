package pos.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import pos.dao.MarketMemberDAO;
import pos.dao.ProductDAO;
import pos.dao.SaleDAO;
import pos.dao.SaleListDAO;
import pos.dao.WarehouseDAO;
import pos.vo.MarketMemberVo;
import pos.vo.ProductVo;
import pos.vo.SaleListVo;
import pos.vo.SaleVo;
import pos.vo.WarehouseVo;

public class PosWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	JTabbedPane jtab = new JTabbedPane();

	JPanel sale = new JPanel();
	JPanel panSaleLeft = new JPanel();
	JPanel panSaleRight = new JPanel();
	JPanel panSaleTop = new JPanel();
	JPanel panSaleBot = new JPanel();
	DefaultTableModel modelItem = null;
	JTable tableItem = null;
	DefaultTableModel modelCart = null;
	JTable tableCart = null;
	JPanel panInputNum = new JPanel();
	JPanel showMem = new JPanel();
	JPanel panSumPrice = new JPanel();
	JPanel panUsePoint = new JPanel();
	JPanel panPayPrice = new JPanel();
	JTextField txtMemNum = new JTextField(21);
	JButton btnCheck = new JButton("��ȸ");
	JButton btnApply = new JButton("����");
	JButton btnPay = new JButton("����");
	JTextField showMemName = new JTextField(10);
	JTextField showMemPoint = new JTextField(10);
	JTextField showSumPrice = new JTextField("0", 27);
	JTextField usePoint = new JTextField(19);
	JTextField showPayPrice = new JTextField("0", 27);

	JPanel regPro = new JPanel();
	JPanel panProTop = new JPanel();
	JPanel panProName = new JPanel();
	JPanel panProPrice = new JPanel();
	JPanel panProBtn = new JPanel();
	JPanel panProUpd = new JPanel();
	JTextField txtProName = new JTextField(10);
	JTextField txtProPrice = new JTextField(10);
	JButton btnProReg = new JButton("���");
	JButton btnProCan = new JButton("���");
	JButton btnProSear = new JButton("�˻�");
	JButton btnProUpd = new JButton("����");
	JButton btnProDel = new JButton("����");
	DefaultTableModel modelPro = null;
	JTable tablePro = null;

	JPanel regMem = new JPanel();
	JPanel panMemTop = new JPanel();
	JPanel panMemName = new JPanel();
	JPanel panMemPhone = new JPanel();
	JPanel panMemAddr = new JPanel();
	JPanel panMemBtn = new JPanel();
	JPanel panMemUpd = new JPanel();
	JLabel laMemName = new JLabel("ȸ���̸�");
	JLabel laMemPhone = new JLabel("��ȭ��ȣ");
	JLabel laMemAddr = new JLabel("ȸ���ּ�");
	JTextField txtMemName = new JTextField(10);
	JTextField txtMemPhone = new JTextField(10);
	JTextField txtMemAddr = new JTextField(10);
	JButton btnMemReg = new JButton("���");
	JButton btnMemCan = new JButton("���");
	JButton btnMemSear = new JButton("�˻�");
	JButton btnMemUpd = new JButton("����");
	JButton btnMemDel = new JButton("����");
	DefaultTableModel modelMem = null;
	JTable tableMem = null;

	JPanel regWare = new JPanel();
	JPanel panWareTop = new JPanel();
	JPanel panWareName = new JPanel();
	JPanel panWarePhone = new JPanel();
	JPanel panWareAddr = new JPanel();
	JPanel panWareBtn = new JPanel();
	JPanel panWareUpd = new JPanel();
	JTextField txtWareProNum = new JTextField(10);
	JTextField txtWareCnt = new JTextField(10);
	JTextField txtWarePrice = new JTextField(10);
	JButton btnWareReg = new JButton("���");
	JButton btnWareCan = new JButton("���");
	JButton btnWareSear = new JButton("�˻�");
	JButton btnWareUpd = new JButton("����");
	JButton btnWareDel = new JButton("����");
	DefaultTableModel modelWare = null;
	JTable tableWare = null;

	JPanel saleList = new JPanel();
	JPanel panDate = new JPanel();
	JPanel panSalesPrice = new JPanel();
	JTextField startDate = new JTextField(15);
	JTextField endDate = new JTextField(15);
	JButton btnSearch = new JButton("��ȸ");
	JButton btnRefund = new JButton("ȯ��");
	DefaultTableModel modelSaleList = null;
	JTable tableSaleList = null;
	JTextField txtSalesPrice = new JTextField("0", 25);

	JPanel saleItemList = new JPanel();
	JPanel panSaleNum = new JPanel();
	JTextField txtSaleNum = new JTextField(15);
	JButton btnSaleNum = new JButton("��ȸ");
	DefaultTableModel modelSaleItemList = null;
	JTable tableSaleItemList = null;

	MarketMemberDAO memDAO = new MarketMemberDAO(); 
	ProductDAO proDAO = new ProductDAO();
	SaleDAO saleDAO = new SaleDAO();
	SaleListDAO saleListDAO = new SaleListDAO();
	WarehouseDAO wareDAO = new WarehouseDAO();

	// ���� ������ ����
	public PosWindow() {
		super("�߾Ӹ�Ʈ");

		setMenu();
		setRegMem();
		setRegPro();
		setRegWare();
		setSaleList();
		setSaleItemList();
		setSale();

		setSize(930, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// ��ǰ�Ǹ� ȭ�� �ʱ�ȭ
	public void initSale() {
		txtMemNum.setText("");
		showMemName.setText("");
		showMemPoint.setText("");
		showSumPrice.setText("0");
		usePoint.setText("0");
		showPayPrice.setText("0");

		for (int i = modelItem.getRowCount() - 1; i >= 0; i--) {
			modelItem.removeRow(i);
		}

		for (int i = modelCart.getRowCount() - 1; i >= 0; i--) {
			modelCart.removeRow(i);
		}

		ArrayList<ProductVo> voList = proDAO.showAll();

		if (voList != null) {
			for (int i = 0; i < voList.size(); i++) {
				ProductVo vo = voList.get(i);
				Object[] rowData = { vo.getpNum(), vo.getpName(), vo.getpPrice(), vo.getpStock() };
				modelItem.addRow(rowData);
			}
		}
	}

	// ��ǰ�Ǹ� ȭ�鱸��
	public void setSale() {
		String[] itemColunm = { "��ǰ��ȣ", "��ǰ�̸�", "��ǰ����", "���" };
		modelItem = new DefaultTableModel(itemColunm, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableItem = new JTable(modelItem);
		panSaleLeft.setLayout(new BorderLayout());
		panSaleLeft.add(new JScrollPane(tableItem), BorderLayout.CENTER);

		String[] cartColunm = { "��ǰ��ȣ", "��ǰ�̸�", "��ǰ����", "����" };
		modelCart = new DefaultTableModel(cartColunm, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCart = new JTable(modelCart);

		panInputNum.add(new JLabel("ȸ����ȣ"));
		panInputNum.add(txtMemNum);
		panInputNum.add(btnCheck);
		showMem.add(new JLabel("ȸ���̸�"));
		showMem.add(showMemName);
		showMem.add(new JLabel("��������Ʈ"));
		showMem.add(showMemPoint);
		panSumPrice.add(new JLabel("���� �հ�"));
		showSumPrice.setHorizontalAlignment(JTextField.RIGHT);
		panSumPrice.add(showSumPrice);
		panUsePoint.add(new JLabel("����� ����Ʈ"));
		usePoint.setHorizontalAlignment(JTextField.RIGHT);
		panUsePoint.add(usePoint);
		panUsePoint.add(btnApply);
		panPayPrice.add(new JLabel("���� �ݾ�"));
		showPayPrice.setHorizontalAlignment(JTextField.RIGHT);
		panPayPrice.add(showPayPrice);

		panSaleBot.setLayout(new GridLayout(6, 1));
		panSaleBot.add(panInputNum);
		panSaleBot.add(showMem);
		panSaleBot.add(panSumPrice);
		panSaleBot.add(panUsePoint);
		panSaleBot.add(panPayPrice);
		panSaleBot.add(btnPay);

		panSaleRight.setLayout(new BorderLayout());
		panSaleRight.add(new JScrollPane(tableCart), BorderLayout.NORTH);
		panSaleRight.add(panSaleBot, BorderLayout.SOUTH);

		sale.setLayout(new BorderLayout());
		sale.add(panSaleLeft, BorderLayout.WEST);
		sale.add(panSaleRight, BorderLayout.EAST);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCheck) {
					setBuyMember();
				} else if (e.getSource() == btnApply) {
					payPrice();
				} else {
					pay();
				}
			}
		};

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == tableItem) {
					addCart();
				} else {
					deleteCart();
				}
			}
		};

		tableItem.addMouseListener(mouseAdapter);
		tableCart.addMouseListener(mouseAdapter);

		btnCheck.addActionListener(listener);
		btnApply.addActionListener(listener);
		btnPay.addActionListener(listener);

		initSale();
	}

	public void addCart() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "���� ��Ͽ� �߰��� ���� �Է�");
		if ("".equals(num)) {
			JOptionPane.showMessageDialog(PosWindow.this, "������ �Է����ּ���");
		} else if (num != null) {
			int pNum = Integer.parseInt(String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 0)));
			String name = String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 1));
			int price = Integer.parseInt(String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 2)));
			int stock = Integer.parseInt(String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 3)));

			int cnt = Integer.parseInt(num);
			if (cnt > stock) {
				JOptionPane.showMessageDialog(PosWindow.this, "��ǰ�� ��� �̻� ������ �� �����ϴ�");
				addCart();
				return;
			} else {
				Object[] rowData = { pNum, name, price, cnt };
				modelCart.addRow(rowData);
				sumPrice();
			}
		}
	}

	public void deleteCart() {
		int cho = JOptionPane.showConfirmDialog(PosWindow.this, "��ǰ�� �����Ͻðڽ��ϱ�?");
		if (cho == JOptionPane.OK_OPTION) {
			modelCart.removeRow(tableCart.getSelectedRow());
		}
		sumPrice();
	}

	public int sumPrice() {
		int sumPrice = 0;

		for (int i = 0; i < modelCart.getRowCount(); i++) {
			int price = Integer.parseInt(String.valueOf(modelCart.getValueAt(i, 2)));
			int stock = Integer.parseInt(String.valueOf(modelCart.getValueAt(i, 3)));

			sumPrice += (price * stock);
		}

		showSumPrice.setText(String.valueOf(sumPrice));
		showPayPrice.setText(String.valueOf(sumPrice));
		return sumPrice;
	}

	public void payPrice() {
		if ("".equals(usePoint.getText())) {
			JOptionPane.showMessageDialog(PosWindow.this, "����� ����Ʈ�� �Է����ּ���");
		} else {
			int usingPoint = Integer.parseInt(usePoint.getText());
			int havingPoint = Integer.parseInt(showMemPoint.getText());

			if (usingPoint > havingPoint) {
				JOptionPane.showMessageDialog(PosWindow.this, "���� ����Ʈ �̻� ����� �� �����ϴ�");
			} else {
				String payPrice = String.valueOf(sumPrice() - usingPoint);
				showPayPrice.setText(payPrice);
			}
		}
	}

	public void setBuyMember() {
		showMemName.setText("");
		showMemPoint.setText("");
		if ("".equals(txtMemNum.getText())) {
			JOptionPane.showMessageDialog(PosWindow.this, "ȸ����ȣ�� �Է��ϼ���");
		} else {
			MarketMemberVo vo = memDAO.search(Integer.parseInt(txtMemNum.getText()));
			if (vo != null) {
				showMemName.setText(vo.getmName());
				showMemPoint.setText(String.valueOf(vo.getmPoint()));
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "ȸ����ȣ�� Ȯ�����ּ���");
			}
		}
	}

	public void pay() {
		int n = -1;
		int mNum = Integer.parseInt(txtMemNum.getText());
		int price = Integer.parseInt(showPayPrice.getText());
		int point = (int) (price * 0.03);

		MarketMemberVo vo = memDAO.search(mNum);
		int mPoint = vo.getmPoint() - Integer.parseInt(usePoint.getText());
		memDAO.update(new MarketMemberVo(vo.getmNum(), vo.getmName(), vo.getmPhone(), vo.getmAddr(), mPoint,
				vo.getmHiredate()));
		n = saleDAO.insert(new SaleVo(0, mNum, price, point, null));

		if (n > 0) {
			n = saleDAO.search();

			for (int i = 0; i < modelCart.getRowCount(); i++) {
				int saNum = n;
				int pNum = Integer.parseInt(String.valueOf(modelCart.getValueAt(i, 0)));
				int slCnt = Integer.parseInt(String.valueOf(modelCart.getValueAt(i, 3)));

				saleListDAO.insert(new SaleListVo(0, saNum, pNum, slCnt));
			}

			JOptionPane.showMessageDialog(PosWindow.this, "���� �Ϸ�");

			initSale();
		} else {
			JOptionPane.showMessageDialog(PosWindow.this, "���� ����");
		}
	}

	// ��ü �Ǳ���
	public void setMenu() {
		jtab.add("��ǰ�Ǹ�", sale);
		jtab.add("��ǰ����", regPro);
		jtab.add("ȸ������", regMem);
		jtab.add("�԰����", regWare);
		jtab.add("�Ǹų���", saleList);
		jtab.add("�ǸŻ�ǰ����", saleItemList);
		jtab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				switch (jtab.getSelectedIndex()) {
				case 0:
					initSale();
					break;
				case 1:
					initRegPro();
					break;
				case 2:
					initRegMem();
					break;
				case 3:
					initRegWare();
					break;
				case 4:
					initSaleList();
					break;
				case 5:
					initSaleItemList();
					break;
				}
			}
		});

		add(jtab);
	}

	// ��ǰ�Ǹ� ȭ�鱸�� �ʱ�ȭ
	public void initSaleList() {
		startDate.setText("");
		endDate.setText("");
		int tot = 0;

		for (int i = modelSaleList.getRowCount() - 1; i >= 0; i--) {
			modelSaleList.removeRow(i);
		}

		ArrayList<SaleVo> voList = saleDAO.showAll();

		if (voList != null) {
			for (int i = 0; i < voList.size(); i++) {
				SaleVo vo = voList.get(i);
				Object[] rowData = { vo.getSaNum(), vo.getmNum(), vo.getSaPrice(), vo.getSaPoint(), vo.getSaDate() };
				modelSaleList.addRow(rowData);

				tot += Integer.parseInt(String.valueOf(vo.getSaPrice()));
			}
		}

		txtSalesPrice.setText(String.valueOf(tot));
	}

	// ��ǰ�Ǹ� ȭ�鱸��
	public void setSaleList() {
		panDate.add(new JLabel("�Ⱓ�˻� : ������(ex 2018-01-01)"));
		panDate.add(startDate);
		panDate.add(new JLabel("~ ������(ex 2018-01-01)"));
		panDate.add(endDate);
		panDate.add(btnSearch);
		panDate.add(btnRefund);
		String[] saleListColumn = { "�ǸŹ�ȣ", "ȸ����ȣ", "�ǸŰ���", "�߻�����Ʈ", "�Ǹų�¥" };
		modelSaleList = new DefaultTableModel(saleListColumn, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableSaleList = new JTable(modelSaleList);

		panSalesPrice.add(new JLabel("���� ��ȸ�� �Ⱓ ���� : "));
		txtSalesPrice.setHorizontalAlignment(JTextField.RIGHT);
		panSalesPrice.add(txtSalesPrice);

		saleList.setLayout(new BorderLayout());
		saleList.add(panDate, BorderLayout.NORTH);
		saleList.add(new JScrollPane(tableSaleList), BorderLayout.CENTER);
		saleList.add(panSalesPrice, BorderLayout.SOUTH);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSearch) {
					if ("".equals(startDate.getText()) || "".equals(endDate.getText())) {
						JOptionPane.showMessageDialog(PosWindow.this, "��� �Ⱓ�� �Է����ּ���.");
					} else {
						SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
						try {
							Date start = form.parse(startDate.getText());
							Date end = form.parse(endDate.getText());

							if (start.compareTo(end) > 0) {
								JOptionPane.showMessageDialog(PosWindow.this, "�Ⱓ�� Ȯ�����ּ���.");
							} else {
								int tot = 0;
								ArrayList<SaleVo> voList = saleDAO.search(startDate.getText(), endDate.getText());

								if (voList != null) {
									for (int i = modelSaleList.getRowCount() - 1; i >= 0; i--) {
										modelSaleList.removeRow(i);
									}

									for (int i = 0; i < voList.size(); i++) {
										SaleVo vo = voList.get(i);
										Object[] rowData = { vo.getSaNum(), vo.getmNum(), vo.getSaPrice(),
												vo.getSaPoint(), vo.getSaDate() };
										modelSaleList.addRow(rowData);
										tot += Integer.parseInt(String.valueOf(vo.getSaPrice()));
									}
								}

								txtSalesPrice.setText(String.valueOf(tot));
							}
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(PosWindow.this, "��¥ ������ �ٽ� Ȯ�����ּ���.");
						}
					}
				} else { // ȯ��ó��
					int num = Integer
							.parseInt(String.valueOf(modelSaleList.getValueAt(tableSaleList.getSelectedRow(), 0)));
					int n = saleDAO.delete(num);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "ȯ��ó���� �Ϸ�Ǿ����ϴ�");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "ȯ��ó���� �����Ͽ����ϴ�");
					}

					initSaleList();
				}
			}
		};

		btnSearch.addActionListener(listener);
		btnRefund.addActionListener(listener);
	}

	// ��ǰ�Ǹų��� ȭ�鱸��
	public void setSaleItemList() {
		panSaleNum.add(new JLabel("�ǸŹ�ȣ"));
		panSaleNum.add(txtSaleNum);
		panSaleNum.add(btnSaleNum);
		String[] saleItemListColumn = { "�Ǹų�����ȣ", "�ǸŹ�ȣ", "��ǰ�̸�", "�Ǹż���" };
		modelSaleItemList = new DefaultTableModel(saleItemListColumn, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		tableSaleItemList = new JTable(modelSaleItemList);

		saleItemList.setLayout(new BorderLayout());
		saleItemList.add(panSaleNum, BorderLayout.NORTH);
		saleItemList.add(new JScrollPane(tableSaleItemList), BorderLayout.CENTER);

		btnSaleNum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searSaleItemList();
			}
		});
	}

	public void initSaleItemList() {
		txtSaleNum.setText("");

		for (int i = modelSaleItemList.getRowCount() - 1; i >= 0; i--) {
			modelSaleItemList.removeRow(i);
		}

		ArrayList<SaleListVo> voList = saleListDAO.showAll();

		if (voList != null) {
			for (int i = 0; i < voList.size(); i++) {
				SaleListVo vo = voList.get(i);
				Object[] rowData = { vo.getSlNum(), vo.getSaNum(), vo.getpNum(), vo.getSlCnt() };
				modelSaleItemList.addRow(rowData);
			}
		}
	}

	public void searSaleItemList() {
		if ("".equals(txtSaleNum.getText())) {
			JOptionPane.showMessageDialog(PosWindow.this, "�ǸŹ�ȣ�� �Է����ּ���");
		} else {
			int num = Integer.parseInt(txtSaleNum.getText());
			ArrayList<SaleListVo> voList = saleListDAO.search(num);

			if (voList != null) {
				for(int i=modelSaleItemList.getRowCount()-1;i>=0;i--) {
					modelSaleItemList.removeRow(i);
				}
				
				for (int i = 0; i < voList.size(); i++) {
					SaleListVo vo = voList.get(i);
					Object[] rowData = { vo.getSlNum(), vo.getSaNum(), vo.getpNum(), vo.getSlCnt() };
					modelSaleItemList.addRow(rowData);
				}
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "�ش� �Ǹų����� �����ϴ�");
			}
		}
	}

	// �԰���� ȭ�� �ʱ�ȭ
	public void initRegWare() {
		txtWareProNum.setText("");
		txtWareCnt.setText("");
		txtWarePrice.setText("");

		for (int i = modelWare.getRowCount() - 1; i >= 0; i--) {
			modelWare.removeRow(i);
		}

		ArrayList<WarehouseVo> voList = wareDAO.showAll();

		if (voList != null) {
			for (int i = 0; i < voList.size(); i++) {
				WarehouseVo vo = voList.get(i);
				Object[] rowData = { vo.getwNum(), vo.getpNum(), vo.getwCnt(), vo.getwPrice(), vo.getwDate() };
				modelWare.addRow(rowData);
			}
		}
	}

	// �԰���� ȭ�鱸��
	public void setRegWare() {
		panWareName.add(new JLabel("��ǰ��ȣ"));
		panWareName.add(txtWareProNum);
		panWarePhone.add(new JLabel("�԰����"));
		panWarePhone.add(txtWareCnt);
		panWareAddr.add(new JLabel("�԰���"));
		panWareAddr.add(txtWarePrice);
		panWareBtn.add(btnWareReg);
		panWareBtn.add(btnWareCan);
		panWareTop.setLayout(new GridLayout(4, 1));
		panWareTop.add(panWareName);
		panWareTop.add(panWarePhone);
		panWareTop.add(panWareAddr);
		panWareTop.add(panWareBtn);
		panWareUpd.add(btnWareSear);
		panWareUpd.add(btnWareUpd);
		panWareUpd.add(btnWareDel);

		String[] wareColumn = { "�԰��ȣ", "��ǰ��ȣ", "�԰����", "�԰���", "�԰���" };
		modelWare = new DefaultTableModel(wareColumn, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 4) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		tableWare = new JTable(modelWare);

		regWare.setLayout(new BorderLayout());
		regWare.add(panWareTop, BorderLayout.NORTH);
		regWare.add(new JScrollPane(tableWare), BorderLayout.CENTER);
		regWare.add(panWareUpd, BorderLayout.SOUTH);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnWareReg) {
					if (txtWareProNum.getText().equals("") || txtWareCnt.getText().equals("")
							|| txtWarePrice.getText().equals("")) {
						JOptionPane.showMessageDialog(PosWindow.this, "��� �׸��� �Է����ּ���");
					} else {
						int n = wareDAO.insert(new WarehouseVo(0, Integer.parseInt(txtWareProNum.getText()),
								Integer.parseInt(txtWareCnt.getText()), Integer.parseInt(txtWarePrice.getText()),
								null));
						if (n > 0) {
							JOptionPane.showMessageDialog(PosWindow.this, "�԰����� �Ϸ�Ǿ����ϴ�");
						} else {
							JOptionPane.showMessageDialog(PosWindow.this, "�԰��Ͽ� �����Ͽ����ϴ�");
						}

						initRegWare();
					}
				} else if (e.getSource() == btnWareCan) {
					txtWareProNum.setText("");
					txtWareCnt.setText("");
					txtWarePrice.setText("");
				} else if (e.getSource() == btnWareSear) {
					searWare();
				} else if (e.getSource() == btnWareUpd) {
					int wNum = Integer.parseInt(String.valueOf(modelWare.getValueAt(tableWare.getSelectedRow(), 0)));
					int pNum = Integer.parseInt(String.valueOf(modelWare.getValueAt(tableWare.getSelectedRow(), 1)));
					int wCnt = Integer.parseInt(String.valueOf(modelWare.getValueAt(tableWare.getSelectedRow(), 2)));
					int wPrice = Integer.parseInt(String.valueOf(modelWare.getValueAt(tableWare.getSelectedRow(), 3)));

					int n = wareDAO.update(new WarehouseVo(wNum, pNum, wCnt, wPrice, null));
					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "�԰� ������ �����Ǿ����ϴ�.");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "�԰� ���� ������ �����Ͽ����ϴ�");
					}

					initRegWare();
				} else { // ������
					int wNum = Integer.parseInt(String.valueOf(modelWare.getValueAt(tableWare.getSelectedRow(), 0)));
					int n = wareDAO.delete(wNum);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "�԰������� ���� �Ǿ����ϴ�");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "�԰����� ������ �����Ͽ����ϴ�");
					}

					initRegWare();
				}
			}
		};

		btnWareReg.addActionListener(listener);
		btnWareCan.addActionListener(listener);
		btnMemSear.addActionListener(listener);
		btnWareUpd.addActionListener(listener);
		btnWareDel.addActionListener(listener);
	}

	// �԰����� �˻�
	public void searWare() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "��ǰ��ȣ�� �Է����ּ���");
		if ("".equals(num)) {
			searWare(); // �԰��ȣ ���Է½� ���
		} else if (num != null) {
			WarehouseVo vo = wareDAO.search(Integer.parseInt(num));
			if (vo != null) {
				for (int i = modelPro.getRowCount() - 1; i >= 0; i--) {
					modelPro.removeRow(i);
				}

				Object[] rowData = { vo.getwNum(), vo.getpNum(), vo.getwCnt(), vo.getwPrice(), vo.getwDate() };
				modelPro.addRow(rowData);
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "�˻��� ��ǰ�� �����ϴ�");
			}
		}
	}

	// ��ǰ���� ȭ�鱸��
	public void setRegPro() {
		panProName.add(new JLabel("��ǰ�̸�"));
		panProName.add(txtProName);
		panProPrice.add(new JLabel("��ǰ����"));
		panProPrice.add(txtProPrice);
		panProBtn.add(btnProReg);
		panProBtn.add(btnProCan);
		panProTop.setLayout(new GridLayout(3, 1));
		panProTop.add(panProName);
		panProTop.add(panProPrice);
		panProTop.add(panProBtn);
		panProUpd.add(btnProSear);
		panProUpd.add(btnProUpd);
		panProUpd.add(btnProDel);

		String[] proColumn = { "��ǰ��ȣ", "��ǰ�̸�", "��ǰ����", "���" };
		modelPro = new DefaultTableModel(proColumn, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		tablePro = new JTable(modelPro);

		regPro.setLayout(new BorderLayout());
		regPro.add(panProTop, BorderLayout.NORTH);
		regPro.add(new JScrollPane(tablePro), BorderLayout.CENTER);
		regPro.add(panProUpd, BorderLayout.SOUTH);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnProReg) {
					if (txtProName.getText().equals("") || txtProPrice.getText().equals("")) {
						JOptionPane.showMessageDialog(PosWindow.this, "��� �׸��� �Է����ּ���");
					} else {
						int n = proDAO.insert(
								new ProductVo(0, txtProName.getText(), Integer.parseInt(txtProPrice.getText()), 0));
						if (n > 0) {
							JOptionPane.showMessageDialog(PosWindow.this, "��ǰ����� �Ϸ�Ǿ����ϴ�");
						} else {
							JOptionPane.showMessageDialog(PosWindow.this, "��ǰ��Ͽ� �����Ͽ����ϴ�");
						}

						initRegPro();
					}
				} else if (e.getSource() == btnProCan) {
					txtProName.setText("");
					txtProPrice.setText("");
				} else if (e.getSource() == btnProSear) {
					searPro();
				} else if (e.getSource() == btnProUpd) {
					int num = Integer.parseInt(String.valueOf(modelPro.getValueAt(tablePro.getSelectedRow(), 0)));
					String name = String.valueOf(modelPro.getValueAt(tablePro.getSelectedRow(), 1));
					int price = Integer.parseInt(String.valueOf(modelPro.getValueAt(tablePro.getSelectedRow(), 2)));
					int stock = Integer.parseInt(String.valueOf(modelPro.getValueAt(tablePro.getSelectedRow(), 3)));

					int n = proDAO.update(new ProductVo(num, name, price, stock));
					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "��ǰ ������ �����Ǿ����ϴ�.");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "��ǰ ���� ������ �����Ͽ����ϴ�");
					}

					initRegPro();
				} else { // ������ư Ŭ��
					int num = Integer.parseInt(String.valueOf(modelPro.getValueAt(tablePro.getSelectedRow(), 0)));
					int n = proDAO.delete(num);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "��ǰ�� ���� �Ǿ����ϴ�");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "��ǰ ������ �����Ͽ����ϴ�");
					}

					initRegPro();
				}
			}
		};

		btnProReg.addActionListener(listener);
		btnProCan.addActionListener(listener);
		btnProSear.addActionListener(listener);
		btnProUpd.addActionListener(listener);
		btnProDel.addActionListener(listener);
	}

	// ��ǰ���� ȭ�� �ʱ�ȭ
	public void initRegPro() {
		txtProName.setText("");
		txtProPrice.setText("");

		for (int i = modelPro.getRowCount() - 1; i >= 0; i--) {
			modelPro.removeRow(i);
		}

		ArrayList<ProductVo> voList = proDAO.showAll();

		if (voList != null) {
			for (int i = 0; i < voList.size(); i++) {
				ProductVo vo = voList.get(i);
				Object[] rowData = { vo.getpNum(), vo.getpName(), vo.getpPrice(), vo.getpStock() };
				modelPro.addRow(rowData);
			}
		}
	}

	// ��ǰ�˻�
	public void searPro() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "��ǰ��ȣ�� �Է����ּ���");
		if ("".equals(num)) {
			searPro(); // ��ǰ��ȣ ���Է½� ���
		} else if (num != null) {
			ProductVo vo = proDAO.search(Integer.parseInt(num));
			if (vo != null) {
				for (int i = modelPro.getRowCount() - 1; i >= 0; i--) {
					modelPro.removeRow(i);
				}

				Object[] rowData = { vo.getpNum(), vo.getpName(), vo.getpPrice(), vo.getpStock() };
				modelPro.addRow(rowData);
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "�˻��� ��ǰ�� �����ϴ�");
			}
		}
	}

	// ȸ������ ȭ�� �ʱ�ȭ
	public void initRegMem() {
		txtMemName.setText("");
		txtMemPhone.setText("");
		txtMemAddr.setText("");

		for (int i = modelMem.getRowCount() - 1; i >= 0; i--) {
			modelMem.removeRow(i);
		}

		ArrayList<MarketMemberVo> voList = memDAO.showAll();

		if (voList != null) {
			for (int i = 0; i < voList.size(); i++) {
				MarketMemberVo vo = voList.get(i);
				Object[] rowData = { vo.getmNum(), vo.getmName(), vo.getmPhone(), vo.getmAddr(), vo.getmPoint(),
						vo.getmHiredate() };
				modelMem.addRow(rowData);
			}
		}
	}

	// ȸ������ ȭ�鱸��
	public void setRegMem() {
		panMemName.add(new JLabel("ȸ���̸�"));
		panMemName.add(txtMemName);
		panMemPhone.add(new JLabel("��ȭ��ȣ"));
		panMemPhone.add(txtMemPhone);
		panMemAddr.add(new JLabel("ȸ���ּ�"));
		panMemAddr.add(txtMemAddr);
		panMemBtn.add(btnMemReg);
		panMemBtn.add(btnMemCan);
		panMemTop.setLayout(new GridLayout(4, 1));
		panMemTop.add(panMemName);
		panMemTop.add(panMemPhone);
		panMemTop.add(panMemAddr);
		panMemTop.add(panMemBtn);
		panMemUpd.add(btnMemSear);
		panMemUpd.add(btnMemUpd);
		panMemUpd.add(btnMemDel);

		String[] memColumn = { "ȸ����ȣ", "�̸�", "��ȭ��ȣ", "�ּ�", "����Ʈ", "������" };
		modelMem = new DefaultTableModel(memColumn, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 5) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		tableMem = new JTable(modelMem);

		regMem.setLayout(new BorderLayout());
		regMem.add(panMemTop, BorderLayout.NORTH);
		regMem.add(new JScrollPane(tableMem), BorderLayout.CENTER);
		regMem.add(panMemUpd, BorderLayout.SOUTH);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnMemReg) {
					if (txtMemName.getText().equals("") || txtMemPhone.getText().equals("")
							|| txtMemAddr.getText().equals("")) {
						JOptionPane.showMessageDialog(PosWindow.this, "��� �׸��� �Է����ּ���");
					} else {
						int n = memDAO.insert(new MarketMemberVo(0, txtMemName.getText(), txtMemPhone.getText(),
								txtMemAddr.getText(), 0, null));

						if (n > 0) {
							JOptionPane.showMessageDialog(PosWindow.this, "ȸ������� �Ϸ�Ǿ����ϴ�");
						} else {
							JOptionPane.showMessageDialog(PosWindow.this, "ȸ����Ͽ� �����Ͽ����ϴ�");
						}
						initRegMem();
					}
				} else if (e.getSource() == btnMemCan) {
					txtMemName.setText("");
					txtMemPhone.setText("");
					txtMemAddr.setText("");
				} else if (e.getSource() == btnMemSear) {
					searMem();
				} else if (e.getSource() == btnMemUpd) {
					int num = Integer.parseInt(String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 0)));
					String name = String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 1));
					String phone = String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 2));
					String addr = String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 3));
					int point = Integer.parseInt(String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 4)));

					int n = memDAO.update(new MarketMemberVo(num, name, phone, addr, point, null));

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "ȸ�� ������ �����Ǿ����ϴ�.");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "ȸ�� ���� ������ �����Ͽ����ϴ�");
					}

					initRegMem();
				} else { // ������ư Ŭ��
					int num = Integer.parseInt(String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 0)));
					int n = memDAO.delete(num);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "ȸ���� ���� �Ǿ����ϴ�");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "ȸ�� ������ �����Ͽ����ϴ�");
					}

					initRegMem();
				}
			}
		};

		btnMemReg.addActionListener(listener);
		btnMemCan.addActionListener(listener);
		btnMemSear.addActionListener(listener);
		btnMemUpd.addActionListener(listener);
		btnMemDel.addActionListener(listener);
	}

	// ��ǰ�˻�
	public void searMem() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "ȸ����ȣ�� �Է����ּ���");
		if ("".equals(num)) {
			searMem(); // ȸ����ȣ ���Է½� ���
		} else if (num != null) {
			MarketMemberVo vo = memDAO.search(Integer.parseInt(num));
			if (vo != null) {
				for (int i = modelMem.getRowCount() - 1; i >= 0; i--) {
					modelMem.removeRow(i);
				}

				Object[] rowData = { vo.getmNum(), vo.getmName(), vo.getmPhone(), vo.getmAddr(), vo.getmPoint(),
						vo.getmHiredate() };
				modelMem.addRow(rowData);
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "�˻��� ȸ���� �����ϴ�");
			}
		}
	}
}