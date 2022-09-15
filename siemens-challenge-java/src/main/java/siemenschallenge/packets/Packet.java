package siemenschallenge.packets;

/**
 * 
 * @author Luis Henriques
 *
 */
public interface Packet {
	
	/**
	 * Get packet type id.
	 * 
	 * @return packet type id.
	 */
	int getTypeID();
	
	/**
	 * Check if the packet has sub-packets.
	 * 
	 * @return true if the packet has sub-packets, otherwise false.
	 */
	boolean hasSubPackets();
	
	/**
	 * Calculate the packet final result.
	 * 
	 * @param values The decoded values.
	 * @return the packet final result.
	 */
	int calculateResult(int[] values);

}
