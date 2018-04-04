#/bin/bash

grep 'map' output/trec_eval-vsm-simple-results.txt >> maps.txt

#/bin/bash


SCORING_MODEL="vsm"
(echo "---- $SCORING_MODEL custom_A ----" &&  grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt) >> maps.txt
(echo "---- $SCORING_MODEL custom_B ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL custom_C ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL simple ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL standard ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL stop ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL shingle ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL keyword ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL whitespace ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL morfologik ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt)  >> maps.txt


SCORING_MODEL="bm25"
(echo "---- $SCORING_MODEL custom_A ----" &&  grep 'map ' "$(pwd)"/output/$SCORING_MODEL-custom_A-results.txt) >> maps.txt
(echo "---- $SCORING_MODEL custom_B ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL custom_C ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL simple ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL standard ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL stop ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL shingle ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL keyword ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL whitespace ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL morfologik ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt)  >> maps.txt



SCORING_MODEL="boolean"
(echo "---- $SCORING_MODEL custom_A ----" &&  grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt) >> maps.txt
(echo "---- $SCORING_MODEL custom_B ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL custom_C ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL simple ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL standard ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL stop ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL shingle ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL keyword ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL whitespace ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL morfologik ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt)  >> maps.txt



SCORING_MODEL="dirichlet"
(echo "---- $SCORING_MODEL custom_A ----" &&  grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt) >> maps.txt
(echo "---- $SCORING_MODEL custom_B ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL custom_C ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL simple ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL standard ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL stop ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL shingle ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL keyword ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL whitespace ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL morfologik ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt)  >> maps.txt



SCORING_MODEL="sweetspot"
(echo "---- $SCORING_MODEL custom_A ----" &&  grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_A-results.txt) >> maps.txt
(echo "---- $SCORING_MODEL custom_B ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_B-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL custom_C ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-custom_C-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL simple ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-simple-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL standard ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-standard-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL stop ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-stop-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL shingle ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-shingle-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL keyword ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-keyword-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL whitespace ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-whitespace-results.txt)  >> maps.txt
(echo "---- $SCORING_MODEL morfologik ----" && grep 'map ' "$(pwd)"/output/trec_eval-$SCORING_MODEL-morfologik-results.txt)  >> maps.txt


