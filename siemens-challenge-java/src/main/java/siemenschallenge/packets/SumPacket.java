package siemenschallenge.packets;

public class SumPacket implements Packet {

	public final static int TYPE_ID = 0;
	public final static boolean HAS_SUB_PACKETS = true;

	@Override
	public int getTypeID() {
		return TYPE_ID;
	}

	@Override
	public boolean hasSubPackets() {
		return HAS_SUB_PACKETS;
	}

	@Override
	public int calculateResult(int[] values) {
		int result = 0;
		
		for (int i = 0; i < values.length; i++) {
			result += values[i];
		}
		
		return result;
	}
}