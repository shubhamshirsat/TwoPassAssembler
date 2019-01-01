import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class PassOne {

	
	public void passOne() throws Exception
	{
		HashMap<String, Mnemonic> mot = getMnemonicOpcodeTable(new FileReader("/home/student/workspace/TwoPassAssembler/src/optab.txt"));	// get the mot table
		HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
		
		BufferedReader src = new BufferedReader(new FileReader("/home/student/workspace/TwoPassAssembler/src/src.txt"));						// read the src file


		String line,token;
		StringTokenizer stringTokenizer;
		Mnemonic mnemonic = null;
		boolean syntaxError = false;
		int locationCounter = 0;

		while((line = src.readLine()) != null)
		{
			stringTokenizer = new StringTokenizer(line.toUpperCase());											// break line into tokens
			
			while(stringTokenizer.hasMoreTokens())
			{
				token = stringTokenizer.nextToken();
				
				if((!token.equalsIgnoreCase("START")) && locationCounter == 0)		// if first token is not start then it is syntax error
				{
					syntaxError = true;
					break;			
				}
					
								
				if(mot.containsKey(token))							// validate token
				{
						mnemonic = mot.get(token);
						
						switch(mnemonic.getStatement())
						{
							case "AD":								// if token is assembler directive
										if(token.equalsIgnoreCase("START"))
										{
											locationCounter = Integer.parseInt(stringTokenizer.nextToken())-1;
										}
									break;
									
							case "IS":								// if token is imperative statement
									System.out.println("Imperative statement: "+token+"\noprand1: "+stringTokenizer.nextToken()+"\noperand2: "+stringTokenizer.nextToken());
									break;
									
							case "DL":
									break;
						
						}
				}
				else
				{
					System.out.println("Token is symbol: "+token);
					symbolTable.put(token, locationCounter);					// if token is label then add to symbol table
				}
			}
			locationCounter++;
			if(syntaxError)
			{
				System.out.println("\n\nSTART not found");	
				break;
			}
		}
		
		System.out.println("\n\nlocation counter: "+locationCounter);

	}


	// Reads the mnemonic opcode file and construct the MOT table
	public HashMap<String, Mnemonic> getMnemonicOpcodeTable(FileReader optab) throws Exception
	{
		HashMap<String, Mnemonic> mot = new HashMap<String, Mnemonic>();
		BufferedReader buffer = new BufferedReader(optab);
		String line;
		String key;
		StringTokenizer lineTokens;

		while ((line = buffer.readLine()) != null)				// read bufferred file line by line
		{
			lineTokens = new StringTokenizer(line);		// break line into tokens
			while(lineTokens.hasMoreTokens())
			{
				key = lineTokens.nextToken();
				mot.put(key,new Mnemonic(key, lineTokens.nextToken(),lineTokens.nextToken(),Integer.parseInt(lineTokens.nextToken())));
			}
		}
		System.out.println("\n\nMnemonic opcode table constructed ...");
		return mot;			// return the constructed mot table
	}
}
