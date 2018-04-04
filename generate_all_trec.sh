#/bin/bash


SCORING_MODEL="vsm"
echo "Generating trec results for $SCORING_MODEL"
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_A-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_B-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_C-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-simple-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-standard-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-stop-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-shingle-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-keyword-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-whitespace-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-morfologik-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt


SCORING_MODEL="bm25"
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_A-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_B-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_C-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-simple-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-standard-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-stop-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-shingle-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-keyword-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-whitespace-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-morfologik-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt

SCORING_MODEL="boolean"
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_A-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_B-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_C-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-simple-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-standard-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-stop-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-shingle-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-keyword-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-whitespace-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-morfologik-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt

SCORING_MODEL="dirichlet"
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_A-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_B-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_C-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-simple-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-standard-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-stop-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-shingle-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-keyword-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-whitespace-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-morfologik-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt

SCORING_MODEL="sweetspot"
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_A-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_B-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-custom_C-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-simple-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-standard-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-stop-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-shingle-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-keyword-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-whitespace-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt
"$(pwd)"/trec_eval/trec_eval "$(pwd)"/qrels.assignment2.part1 "$(pwd)"/output/$SCORING_MODEL-morfologik-results.txt >> "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt
