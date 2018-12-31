import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class PassOne {

	
	public void passOne() throws Exception
	{
		HashMap<String, Mnemonic> mot = getMnemonicOpcodeTable(new FileReader("optab.txt"));	// get the mot table
		BufferedReader src = new BufferedReader(new FileReader("src.txt"));						// read the src file


		String line;
		StringTokenizer token;

		while((line = src.readLine()) != null)
		{
			token = new StringTokenizer(line.toUpperCase());											// break line into tokens
			while(token.hasMoreTokens())
			{
				System.out.println("\n\n"+mot.containsKey(token.nextToken()));							// validate token
			}
		}

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
