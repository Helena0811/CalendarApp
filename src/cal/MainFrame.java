package cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
	JPanel p_north, p_center;
	JButton bt_prev, bt_next;
	JLabel la_title;
	
	DateBox[] box=new DateBox[7*6];
	
	Calendar cal=Calendar.getInstance();
	
	// ���� ��¥�� ����ϱ� ���� ����
	int yy, mm, dd;
	
	public MainFrame() {
		p_north=new JPanel();
		p_center=new JPanel();
		bt_prev=new JButton("��");
		bt_next=new JButton("��");
		la_title=new JLabel("2017�� 4��");
		la_title.setFont(new Font("����", Font.BOLD|Font.ITALIC, 28));
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		yy=cal.get(Calendar.YEAR);
		mm=cal.get(Calendar.MONTH);		// ���� 0���� ����, ����� ���� +1
		dd=cal.get(Calendar.DATE);
		
		System.out.println(yy+"-"+(mm+1)+"-"+dd);
		printDate();
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setVisible(true);
		setSize((120*7)+100, (120*6)+150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	// ��¥ ��� �޼ҵ� ����
	// �� ���� ù�� ���� ���ϱ�
	public void printDate(){
		// ���� ��¥�� �󺧿� ���
		la_title.setText(yy+"-"+(mm+1));
		
		// ������ Datebox ����
		p_center.removeAll();
		
		// �� ���� ���� ���Ϻ��� �����ϴ���, ���� �������� �� ���ϱ�
		// �ش� ���� 1�Ϸ� ���߰�, �� ������ ���� �������� ���ϸ� ��
		cal.set(yy, mm, 1);
		
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		System.out.println((mm+1)+"���� ���� ������ "+firstDay);
		
		// �� ���� ������ ��¥ ���ϱ�
		// ���� ���� 1�� �ٷ� ���� ���� ���߰�, �� ��¥�� ���ϸ� �ش� ���� ������ ��¥�� ���� �� ����
		// -> �ݺ����� ���� ���ϱ� ���ؼ�
		cal.set(yy, mm+1, 0);
		int lastDay=cal.get(Calendar.DATE);
		
		int numDay=1;		// ���� ���� ��¥�� ����
		
		for(int i=0; i<box.length; i++){
			box[i]=new DateBox(this);
			
			// �� ���� 1�Ϻ��� ����
			if(i>=firstDay-1){
				if(numDay<=lastDay){
					box[i].la.setText(Integer.toString(numDay));
					// ���� �� ��Ĵ�� �����ϸ� ��¥�� ������ �ʴ� ���� ���ܵ�!
					/*
					box[i].addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							Object obj=e.getSource();
							getDate(obj);
						}
					});
					*/
				}
				numDay++;
			}
			else{
				box[i].la.setText("");
			}
			p_center.add(box[i]);
		}
	}
	
	/*
	public void getDate(Object obj){
		// �� ��¥ �г��� �Է��ϸ�
		for(int i=0; i<box.length; i++){
			if(obj==box[i]){
				// ���� ��ȭ�� �ش� ��¥ ���
				box[i].setBackground(Color.CYAN);
				JOptionPane.showMessageDialog(this, "���� "+yy+" ��"+(mm+1)+" ��"+box[i].la.getText() +"�� �Դϴ�.");
			}
			else{
				box[i].setBackground(Color.pink);
			}
		}
	}
	*/

	// ���� �ѱ�� ��ư
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		// ���� ��
		if(obj==bt_prev){
			mm--;
			if(mm<0){
				//mm+=12;
				mm=11;
				yy-=1;
			}
			printDate();
		}
		
		// ���� ��
		else if(obj==bt_next){
			mm++;
			/*
			if(mm>=12){
				mm-=12;
				yy+=1;
			}
			*/
			if(mm>11){
				mm=0;
				yy+=1;
			}
			printDate();
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
