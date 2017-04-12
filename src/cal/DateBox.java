/*
 * �� ��¥�� ǥ���ϴ� ������Ʈ
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
		
		// panel�� MouseListener ����
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pop();
			}
		});
		
		add(la,BorderLayout.NORTH);
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(120, 120));
	}
	
	// �ش� ��¥�� ������ ���� ���� �� ��¥ ���
	public void pop(){
		// int yy=mainFrame.yy;
		// int mm=mainFrame.mm+1;
		// int dd=Integer.parseInt(la.getText());
		JOptionPane.showMessageDialog(mainFrame, "���� "+mainFrame.yy+" ��"+(mainFrame.mm+1)+" ��"+la.getText() +"�� �Դϴ�.");
		
		for(int i=0; i<mainFrame.box.length; i++){
			// ���� �ƴ϶��
			if(mainFrame.box[i]!=this){
				mainFrame.box[i].setBackground(Color.pink);
			}
			else{
				mainFrame.box[i].setBackground(Color.cyan);
			}
		}
	}

}
