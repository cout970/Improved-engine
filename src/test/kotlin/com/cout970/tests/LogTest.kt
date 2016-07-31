package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.util.EngineLogger

/**
 * Created by cout970 on 26/07/2016.
 */

fun main(args: Array<String>) {

    Loader.init()
    EngineLogger.info("info")
    EngineLogger.info("info")
    EngineLogger.info("info")
    EngineLogger.info("info")
    EngineLogger.info("info")
    EngineLogger.fatal("fatal")
    EngineLogger.fatal("fatal")
    EngineLogger.fatal("fatal")
    EngineLogger.fatal("fatal")
    EngineLogger.fatal("fatal")
    EngineLogger.error("Error")
    EngineLogger.error("Error")
    EngineLogger.error("Error")
    EngineLogger.error("Error")
    EngineLogger.error("Error")
    EngineLogger.debug("test")
    EngineLogger.debug("test")
    EngineLogger.debug("test")
    EngineLogger.debug("test")
    Loader.close()
}