# LuceneInTheSkyWithDiamonds

A Lucene search engine for the document collection from the <i>8th Text-based REtrieval Conference (TREC)</i>, consisting of documents from the Los Angeles Times (<i>latimes</i>), the Foreign Broadcast Information Service (<i>fbis</i>, the Federal Register 1994 (<i>fr94</i>) and the Financial Times (<i>ft</i>) collections.

## Running the Search Engine
The <i>run_search_engine.sh</i> script will run the search engine and output results in <i>./output/multi-custom_A-results.txt</i>. 

It will also generate the trec-eval results file, which can be found in <i>./output/trec-eval-multi-custom_A-results.txt</i>.

Arguments required:
* $1 = qrels file


<i>Note: it is also possible to experiment with the other analyzers and scoring models that were experimented with during the course of the project, using the <b>run_custom_search_engine.sh</b> script. 

These can be seen by running 'run_custom_search_engine.sh' without specifying any parameters, which will display the <u>usage</u> information.</i>


## Implementation Details
The search engine was implemented using the <i>Lucene 7.2.1 API</i> in Java.

### Scoring Model
There were a combination of different scoring models used, bundled into the <i>MultiSimilarity</i> type. This includes use of the LMJelinekMercerSimilarity, and LMDirichletSimilarity models.


### Text Pre-processing / Analyzers
A custom analyzer, com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzer, was used as the analyzer for text processing operations. It comprises of a number of filters:
* StandardFilter;
* LowerCaseFilter;
* StopFilter, using the StandardAnalyzer.ENGLISH_STOP_WORDS_SET;
* SnowballFilter, with an EnglishStemmer;
* PorterStemFilter;
* TrimFilter.

There were a number of other analyzers experimented with but not used in the final search engine, including two other custom analyzers. 

The optimal value of lambda for the LMJelinekMercerSimilarity was found to be 0.75. The default parameters were assumed for the LMDirichletSimilarity model.

### Document Fields
There were a number of fields which were common across all of the documents: in particular, the 'Headline' and 'Text' fields. These were the two fields used to deliver the most performant search engine for the datasets.

Date fields were also explored as part of the project: However, overall they disimproved the performance of the search engine and so were omitted from the final implementation.

### Field Boosts
Field boosts were used on the <i>Text</i> and <i>Headline</i> fields of the documents, with a weighting of 0.2 on the <i>Headline</i> and of 0.8 on the <i>Text</i>.

### Query Construction
The queries were constructed from a <i>topics</i> file.

The fields 'Title', 'Description' and 'Narrative' were all used to construct the query.

The 'Narrative' field in particular was parsed based on the occurrence of phrases such as 'not relevant', 'will discuss', 'must cite' (for relevant documents) and 'is not relevant, 'are irrelevant', etc. for irrelevant documents.


### Query Expansion
Query expansion was used to improve the search engine results. This expansion was performed only on the 'Text' field of the documents.

An optimal number of documents to use in query expansion was found to be 4 based on experimentation.
