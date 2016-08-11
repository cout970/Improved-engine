package com.cout970.engine.log

import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.PropertyConfigurator


/**
 * Created by cout970 on 25/07/2016.
 */

object EngineLogger {

    private val LOGGER = LogManager.getLogger("Engine")

    fun init() {
        PropertyConfigurator.configure(Resource(EngineDomain, "logger.cfg").inputStream)
    }

    fun info(str: String, vararg args: Any?) {
        LOGGER.log(Level.INFO, str.format(args))
    }

    fun debug(vararg obj: Any?) {
        val s = StringBuilder()
        var i = obj.size
        for (o in obj) {
            s.append(o)
            i--
            if (i > 0) {
                s.append(", ")
            }
        }
        LOGGER.log(Level.DEBUG, s.toString())
    }

    fun error(str: String, vararg args: Any?) {
        LOGGER.log(Level.ERROR, str.format(args))
    }

    fun fatal(str: String, vararg args: Any?) {
        LOGGER.log(Level.FATAL, str.format(args))
    }
}