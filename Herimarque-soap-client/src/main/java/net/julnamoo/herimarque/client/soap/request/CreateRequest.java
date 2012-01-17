package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.sbe.api.IntegrationClientAPI;

/**
 * Create the soap request for culture soap request api
 * @author Julie
 *
 */

public class CreateRequest {

	/** For client setup **/
	private IntegrationClientAPI apiClient;
	
	/** **/
	public CreateRequest() throws Exception
	{
		apiClient = new IntegrationClientAPI();
	}
	
}
