# Citation Metadata to Excel ReadMe

  This document serves as a guide on how to use the program and how to achieve the best results.
  
  **Download link --->** [HERE](https://github.com/csumsl/eec-493-f18/blob/master/Metadata%20to%20Excel.jar)
  
## Table of Contents:

1. [Requirements](https://github.com/csumsl/eec-493-f18/blob/master/README.md#requirements)
2. [Retrieving citation file off of Web of Science](https://github.com/csumsl/eec-493-f18/blob/master/README.md#retrieving-citation-file-off-of-web-of-science)
3. [Program Instructions](https://github.com/csumsl/eec-493-f18/blob/master/README.md#program-instructions)
4. [File Handling](https://github.com/csumsl/eec-493-f18/blob/master/README.md#file-handling)
5. [Other Recommendations](https://github.com/csumsl/eec-493-f18/blob/master/README.md#other-recommendations)
6. [FAQs](https://github.com/csumsl/eec-493-f18/blob/master/README.md#faqs)

## Requirements:

1. [Java](https://www.java.com/en/)
2. Internet Connection
    - Used to fetch affiliations (Supported websites: asme, springer, and ieeexplore)
    
## Retrieving citation file off of Web of Science:

1. First, select the articles you want to export as a .bib file
  ![Alt text](https://i.imgur.com/d4aPcGc.png)

2. Second, select the setting "Save to Other File Formats"
  ![Alt text](https://i.imgur.com/XzT2Ikb.png)

3. Lastly, make sure the file format is set to BibTeX
  ![Alt text](https://i.imgur.com/iwEQAxZ.png)
  The file will be located in your downloads folder if done correctly. 
  
  **MAKE SURE THIS FILE IS NAMED savedrecs.bib DO NOT ALTER THE FILENAME. This is only for .bib files coming off of Web of Science! Anything like "savedrecs(1).bib" is not accepted and will be ignored. Only modify the name of this specific .bib file if it's not named "savedrecs.bib". Again, this only goes for .bib files coming from Web of Science! (See [File handling](https://github.com/csumsl/eec-493-f18/blob/master/README.md#file-handling) section for more information)**
    
## Program Instructions:

1. Select a folder which contains the citation files (See [File handling](https://github.com/csumsl/eec-493-f18/blob/master/README.md#file-handling) section for more information)
    - Acceptable files include **.bib** and **.ris**
    ![Alt text](https://i.imgur.com/UDnykkJ.png)
    ![Alt text](https://i.imgur.com/dFzzwXX.png)
    
2. Choose name and location for excel spreadsheet
    ![Alt text](https://i.imgur.com/32GmT2u.png)
    
3. Let program run until completion
    ![Alt text](https://i.imgur.com/7wa0P8R.png)

4. Navigate to the excel spreadsheet location and you're done!

## File Handling:

  This section talks about how your folder with the .bib/.ris files should be set up. It's best to create a folder that's specifically for your .bib/.ris files. If this folder contains other types of files they will just be ignored, but just to be on the safe side, make sure this folder contains only .bib/.ris files.
  
  This folder can contain as many .bib/.ris files as you want. During any particular execution of the program, it will always parse the files in the folder and export them to excel. If you don't want to include the previous files that you may have already exported, simply remove them from the folder and place the new .bib/.ris files that need exporting in the folder. If you want to combine information of two spreadsheets, just copy one spreadsheet over to the other.
  
  **While you can have as many .bib/.ris files as you want in the folder, you can only have one Web of Science "savedrecs.bib" in there are a time! You may combine random assortments of .bib/.ris files from other websites along with your "savedrecs.bib", but you CANNOT have multiple "savedrecs.bib" in the folder at one time.**
  
  **Please make sure that the Web of Science "savedrecs.bib" file is always named "savedrecs.bib" and not any other name, otherwise that file will be ignored/crash the program.**
  
  Again, it is best practice to empty the folder of .bib/.ris files once you've exported them to a spreadsheet. This is so you're ready for the next time you decide to use the program.

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

  Q: I just have one .bib/.ris file I want to export to excel, can I just select that .bib/.ris file?
  
  A: The program can do as many files as you want at a time. No matter the amount of files be it 1 or 40, you must place all the file(s) in a folder and select that folder from within the program when prompted.

  Q: Does the program grab affiliations?

  A: Yes, if the article's host website has a supported parser the program will attempt to parse for the author affiliations  (institution and email if applicable). The three websites that are supported at the moment are https://www.asme.org/, https://ieeexplore.ieee.org/, and https://www.springer.com/us. If a website doesn't have a supported parser that means the website has incompatible HTML code that can't be parsed in a trivial matter.
  
  Q: The output excel file is a .csv, will that cause issues?
  
  A: No, you can use it as a normal excel file. .csv stands for comma separated values and it's how the program creates the excel spreadsheet from within the program. If you want to convert it over to another excel format such as .xlsx, just open the .csv file that was just created and save it as any type of excel filetype you want.
  
  Q: Can the folder that contains the citation files include both .bib and .ris files?
  
  A: Yes, put as many .bib and .ris files in the folder as you want. Very large amounts, like thousands, may take a while to execute through, but it will do the job.
  
  Q: Can I have multiple savedrecs.bib files from Web of Science in one folder?
  
  A: No. The program only supports ONE savedrecs.bib file at a time. (See [File handling](https://github.com/csumsl/eec-493-f18/blob/master/README.md#file-handling) section for more information)
  
  Q: Can the savedrecs.bib from Web of Science be renamed to something else?
  
  A: No, it has to stay named exactly like: savedrecs.bib (See [File handling](https://github.com/csumsl/eec-493-f18/blob/master/README.md#file-handling) section for more information)
  
  Q: Where is the download link to the program and do I need Java?
  
  A: Download link is [here](https://github.com/csumsl/eec-493-f18/blob/master/Metadata%20to%20Excel.jar), click the "download" button right here ![Alt text](https://imgur.com/a/5axStc4). And yes, you do need Java to run this program. It will not run without Java.
  
  Q: There are articles on the Web of Science .bib file that are from one of the three supported websites for author affiliations, why are they not population on the spreadsheet?
  
  A: If there's an article on Web of Science that's hosted on asme, springer, or ieeexplore, it's best to export the .bib/.ris file from the article's webpage directly. Web of Science .bib files are not able to find affiliations unfortuately. To export .bib/.ris files from one of those three websites, see the [Other Recommendations](https://github.com/csumsl/eec-493-f18/blob/master/README.md#other-recommendations)) section.
