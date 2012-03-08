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
	public String addNotice(String notice)
	{
		logger.debug("request save the new notice");
		
		Notice newNotice = new Gson().fromJson(notice, Notice.class);
		newNotice.setDate(DateFormat.getInstance().format(new Date()));
		newNotice = noticeDAO.addNotice(newNotice);

		String msg = new Gson().toJson(newNotice);
		logger.debug("addNotice, return {}", msg);
		return msg;
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
			logger.debug("Compare the date usr:{} from mongo:{}", lastupdate, last);
			usrNewest = DateFormat.getDateTimeInstance().parse(lastupdate);
			hmqNewest = DateFormat.getDateTimeInstance().parse(last);
			
			result = usrNewest.before(hmqNewest);
			
		} catch (ParseException e) 
		{
			e.printStackTrace();
			result = true;
		}
		logger.debug("checkNew, return {}", result);
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
		logger.debug("request notices from {}", from);
		List<Notice> noticeList = noticeDAO.getNotices(from);
		
		return new Gson().toJson(noticeList);
	}
}
