package net.julnamoo.swm.herimarque.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.model.Position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * 
 * @author Julie_air
 *
 */

@Path("/h")
@Component
public class HeritageResourceImpl implements HeritageResource {

	Logger logger = LoggerFactory.getLogger(HeritageResourceImpl.class.getSimpleName());
	
//	@Resource
//	private ConstantsBean constants;
	
	@GET
	@Path("/g/{itemCd}/{ctrdCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getHeritage(@PathParam("itemCd") String itemCd, @QueryParam("crltsNo") String crltsNo, @PathParam("ctrdCd") String ctrdCd) 
	{
		logger.debug("start handling getHeriage, itemCd:{}, crltsNo:{}", itemCd, crltsNo);
		Response response= Response.ok(new Item()).build();
		return response;
	}

	@GET
	@Path("/kind/{itemCd}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public Response getKindHeritageList(@PathParam("itemCd") String itemCd) 
	{
		logger.debug("start handling getKindHeritageList, with {}", itemCd);
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		String msg = new Gson().toJson(list);
		Response response = Response.ok(msg).build();
		return response;
	}

	@GET
	@Path("/age/{ageCd}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public Response getAgeHeritageList(@PathParam("ageCd") String ageCd) 
	{
		logger.debug("start handling getAgeHeriategList, with {}", ageCd);
		
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		String msg = new Gson().toJson(list);
		Response response = Response.ok(msg).build();
		return response;
	}

	@GET
	@Path("/area/{ctrdCd}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public Response getAreaHeritageList(@PathParam("ctrdCd") String ctrdCd) 
	{
		logger.debug("start handling getAreaHeritageList, with {}", ctrdCd);
		
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		String msg = new Gson().toJson(list);
		Response response = Response.ok(msg).build();
		return response;
	}

	@GET
	@Path("/near/{latitude}/{longitude}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getNearHeritageList(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude) 
	{
		Position position = new Position();
		position.setLatitude(latitude);
		position.setLongitude(longitude);
		
		logger.debug("start handling getNerHeritageList, with ({}, {})", position.getLatitude(), position.getLongitude());
		
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		String msg = new Gson().toJson(list);
		Response response = Response.ok(msg).build();
		return response;
	}

}
