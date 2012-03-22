package net.julnamoo.swm.herimarque.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Notice;

public interface NoticeDAO {

	@PostConstruct
	public abstract void setCollectionName();

	/**
	 * add notice object to mongo
	 * @param newNotice
	 */
	public abstract Notice addNotice(Notice newNotice);

	/**
	 * Retrieve the newest notice
	 * @return newes notice object
	 */
	public abstract Notice getNewest();

	/**
	 * The date of the newest notice
	 * @return the formated date string of the newset notice(yyy/MM/dd/HH:mm:ss)
	 */
	public abstract String getLastUpdateNoticeDate();

	/**
	 * return the newest 10 notices in the order from start date 
	 * @param start
	 * @return noticeList with 10 
	 */
	public abstract List<Notice> getNotices(String start);

}