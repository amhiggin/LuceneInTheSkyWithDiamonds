#/bin/bash

#######################################################
# When running this script, specify the RELATIVE path #
# of the qrels file.                                  #
# For example, if the qrels file is located in        #
#               ./qrels/trec-qrels                    #
# then specify                                        #
#               qrels/trec-qrels                      #
# as the argument for this script.                    #
#######################################################

QRELS_FILE="$1"
SCORING_MODEL="multi"
ANALYZER="custom_A"


# This will run the search engine with a user-input specifying the qrels file, the scoring model and the analyzer
# It will use the MultiSimilarity that was defined in the code (ApplicationLibrary.java) and the CustomAnalyzer.java analyzer
java -jar -XX:-UseGCOverheadLimit "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar "$(pwd)"/"$QRELS_FILE" $SCORING_MODEL $ANALYZER && \
	"$(pwd)"/trec_eval/trec_eval "$(pwd)"/"$QRELS_FILE" "$(pwd)"/output/"$SCORING_MODEL-$ANALYZER-results.txt" > "$(pwd)"/output/"trec-eval-$SCORING_MODEL-$ANALYZER-results.txt" && \
        echo "Trec eval results available in the output directory"


