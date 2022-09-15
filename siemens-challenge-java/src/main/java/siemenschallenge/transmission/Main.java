package siemenschallenge.transmission;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {

			if(args == null) {
				throw new Exception("Program arguments can't be null.");
			}

			if(args.length == 0) {
				throw new Exception("Program is expecting one argument.");
			}

			if(args[0] == null) {
				throw new Exception("Program expected argument can't be null.");
			}

			if(args[0].isEmpty()) {
				throw new Exception("Program expected argument can't be empty.");
			}

			String hexString = args[0]; 
			List<String> binaryList = Utils.convertHexToBinary(hexString);
			int decodedValue = Decoder.decode(binaryList);
			System.out.println(decodedValue);

		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
