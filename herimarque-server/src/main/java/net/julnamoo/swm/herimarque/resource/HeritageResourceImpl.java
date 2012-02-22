package net.julnamoo.swm.herimarque.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Julie_air
 *
 */

@Path("/h")
@Component
public class HeritageResourceImpl implements HeritageResource {

	@Resource
	private ConstantsBean constants;
	
	@GET
	@Path("/g")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getHeritage(Item item) 
	{
		Response response= Response.ok(new Item()).build();
		return response;
	}

	@GET
	@Path("/kind")
	@Override
	public Response getKindHeritageList(String itemCd) 
	{
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Path("/age")
	@Override
	public Response getAgeHeritageList(String ageCd) 
	{
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Path("/area")
	@Override
	public Response getAreaHeritageList(String ctrdCd) 
	{
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Path("/near")
	@Override
	public Response getNearHeritageList(String latitude, String longitude) 
	{
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

}
