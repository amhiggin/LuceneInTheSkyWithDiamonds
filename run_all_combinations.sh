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

SCORING_MODEL="$(echo vsm)"
echo "Running experiments for $SCORING_MODEL"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "simple"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "stop"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "standard"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "morfologik"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_A"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_B"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_C"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "keyword"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "shingle"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "whitespace"


SCORING_MODEL="$(echo bm25)"
echo "Running experiments for $SCORING_MODEL"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "simple"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "stop"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "standard"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "morfologik"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_A"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_B"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_C"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "keyword"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "shingle"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "whitespace"

SCORING_MODEL="$(echo boolean)"
echo "Running experiments for $SCORING_MODEL"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "simple"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "stop"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "standard"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "morfologik"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_A"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_B"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_C"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "keyword"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "shingle"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "whitespace"


SCORING_MODEL="$(echo dirichlet)"
echo "Running experiments for $SCORING_MODEL"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "simple"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "stop"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "standard"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "morfologik"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_A"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_B"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_C"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "keyword"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "shingle"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "whitespace"

SCORING_MODEL="$(echo sweetspot)"
echo "Running experiments for $SCORING_MODEL"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "simple"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "stop"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "standard"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "morfologik"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_A"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_B"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "custom_C"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "keyword"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "shingle"
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL "whitespace"
