package net.julnamoo.swm.herimarque.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.model.Position;
import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Julie_air
 *
 */

@Path("/h")
@Component
public class HeritageResourceImpl implements HeritageResource {

	Logger logger = LoggerFactory.getLogger(HeritageResourceImpl.class.getSimpleName());
	
	@Resource
	private ConstantsBean constants;
	
	@GET
	@Path("/g")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getHeritage(Item item) 
	{
		logger.debug("start handling getHeriage, itemCd:{}, crltsNo:{}", item.getItemCd(), item.getCrltsNo());
		Response response= Response.ok(new Item()).build();
		return response;
	}

	@GET
	@Path("/kind/{itemCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getKindHeritageList(@PathParam("itemCd") String itemCd) 
	{
		logger.debug("start handling getKindHeritageList, with {}", itemCd);
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Path("/age/{ageCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getAgeHeritageList(@PathParam("ageCd") String ageCd) 
	{
		logger.debug("start handling getAgeHeriategList, with {}", ageCd);
		
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Path("/area/{ctrdCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getAreaHeritageList(@PathParam("ctrdCd") String ctrdCd) 
	{
		logger.debug("start handling getAreaHeritageList, with {}", ctrdCd);
		
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Path("/near")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getNearHeritageList(Position position) 
	{
		logger.debug("start handling getNerHeritageList, with ({}, {})", position.getLatitude(), position.getLongitude());
		
		List<Item> list = new ArrayList<Item>();
		list.add(new Item());
		list.add(new Item());
		
		Response response = Response.ok(list).build();
		return response;
	}

}
