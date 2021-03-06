package net.helix.pendulum.service.dto;

import net.helix.pendulum.service.API;

import java.util.List;

/**
 *
 * Contains information about the result of a successful {@code attachToTangle} API call.
 * @see {@link API#attachToTangleStatement} for how this response is created.
 *
 */
public class AttachToTangleResponse extends AbstractResponse {

	/**
	 * List of the attached transactions in hexadecimal representation (txs).
	 * The last 96 bytes of the return value consist of the:
	 * <code>trunkTransaction</code> + <code>branchTransaction</code> + <code>nonce</code>.
	 */
	private List<String> txs;

	/**
	 * Creates a new {@link AttachToTangleResponse}
	 * @param elements {@link #transactionStrings}
	 * @return an {@link AttachToTangleResponse} filled with the txs
	 */
	public static AbstractResponse create(List<String> elements) {
		AttachToTangleResponse res = new AttachToTangleResponse();
		res.txs = elements;
		return res;
	}

	/**
	 *
	 * @return {@link #txs}
	 */
	public List<String> getTransactionStrings() {
		return txs;
	}
}
