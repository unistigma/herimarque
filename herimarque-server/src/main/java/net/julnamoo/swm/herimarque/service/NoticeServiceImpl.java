package net.julnamoo.swm.herimarque.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.NoticeDAO;
import net.julnamoo.swm.herimarque.model.Notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class NoticeServiceImpl implements NoticeService {

	Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Resource(name="noticeDAO")
	NoticeDAO noticeDAO;
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.NoticeService#addNotice(java.lang.String, java.lang.String)
	 */
	@Override
	public String addNotice(String title, String content)
	{
		logger.debug("request save the new notice");
		
		Notice newNotice = new Notice();
		newNotice.setTitle(title);
		newNotice.setContent(content);
		newNotice.setDate(DateFormat.getInstance().format(new Date()));
		newNotice = noticeDAO.addNotice(newNotice);

		String msg = new Gson().toJson(newNotice);
		logger.debug("addNotice, return {}", msg);
		return msg;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.NoticeService#checkNew(java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.NoticeService#getNotices(java.lang.String)
	 */
	@Override
	public String getNotices(String from)
	{
		logger.debug("request notices from {}", from);
		List<Notice> noticeList = noticeDAO.getNotices(from);
		String msg = new Gson().toJson(noticeList); 
		logger.debug("return the notice list: {}", msg);
		return msg;
	}
}
