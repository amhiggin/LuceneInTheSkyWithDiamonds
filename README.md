# LuceneInTheSkyWithDiamonds

A Lucene search engine for the document collection from the <i<8th Text-based REtrieval Conference (TREC)</i>, consisting of documents from the Los Angeles Times (<i>latimes</i>), the Foreign Broadcast Information Service (<i>fbis</i>, the Federal Register 1994 (<i>fr94</i>) and the Financial Times (<i>ft</i>) collections.

## Running the Search Engine
The <i>run_search_engine.sh</i> script will run the search engine.
* <b>TODO note what analyzer and scoring model is being used</b>

## Implementation Details
The search engine was implemented using the <i>Lucene 7.2.1 API</i> in Java.

### Scoring Model
To be finalised.
Explain tuning of the scoring model if using LMJelinekMercerSimilarity.

### Text Pre-processing / Analyzers
To be finalised

### Document Fields
There were a number of fields which were common across all of the documents: in particular, the 'Headline' and 'Text' fields.

### Field Boosts
Field boosts 

### Query Construction
The queries were constructed from a <i>topics</i> file.

The fields 'Title', 'Description' and 'Narrative' were all used to construct the query.

