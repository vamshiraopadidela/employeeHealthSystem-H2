package com.prokarma.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequenceGenerator {
private static final ReentrantLock Lock=new ReentrantLock();
private static Long employeeIdCache=(long)0;
private static Long emailIdCache=(long)0;
private static final Logger logger = LoggerFactory.getLogger(SequenceGenerator.class);

public static String generateEmployeeIdSequence() {
	long seqCache;
	String sequeId = null;
	try {
		Lock.lock();
		seqCache=SequenceGenerator.employeeIdCache;
		if(seqCache!=0) {
			seqCache=seqCache+1;
		}else {
			seqCache=1;
		}
		if(seqCache>=99999) {
			seqCache=0;
		}
		SequenceGenerator.employeeIdCache=seqCache;
		sequeId=appenDateTimeSequence(seqCache,"E");
	}catch(Exception e) {
		logger.error("unable to generate sequence generator",e);
	}finally {
		Lock.unlock();
	}
	return sequeId;
	
}

public static String generateEmailSequence() {
	long seqCache;
	String sequeId = null;
	try {
		Lock.lock();
		seqCache=SequenceGenerator.emailIdCache;
		if(seqCache!=0) {
			seqCache=seqCache+1;
		}else {
			seqCache=1;
		}
		if(seqCache>=99999) {
			seqCache=0;
		}
		SequenceGenerator.emailIdCache=seqCache;
		sequeId=appenDateTimeSequence(seqCache,"T");
	}catch(Exception e) {
		logger.error("unable to generate sequence generator",e);
	}finally {
		Lock.unlock();
	}
	return sequeId;
	
}
public static String appenDateTimeSequence(final long seqNo,final String prefix) {
	StringBuilder dateAppendar=null;
	String generatedSeq="";
	dateAppendar=new StringBuilder();
	dateAppendar.append(prefix);
	dateAppendar.append(getCurrentTimeString());
	generatedSeq=dateAppendar.append(seqNo).toString();
	return generatedSeq;
}


private static String getCurrentTimeString() {
	StringTokenizer dateTokenizer=null;
	StringBuilder dateAppendar=new StringBuilder();
	Date date=Calendar.getInstance().getTime();
	String dateString=new SimpleDateFormat("yy/MM/dd/HH/mm/ss").format(date);
	dateTokenizer=new StringTokenizer(dateString,"/");
	while(dateTokenizer.hasMoreTokens()) {
		dateAppendar.append(dateTokenizer.nextToken());
	}
	return dateAppendar.toString();
}
	
	
	
	
	

}
