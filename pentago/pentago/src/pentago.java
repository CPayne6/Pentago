import java.util.Scanner;


public class pentago {

	public static void main(String[] args) {
		String play="y";
		Scanner in=new Scanner(System.in);
		while(play.equals("y")){
			int win=0;
			boolean first=true;
			System.out.println("Welcome to pentago!!!\n"
					+ "Player 1 is @ and player 2 is X\n"
					+ "The board looks like this:\n\n"
					+ "     ----- -----\n"
					+ "6-> |O O O|O O O|\n"
					+ "5-> |O O O|O O O|\n"
					+ "4-> |O O O|O O O|\n"
					+ "     ===== =====\n"
					+ "3-> |O O O|O O O|\n"
					+ "2-> |O O O|O O O|\n"
					+ "1-> |O O O|O O O|\n"
					+ "     ----- -----\n"
					+ "     ^ ^ ^ ^ ^ ^\n"
					+ "     1 2 3 4 5 6\n\n"
					+ "player 1 goes first"
					+ "\nenter a pair of digits in the form: X Y");
				int turn=1;
				int[][] board=new int[6][6];
			while(win==0){
				if(!first){
					System.out.println("\nplease enter where you want to go in the form: X Y");
				}
				int tempX=in.nextInt()-1;
				int tempY=in.nextInt()-1;
				while(tempX<0 ||tempX>5 || tempY<0 || tempY>5){
					System.out.println("please enter values that are greater than 0 and less than 7");
					tempX=in.nextInt()-1;
					tempY=in.nextInt()-1;
				}
				while(board[tempX][tempY]!=0){
					System.out.println("that spot had already been taken, please choose an other spot");
					tempX=in.nextInt()-1;
					tempY=in.nextInt()-1;
				}
				if(turn==1)
					board[tempX][tempY]=1;
				else
					board[tempX][tempY]=2;
				win=findWinner(board);
				if(!first && win==0){
					printBoard(board);
					System.out.println("which square do you want to spin? \nnote that you may not spin the square that you just placed\n(1=topleft 2=topright 3=bottomleft 4=bottomright)");
					int square=in.nextInt();
					while((square==1 && tempX<=2 && tempY>=3)||(square==2 && tempX>=3 && tempY>=3)||(square==3 && tempX<=2 && tempY<=2)||(square==4 && tempX>=3 && tempY<=2)){
						System.out.println("you may not turn the square that you placed your character in\nplease enter an other square:");
						square=in.nextInt();
					}
					while(square>4 || square<1){
						System.out.println("please enter a value between or including 1-4");
						square=in.nextInt();
					}
					System.out.println("\nwhich way would you like to spin it? (1=clockwise 2=counter clockwise)");
					int spin=in.nextInt();
					while(spin!=1 && spin!=2){
						System.out.println("please enter a 1 or a 2");
						spin=in.nextInt();
					}
					board=spinBoard(board,spin,square);
					win=findWinner(board);
				}
				else
					first=false;
				printBoard(board);
				if(turn==1 && win==0){
					turn=2;
					System.out.println("player 2's turn");
				}
				else if(win==0){
					turn=1;
					System.out.println("player 1's turn");
				}
				else if(win==1){
					System.out.println("\nPLAYER 1 WINS!!!!!!!!!");
				}
				else{
					System.out.println("\nPLAYER 2 WINS!!!!!!!!!");
				}
			}
			System.out.println("Would you like to play again? (y=yes n=no)");
			play=in.next();
			while(!play.equals("n") && !play.equals("y")){
				System.out.println("please enter a 'y' or an 'n'");
				play=in.next();
			}
		}
		in.close();
	}
	public static void printBoard(int[][] board){
		String output="     ----- -----\n";
		for(int y=5;y>=0;y--){
			output+=""+(y+1)+"-> |";
			for(int x=0;x<=5;x++){
				if(x==2 || x==5){
					if(board[x][y]==0)
						output+="O|";
					else if(board[x][y]==1)
						output+="@|";
					else
						output+="X|";
				}
				else{
					if(board[x][y]==0)
						output+="O ";
					else if(board[x][y]==1)
						output+="@ ";
					else
						output+="X ";
				}
			}
			if(y==3)
				output+="\n     ===== =====\n";
			else if(y==0)
				output+="\n     ----- -----\n     ^ ^ ^ ^ ^ ^\n     1 2 3 4 5 6\n";
			else
				output+="\n";
		}
		System.out.println(output);
	}
	public static int[][] spinBoard(int[][] board,int spin,int square){
		if(square==1){
			if(spin==1){
				int temp=board[0][5];
				board[0][5]=board[0][3];
				board[0][3]=board[2][3];
				board[2][3]=board[2][5];
				board[2][5]=temp;
				temp=board[0][4];
				board[0][4]=board[1][3];
				board[1][3]=board[2][4];
				board[2][4]=board[1][5];
				board[1][5]=temp;
			}
			else{
				int temp=board[0][3];
				board[0][3]=board[0][5];
				board[0][5]=board[2][5];
				board[2][5]=board[2][3];
				board[2][3]=temp;
				temp=board[1][5];
				board[1][5]=board[2][4];
				board[2][4]=board[1][3];
				board[1][3]=board[0][4];
				board[0][4]=temp;
			}
		}
		else if(square==2){
			if(spin==1){
				int temp=board[3][5];
				board[3][5]=board[3][3];
				board[3][3]=board[5][3];
				board[5][3]=board[5][5];
				board[5][5]=temp;
				temp=board[3][4];
				board[3][4]=board[4][3];
				board[4][3]=board[5][4];
				board[5][4]=board[4][5];
				board[4][5]=temp;
			}
			else{
				int temp=board[3][3];
				board[3][3]=board[3][5];
				board[3][5]=board[5][5];
				board[5][5]=board[5][3];
				board[5][3]=temp;
				temp=board[4][5];
				board[4][5]=board[5][4];
				board[5][4]=board[4][3];
				board[4][3]=board[3][4];
				board[3][4]=temp;
			}
		}
		else if(square==3){
			if(spin==1){
				int temp=board[0][2];
				board[0][2]=board[0][0];
				board[0][0]=board[2][0];
				board[2][0]=board[2][2];
				board[2][2]=temp;
				temp=board[0][1];
				board[0][1]=board[1][0];
				board[1][0]=board[2][1];
				board[2][1]=board[1][2];
				board[1][2]=temp;
			}
			else{
				int temp=board[0][0];
				board[0][0]=board[0][2];
				board[0][2]=board[2][2];
				board[2][2]=board[2][0];
				board[2][0]=temp;
				temp=board[1][2];
				board[1][2]=board[2][1];
				board[2][1]=board[1][0];
				board[1][0]=board[0][1];
				board[0][1]=temp;
			}
		}
		else if(square==4){
			if(spin==1){
				int temp=board[5][2];
				board[5][2]=board[3][2];
				board[3][2]=board[3][0];
				board[3][0]=board[5][0];
				board[5][0]=temp;
				temp=board[3][1];
				board[3][1]=board[4][0];
				board[4][0]=board[5][1];
				board[5][1]=board[4][2];
				board[4][2]=temp;
			}
			else{
				int temp=board[5][2];
				board[5][2]=board[5][0];
				board[5][0]=board[3][0];
				board[3][0]=board[3][2];
				board[3][2]=temp;
				temp=board[4][2];
				board[4][2]=board[5][1];
				board[5][1]=board[4][0];
				board[4][0]=board[3][1];
				board[3][1]=temp;
			}
		}
		return board;
	}
	public static int findWinner(int[][] board){
		for(int x=0;x<6;x++){
			for(int y=0;y<6;y++){
				if(y<2 && x<2){
					if((board[x][y]==1 && board[x+1][y+1]==1 &&board[x+2][y+2]==1 &&board[x+3][y+3]==1 &&board[x+4][y+4]==1)||(board[x+4][y]==1 && board[x+3][y] ==1 && board[x+2][y]==1 && board[x+1][y]==1 && board[x][y]==1)){
						return 1;
					}
					else if((board[x][y]==2 && board[x+1][y+1]==2 &&board[x+2][y+2]==2 &&board[x+3][y+3]==2 &&board[x+4][y+4]==2)||(board[x+4][y]==2 && board[x+3][y] ==2 && board[x+2][y]==2 && board[x+1][y]==2 && board[x][y]==2)){
						return 2;
					}
				}
				if(y<2){
					if(board[x][y]==1 && board[x][y+1] ==1 && board[x][y+2]==1 && board[x][y+3]==1 && board[x][y+4]==1){
						return 1;
					}
					else if(board[x][y]==2 && board[x][y+1] ==2 && board[x][y+2]==2 && board[x][y+3]==2 && board[x][y+4]==2){
						return 2;
					}
				}
				if(x<2){
					if(board[x][y]==1 && board[x+1][y]==1 && board[x+2][y]==1 && board[x+3][y]==1 && board[x+4][y]==1){
						return 1;
					}
					else if(board[x][y]==2 && board[x+1][y]==2 && board[x+2][y]==2 && board[x+3][y]==2 && board[x+4][y]==2){
						return 2;
					}
				}
			}
		}
		return 0;
	}
}
