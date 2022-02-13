/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package riolog;


import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;


/**
 *
 **/
public class RioLogger
{

   // LOGGER Setting for default level
   //private static final Level defaultLevel = Level.DEBUG.level;
   private static final Level defaultLevel = Level.TRACE;

   private static final String logMountPoint = "/media/sda1/";
   private static final String logDirName = "501logs/";
   private static final String logFileName = "logfile-";
   private static final String logFileExtension = ".log";
   private static final String logFile;
   private static final boolean useLogFile;

   static
   {
      final File logDir = new File( logMountPoint + logDirName );
      useLogFile = logDir.exists();
      System.out.println( "useLogFile=" + useLogFile );
      if ( useLogFile )
      {
         final StringBuilder buf = new StringBuilder();
         buf.append( logMountPoint );
         buf.append( logDirName ).append( logFileName );
         final int keepBufLen = buf.length();
         for ( int i = 1; i <= 100; i++ )
         {
            buf.setLength( keepBufLen );
            buf.append( String.format( "%03d", i ) );
            buf.append( logFileExtension );
            System.out.println( "[" + i + "]:" + buf.toString() );
            final File logFile = new File( buf.toString() );
            if ( !logFile.exists() )
            {
               break;
            }
         }
         // If we get to 100; we are just going to re-use it regardless
         logFile = buf.toString();
      }
      else
      {
         logFile = "";
      }
   }


   public static PKLogger getLogger( String loggerName )
   {
      final ch.qos.logback.classic.Logger logger =
         (ch.qos.logback.classic.Logger) LoggerFactory.getLogger( loggerName );

      final LoggerContext lc =
         (LoggerContext) LoggerFactory.getILoggerFactory();

      /*
       * Can't share encoders, so each appender needs to have it's own
       */

      {
         final PatternLayoutEncoder ple = new PatternLayoutEncoder();
         ple.setPattern(
            "%date{HH:mm:ss.SSS} %level [%thread] %logger{10}[%line] %msg%n" );
         ple.setContext( lc );
         ple.start();

         final ConsoleAppender< ILoggingEvent > consoleAppender =
            new ConsoleAppender<>();
         consoleAppender.setEncoder( ple );
         consoleAppender.setContext( lc );
         consoleAppender.start();
         //
         logger.addAppender( consoleAppender );
      }

      if ( useLogFile )
      {
         final PatternLayoutEncoder ple = new PatternLayoutEncoder();
         ple.setPattern(
            "%date{HH:mm:ss.SSS} %level [%thread] %logger{10}[%line] %msg%n" );
         ple.setContext( lc );
         ple.start();

         final FileAppender< ILoggingEvent > fileAppender =
            new FileAppender<>();
         fileAppender.setFile( logFile );
         fileAppender.setEncoder( ple );
         fileAppender.setContext( lc );
         fileAppender.setAppend( true );
         fileAppender.start();
         //
         logger.addAppender( fileAppender );
      }

      setLevel( logger, defaultLevel );
      logger.setAdditive( false ); /* set to true if root should log too */

      return new PKLogger( logger );
   }


   public static void setLevel( Logger logger, Level level )
   {
      ( (ch.qos.logback.classic.Logger) logger ).setLevel( level.level );
   }

}
