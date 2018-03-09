set term png
set output "BM25_pr_graph.png"
set title "P/R Graph for ABC scoring model from trec_eval"
set ylabel "Precision"
set xlabel "Recall"
set xrange [0:1]
set yrange [0:1]
set xtics 0,.2,1
set ytics 0,.2,1
plot 'simple.dat' title "SimpleAnalyzer" with lines, \
     'standard.dat' title "StandardAnalyzer" with lines, \
     'stop.dat' title "StopAnalyzer" with lines, \
     'whitespace.dat' title "WhitespaceAnalyzer" with lines, \
     'custom.dat' title "CustomAnalyzer" with lines
