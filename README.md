# Text-Based-Unigram-search-Engine
# Aim:-
The aim of this project is to build a prototype of a search engine which works on XML page.One demo XML page has been added to this project as database.xml.This is a kind of search cum sort text search engine. In which we sort all the documents based on the relevance.Than given a query, it retrieves relevant ranked documents and their titles using index. 

I have tried to maintain a data structure which can keep information of all the documents id, a word present in. Example Raja is present in 1, 12,100 etc.

Most crucial thing is how to select the top 10 documents to show to the users. This can be done by a concept called as indexing.
This search engine is full text search engine unlike exact value search in DBMS. Here without using the concept of machine learning I have tried to make it a better search engine by applying word similarity like jump and leap are same and stemming like jumping to jump etc.

# Inverted Indexing:- 
An inverted index contains all the unique words that appear in any document and each word, a list of documents in which it appears. Inverted index is a kind of matrix which keeps record of every different word. Matrix also stores about all the documents in which the particular word occurred.

# teXt Pre-ProcessIng:
1. Tokenization  
White Space tokenization: Simply through white space. Hyphen, apostrophes are not considered at all.  
Issue with white space tokenization: “I’m” is one single word but it actually mean “I am”. Similarly a name of a person like “Raja Kumar” should be treated as one word but white space tokenization will count it as two. There are lot many problems with identification of symbols too in white space tokenization.
2.Case folding: Entire input either in small letter or capital letter. In general into small letter word so that two words like RAJA and Raja will be treated same.
3.Remove the stop words: Remove words like is, a, and, the etc. They are not important for searching. 
4.Stemming: To find out the root word.Here I have used Porter stemming algorithm.
 
# Xml: 
XML is extensible that is it allows us to create and use our own tags if server and clients has no problem. Actually XML carries data. It is a public standard.
Example: 
<Movie>       
  <Name> The  pursuits of happiness</Name>       
  <Actor> Will Smith </Actor>        
  <Rating> 8/10 </Rating> 
</Movie>

#  saX Parser saX Parser:
It is a package available with java when we download it. We can use it to parse XML documents. It has broadly two parts one is parser and other is handler. Now on the basis of requirement parser will call handler example: StartDocumentHandler(). Handler can be modified according to our use.
 
 Q) Why do I use SAX parser? 
 Answer: Other parser first tries to get entire XML file into main memory and then make a parse tree of it but then Sax parser it read the document in byte by byte fashion. Depending on the byte it will call the appropriate handler.
 
 Working: 
 1. We take the Our XML pages as object  
 1) Split the text into tokens 
 2) Remove the stop words 
 3) Stem the words 
 4) Maintain where each word has occurred (using Hash Map) like a key-value pair.
 We put word as the key and an array of integer as value. The array at each index contains count of that word present in Title, and other tags.
 Example Let the word has occurred a lot many times in the title so its priority will be given high than the words which has occurred in text and all others.
 
  # term frequency:
  It is a numerical statistics that is intended to reflect how important the word is to the document in the collection.
  
  

