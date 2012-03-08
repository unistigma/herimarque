package net.julnamoo.swm.herimarque.service;

public interface NoticeService {

	/**
	 * Convert json format String to the notice obejct
	 * and request insert the object to mongo
	 * @param notice
	 * @return Notice inserted notice Object
	 */
	public abstract String addNotice(String title, String content);

	/**
	 * Parse the date of lastupdate of user notice to date.
	 * Also, get the last updated notice date and compare of them.
	 * If the user's is out of date then return true, otherwise return false
	 * 
	 * @param lastupdate
	 * @return true if out of date
	 */
	public abstract boolean checkNew(String lastupdate);

	/**
	 * Retrieve notices uploaded from the from date
	 * 
	 * @param from - start date
	 * @return List<Notice> - notices updated from 'from'
	 */
	public abstract String getNotices(String from);

}