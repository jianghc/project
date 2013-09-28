class cvalid
{
	boolean valid=false;
	boolean n=false,s=false,e=false,w=false,ne=false,nw=false,se=false,sw=false;
	int[][] a;
	boolean can=false;
	//Check if the player made a valid move
	public cvalid(int [][]g,int p,int x, int y)
	{
		a=g; 
		for (int i=x+1;i<7&&a[i][y]!=p&&a[i][y]!=0&&s==false;i++)
			if (a[i+1][y]==p)
			{
				s=true;
				valid =true;
				a[x][y]=p;
			}
		
		if (s==true)
		{
			for (int k=x+1;a[k][y]!=p&&a[k][y]!=0;k++)
			{
				a[k][y]=p;
			}
		}
			
		for (int i=x-1;i>0&&a[i][y]!=p&&a[i][y]!=0&&n==false;i--)
			if (a[i-1][y]==p)
			{
				n=true;
				a[x][y]=p;
				valid =true;
			}	
		
		if (n==true)
		{
			for (int k=x-1;a[k][y]!=p&&a[k][y]!=0;k--)
			{
				a[k][y]=p;
			}
		}
			
		for (int i=y+1;i<7&&a[x][i]!=p&&a[x][i]!=0&&e==false;i++)
			if (a[x][i+1]==p)
			{
				e=true;
				a[x][y]=p;
				valid =true;
			}
			
		if (e==true)
		{
			for (int k=y+1;a[x][k]!=p&&a[x][k]!=0;k++)
			{
				a[x][k]=p;
			}
		}
			
		for (int i=y-1;i>0&&a[x][i]!=p&&a[x][i]!=0&&w==false;i--)
			if (a[x][i-1]==p)
			{
				w=true;
				a[x][y]=p;
				valid =true;
			}
			
		if (w==true)
		{
			for (int k=y-1;a[x][k]!=p&&a[x][k]!=0;k--)
			{
				a[x][k]=p;
			}
		}
		
		for (int i=x-1,j=y+1;i>0&&j<7&&a[i][j]!=p&&a[i][j]!=0&&ne==false;i--,j++)
			if (a[i-1][j+1]==p)
			{
				ne=true;
				a[x][y]=p;
				valid=true; 
			}
			
		if (ne==true)
		{
			for (int k=x-1,h=y+1;a[k][h]!=p&&a[k][h]!=0;k--,h++)
			{
				a[k][h]=p;
			}
		}
		
		for (int i=x-1,j=y-1;i>0&&j>0&&a[i][j]!=p&&a[i][j]!=0&&nw==false;i--,j--)
			if (a[i-1][j-1]==p)
			{
				nw=true;
				a[x][y]=p;
				valid=true; 
			}
			
		if (nw==true)
		{
			for (int k=x-1,h=y-1;a[k][h]!=p&&a[k][h]!=0;k--,h--)
			{
				a[k][h]=p;
			}
		}
		
		for (int i=x+1,j=y-1;i<7&&j>0&&a[i][j]!=p&&a[i][j]!=0&&sw==false;i++,j--)
			if (a[i+1][j-1]==p)
			{
				sw=true;
				a[x][y]=p;
				valid=true; 
			}
		
		if (sw==true)
		{
			for (int k=x+1,h=y-1;a[k][h]!=p&&a[k][h]!=0;k++,h--)
			{
				a[k][h]=p;
			}
		}
		
		for (int i=x+1,j=y+1;i<7&&j<7&&a[i][j]!=p&&a[i][j]!=0&&se==false;i++,j++)
			if (a[i+1][j+1]==p)
			{
				se=true;
				a[x][y]=p;
				valid=true; 
			}
		
		if (se==true)
		{
			for (int k=x+1,h=y+1;a[k][h]!=p&&a[k][h]!=0;k++,h++)
			{
				a[k][h]=p;
			}
		}	
	}
	//Check if any move is available for the player
	public boolean can1(int [][]a,int p )
	{
		for (int i=0;i<8&&can==false;i++)
			for (int j=0;j<8&&can ==false;j++)
			{
				if (a[i][j]==p)
				{
					if (i>1)
						if (a[i-1][j]!=p&&a[i-1][j]!=0)
							for (int k=i-2;k>=0&&a[k][j]!=p;k--)
								if (a[k][j]==0)
								{	
									can=true;
								}
							
					if (j<6)
						if (a[i][j+1]!=p&&a[i][j+1]!=0)
							for (int k=j+2;k<8&&a[i][k]!=p;k++)
								if (a[i][k]==0)
								{
									can=true;
								}
				
					if (i<6)
						if (a[i+1][j]!=p&&a[i+1][j]!=0)
							for (int k=i+2;k<8&&a[k][j]!=p;k++)
								if (a[k][j]==0)
								{
									can=true;
								}
				
					if (j>1)
						if (a[i][j-1]!=p&&a[i][j-1]!=0)
							for (int k=j-2;k>=0&&a[i][k]!=p;k--)
								if (a[i][k]==0)
								{
									can=true;
								}
				
					if (i>1&&j>1)
						if (a[i-1][j-1]!=p&&a[i-1][j-1]!=0)
							for (int k=i-2,h=j-2;k>=0&&h>=0&&a[k][h]!=p;k--,h--)
								if (a[k][h]==0)
								{
									can = true; 
								}
				
					if (i>1&&j<6)
						if (a[i-1][j+1]!=p&&a[i-1][j+1]!=0)
							for (int k=i-2,h=j+2;k>=0&&h<8&&a[k][h]!=p;k--,h++)
								if (a[k][h]==0)
								{
									can = true; 
								}
											
					if (i<6&&j>1)
						if (a[i+1][j-1]!=p&&a[i+1][j-1]!=0)
							for (int k=i+2,h=j-2;k<8&&h>=0&&a[k][h]!=p;k++,h--)
								if (a[k][h]==0)
								{
									can = true; 
								}
								
					if (i<6&&j<6)
						if (a[i+1][j+1]!=p&&a[i+1][j+1]!=0)
							for (int k=i+2,h=j+2;k<8&&h<8&&a[k][h]!=p;k++,h++)
								if (a[k][h]==0)
								{
									can = true; 
								}
				}
			}
			return can;
	}	
}