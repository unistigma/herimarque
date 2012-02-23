package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.model.Position;

public interface HeritageResource {

	public Response getHeritage(String itemCd, String crltsNo, String ctrdCd); //get a heritage Item
	public Response getKindHeritageList(String itemCd); //retrieve heritage list with kind category
	public Response getAgeHeritageList(String ageCd); //retrieve heritage list with age category
	public Response getAreaHeritageList(String ctrdCd); // retrieve heritage list with area category //It is blocked by OPEN API ERROR
	public Response getNearHeritageList(String latitude, String longitude); // retrieve heritage list near the point
}
