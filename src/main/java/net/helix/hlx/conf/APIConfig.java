package net.helix.hlx.conf;

import java.util.List;

/**
 * Configurations for node API
 */
public interface APIConfig extends Config {
    /**
     * @return {@value Descriptions#PORT}
     */
    int getPort();
    /**
     * @return {@value Descriptions#API_HOST}
     */
    String getApiHost();
    /**
     * @return {@value Descriptions#REMOTE_LIMIT_API}
     */
    List<String> getRemoteLimitApi();
    /**
     * @return {@value Descriptions#MAX_FIND_TRANSACTIONS}
     */
    int getMaxFindTransactions();
    /**
     * @return {@value Descriptions#MAX_REQUESTS_LIST}
     */
    int getMaxRequestsList();
    /**
     * @return {@value Descriptions#MAX_GET_BYTES}
     */
    int getMaxBytes();
    /**
     * @return {@value Descriptions#MAX_BODY_LENGTH}
     */
    int getMaxBodyLength();
    /**
     * @return {@value Descriptions#REMOTE_AUTH}
     */
    String getRemoteAuth();
    /**
     * @return {@value Descriptions#IS_POW_DISABLED}
     */
    boolean isPoWDisabled();

    interface Descriptions {
        String PORT = "The port that will be used by the API.";
        String API_HOST = "The host on which the API will listen to. Set to 0.0.0.0 to accept any host.";
        String REMOTE_LIMIT_API = "Commands that should be ignored by API.";
        String REMOTE_AUTH = "A string in the form of <user>:<password>. Used to access the API";
        String MAX_FIND_TRANSACTIONS = "The maximal number of transactions that may be returned by the \"findTransactions\" API call. If the number of transactions found exceeds this number an error will be returned.";
        String MAX_REQUESTS_LIST = "The maximal number of parameters one can place in an API call. If the number parameters exceeds this number an error will be returned";
        String MAX_GET_BYTES = "The maximal number of bytes that may be returned by the \"getHBytes\" API call. If the number of transactions found exceeds this number an error will be returned.";
        String MAX_BODY_LENGTH = "The maximal number of characters the body of an API call may hold. If a request body length exceeds this number an error will be returned.";
        String REMOTE = "Open the API interface to any host. Equivalent to \"--api-host 0.0.0.0\"";
        String IS_POW_DISABLED = "If pow is disabled the node will not require a valid nonce.";
    }
}