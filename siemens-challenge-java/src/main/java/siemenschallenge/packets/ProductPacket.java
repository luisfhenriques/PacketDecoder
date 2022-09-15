package siemenschallenge.packets;

public class ProductPacket implements Packet {

	public final static int TYPE_ID = 1;
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
		int result = 1;
		
		for (int i = 0; i < values.length; i++) {
			result *= values[i];
		}
		
		return result;
	}
}