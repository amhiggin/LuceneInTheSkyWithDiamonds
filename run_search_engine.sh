#/bin/bash

SCORING_MODEL="$1"
ANALYZER="$2"
java -jar "$(pwd)"/target/IR_Document_Analysis-0.0.1-SNAPSHOT.jar $SCORING_MODEL $ANALYZER
