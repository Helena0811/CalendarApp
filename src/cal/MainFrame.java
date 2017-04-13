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
	
	// 현재 날짜를 계산하기 위한 변수
	int yy, mm, dd;
	
	public MainFrame() {
		p_north=new JPanel();
		p_center=new JPanel();
		bt_prev=new JButton("◀");
		bt_next=new JButton("▶");
		la_title=new JLabel("2017년 4월");
		la_title.setFont(new Font("돋움", Font.BOLD|Font.ITALIC, 28));
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		yy=cal.get(Calendar.YEAR);
		mm=cal.get(Calendar.MONTH);		// 월은 0부터 시작, 출력할 때만 +1
		dd=cal.get(Calendar.DATE);
		
		System.out.println(yy+"-"+(mm+1)+"-"+dd);
		printDate();
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setVisible(true);
		setSize((120*7)+100, (120*6)+150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	// 날짜 출력 메소드 정의
	// 각 월의 첫날 요일 구하기
	public void printDate(){
		// 현재 날짜를 라벨에 출력
		la_title.setText(yy+"-"+(mm+1));
		
		// 기존의 Datebox 제거
		p_center.removeAll();
		
		// 각 월이 무슨 요일부터 시작하는지, 몇일 동안인지 값 구하기
		// 해당 월을 1일로 맞추고, 그 요일이 무슨 요일인지 구하면 됨
		cal.set(yy, mm, 1);
		
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		System.out.println((mm+1)+"월의 시작 요일은 "+firstDay);
		
		// 각 월의 마지막 날짜 구하기
		// 다음 달의 1일 바로 이전 날로 맞추고, 그 날짜를 구하면 해당 월의 마지막 날짜를 구할 수 있음
		// -> 반복문의 끝을 구하기 위해서
		cal.set(yy, mm+1, 0);
		int lastDay=cal.get(Calendar.DATE);
		
		int numDay=1;		// 실제 찍힐 날짜용 변수
		
		for(int i=0; i<box.length; i++){
			box[i]=new DateBox(this);
			
			// 각 월의 1일부터 시작
			if(i>=firstDay-1){
				if(numDay<=lastDay){
					box[i].la.setText(Integer.toString(numDay));
					// 내가 한 방식대로 구현하면 날짜가 나오지 않는 날은 제외됨!
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
		// 각 날짜 패널을 입력하면
		for(int i=0; i<box.length; i++){
			if(obj==box[i]){
				// 색상 변화와 해당 날짜 출력
				box[i].setBackground(Color.CYAN);
				JOptionPane.showMessageDialog(this, "현재 "+yy+" 년"+(mm+1)+" 월"+box[i].la.getText() +"일 입니다.");
			}
			else{
				box[i].setBackground(Color.pink);
			}
		}
	}
	*/

	// 월을 넘기는 버튼
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		// 이전 달
		if(obj==bt_prev){
			mm--;
			if(mm<0){
				//mm+=12;
				mm=11;
				yy-=1;
			}
			printDate();
		}
		
		// 다음 달
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
