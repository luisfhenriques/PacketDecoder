package siemenschallenge.packets;

public class LiteralPacket implements Packet {

	public final static int TYPE_ID = 4;
	public final static boolean HAS_SUB_PACKETS = false;

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
		return values[0];
	}

}
