# Citation Metadata to Excel ReadMe

  This document serves as a guide on how to use the program and how to achieve the best results.

## Requirements:

1. [Java](https://www.java.com/en/)
2. Internet Connection
    - Used to fetch affiliations if applicable
    
## Retrieving citation file off of Web of Science:

1. 

2. 

3. 
    
## Instructions:

1. Select a folder which contains the citation files
    - Acceptable files include .bib and .ris
    ![Alt text](https://i.imgur.com/UDnykkJ.png)
    ![Alt text](https://i.imgur.com/dFzzwXX.png)
    
2. Choose name and location for excel spreadsheet
    ![Alt text](https://i.imgur.com/32GmT2u.png)
    
3. Let program run until completion
    ![Alt text](https://i.imgur.com/7wa0P8R.png)

4. Navigate to the excel spreadsheet location and you're done!

## Other Recommendations:

  For the best results, go to an article's webpage directly and retrieve the citation file (.bib or .ris).
  
  Here are some examples:
    - Future images w/ explanation
    
  By doing this, the program has an easier time checking for affiliations.
  
## FAQs:

  Q: Does the program grab affiliations?

  A: Yes, if the article's host website has a supported parser the program will attempt to parse for the author affiliations  (institution and email if applicable). The three websites that are supported at the moment are https://www.asme.org/, https://ieeexplore.ieee.org/, and https://www.springer.com/us. If a website doesn't have a supported parser that means the website has incompatible HTML code that can be parsed in a trivial matter.
  
  Q: The output excel file is a .csv, will that cause issues?
  
  A: No, you can use it as a normal excel file. .csv stands for comma separated values and it's how the program creates the excel spreadsheet from within the program. If you want to convert it over to another excel format such as .xlsx, just open the .csv file that was just created and save it as any type of excel filetype you want.
