package net.julnamoo.swm.herimarque.service;

import java.io.File;

import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqliteServiceImpl implements SqliteService {

	Logger logger = LoggerFactory.getLogger(SqliteServiceImpl.class);

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.SqliteService#saveNewVersion(java.lang.String)
	 */
	@Override
	public String saveNewVersion(String version)
	{
		PropertiesUtil.alterValueFromProperties("herimarque.properties", "dbVersion", version);
		logger.info("update the herimarque db to {} version.", version);
		return version;
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.SqliteService#itsNew(java.lang.String)
	 */
	@Override
	public boolean itsNew(String version)
	{
		String lastVersion = PropertiesUtil.getValueFromProperties("herimarque.properties", "dbVersion");
		float lval = Float.valueOf(lastVersion);
		float uval = Float.valueOf(version);

		logger.debug("compare the dbVersion user is {}, server is {}", version, lastVersion);
		return (lval > uval ? false : true);
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.SqliteService#getDB(java.lang.String)
	 */
	@Override
	public File getDB(String version)
	{
		String dbRepo = PropertiesUtil.getValueFromProperties("herimarque.properties", "db");
		String fileName = new StringBuilder(dbRepo).append(File.separatorChar).append(version).append(".db").toString();
		File file = new File(fileName);

		if(file.exists())
		{
			logger.debug("return the db file {}", file.getName());
			return file;
		}else
		{
			logger.debug("There is no db of the version {}, return null", version);
			return null;
		}
	}
}
