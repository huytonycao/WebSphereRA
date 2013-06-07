#!/bin/sh

if [ -f ./WSPMI.conf ]
then
   DATAFILE=`grep DATAFILE ./WSPMI.conf | grep ^DATAFILE | awk -F"=" '{print $2}'`
   ERRORFILE=`grep ERRORLOGFILE ./WSPMI.conf | grep ^ERRORLOGFILE | awk -F"=" '{print $2}'`

   if [ -f $DATAFILE ]
   then
     cp $DATAFILE $DATAFILE-`date +"%m%d%y-%H%M%S"`
   fi

   if [ -f $ERRORFILE ]
   then
     cp $ERRORFILE $ERRORFILE-`date +"%m%d%y-%H%M%S"`
   fi
fi

