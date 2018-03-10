#/bin/bash

SCORING_MODEL="$1"
ANALYZER="$2"
java -jar "$(pwd)"/target/Lucene_in_The_Sky_With_Diamonds-0.0.1-SNAPSHOT.jar $SCORING_MODEL $ANALYZER
