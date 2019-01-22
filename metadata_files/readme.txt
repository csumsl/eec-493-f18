Goals for this step in the project:

1. Create a parser in Java for each file type
	- .ris parser
	- bibtext parser
	- plaintext parser

1a.
	- The parser should realistically only go through the metadata file once and gather all the information
	- We must compensate for all the types of metadata that the library staff wants, even if it's not included in a certain filetype

2. Parse the information on the file and write to a text file in the form of the library's excel spreadsheet
	- We will most likely have missing metadata that's needed, but we'll worry about that later
	- End goal is to write directly to an excel sheet, but for now let's do it to a text file

3. Figure out how to write directly to excel sheet