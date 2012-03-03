package net.julnamoo.swm.herimarque.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.NoticeDAOImpl;
import net.julnamoo.swm.herimarque.model.Notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class NoticeServiceImpl {

	Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Resource(name="noticeDAO")
	NoticeDAOImpl noticeDAO;
	
	/**
	 * Convert json format String to the notice obejct
	 * and request insert the object to mongo
	 * @param notice
	 * @return Notice inserted notice Object
	 */
	public Notice addNotice(String notice)
	{
		Notice newNotice = new Gson().fromJson(notice, Notice.class);
		newNotice.setDate(DateFormat.getInstance().format(new Date()));
		noticeDAO.addNotice(newNotice);
		
		return newNotice;
	}
	
	/**
	 * Parse the date of lastupdate of user notice to date.
	 * Also, get the last updated notice date and compare of them.
	 * If the user's is out of date then return true, otherwise return false
	 * 
	 * @param lastupdate
	 * @return true if out of date
	 */
	public boolean checkNew(String lastupdate)
	{
		boolean result = true;
		
		Date usrNewest = null;
		
		//get the last updated
		String last = noticeDAO.getLastUpdateNoticeDate();
		Date hmqNewest = null;
		try 
		{
			usrNewest = DateFormat.getDateTimeInstance().parse(lastupdate);
			hmqNewest = DateFormat.getDateTimeInstance().parse(last);
			
			result = usrNewest.before(hmqNewest);
			
		} catch (ParseException e) 
		{
			e.printStackTrace();
			result = true;
		}
		return result;
	}
	
	/**
	 * Retrieve notices uploaded from the from date
	 * 
	 * @param from - start date
	 * @return List<Notice> - notices updated from 'from'
	 */
	public String getNotices(String from)
	{
		Date start;
		try 
		{
			start = DateFormat.getInstance().parse(from);
		} catch (ParseException e) 
		{
			e.printStackTrace();
			return "";
		}
		
		List<Notice> noticeList = noticeDAO.getNotices(start);
		
		return new Gson().toJson(noticeList);
	}
}
