package siemenschallenge.transmission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
	
	private static Map<Character, String> hexToBinaryMap = new HashMap<>();
	
	static {
		hexToBinaryMap.put('0', "0000");
		hexToBinaryMap.put('1', "0001");
		hexToBinaryMap.put('2', "0010");
		hexToBinaryMap.put('3', "0011");
		hexToBinaryMap.put('4', "0100");
		hexToBinaryMap.put('5', "0101");
		hexToBinaryMap.put('6', "0110");
		hexToBinaryMap.put('7', "0111");
		hexToBinaryMap.put('8', "1000");
		hexToBinaryMap.put('9', "1001");
		hexToBinaryMap.put('A', "1010");
		hexToBinaryMap.put('B', "1011");
		hexToBinaryMap.put('C', "1100");
		hexToBinaryMap.put('D', "1101");
		hexToBinaryMap.put('E', "1110");
		hexToBinaryMap.put('F', "1111");
	}
	
	/**
	 * Packet type id (Number of bits).
	 */
	public final static int PACKET_TYPE_ID_BITS = 3;
	
	/**
	 * Number of sub packets (Number of bits).
	 */
	public final static int NBR_OF_SUB_PACKETS_BITS = 4;
	
	/**
	 * Packet group (Number of bits).
	 */
	public final static int PACKET_GROUP_BITS = 5;
	
	/**
	 * Converts the given hexadecimal string to binary.
	 * 
	 * @param hexString The given hexadecimal string.
	 * @return A list of strings containing each bit value.
	 * @throws Exception Throws an Exception if the character is not a valid hexadecimal character.
	 */
	public static List<String> convertHexToBinary(String hexString) throws Exception {
		StringBuilder binaryPacketSB = new StringBuilder();
		
		for (int i = 0; i < hexString.length(); i++) {
			char c = hexString.charAt(i);
			
			if(hexToBinaryMap.containsKey(c)) {
				binaryPacketSB.append(hexToBinaryMap.get(c));
			} else {
				throw new Exception("Invalid Hex Character '" + c + "'");
			}
		}
		
		String binaryString = binaryPacketSB.toString();
		
		List<String> binaryList = new ArrayList<>();
		for (int i = 0; i < binaryString.length(); i++) {
			binaryList.add(binaryString.charAt(i)+"");
		}
		
		return binaryList;
	}


	/**
	 * Gets the packet type id.
	 * 
	 * @param binaryList The packet binary list.
	 * @return The type id.
	 * @throws Exception Throws an Exception if the binary list doesn't have at least 3 bits.
	 */
	public static int getPacketTypeID(List<String> binaryList) throws Exception {
		
		if(binaryList.size() < PACKET_TYPE_ID_BITS) {
			throw new Exception("Error: Unable to extract packet type id. Binary packet length = " + binaryList.size());
		}
		
		String binaryString = removeAndReturn(binaryList, PACKET_TYPE_ID_BITS);
		return Integer.parseInt(binaryString, 2);
	}


	/**
	 * Gets the number of sub-packets of a packet.
	 * 
	 * @param binaryList The packet binary list.
	 * @return The number of sub-packets.
	 * @throws Exception Throws an Exception if the binary list doesn't have at least 4 bits.
	 */
	public static int getNbrOfSubPackets(List<String> binaryList) throws Exception {
		if(binaryList.size() < NBR_OF_SUB_PACKETS_BITS) {
			throw new Exception("Error: Unable to extract packet type id. Binary packet length = " + binaryList.size());
		}
			
		String binaryString = removeAndReturn(binaryList, NBR_OF_SUB_PACKETS_BITS);
		return Integer.parseInt(binaryString, 2);
	}
	
	/**
	 * Gets a packet group.
	 * 
	 * @param binaryList The packet binary list.
	 * @return The packet group.
	 * @throws Exception Throws an Exception if the binary list doesn't have at least 5 bits.
	 */
	public static String getGroup(List<String> binaryList) throws Exception {
		if(binaryList.size() < PACKET_GROUP_BITS) {
			throw new Exception("Error: Unable to extract group. Binary packet length = " + binaryList.size());
		}
			
		return removeAndReturn(binaryList, PACKET_GROUP_BITS);
	}
	
	private static String removeAndReturn(List<String> binaryList, int size) {
		String result = "";

		for (int i = 0; i < size; i++) {
			result += binaryList.remove(0);
		}

		return result;
	}
}
