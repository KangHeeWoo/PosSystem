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
	JButton btnCheck = new JButton("조회");
	JButton btnApply = new JButton("적용");
	JButton btnPay = new JButton("결제");
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
	JButton btnProReg = new JButton("등록");
	JButton btnProCan = new JButton("취소");
	JButton btnProSear = new JButton("검색");
	JButton btnProUpd = new JButton("수정");
	JButton btnProDel = new JButton("삭제");
	DefaultTableModel modelPro = null;
	JTable tablePro = null;

	JPanel regMem = new JPanel();
	JPanel panMemTop = new JPanel();
	JPanel panMemName = new JPanel();
	JPanel panMemPhone = new JPanel();
	JPanel panMemAddr = new JPanel();
	JPanel panMemBtn = new JPanel();
	JPanel panMemUpd = new JPanel();
	JLabel laMemName = new JLabel("회원이름");
	JLabel laMemPhone = new JLabel("전화번호");
	JLabel laMemAddr = new JLabel("회원주소");
	JTextField txtMemName = new JTextField(10);
	JTextField txtMemPhone = new JTextField(10);
	JTextField txtMemAddr = new JTextField(10);
	JButton btnMemReg = new JButton("등록");
	JButton btnMemCan = new JButton("취소");
	JButton btnMemSear = new JButton("검색");
	JButton btnMemUpd = new JButton("수정");
	JButton btnMemDel = new JButton("삭제");
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
	JButton btnWareReg = new JButton("등록");
	JButton btnWareCan = new JButton("취소");
	JButton btnWareSear = new JButton("검색");
	JButton btnWareUpd = new JButton("수정");
	JButton btnWareDel = new JButton("삭제");
	DefaultTableModel modelWare = null;
	JTable tableWare = null;

	JPanel saleList = new JPanel();
	JPanel panDate = new JPanel();
	JPanel panSalesPrice = new JPanel();
	JTextField startDate = new JTextField(15);
	JTextField endDate = new JTextField(15);
	JButton btnSearch = new JButton("조회");
	JButton btnRefund = new JButton("환불");
	DefaultTableModel modelSaleList = null;
	JTable tableSaleList = null;
	JTextField txtSalesPrice = new JTextField("0", 25);

	JPanel saleItemList = new JPanel();
	JPanel panSaleNum = new JPanel();
	JTextField txtSaleNum = new JTextField(15);
	JButton btnSaleNum = new JButton("조회");
	DefaultTableModel modelSaleItemList = null;
	JTable tableSaleItemList = null;

	MarketMemberDAO memDAO = new MarketMemberDAO(); 
	ProductDAO proDAO = new ProductDAO();
	SaleDAO saleDAO = new SaleDAO();
	SaleListDAO saleListDAO = new SaleListDAO();
	WarehouseDAO wareDAO = new WarehouseDAO();

	// 메인 프레임 구성
	public PosWindow() {
		super("중앙마트");

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

	// 상품판매 화면 초기화
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

	// 상품판매 화면구성
	public void setSale() {
		String[] itemColunm = { "상품번호", "상품이름", "상품가격", "재고" };
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

		String[] cartColunm = { "상품번호", "상품이름", "상품가격", "개수" };
		modelCart = new DefaultTableModel(cartColunm, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCart = new JTable(modelCart);

		panInputNum.add(new JLabel("회원번호"));
		panInputNum.add(txtMemNum);
		panInputNum.add(btnCheck);
		showMem.add(new JLabel("회원이름"));
		showMem.add(showMemName);
		showMem.add(new JLabel("현재포인트"));
		showMem.add(showMemPoint);
		panSumPrice.add(new JLabel("가격 합계"));
		showSumPrice.setHorizontalAlignment(JTextField.RIGHT);
		panSumPrice.add(showSumPrice);
		panUsePoint.add(new JLabel("사용할 포인트"));
		usePoint.setHorizontalAlignment(JTextField.RIGHT);
		panUsePoint.add(usePoint);
		panUsePoint.add(btnApply);
		panPayPrice.add(new JLabel("결제 금액"));
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
		String num = JOptionPane.showInputDialog(PosWindow.this, "구매 목록에 추가할 갯수 입력");
		if ("".equals(num)) {
			JOptionPane.showMessageDialog(PosWindow.this, "갯수를 입력해주세요");
		} else if (num != null) {
			int pNum = Integer.parseInt(String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 0)));
			String name = String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 1));
			int price = Integer.parseInt(String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 2)));
			int stock = Integer.parseInt(String.valueOf(modelItem.getValueAt(tableItem.getSelectedRow(), 3)));

			int cnt = Integer.parseInt(num);
			if (cnt > stock) {
				JOptionPane.showMessageDialog(PosWindow.this, "상품의 재고량 이상 구매할 수 없습니다");
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
		int cho = JOptionPane.showConfirmDialog(PosWindow.this, "상품을 제거하시겠습니까?");
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
			JOptionPane.showMessageDialog(PosWindow.this, "사용할 포인트를 입력해주세요");
		} else {
			int usingPoint = Integer.parseInt(usePoint.getText());
			int havingPoint = Integer.parseInt(showMemPoint.getText());

			if (usingPoint > havingPoint) {
				JOptionPane.showMessageDialog(PosWindow.this, "보유 포인트 이상 사용할 수 없습니다");
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
			JOptionPane.showMessageDialog(PosWindow.this, "회원번호를 입력하세요");
		} else {
			MarketMemberVo vo = memDAO.search(Integer.parseInt(txtMemNum.getText()));
			if (vo != null) {
				showMemName.setText(vo.getmName());
				showMemPoint.setText(String.valueOf(vo.getmPoint()));
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "회원번호를 확인해주세요");
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

			JOptionPane.showMessageDialog(PosWindow.this, "구매 완료");

			initSale();
		} else {
			JOptionPane.showMessageDialog(PosWindow.this, "구매 실패");
		}
	}

	// 전체 탭구성
	public void setMenu() {
		jtab.add("상품판매", sale);
		jtab.add("상품관리", regPro);
		jtab.add("회원관리", regMem);
		jtab.add("입고관리", regWare);
		jtab.add("판매내역", saleList);
		jtab.add("판매상품내역", saleItemList);
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

	// 상품판매 화면구성 초기화
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

	// 상품판매 화면구성
	public void setSaleList() {
		panDate.add(new JLabel("기간검색 : 시작일(ex 2018-01-01)"));
		panDate.add(startDate);
		panDate.add(new JLabel("~ 종료일(ex 2018-01-01)"));
		panDate.add(endDate);
		panDate.add(btnSearch);
		panDate.add(btnRefund);
		String[] saleListColumn = { "판매번호", "회원번호", "판매가격", "발생포인트", "판매날짜" };
		modelSaleList = new DefaultTableModel(saleListColumn, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableSaleList = new JTable(modelSaleList);

		panSalesPrice.add(new JLabel("현재 조회된 기간 매출 : "));
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
						JOptionPane.showMessageDialog(PosWindow.this, "모든 기간을 입력해주세요.");
					} else {
						SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd");
						try {
							Date start = form.parse(startDate.getText());
							Date end = form.parse(endDate.getText());

							if (start.compareTo(end) > 0) {
								JOptionPane.showMessageDialog(PosWindow.this, "기간을 확인해주세요.");
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
							JOptionPane.showMessageDialog(PosWindow.this, "날짜 형식을 다시 확인해주세요.");
						}
					}
				} else { // 환불처리
					int num = Integer
							.parseInt(String.valueOf(modelSaleList.getValueAt(tableSaleList.getSelectedRow(), 0)));
					int n = saleDAO.delete(num);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "환불처리가 완료되었습니다");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "환불처리가 실패하였습니다");
					}

					initSaleList();
				}
			}
		};

		btnSearch.addActionListener(listener);
		btnRefund.addActionListener(listener);
	}

	// 상품판매내역 화면구성
	public void setSaleItemList() {
		panSaleNum.add(new JLabel("판매번호"));
		panSaleNum.add(txtSaleNum);
		panSaleNum.add(btnSaleNum);
		String[] saleItemListColumn = { "판매내역번호", "판매번호", "상품이름", "판매수량" };
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
			JOptionPane.showMessageDialog(PosWindow.this, "판매번호를 입력해주세요");
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
				JOptionPane.showMessageDialog(PosWindow.this, "해당 판매내역이 없습니다");
			}
		}
	}

	// 입고관리 화면 초기화
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

	// 입고관리 화면구성
	public void setRegWare() {
		panWareName.add(new JLabel("상품번호"));
		panWareName.add(txtWareProNum);
		panWarePhone.add(new JLabel("입고수량"));
		panWarePhone.add(txtWareCnt);
		panWareAddr.add(new JLabel("입고가격"));
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

		String[] wareColumn = { "입고번호", "상품번호", "입고수량", "입고가격", "입고일" };
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
						JOptionPane.showMessageDialog(PosWindow.this, "모든 항목을 입력해주세요");
					} else {
						int n = wareDAO.insert(new WarehouseVo(0, Integer.parseInt(txtWareProNum.getText()),
								Integer.parseInt(txtWareCnt.getText()), Integer.parseInt(txtWarePrice.getText()),
								null));
						if (n > 0) {
							JOptionPane.showMessageDialog(PosWindow.this, "입고등록이 완료되었습니다");
						} else {
							JOptionPane.showMessageDialog(PosWindow.this, "입고등록에 실패하였습니다");
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
						JOptionPane.showMessageDialog(PosWindow.this, "입고 정보가 수정되었습니다.");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "입고 정보 수정이 실패하였습니다");
					}

					initRegWare();
				} else { // 삭제시
					int wNum = Integer.parseInt(String.valueOf(modelWare.getValueAt(tableWare.getSelectedRow(), 0)));
					int n = wareDAO.delete(wNum);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "입고정보가 삭제 되었습니다");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "입고정보 삭제에 실패하였습니다");
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

	// 입고정보 검색
	public void searWare() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "상품번호를 입력해주세요");
		if ("".equals(num)) {
			searWare(); // 입고번호 미입력시 재귀
		} else if (num != null) {
			WarehouseVo vo = wareDAO.search(Integer.parseInt(num));
			if (vo != null) {
				for (int i = modelPro.getRowCount() - 1; i >= 0; i--) {
					modelPro.removeRow(i);
				}

				Object[] rowData = { vo.getwNum(), vo.getpNum(), vo.getwCnt(), vo.getwPrice(), vo.getwDate() };
				modelPro.addRow(rowData);
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "검색된 상품이 없습니다");
			}
		}
	}

	// 상품관리 화면구성
	public void setRegPro() {
		panProName.add(new JLabel("상품이름"));
		panProName.add(txtProName);
		panProPrice.add(new JLabel("상품가격"));
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

		String[] proColumn = { "상품번호", "상품이름", "상품가격", "재고" };
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
						JOptionPane.showMessageDialog(PosWindow.this, "모든 항목을 입력해주세요");
					} else {
						int n = proDAO.insert(
								new ProductVo(0, txtProName.getText(), Integer.parseInt(txtProPrice.getText()), 0));
						if (n > 0) {
							JOptionPane.showMessageDialog(PosWindow.this, "상품등록이 완료되었습니다");
						} else {
							JOptionPane.showMessageDialog(PosWindow.this, "상품등록에 실패하였습니다");
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
						JOptionPane.showMessageDialog(PosWindow.this, "상품 정보가 수정되었습니다.");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "상품 정보 수정이 실패하였습니다");
					}

					initRegPro();
				} else { // 삭제버튼 클릭
					int num = Integer.parseInt(String.valueOf(modelPro.getValueAt(tablePro.getSelectedRow(), 0)));
					int n = proDAO.delete(num);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "상품이 삭제 되었습니다");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "상품 삭제에 실패하였습니다");
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

	// 상품관리 화면 초기화
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

	// 상품검색
	public void searPro() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "상품번호를 입력해주세요");
		if ("".equals(num)) {
			searPro(); // 상품번호 미입력시 재귀
		} else if (num != null) {
			ProductVo vo = proDAO.search(Integer.parseInt(num));
			if (vo != null) {
				for (int i = modelPro.getRowCount() - 1; i >= 0; i--) {
					modelPro.removeRow(i);
				}

				Object[] rowData = { vo.getpNum(), vo.getpName(), vo.getpPrice(), vo.getpStock() };
				modelPro.addRow(rowData);
			} else {
				JOptionPane.showMessageDialog(PosWindow.this, "검색된 상품이 없습니다");
			}
		}
	}

	// 회원관리 화면 초기화
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

	// 회원관리 화면구성
	public void setRegMem() {
		panMemName.add(new JLabel("회원이름"));
		panMemName.add(txtMemName);
		panMemPhone.add(new JLabel("전화번호"));
		panMemPhone.add(txtMemPhone);
		panMemAddr.add(new JLabel("회원주소"));
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

		String[] memColumn = { "회원번호", "이름", "전화번호", "주소", "포인트", "가입일" };
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
						JOptionPane.showMessageDialog(PosWindow.this, "모든 항목을 입력해주세요");
					} else {
						int n = memDAO.insert(new MarketMemberVo(0, txtMemName.getText(), txtMemPhone.getText(),
								txtMemAddr.getText(), 0, null));

						if (n > 0) {
							JOptionPane.showMessageDialog(PosWindow.this, "회원등록이 완료되었습니다");
						} else {
							JOptionPane.showMessageDialog(PosWindow.this, "회원등록에 실패하였습니다");
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
						JOptionPane.showMessageDialog(PosWindow.this, "회원 정보가 수정되었습니다.");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "회원 정보 수정이 실패하였습니다");
					}

					initRegMem();
				} else { // 삭제버튼 클릭
					int num = Integer.parseInt(String.valueOf(modelMem.getValueAt(tableMem.getSelectedRow(), 0)));
					int n = memDAO.delete(num);

					if (n > 0) {
						JOptionPane.showMessageDialog(PosWindow.this, "회원이 삭제 되었습니다");
					} else {
						JOptionPane.showMessageDialog(PosWindow.this, "회원 삭제에 실패하였습니다");
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

	// 상품검색
	public void searMem() {
		String num = JOptionPane.showInputDialog(PosWindow.this, "회원번호를 입력해주세요");
		if ("".equals(num)) {
			searMem(); // 회원번호 미입력시 재귀
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
				JOptionPane.showMessageDialog(PosWindow.this, "검색된 회원이 없습니다");
			}
		}
	}
}