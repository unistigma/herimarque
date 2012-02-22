package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Item;

public interface HeritageResource {

	public Response getHeritage(Item item); //get a heritage Item
	public Response getKindHeritageList(String itemCd); //retrieve heritage list with kind category
	public Response getAgeHeritageList(String ageCd); //retrieve heritage list with age category
	public Response getAreaHeritageList(String ctrdCd); // retrieve heritage list with area category //It is blocked by OPEN API ERROR
	public Response getNearHeritageList(String latitude, String longitude); // retrieve heritage list near the point
}
