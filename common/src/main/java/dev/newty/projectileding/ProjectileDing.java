package dev.newty.projectileding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectileDing
{
	public static final String MOD_ID = "projectileding";
	private static final Logger LOGGER = LogManager.getLogger("ProjectileDing");

	public static void init() {
		LOGGER.info("Hello from ProjectileDing!");
	}
}
