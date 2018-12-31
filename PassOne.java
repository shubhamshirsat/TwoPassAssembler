import java.io.FileReader;


public class PassOne {

	
	public void passOne() throws Exception
	{	
		FileReader src =  new FileReader("src.txt");
		char buffer[] = new char[2000];
		src.read(buffer);
		System.out.println(buffer);


	}
}
