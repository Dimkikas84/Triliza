
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Triliza extends JApplet {

	public static boolean finish = false;

	private char SiraMou = 'X';
	//domh didiastatos pinakas
	//apo8hkeuei x kai o kai xrhsimeuei gia na ta emfanizei
	private Koutaki[][] koutaki = new Koutaki [3][3];
	//allhlepidrash tou soft me ton xrhsth gia na enhmerwnei poios paizei
	private JLabel KatastasiLabel = new JLabel ("Παίζει ο X");


	//************************************
	public void init() {  
		//dhmiourgia tou panel
		JPanel p = new JPanel();
		//dhmiourgia tou grid
		p.setLayout(new GridLayout(3,3,0,0));
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				//ka8e koutaki sto panel einai ena antikeimeno 
				//ths klashs Koutaki
				p.add(koutaki[i][j] = new Koutaki());

		KatastasiLabel.setBorder(new LineBorder(Color.red, 4));

		//bazei ta antikeimena sto panel
		this.getContentPane().add(p, BorderLayout.CENTER);
		this.getContentPane().add(KatastasiLabel, BorderLayout.SOUTH);
	}

	public static void main (String[] args) {
		//dhmiurgia olo to frame
		JFrame frame = new JFrame ("Tρίλιζα");
		//dhmiourgei ena antikeimeno ths klashs triliza (einai h apo panw)
		Triliza trl = new Triliza();

		frame.getContentPane().add(trl, BorderLayout.CENTER);    //getContentPane -> epistrafei to perioxomeno
		//kalei thn me8odo init
		trl.init();

		//orizei tis diastaseis
		frame.setSize(450, 450);
		//to 8etei orato
		frame.setVisible(true);		
	}
	//me8odos gemato h opoia epistrefei true h false
	//
	public boolean Gemato() {          
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				if (koutaki[i][j].getXaraktir() == ' ')
					return false;

		return true;
	}
	//elegxei an uparxei nikhths
	//logikh tou paixndiou 
	//me8odos
	public boolean Nikitis(char xaraktir) {
		for (int i=0; i<3; i++)
			if ((koutaki[i][0].getXaraktir() == xaraktir)
					&& (koutaki[i][1].getXaraktir() == xaraktir)
					&& (koutaki[i][2].getXaraktir() == xaraktir)) {
				Triliza.finish = true;
				return true;
			}

		for (int j=0; j<3; j++)
			if ((koutaki[0][j].getXaraktir() ==  xaraktir)
					&& (koutaki[1][j].getXaraktir() == xaraktir)
					&& (koutaki[2][j].getXaraktir() == xaraktir)) {
				Triliza.finish = true;
				return true;
			}

		if ((koutaki[0][0].getXaraktir() == xaraktir)
				&& (koutaki[1][1].getXaraktir() == xaraktir)
				&& (koutaki[2][2].getXaraktir() == xaraktir)) {
			Triliza.finish = true;
			return true;
		}

		if ((koutaki[0][2].getXaraktir() == xaraktir)
				&& (koutaki[1][1].getXaraktir() == xaraktir)
				&& (koutaki[2][0].getXaraktir() == xaraktir)) {
			Triliza.finish = true;
			return true;
		}

		return false;

	}

	//klash koutaki h opoia exei mono mia idiothta
	public class Koutaki extends JPanel implements MouseListener {
		//molis anoigei to paixnidi to koutaki na einai keno
		//kai pernei times o kai x
		private char xaraktir = ' ';
		//dhmiourgei to koutaki
		public Koutaki() {
			setBorder(new LineBorder(Color.black, 2));
			addMouseListener (this);
		}
		//epistrefei thn timh apo to koutaki
		public char getXaraktir() {
			return xaraktir;
		}
		//to 8etei sto xar
		public void setXaraktir (char xar) {
			xaraktir = xar;
			//to 3ana zwgrafizei
			repaint();
		}
		//me8odos dhmiourgeias x kai o 
		public void paintComponent (Graphics m ){
			super.paintComponent(m);

			if (xaraktir == 'X' ) {
				m.drawLine(10, 10, getSize().width-10, getSize().height-10);
				m.drawLine(getSize().width-10, 10, 10, getSize().height-10);
			} else if (xaraktir == 'O' ) {
				//kanei kuklo
				m.drawOval(10, 5, getSize().width-20, getSize().width-20);
			}
		}
		//me8odos gia to klik tou pontikiou
		public void mouseClicked(MouseEvent e) {
			//elegxei poianou seira einai na pai3ei
			if (xaraktir == ' ' && ! Triliza.finish) {
				if (SiraMou == 'X' ) {
					setXaraktir ('X');
					//allazei thn seira tou paikth
					SiraMou = 'O';
					KatastasiLabel.setText("Σειρά του 'O'");
					//elegxei panta an einai kapoios nikhths
					if (Nikitis ('X')){
						KatastasiLabel.setText(" Ο 'X' κέρδισε ");
						JOptionPane.showMessageDialog(null, "Ο 'X' κέρδισε");
					}
					//an einai isopalia
					else if (Gemato()){
						KatastasiLabel.setText("Νέο παιχνίδι!");			
					JOptionPane.showMessageDialog(null, "Ισοπαλία");
				
					}
		   }
				
				else if (SiraMou == 'O') {
					setXaraktir('O');
					SiraMou = 'X';
					KatastasiLabel.setText("Σειρά του 'X' ");
					if (Nikitis('O')){
						JOptionPane.showMessageDialog(null, "Ο 'O' Κέρδισε");
					}
					else if (Gemato()){
						KatastasiLabel.setText("Νέο παιχνίδι!");			
					JOptionPane.showMessageDialog(null, "Ισοπαλία");
				
					}
				}
			}
		}

		public void mousePressed(MouseEvent e){

		}
		public void mouseReleased(MouseEvent e){

		}
		public void mouseEntered(MouseEvent e){

		}
		public void mouseExited(MouseEvent e){

		}

	}




}
