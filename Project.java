
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
public class Project extends JFrame {
	JLabel sLabel = new JLabel(" ����");
	JLabel hsLabel = new JLabel(" �ְ�����");
	JLabel[] scLabel = new JLabel[5];
	int[] scorearr = new int[] {0,0,0,0,0}; //�������
	private int score = 0;
	private int bkscore = 0;
	private int high = 0;
	private int max=4; //ť�� �ִ�ũ�� 4
	private int num=0;   //�����ͼ�
	private String[] que = new String[max]; //ť ����
	List<Integer> set = new ArrayList<>(); //���� ���������� ���� ����Ʈ
	
	JPanel p1 = new JPanel(); //�ʱ�ȭ��
	JPanel p2 = new JPanel(); //���ӽ���ȭ��
	JPanel p3 = new JPanel(); //���ӿ���ȭ��
	JPanel p2re = new JPanel(); //�ٽý����Ͻðڽ��ϱ�?
	JPanel p1rank = new JPanel(); //����ǥ
	JPanel arrp = new JPanel(new GridLayout(0,4,7,7)); //�迭 �����ִ� �г�
	JLabel[][] arr = new JLabel[4][4];
	JLabel[][] savearr = new JLabel[4][4];

	/*------ť-------*/
	
	//ť�� ��� ����
	public class EmptyIntQueueException extends RuntimeException{
		public EmptyIntQueueException() {}
	}
	
	//ť�� ������
	public class OverflowIntQueueException extends RuntimeException{
		public OverflowIntQueueException() {}
	}
	
	//��ť
	public String enque(String string) throws OverflowIntQueueException{
		if(num>=max)
			throw new OverflowIntQueueException();
		que[num++] = string;
		return string;
	}
	
	//��ť
	public String deque() throws EmptyIntQueueException{
		if(num <= 0)
			throw new EmptyIntQueueException();
		String x = que[0];
		for(int i=0; i<num-1; i++) {
				que[i] = que[i+1];
		}
		num--;
		return x;
	}
	
	//ť ����
	public void clearq() {
		num = 0;
	}
	
	//ť�� ����
	public boolean isFull() {
		return num >= max;
	}
	
	//ť�� �������
	public boolean isEmpty() {
		return num <=0;
	}
	
	
	/*------������-------*/
	
	//������Ʈ ������
	Project(){
		setTitle("2048 GAME");
		setSize(500,800);
		show1();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		showrank();
		show2();
	
		p1.setVisible(true);
		p1rank.setVisible(false);
		p2.setVisible(false);
		arrp.setVisible(false);
		p2re.setVisible(false);
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
			savearr[i][j] = new JLabel(" ");
			}
		}
	}
	
	/*------ȭ�����(�� ������(�г�))-------*/
	
	//����ȭ��
	void show1() {
		
		p1.setBackground(new Color(251,248,239));
		
		
		//2048
		JLabel txt2048 = new JLabel("2048");
		txt2048.setHorizontalAlignment(SwingConstants.CENTER);
		txt2048.setFont(new Font("�� M", Font.PLAIN, 55));
		txt2048.setBounds(155, 60, 169, 86);
		txt2048.setForeground(new Color(148,120,107));
		
		//�����̹���
		JLabel example = new JLabel(new ImageIcon("projectex.png"));
		example.setBounds(90, 100,300,320);
		
		//���ӻ����� �ؽ�Ʈ
		JLabel gamesize = new JLabel("< 4x4 >");
		gamesize.setHorizontalAlignment(SwingConstants.CENTER);
		gamesize.setFont(new Font("�� M", Font.PLAIN, 25));
		gamesize.setBounds(155, 396, 169, 62);
		gamesize.setForeground(new Color(143,122,101));
		
		//���ӽ���, ����ǥ ��ư
		JButton btn1 = new JButton("���� ����");
		JButton btn2 = new JButton("����ǥ");
		btn1.setBounds(85, 496, 310, 46); //��ġ
		btn2.setBounds(85, 554, 310, 46);
		btn1.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 25)); //��Ʈ ����
		btn2.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 25));
		btn1.setBackground(new Color(245,131,95));
		btn2.setBackground(new Color(143,122,101));
		btn1.setForeground(Color.WHITE);
		btn2.setForeground(Color.WHITE);
		btn1.setBorder(null);
		btn2.setBorder(null);

		p1.setLayout(null);
		p1.add(txt2048);
		p1.add(btn1);
		p1.add(btn2);
		p1.add(example);
		p1.add(gamesize);
		
		add(p1);
		

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.setVisible(false);
				p2.setVisible(true);
				arrp.setVisible(true);
				start();
			}
			
		});
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.setVisible(false);
				p1rank.setVisible(true);
				
			}
			
		});
		
				
	}
	
	//����ȭ���� ����ǥ
	void showrank() {
		//����ǥ
				p1rank.setBackground(new Color(251,248,239));
				
				JLabel rank = new JLabel("����ǥ");
				rank.setHorizontalAlignment(SwingConstants.CENTER);
				rank.setFont(new Font("�� M", Font.PLAIN, 37));
				rank.setBounds(173, 124, 132, 82);
				rank.setForeground(new Color(143,122,101));
				p1rank.add(rank);
				
				for(int i=0; i<5; i++) {	
					scLabel[i] = new JLabel(i+1+". ");
					scLabel[i].setFont(new Font("�� M", Font.PLAIN, 23));
					scLabel[i].setBounds(135, 264+i*67, 213, 32);
					scLabel[i].setForeground(new Color(148,120,107));
					p1rank.add(scLabel[i]);
					
				}
				
				JButton bkmain = new JButton("main menu");
				bkmain.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 16));
				bkmain.setBounds(173, 635, 132, 27);
				bkmain.setBackground(new Color(143,122,101));
				bkmain.setForeground(Color.white);
				p1rank.add(bkmain);
				
				p1rank.setLayout(null);
				
				add(p1rank);
				
				bkmain.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						p1rank.setVisible(false);
						p1.setVisible(true);						
					}
					
				});
		
		
		
	}
	
	//����ȭ��
	void show2() {
		p2.setBackground(new Color(251,248,239)); //���� ���� (p2�� ��ü��� �г� , arrp�迭 �г�)
		
		//���ӹ迭 �׵θ�
		JLabel gameborder = new JLabel();
		gameborder.setOpaque(true);
		gameborder.setBackground(new Color(187,173,160));
		gameborder.setBounds(35, 271, 414, 414);
		
		//2048
		JLabel txt2048 = new JLabel("2048");
		txt2048.setFont(new Font("�� M", Font.PLAIN, 55));
		txt2048.setBounds(42, 104, 150, 100);
		txt2048.setForeground(new Color(148,120,107));
		
		//����
		sLabel.setBounds(276, 156, 170, 35);
		sLabel.setOpaque(true);
		sLabel.setFont(new Font("�� M", Font.PLAIN, 20));
		sLabel.setBackground(new Color(187,173,160));
		
		//�ְ�����
		hsLabel.setBounds(276, 124, 170, 35);
		hsLabel.setOpaque(true);
		hsLabel.setFont(new Font("�� M", Font.PLAIN, 20));
		hsLabel.setBackground(new Color(187,173,160));

		
		//���� �迭��
		arrp.setBackground(new Color(187,173,160));
		arrp.setBounds(42, 279, 400, 400);
	
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
			arr[i][j] = new JLabel(" ");
			arr[i][j].setHorizontalAlignment(SwingConstants.CENTER);
			arr[i][j].setFont(new Font("�� M", Font.PLAIN, 30));
			arr[i][j].setOpaque(true);
			arr[i][j].setBackground(new Color(214,205,196));
			arrp.add(arr[i][j]);
			}
		}
		
		//�ٽý��� �г�
		p2re.setLayout(new GridLayout(3,3));
		p2re.setBackground(new Color(251,248,239));
		p2re.setBounds(42, 279, 400, 400);
		p2re.setLayout(null);
		
		JLabel retxt = new JLabel("�ٽ� ���� �Ͻðڽ��ϱ�?"); //�ٽý��� ��
	    retxt.setForeground(Color.BLACK);
		retxt.setFont(new Font("�� M", Font.PLAIN, 25));
		retxt.setHorizontalAlignment(SwingConstants.CENTER);
	    retxt.setBounds(50,100, 300, 80);
		p2re.add(retxt);
		
		JButton yesButton = new JButton("YES");
		yesButton.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 15));
		yesButton.setBounds(105, 227, 84, 27);
		yesButton.setBackground(new Color(143,122,101));
		yesButton.setForeground(Color.white);
		p2re.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 15));
		noButton.setBounds(205, 227, 84, 27);
		noButton.setBackground(new Color(143,122,101));
		noButton.setForeground(Color.white);
		p2re.add(noButton);
		
		
		
		//�ٽý���, ������ư
		JButton restart = new JButton();
		restart.setBounds(398, 213, 50, 50);
		ImageIcon reIcon = new ImageIcon("re.png");
		Image reimg = reIcon.getImage().getScaledInstance(restart.getWidth(),restart.getHeight(), java.awt.Image.SCALE_SMOOTH);
		reIcon = new ImageIcon(reimg);
		restart.setIcon(reIcon);
		restart.setBorder(null);
		
		JButton back = new JButton();
		back.setBounds(340, 213, 50, 50);
		ImageIcon bkIcon = new ImageIcon("back.png");
		Image bkimg = bkIcon.getImage().getScaledInstance(back.getWidth(),back.getHeight(), java.awt.Image.SCALE_SMOOTH);
		bkIcon = new ImageIcon(bkimg);
		back.setIcon(bkIcon);
		back.setBorder(null);
		
		//�����¿� ��ư
		JButton left = new JButton("��");
		left.setBounds(285, 715, 55, 27);
		left.setBackground(new Color(187,173,160));
		
		JButton down = new JButton("��");
		down.setBounds(338, 715, 55, 27);
		down.setBackground(new Color(187,173,160));
		
		JButton right = new JButton("��");
		right.setBounds(387, 715, 55, 27);
		right.setBackground(new Color(187,173,160));
		
		JButton up = new JButton("��");
		up.setBounds(338, 691, 55, 27);
		up.setBackground(new Color(187,173,160));
		
		
		p2.add(gameborder);
		p2.add(txt2048);
		p2.add(sLabel);
		p2.add(hsLabel);
		p2.add(back);
		p2.add(restart);
		p2.add(left);
		p2.add(down);
		p2.add(right);
		p2.add(up);
		p2.setLayout(null);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				setback();		
			}
			
		});

		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				arrp.setVisible(false);
				p2re.setVisible(true);
				
			}
			
		});

		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				p2re.setVisible(false);
				arrp.setVisible(true);
				cmphigh();
				start();
			}
			
		});
		
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2re.setVisible(false);
				arrp.setVisible(true);
			}
			
		});
		
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				lblank();
				lsum();
				highscore();
				showgameover(isgameOver());
				lblank();
				randnum();
			}
			
		});
		
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				rblank();
				rsum();
				highscore();
				showgameover(isgameOver());
				rblank();
				randnum();		
			}
			
		});
		
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				upblank();
				upsum();
				highscore();
				showgameover(isgameOver());
				upblank();
				randnum();
			}
			
		});
		
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				dblank();
				dsum();
				highscore();
				showgameover(isgameOver());
				dblank();
				randnum();
			}
			
		});
		
		
		
		
		
		add(arrp);
		add(p2re);
		add(p2);
		
	
		
	}
	
	//���ӿ��� �� �ߴ� ȭ��
	void showgameover(boolean gameover) { 
		if(!gameover) {
			return;
		}
		p2.setVisible(false);
		arrp.setVisible(false);
		p3.setVisible(true);
		p3.setBackground(new Color(251,248,239));
		
		cmphigh();
		
		JLabel gameovertxt = new JLabel("Game Over");
		gameovertxt.setForeground(Color.RED);
		gameovertxt.setFont(new Font("�� M", Font.PLAIN, 45));
		gameovertxt.setHorizontalAlignment(SwingConstants.CENTER);
		gameovertxt.setBounds(114, 209, 246, 86);
		p3.add(gameovertxt);
		
		JLabel restartlabel = new JLabel("�ٽ� ���� �Ͻðڽ��ϱ�?");
		restartlabel.setHorizontalAlignment(SwingConstants.CENTER);
		restartlabel.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 22));
		restartlabel.setBounds(114, 303, 246, 42);
		p3.add(restartlabel);
		
		JButton yesButton = new JButton("YES");
		yesButton.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 15));
		yesButton.setBounds(135, 357, 84, 27);
		yesButton.setBackground(new Color(143,122,101));
		yesButton.setForeground(Color.white);
		p3.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.setFont(new Font("KoPub����ü Medium", Font.PLAIN, 15));
		noButton.setBounds(255, 357, 84, 27);
		noButton.setBackground(new Color(143,122,101));
		noButton.setForeground(Color.white);
		p3.add(noButton);
		
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p3.setVisible(false);
				p2.setVisible(true);
				arrp.setVisible(true);
				start();
			}
			
		});
		
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p3.setVisible(false);
				p1.setVisible(true);
			}
			
		});
		
		p3.setLayout(null);
		add(p3);
	}
	
	/*------�޼���-------*/
	
	//����� �Ǵ� ó�� ����
	void start() { 
		score = 0; //������ �ʱ�ȭ
		sLabel.setText(" ����          "+score);
		int n=0,re=0;

		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
			arr[i][j].setText(" ");
			
			}
		}
		
		while(n<2) {
			int r = (int)(Math.random()*16);
			if(n>0) {
				if(re==r)
					continue;
			}
			arr[r/4][r%4].setText("2");
			re=r;
			n++;
		}
		
		save();
		bkscore = 0;
		
	}
	
	//�� �迭�� ���� ���ڸ� ����, ��������
	void save() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
			        savearr[i][j].setText(arr[i][j].getText());     
			
			}
		}
		bkscore = score;
	}
	
	//�������·� ����
	void setback() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
			        arr[i][j].setText(savearr[i][j].getText());     
			
			}
		}
		if(score==high) { //���ھ �ְ������� ���
			high = bkscore;
			hsLabel.setText(" �ְ�����    "+high);
		}
		score = bkscore;
		sLabel.setText(" ����          "+score);
	}
	
	//�ְ��������� Ȯ���� ����
	void highscore() {
		if(score>high) {
			high = score;
			hsLabel.setText(" �ְ�����    "+high);
		}
	}
	
	//����ǥ ����
	void cmphigh() {
		int tmp,h;
	    h = score;
		for(int i=0; i<5; i++) {
			if(scorearr[i] < h) {
				tmp = h;
				h = scorearr[i];
				scorearr[i] = tmp;
				
			}	
		}
		for(int i=0; i<5; i++) {
			scLabel[i].setText(i+1+". "+scorearr[i]);
		}

		
	}
	
	//���� ����
	void lblank() { //���� ���� ����
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) { //ť�� �ֱ�
				if(arr[i][j].getText()!=" ")
					enque(arr[i][j].getText());
			}
			
			for(int j=0; j<4; j++) {
				if(!isEmpty())
					arr[i][j].setText(deque());
				else
					arr[i][j].setText(" ");
			}	
		}
		clearq();				
	}
	boolean lsum() {  
		int tmp = 0;
		boolean bsum = false;
		for(int i=0; i<4; i++) {
			for(int j=0; j<3; j++) {
				if(arr[i][j].getText()!=" ") {
					if(arr[i][j].getText().equals(arr[i][j+1].getText())) {
						tmp = Integer.parseInt(arr[i][j].getText())+Integer.parseInt(arr[i][j+1].getText());
						arr[i][j].setText(String.valueOf(tmp));
						arr[i][j+1].setText(" ");
						
						score+=tmp; //���� �߰�
						sLabel.setText(" ����          "+score);
						bsum = true;
					}
				}
			}		
		}
		return bsum;
	}
	
	//�����ʿ���
	void rblank() { //������ ���� ����
		for(int i=0; i<4; i++) {
			for(int j=3; j>-1; j--) { //ť�� �ֱ�
				if(arr[i][j].getText()!=" ")
					enque(arr[i][j].getText());
			}
			
			for(int j=3; j>-1; j--) {
				if(!isEmpty())
					arr[i][j].setText(deque());
				else
					arr[i][j].setText(" ");
			}
			
		}
		clearq();			
	}
	
	boolean rsum() {
		int tmp = 0;
		boolean bsum = false;
		for(int i=0; i<4; i++) {
			for(int j=3; j>0; j--) {
				if(arr[i][j].getText()!=" ") {
					if(arr[i][j].getText().equals(arr[i][j-1].getText())) {
						tmp = Integer.parseInt(arr[i][j].getText())+Integer.parseInt(arr[i][j-1].getText());
						arr[i][j].setText(String.valueOf(tmp));
						arr[i][j-1].setText(" ");
						
						score+=tmp; //���� �߰�
						sLabel.setText(" ����          "+score);
						bsum = true;
					}
				}
			}		
		}
		return bsum;
	}
	
	
	// ���� ����
	void upblank() { // ���� ���� ����
		for (int j = 0; j < 4; j++) { // ��
			for (int i = 0; i < 4; i++) { // ��, ť�� �ֱ�
				if (arr[i][j].getText() != " ")
					enque(arr[i][j].getText());

			}

			for (int i = 0; i < 4; i++) {
				if (!isEmpty()) {
					arr[i][j].setText(deque());
				} else
					arr[i][j].setText(" ");
			}

		}
		clearq();
	}

	boolean upsum() {
		int tmp = 0;
		boolean bsum = false;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				if (arr[i][j].getText() != " ") {
					if (arr[i][j].getText().equals(arr[i + 1][j].getText())) {
						tmp = Integer.parseInt(arr[i][j].getText()) + Integer.parseInt(arr[i + 1][j].getText());
						arr[i][j].setText(String.valueOf(tmp));
						arr[i + 1][j].setText(" ");

						score += tmp; // ���� �߰�
						sLabel.setText(" ����          " + score);
						bsum = true;
					}
				}
			}
		}
		return bsum;
	}

	// �Ʒ��� ����
	void dblank() { // �Ʒ��� ���� ����
		for (int j = 0; j < 4; j++) { // ��
			for (int i = 3; i > -1; i--) { // ��, ť�� �ֱ�
				if (arr[i][j].getText() != " ")
					enque(arr[i][j].getText());

			}

			for (int i = 3; i > -1; i--) {
				if (!isEmpty()) {
					arr[i][j].setText(deque());
				} else
					arr[i][j].setText(" ");
			}

		}
		clearq();
	}

	boolean dsum() {
		int tmp = 0;
		boolean bsum = false;
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				if (arr[i][j].getText() != " ") {
					if (arr[i][j].getText().equals(arr[i - 1][j].getText())) {
						tmp = Integer.parseInt(arr[i][j].getText()) + Integer.parseInt(arr[i - 1][j].getText());
						arr[i][j].setText(String.valueOf(tmp));
						arr[i - 1][j].setText(" ");

						score += tmp; // ���� �߰�
						sLabel.setText(" ����          " + score);
						bsum = true;
					}
				}
			}
		}
		return bsum;
	}

	// ���� ���� ����
	void randnum() { 
		if (isnumFull()) {
			return;
		}
		// Iterator r = set.iterator();
		Integer rand = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j].getText() == " ") {
					set.add(i * 4 + j);
					//System.out.println(i * 4 + j);
				}
			}
		}

		Collections.shuffle(set);
		rand = (Integer) set.get(0);
		arr[rand / 4][rand % 4].setText("2");
		set.clear();

	}
	
	//�迭�� �� á����
	boolean isnumFull() {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j].getText() != " ") { // ������ �ƴ� ��
					num++;
				}
			}
		}

		if (num == 16) {
			return true;
		} else {
			return false;
		}

	}

	//���ӿ��� Ȯ��
	boolean isgameOver() {
		boolean bsum = false; // ���� �� �ִ��� ����
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j].getText() != " ") {
					if (arr[i][j].getText().equals(arr[i][j + 1].getText())) {
						bsum = true;
					}
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				if (arr[i][j].getText() != " ") {
					if (arr[i][j].getText().equals(arr[i][j - 1].getText())) {
						bsum = true;
					}
				}
			}
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				if (arr[i][j].getText() != " ") {
					if (arr[i][j].getText().equals(arr[i + 1][j].getText())) {
						bsum = true;
					}
				}
			}
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				if (arr[i][j].getText() != " ") {
					if (arr[i][j].getText().equals(arr[i - 1][j].getText())) {
						bsum = true;
					}
				}
			}
		}

		return isnumFull() && (!bsum); //�� ���ְ� ���� �� ������ ���ӿ���
	}
	
	public static void main(String[] args) {
		
		new Project();
	}

}
