1. TDD approach is followed.
2. Used EasyMock and JUnit for test cases
3. Clean code approach is used. Used less documentation/comments, and made sure that the code speaks for itself.
4. Test coverage has been verified to be above 90%, except for the Application.java
5. Multi threading not considered, for this application.
6. Assumtions:
   The Date of birth given in the exampe is assumed to be 19th century. i.e. 16/03/77  = 16th March 1977
   All the birth dates are from 19th century only as it was given as two digits. No one belongs to 20th century or 18th century etc.
   The data given in the input files, like AddressBook file, is always properly formatted and the the given dates follow the date format. 16/03/77
   The file contains data : name, Male/Female, DOB in that order - with no headings. It contains only data.
7. If multiple people are the oldest it would return the first one in the list.