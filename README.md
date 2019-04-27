# Citation Metadata to Excel ReadMe

  This document serves as a guide on how to use the program and how to achieve the best results.
  
  **Download link --->** [HERE](https://github.com/csumsl/eec-493-f18/blob/master/Metadata%20to%20Excel.jar)
  
## Table of Contents:

1. [Requirements](https://github.com/csumsl/eec-493-f18/blob/master/README.md#requirements)
2. [Retrieving citation file off of Web of Science](https://github.com/csumsl/eec-493-f18/blob/master/README.md#retrieving-citation-file-off-of-web-of-science)
3. [Program Instructions](https://github.com/csumsl/eec-493-f18/blob/master/README.md#program-instructions)
4. [Other Recommendations](https://github.com/csumsl/eec-493-f18/blob/master/README.md#other-recommendations)
5. [FAQs](https://github.com/csumsl/eec-493-f18/blob/master/README.md#faqs)

## Requirements:

1. [Java](https://www.java.com/en/)
2. Internet Connection
    - Used to fetch affiliations if applicable
    
## Retrieving citation file off of Web of Science:

1. First, select the articles you want to export as a .bib file
  ![Alt text](https://i.imgur.com/d4aPcGc.png)

2. Second, select the setting "Save to Other File Formats"
  ![Alt text](https://i.imgur.com/XzT2Ikb.png)

3. Lastly, make sure the file format is set to BibTeX
  ![Alt text](https://i.imgur.com/iwEQAxZ.png)
  The file will be located in your downloads folder if done correctly. 
  
  **MAKE SURE THIS FILE IS NAMED savedrecs.bib DO NOT ALTER THE FILENAME. This is only for .bib files coming off of Web of Science!**
    
## Program Instructions:

1. Select a folder which contains the citation files
    - Acceptable files include **.bib** and **.ris**
    ![Alt text](https://i.imgur.com/UDnykkJ.png)
    ![Alt text](https://i.imgur.com/dFzzwXX.png)
    
2. Choose name and location for excel spreadsheet
    ![Alt text](https://i.imgur.com/32GmT2u.png)
    
3. Let program run until completion
    ![Alt text](https://i.imgur.com/7wa0P8R.png)

4. Navigate to the excel spreadsheet location and you're done!

## Other Recommendations:

  For the best results, go to an article's webpage directly and retrieve the citation file (.bib or .ris). Here are some examples of how to export the citation off a website. Most websites will have some system like the ones listed below.

**Example 1:**
![Alt text](https://i.imgur.com/RBah8Wu.png)
![Alt text](https://i.imgur.com/IQWEk8y.png)

**Example 2:**
![Alt text](https://i.imgur.com/P6vFXur.png)
![Alt text](https://i.imgur.com/KQjkTBX.png)

**Example 3:**
![Alt text](https://i.imgur.com/BUcqT7X.png)
![Alt text](https://i.imgur.com/Jcx3LIr.png)

  By doing this, the program has an easier time checking for affiliations.
  
## FAQs:

  Q: Does the program grab affiliations?

  A: Yes, if the article's host website has a supported parser the program will attempt to parse for the author affiliations  (institution and email if applicable). The three websites that are supported at the moment are https://www.asme.org/, https://ieeexplore.ieee.org/, and https://www.springer.com/us. If a website doesn't have a supported parser that means the website has incompatible HTML code that can't be parsed in a trivial matter.
  
  Q: The output excel file is a .csv, will that cause issues?
  
  A: No, you can use it as a normal excel file. .csv stands for comma separated values and it's how the program creates the excel spreadsheet from within the program. If you want to convert it over to another excel format such as .xlsx, just open the .csv file that was just created and save it as any type of excel filetype you want.
  
  Q: Can the folder that contains the citation files include both .bib and .ris files?
  
  A: Yes, put as many .bib and .ris files in the folder as you want. Very large amounts, like thousands, may take a while to execute through, but it will do the job.
  
  Q: Can I have multiple savedrecs.bib files from Web of Science in one folder?
  
  A: No. The program only supports ONE savedrecs.bib file at a time.
  
  Q: Can the savedrecs.bib from Web of Science be renamed to something else?
  
  A: No, it has to stay named exactly like: savedrecs.bib
