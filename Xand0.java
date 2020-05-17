package projects;
import java.util.*;


public class XandO {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char[][] layout = {{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		
		printLayout(layout);
	
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter position to place your marker");
			int posPlayer = sc.nextInt();
			
			while(playerPositions.contains(posPlayer) || cpuPositions.contains(posPlayer)) {
				System.out.println("Cannot place here, Enter a correct position.");
				posPlayer = sc.nextInt();
			}
			
			markerPlacement(layout,posPlayer,"player");
			
			String result = checkResult();
			if (result.length()>0) {
				System.out.println(result);
				break;
			}
			
			Random rnd = new Random();
			int posCpu = rnd.nextInt(9) + 1;
			
			while(playerPositions.contains(posCpu) || cpuPositions.contains(posCpu)) {
				posCpu = rnd.nextInt(9) + 1;
			}
			markerPlacement(layout,posCpu,"cpu");
			
			printLayout(layout);
			
			result = checkResult();
			if (result.length()>0) {
				System.out.println(result);
				break;
			}
		}
		
	}
		
	
	public static void printLayout(char[][] layout) {
		for (char[] row : layout) {
			for(char c : row)	{
				System.out.print(c);
			}
			System.out.println();
		}

	}
	
	public static void markerPlacement(char[][] layout, int pos, String user) {
		char marker = ' ';
		
		if(user.equals("player")) {
			marker = 'X';
			playerPositions.add(pos);
		}else if (user.contentEquals("cpu")) {
			marker = 'O';
			cpuPositions.add(pos); 
		}
		
		switch(pos) {
			case 1:
				layout[0][0] = marker;
				break;
			case 2:
				layout[0][2] = marker;
				break;
			case 3:
				layout[0][4] = marker;
				break;
			case 4:
				layout[2][0] = marker;
				break;
			case 5:
				layout[2][2] = marker;
				break;
			case 6:
				layout[2][4] = marker;
				break;
			case 7:
				layout[4][0] = marker;
				break;
			case 8:
				layout[4][2] = marker;
				break;
			case 9	:
				layout[4][4] = marker;
				break;
			default:
				break;
			}
	}
	
	public static String checkResult() {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		List<List> result = new ArrayList<List>();
		result.add(topRow);
		result.add(midRow);
		result.add(botRow);
		result.add(leftCol);
		result.add(midCol);
		result.add(rightCol);
		result.add(cross1);
		result.add(cross2);
		
		for(List l:result) {
			if(playerPositions.containsAll(l)) {
				return "---YOU WIN---";
			}else if(cpuPositions.containsAll(l)) {
				return "---CPU WINS---";
			}else if(playerPositions.size()+cpuPositions.size() == 9) {
				return "---TIE---";
			}
		}
		return "";
	}
}
