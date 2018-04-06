# LuceneInTheSkyWithDiamonds

A Lucene search engine for the document collection from the <i<8th Text-based REtrieval Conference (TREC)</i>, consisting of documents from the Los Angeles Times (<i>latimes</i>), the Foreign Broadcast Information Service (<i>fbis</i>, the Federal Register 1994 (<i>fr94</i>) and the Financial Times (<i>ft</i>) collections.

## Running the Search Engine
The <i>run_search_engine.sh</i> script will run the search engine. It will also generate the trec-eval results file, in the /output directory.

Arguments required:
* $1 = qrels file

## Implementation Details
The search engine was implemented using the <i>Lucene 7.2.1 API</i> in Java.

### Scoring Model
There were a combination of different scoring models used, bundled into the <i>MultiSimilarity</i> type. This includes use of the LMJelinekMercerSimilarity, LMDirichletSimilarity and BM25Similarity models.

### Text Pre-processing / Analyzers
A custom analyzer, com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzer, was used as the analyzer for text processing operations. It comprises of a number of filters:
* StandardFilter;
* LowerCaseFilter;
* StopFilter, using the StandardAnalyzer.ENGLISH_STOP_WORDS_SET;
* SnowballFilter, with an EnglishStemmer;
* PorterStemFilter;
* TrimFilter.


### Document Fields
There were a number of fields which were common across all of the documents: in particular, the 'Headline' and 'Text' fields.

### Field Boosts
Field boosts were used on the <i>Text</i> and <i>Headline</i> fields of the documents, with a weighting of 0.2 on the <i>Headline</i> and of 0.8 on the <i>Text</i>.

### Query Construction
The queries were constructed from a <i>topics</i> file.

The fields 'Title', 'Description' and 'Narrative' were all used to construct the query.

