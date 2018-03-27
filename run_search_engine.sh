#/bin/bash

#######################################################
# When running this script, specify the RELATIVE path #
# of the qrels file. 				      #
# For example, if the qrels file is located in        #
#    		./qrels/trec-qrels		      #
# then specify					      #
#    		qrels/trec-qrels		      #
# as the argument for this script.	      	      #
#######################################################

QRELS_FILE="$1"

java -jar "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" -XX:-UseGCOverheadLimit
