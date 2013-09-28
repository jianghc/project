import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reversi implements ActionListener 
{
	//Set up variables
    int x = -1, y = -1;
    Drawing draw = new Drawing();
    boolean valid=false;
	int [][]backup=new int [8][8];
	int [][]box=new int [8][8];
	int num1;
	int num2;
	int turn=0;
	int p1=0,p2=0;
	int pr1,pr2;
	JButton undo = new JButton("Undo");
	JButton restart = new JButton("Restart");
	JButton help = new JButton("Help");
	boolean game = false;
	boolean can=true;
	int p;
	ImageIcon bg = new ImageIcon("bg1.jpg");
	ImageIcon head = new ImageIcon("21.png");
	boolean lol=true;
	int done=0;
	
	//Set up JFrame and buttons
   public Reversi()
   {
      JFrame frame = new JFrame("Reversi by Henry Jiang and David Xu");
      frame.getContentPane().add(draw);
		frame.setSize(615,700);
      draw.addMouseListener(new MouseListen());
		JPanel button = new JPanel();
		button.setLayout(new GridLayout (0,4));
		button.add(undo);		
		button.add(restart);
		button.add(help);
		undo.addActionListener(this);
		restart.addActionListener(this);
		help.addActionListener(this);
		frame.getContentPane().add(button,"South");
      frame.setVisible(true);
   }
   
	//run if button is clicked on
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == help)
		{
			JOptionPane.showMessageDialog(null,"Basic rules: The two players are represented by eithere black or white.\nThe perspective player must place a piece on a square so that there are only opponent's pieces between yours.\nAll these pieces in between are turned into your own colour. \nThis includes vertical, horizontal, and diagonal rows of pieces.\nIf there is no more possible moves, the game will automatically skip his turn and go the opponent.");
		}
		else if (e.getSource() == undo&&lol==true)
	 	{
			for (int i = 0;i < 8;i++)
				for (int j = 0;j < 8;j++)
					box[i][j] = backup[i][j];
			p1=pr1;
			p2=pr2;
			turn--;
			draw.repaint();
			lol=false;
		}
		else if (e.getSource() == restart&&lol==true)
		{
			for (int i = 0;i < 8;i++)
				for (int j = 0;j < 8;j++)
				{
					box[i][j] = 0;
					backup[i][j]=0;
				}
			turn=0;
			p1=2;
			p2=2;
			box[3][3] = 2;
			box[4][3] = 1;
			box[4][4] = 2;
			box[3][4] = 1;
			backup[3][3] = 2;
			backup[4][3] = 1;
			backup[4][4] = 2;
			backup[3][4] = 1;
			draw.repaint();
			lol=false;
		}
	}
	
   public static void main(String[] args)
   {
      Reversi uma = new Reversi();
   }
   
	//Set up graphics
   class Drawing extends JComponent
   {
      public void paint(Graphics g)
      {
			if (game==false)
			{
				p1=2;
				p2=2;
				turn=0;
				for (int i = 0;i < 8;i++)
					for (int j = 0;j < 8;j++)
					{
						box[i][j] = 0;
						backup[i][j]=0;
					}
				box[3][3] = 2;
				box[4][3] = 1;
				box[4][4] = 2;
				box[3][4] = 1;
				backup[3][3] = 2;
				backup[4][3] = 1;
				backup[4][4] = 2;
				backup[3][4] = 1;
				game=true;
				done=0;
			}

			g.drawImage(bg.getImage(),100,100, 400, 400,this);
			g.drawImage(head.getImage(),140,10, 320, 80,this);
			g.setColor(Color.black);
			g.drawString("Black: "+p1,10, 300);
			g.drawString("White: "+p2, 510, 300);
			g.drawString("turn: "+turn, 260, 550);
			if (turn%2==0)
				g.drawString("Player Black's turn", 15, 30);
			else 
				g.drawString("Player White's turn", 15, 30);
											
			for (int y=0;y<9;y++)
				g.drawLine(y*50+100,100,y*50+100,500);
			for (int u=0;u<9;u++)
				g.drawLine(100,u*50+100,500,u*50+100);

			g.fillOval((4*50)+110,(3*50)+110,30,30);
			g.fillOval((3*50)+110,(4*50)+110,30,30);
			g.setColor(Color.white);
			g.fillOval((3*50)+110,(3*50)+110,30,30);
			g.fillOval((4*50)+110,(4*50)+110,30,30);


			for (int k=0;k<8;k++)
				for (int j=0;j<8;j++)
				{
         		if (box[k][j]==1)
					{
						g.setColor(Color.black);
            		g.fillOval((j*50)+110,(k*50)+110,30,30);
					}
					if (box[k][j]==2)
					{
						g.setColor(Color.white);
            		g.fillOval((j*50)+110,(k*50)+110,30,30);
					}
					else 
					{
					}
				}
				
      }
   }
	
   class MouseListen extends MouseAdapter
   {
		//Performed if mouse is clicked
      public void mouseReleased(MouseEvent e)
      {
         x = e.getX();
         y = e.getY();
			System.out.println("------------------------------------------"+y);
			cvalid c1;
			cvalid c2;
				
			if (x>100&&x<500&&y>100&&y<500)
			{
				num2=(x-100)/50;
				num1=(y-100)/50;
				System.out.println(num1+" ************************ "+num2);
				//valid=true;
				for (int i=0;i<8;i++)
					for (int j=0;j<8;j++)
						backup[i][j]=box[i][j];
				if (turn %2==0&&box[num1][num2]==0)
				{
					c1=new cvalid(box,1,num1,num2);
					valid = c1.valid;
					if (valid)
					{
						box[num1][num2]=1;
						turn++;
						draw.repaint();
						if (done!=1)
							can = c1.can1(box,2);
						if (can==false&&done!=1)
						{
							JOptionPane.showMessageDialog(null,"Player White has no moves");
							turn++;
						}
						if (!lol)
							lol=true;
					}
					else
						JOptionPane.showMessageDialog(null,"Invalid move");
				}
				else if (turn%2!=0&&box[num1][num2]==0)
				{
					c2=new cvalid(box,2,num1,num2);
					valid=c2.valid;
			
					if (valid)
					{
						box[num1][num2]=2;
						turn++;
						draw.repaint();
						if (done!=1)
							can = c2.can1(box,1);
						if (can==false&&done!=1)
						{
							JOptionPane.showMessageDialog(null,"Player Black has no moves");
							turn++;
						}
						if (!lol)
							lol=true;
					}
					else
						JOptionPane.showMessageDialog(null,"Invalid move");
				}	
									
				pr1=p1;
				pr2=p2;
				p1=0;
				p2=0;
				for (int i=0;i<8;i++)
					for (int j=0;j<8;j++)
						if (box[i][j]==1)
							p1++;	
						else if (box[i][j]==2)
							p2++;
			}
			draw.repaint();
			
			done=0;
			for (int i=0;i<8&&done<3;i++)
					for (int j=0;j<8&&done <3;j++)
						if (box[i][j]==0)
						{
							done++;
						}
			if (p1==0||p2==0||done ==0) 
			{	
				if (p1>p2)
					JOptionPane.showMessageDialog(null,"Player Black has  won "+p1+" - "+p2);
				else if (p2>p1)
					JOptionPane.showMessageDialog(null,"Player White has  won "+p1+" - "+p2);
				else
					JOptionPane.showMessageDialog(null,"It is a tie"+p1+" - "+p2);
					
				if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    			{			
					game=false;
					draw.repaint();
				}
				else
					System.exit(0);
				
				//System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+done);
			}	
			
      }
   }
} 