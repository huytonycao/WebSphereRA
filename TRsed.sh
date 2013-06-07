#!/bin/sh

#create a TRCLASSPATH with all requirement jar files

ODIR=$PWD
file1=$ODIR/TRWSPM_1_
file2=$ODIR/TRWSPM_2_

cd $ROOTDIR/lib; ls -l *.jar | sed 's/  */ /g' | cut -d' ' -f9 | sed 's/^/lib\//g' | sed 's/$/:/g' > $file1
cd $ROOTDIR/plugins; ls -l *.jar | sed 's/  */ /g' | cut -d' ' -f9 | sed 's/^com/plugins\/com/g' | sed 's/^org/plugins\/org/g' | sed 's/$/:/g' >> $file1

for i in `cat $file1` ;
do
  echo "$ROOTDIR/$i" >> $file2
done

for i in `cat $file2` ;
do
  TRCLASSPATH=$TRCLASSPATH$i
done

export TRCLASSPATH;

rm -f $file1
rm -f $file2

cd $ODIR
