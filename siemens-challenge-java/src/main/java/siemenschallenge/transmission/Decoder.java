package siemenschallenge.transmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import siemenschallenge.packets.LiteralPacket;
import siemenschallenge.packets.MaxPacket;
import siemenschallenge.packets.MinPacket;
import siemenschallenge.packets.Packet;
import siemenschallenge.packets.ProductPacket;
import siemenschallenge.packets.SumPacket;

public class Decoder{


	private static Map<Integer, Packet> packetsMap = new HashMap<>();


	static {

		try {
			addPacket(new LiteralPacket());
			addPacket(new SumPacket());
			addPacket(new ProductPacket());
			addPacket(new MinPacket());
			addPacket(new MaxPacket());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void addPacket(Packet packet) throws Exception {
		if(packetsMap.containsKey(packet.getTypeID())) {
			throw new Exception("Duplicated Type ID: " + packet.getTypeID());
		}

		packetsMap.put(packet.getTypeID(), packet);
	}




	/**
	 * Decodes a given binary list.
	 * 
	 * @param binaryList The packet binary list.
	 * @return The packet literal value.
	 * @throws Exception Throws an Exception if there is no implementation for the decoded packet type id.
	 */
	public static int decode(List<String> binaryList) throws Exception {
		int packetTypeID = Utils.getPacketTypeID(binaryList);

		Packet packet = packetsMap.get(packetTypeID);

		if(packet == null) {
			throw new Exception("No implementation found for Packet Type ID: " + packetTypeID);
		}


		int[] values;

		if(packet.hasSubPackets()) {
			values = decodeMultiPacket(binaryList);
		} else {
			int value = decodeSinglePacket(binaryList);
			values = new int[]{value};
		}


		return packet.calculateResult(values);
	}

	
	private static int decodeSinglePacket(List<String> binaryList) throws Exception {
		StringBuilder literalValueSB = new StringBuilder();

		while(true) {
			String binaryGroup = Utils.getGroup(binaryList);
			boolean isLastGroup = binaryGroup.startsWith("0");

			literalValueSB.append(binaryGroup.substring(1));

			if(isLastGroup) {
				break;
			}
		}

		return Integer.parseInt(literalValueSB.toString(), 2);
	}


	private static int[] decodeMultiPacket(List<String> binaryList) throws Exception {
		int subPackets = Utils.getNbrOfSubPackets(binaryList);


		int[] subPacketsResults = new int[subPackets];

		for (int i = 0; i < subPacketsResults.length; i++) {
			subPacketsResults[i] = Decoder.decode(binaryList);
		}

		return subPacketsResults;
	}

}
