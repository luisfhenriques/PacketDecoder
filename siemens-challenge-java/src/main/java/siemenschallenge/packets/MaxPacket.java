package siemenschallenge.packets;

public class MaxPacket implements Packet {

	public final static int TYPE_ID = 3;
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
		int result = values[0];

		for (int i = 1; i < values.length; i++) {
			if(values[i] > result) {
				result = values[i];
			}
		}

		return result;
	}
}