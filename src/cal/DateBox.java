/*
 * 각 날짜를 표현하는 컴포넌트
 * */
package cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DateBox extends JPanel{
	JLabel la;
	MainFrame mainFrame;
	
	public DateBox(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		
		this.setLayout(new BorderLayout());
		la=new JLabel();
		
		// panel과 MouseListener 연결
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pop();
			}
		});
		
		add(la,BorderLayout.NORTH);
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(120, 120));
	}
	
	// 해당 날짜를 누르면 색상 변경 및 날짜 출력
	public void pop(){
		// int yy=mainFrame.yy;
		// int mm=mainFrame.mm+1;
		// int dd=Integer.parseInt(la.getText());
		JOptionPane.showMessageDialog(mainFrame, "현재 "+mainFrame.yy+" 년"+(mainFrame.mm+1)+" 월"+la.getText() +"일 입니다.");
		
		for(int i=0; i<mainFrame.box.length; i++){
			// 내가 아니라면
			if(mainFrame.box[i]!=this){
				mainFrame.box[i].setBackground(Color.pink);
			}
			else{
				mainFrame.box[i].setBackground(Color.cyan);
			}
		}
	}

}
