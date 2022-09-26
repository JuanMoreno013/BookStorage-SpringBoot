Book Storage
===
***

### Purpose

The purpose to develop Book Storage, for handle the storage of a library and save all the inventory on DataBase.
### Method Definitions

The set of common methods for HTTP/1.1 is defined below.
Although this set can be expanded, additional methods cannot be assumed to share the same
semantics for separately extended clients and servers.

**_GET_** <br>
The GET method means retrieve whatever information (in the form of an entity) is identified by the Request-URI.
&nbsp;If the Request-URI refers to a data-producing process, it is the produced data which shall be returned as the
entity
&nbsp;in the response and not the source text of the process, unless that text happens to be the output of the process.

**_POST_**<br>
The POST method is used to request that the destination server accept the entity
enclosed in the request as a new subordinate of the resource identified by the Request-URI in the Request-Line.

**_PUT_** <br>
The PUT method requests that the enclosed entity be stored under the supplied Request-URI. If the Request-URI refers
to an already existing resource, the enclosed entity should be considered as a modified version of the one residing
on the origin server. If the Request-URI does not point to an existing resource, and that URI is capable of being
defined as a new resource by the requesting user agent, the origin server can create the resource with that URI.

**_DELETE_** <br>
The DELETE method requests that the origin server delete the resource identified by the Request-URI.


---

### BOOKS

### Requests

---

##### -GET

Show all the books that already exist on storage. <br>
If a book it is taken by user, "user_taken" show the 'Id' of the user, instead if the book it is free show a null value.

URI: `/books`


[//]: # (**Parameter Content type:**)

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
<STATUS> 
&nbsp;&nbsp;&nbsp;&nbsp;
Successful operation </STATUS>
<br>
</REQUEST>

<REQUEST>
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br> 
</REQUEST>



#### Example of response

`  {` <br>
`"id": " 1",` <br>
`"title": " Learn Machine",` <br>
`"author": "secon DArio",` <br>
`"pages": 987,` <br>
`"date_write": "2009-03-11",` <br>
`"nsbn": "980-92-23",` <br>
`"subject": "pop",` <br>
`"status": "New",` <br>
`"editorial": "Princeton"`<br>
`"user_taken": null`<br>
`}` <br>
`{` <br>
`"id": " 2",` <br>
`"title": " Practice and Programming",` <br>
`"author": "Lewis Vundeverth",` <br>
`"pages": 346,` <br>
`"date_write": "2019-08-01",` <br>
`"nsbn": "930-92-18-EQ",` <br>
`"subject": "Technology",` <br>
`"status": "New",` <br>
`"editorial": "Princeton"`<br>
`"user_taken": 1`<br>

`}`


---

##### -GET

Return a single book using the ID. <br>
If a book it is taken by user, "user_taken" show the 'Id' of the user, instead if the book it is free show a null value.


URI: `/books/{bookId}`

**Parameter Content type:**
` bookid` <br>
type: Integer <br>
Description: ID of book to return

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/books/2`

`{` <br>
`"id": " 2",` <br>
`"title": " Practice and Programming",` <br>
`"author": "Lewis Vundeverth",` <br>
`"pages": 346,` <br>
`"date_write": "2019-08-01",` <br>
`"nsbn": "930-92-18-EQ",` <br>
`"subject": "Technology",` <br>
`"status": "New",` <br>
`"editorial": "Princeton",`<br>
`"user_taken": null`<br>
`}`

---

##### -POST

Add new book to the storage

URI: `/books`

**Parameter Content type:**
<br>
`    application/json
`
<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; **Example** <br>

`{` <br>
`"title": " Learn Machine",` <br>
`"author": "Pecon Dario",` <br>
`"pages": 987,` <br>
`"date_write": "2009-03-11",` <br>
`"nsbn": "980-92-23",` <br>
`"subject": "pop",` <br>
`"status": "New",` <br>
`"editorial": "Princeton"`<br>
`}`

##### **Responses:**

<REQUEST>
Code
<CODE> 201 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> 
New book added ! </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br> 
<br>
</REQUEST>

#### Example of response

`  {` <br>
`"id": " 1",` <br>
`"title": " Learn Machine",` <br>
`"author": "secon Dario",` <br>
`"pages": 987,` <br>
`"date_write": "2009-03-11",` <br>
`"nsbn": "980-92-23",` <br>
`"subject": "pop",` <br>
`"status": "New",` <br>
`"editorial": "Princeton",`<br>
`"user_taken": null`<br>
`}` <br>

---

##### -DELETE

Remove a book using the ID

URI: `/books/{bookId}`

**Parameter Content type:**
` bookid` <br>
type: Integer <br>
Description: ID of book to delete

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
Code
<CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> if you don't put a book ID
<br>

</REQUEST>

#### Example of response

URI: `/books/4`

`true` If the book has been removed. <br>
`false` If the book does not been removed.

---

##### -PUT

Modify elements to one specific book using their ID

URI: `/books/{bookId}`

**Parameter Content type:**
<br>
`bookid` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of book to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>

`{` <br>
`"title": " Practice and Programming",` <br>
`"author": "Lewis Vundeverth",` <br>
`"pages": 346,` <br>
`"date_write": "2019-08-01",` <br>
`"nsbn": "930-92-18-EQ",` <br>
`"subject": "Technology",` <br>
`"status": "New",` <br>
`"editorial": "Princeton"`<br>
`}`

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> 
Book modified </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code
<CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a book ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/books/10`

`{` <br>
`"id": "10",` <br>
`"title": "Programming ",` <br>
`"author": "Lewis Vundeverth RA",` <br>
`"pages": 336,` <br>
`"date_write": "2019-05-01",` <br>
`"nsbn": "930-92-18-EQ",` <br>
`"subject": "Technology",` <br>
`"status": "New",` <br>
`"editorial": "Princeton PA",`<br>
`"user_taken": null`<br>
`}`

`true` If the book has been modified. <br>
`false` If the book does not been modified.

---


##### -PUT

Modify field of "user_taken" to set one specific user that is taken that book using their ID. <br>
To release a book of a user, just put null instead of number of 'Id'.

URI: `/books/{bookId}/takenBy`

**Parameter Content type:**
<br>
`bookid` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of book to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>

Set book taken by user <br>
`{` <br>
`user_taken": 9"`<br>
`}` <br>

Release book <br>
`{` <br>
`user_taken": null"`<br>
`}`

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> 
Book modified </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code
<CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a book ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/books/10`

`{` <br>
`"user_taken": null`<br>
`}`

`true` If the book has been modified. <br>

---

### MAGAZINE

### Requests

---

##### -GET

Show all the magazines that already exist on storage. <br>
If a magazine it is taken by user, "user_taken" show the 'Id' of the user, instead if the magazine it is free show a null value.



URI: `/magazines`

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br>
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad request </STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/magazines`

`{` <br>
`"id": " 3",` <br>
`"title": "Top Master Chest",` <br>
`"author": "Vahn Dario",` <br>
`"pages": 358,` <br>
`"date_write": "2009-03-11",` <br>
`"subject": "Pop",` <br>
`"volume": 4,` <br>
`"editorial": "For now",`<br>
`"user_taken": null`<br>
`}` <br>

`{` <br>
`"id": " 5",` <br>
`"title": "Look Chest",` <br>
`"author": "Billa Luxem",` <br>
`"pages": 20,` <br>
`"date_write": "2019-03-11",` <br>
`"subject": "Fashion",` <br>
`"volume": 5,` <br>
`"editorial": "4Young",`<br>
`"user_taken": null`<br>
`}` <br>


---

##### -GET

Return a single magazine using the ID. <br>
If a magazine it is taken by user, "user_taken" show the 'Id' of the user, instead if the magazine it is free show a null value.



URI: `/magazines/{magazineID}`

**Parameter Content type:**
`magazineID` <br>
Type: Integer <br>
Description: ID of magazine to return

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/magazines/5`

`{` <br>
`"id": " 5",` <br>
`"title": "Look Chest",` <br>
`"author": "Billa Luxem",` <br>
`"pages": 20,` <br>
`"date_write": "2019-03-11",` <br>
`"subject": "Fashion",` <br>
`"volume": 5,` <br>
`"editorial": "4Young",`<br>
`"user_taken": null`<br>
`}` <br>

---

##### -POST

Add new magazine to the storage

URI: `/magazines`

**Parameter Content type:**
<br>
`    application/json
`
<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; **Example** <br>

`{` <br>
`"title": "Look Chest",` <br>
`"author": "Billa Luxem",` <br>
`"pages": 20,` <br>
`"dateWrite": "2019-03-11",` <br>
`"subject": "Fashion",` <br>
`"volume": 5,` <br>
`"editorial": "4Young"`<br>
`}` <br>

##### **Responses:**

<REQUEST>
Code
<CODE> 201 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> 
New magazine added ! </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br> 
<br>
</REQUEST>

#### Example of response

`{` <br>
`"id": " 10",` <br>
`"title": "Look Chest",` <br>
`"author": "Billa Luxem",` <br>
`"pages": 20,` <br>
`"date_write": "2019-03-11",` <br>
`"subject": "Fashion",` <br>
`"volume": 5,` <br>
`"editorial": "4Young",`<br>
`"user_taken": null`<br>
`}` <br>

---

##### -DELETE

Remove a magazine using the ID

URI: `/magazines/{magazineID}`

**Parameter Content type:**
` magazineID` <br>
type: Integer <br>
Description: ID of magazine to delete

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
Code
<CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> if you don't put a magazine ID
<br>

</REQUEST>

#### Example of response

URI: `/magazine/4`

`true` If the magazine has been removed. <br>
`false` If the magazine does not have been removed.

---

##### -PUT

Modify elements to one specific magazine using their ID

URI: `/magazines/{magazineID}`

**Parameter Content type:**
<br>
`magazineID` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of magazine to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>

`{` <br>
`"title": "Look Chest",` <br>
`"author": "Billa Luxem",` <br>
`"pages": 20,` <br>
`"dateWrite": "2019-03-11",` <br>
`"subject": "Fashion",` <br>
`"volume": 5,` <br>
`"editorial": "4Young"`<br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Magazine modified </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a magazine ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/magazine/5`

`{` <br>
`"id": " 5",` <br>
`"title": "Moderns Chest",` <br>
`"author": "Fatima Sheil",` <br>
`"pages": 20,` <br>
`"dateWrite": "2021-03-11",` <br>
`"subject": "Model",` <br>
`"volume": 5,` <br>
`"editorial": "4Young"`<br>
`}` <br>

response: <br>
`true` If the magazine has been modified. <br>
`false` If the magazine does not been modified.

---
##### -PUT

Modify field of "user_taken" to set one specific user that is taken that magazine using their ID. <br>
To release a magazine of a user, just put null instead of number of 'Id'. <br>

URI: `/magazines/{magazineID}/takenBy`

**Parameter Content type:**
<br>
`magazineID` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of magazine to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>
Set book taken by user <br>
`{` <br>
`"user_taken": 10`<br>
`}` <br>
Release magazine: <br>
`{` <br>
`"user_taken": 10`<br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Magazine modified </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a magazine ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/magazine/5/takenBy`

`{` <br>
`"user_taken": 10`<br>
`}` <br>


response: <br>
`true` If the magazine has been modified. <br>

---
### LETTER

### Requests

---

##### -GET

Show all the letter that already exists on storage.<br>
If a letter it is taken by user, "user_taken" show the 'Id' of the user, instead if the letter it is free show a null value.

URI: `/letters`

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br>
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad request </STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/letters`

`{` <br>
`"id": " 3",` <br>
`"title": "Top Master Chest",` <br>
`"author": "Vahn Dario",` <br>
`"pages": 358,` <br>
`"date_write": "2009-03-11",` <br>
`"subject": "Pop",` <br>
`"place": "Prince",` <br>
`"user_taken": null`<br>
`}` <br>
`{` <br>
`"id": " 7",` <br>
`"title": "Good bye",` <br>
`"author": "Lucifer",` <br>
`"pages": 358,` <br>
`"date_write": "2009-03-11",` <br>
`"subject": "Important",` <br>
`"place": "Malaga",` <br>
`"user_taken": 10`<br>
`}` <br>


---

##### -GET

Return a single letter using the ID. <br>
If a letter it is taken by user, "user_taken" show the 'Id' of the user, instead if the letter it is free show a null value.


URI: `/letters/{letterID}`

**Parameter Content type:**
`letterID` <br>
Type: Integer <br>
Description: ID of letter to return

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/letters/3`

`{` <br>
`"id": " 3",` <br>
`"title": "Top Master Chest",` <br>
`"author": "Vahn Dario",` <br>
`"pages": 358,` <br>
`"date_write": "2009-03-11",` <br>
`"subject": "Pop",` <br>
`"place": "Prince",` <br>
`"user_taken": null`<br>
`}` <br>


---

##### -POST

Add new letter to the storage.

URI: `/letters`

**Parameter Content type:**
<br>
`    application/json
`
<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; **Example** <br>

`{` <br>
`"title": "Play and Fun",` <br>
`"author": "Maximiliano Shwecx",` <br>
`"pages": 4,` <br>
`"date_write": "1999-04-01",` <br>
`"subject": "Unknown",` <br>
`"place": "Spain"` <br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 201 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> New letter added ! </STATUS>
<br>Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br> 
<br>
</REQUEST>

#### Example of response

`{` <br>
`"id": "4",` <br>
`"title": "Play and Fun",` <br>
`"author": "Maximiliano Shwecx",` <br>
`"pages": 4,` <br>
`"date_write": "1999-04-01",` <br>
`"subject": "Unknown",` <br>
`"place": "Spain",` <br>
`"user_taken": null`<br>
`}` <br>

---

##### -DELETE

Remove a letter using the ID

URI: `/letters/{letterID}`

**Parameter Content type:**
` letterID` <br>
type: Integer <br>
Description: ID of letter to delete

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> if you don't put a leter ID
<br>

</REQUEST>

#### Example of response

URI: `/letter/8`

`true` If the letter has been removed. <br>
`false` If the letter does not have been removed.

---

##### -PUT

Modify elements to one specific letter using their ID.

URI: `/letters/{letterID}`

**Parameter Content type:**
<br>
`letterID` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of letter to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>

`{` <br>
`"title": "Play and Fun",` <br>
`"author": "Maximiliano Shwecx",` <br>
`"pages": 4,` <br>
`"date_write": "1999-04-01",` <br>
`"subject": "Unknown",` <br>
`"place": "Spain"` <br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Letter modified </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a letter ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/letter/5`

`{` <br>
`"title": "Having Fun",` <br>
`"author": "Maximiliano Schrwetz",` <br>
`"pages": 16,` <br>
`"date_write": "1999-04-01",` <br>
`"subject": "History",` <br>
`"place": "Spain, Madrid"` <br>
`}` <br>

response: <br>
`true` <br>

---



##### -PUT

Modify field of "user_taken" to set one specific user that is taken that letter using their ID.
To release a letter of a user, just put null instead of number of 'Id'. <br>

URI: `/letters/{letterID}`

**Parameter Content type:**
<br>
`letterID` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of letter to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>
Set letter taken by user <br>
`{` <br>
`"user_taken": 10`<br>
`}` <br>
Release letter <br>
`{` <br>
`"user_taken": null`<br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Letter modified </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a letter ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/letter/5`

`{` <br>
`"user_taken": null`<br>
`}` <br>

response: <br>
`true` <br>

---
### USER

### Requests

---

##### -GET

Show all the Users that already are registered.<br>

URI: `/users`

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br>
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad request </STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/users`

`{` <br>
`"id": " 1",` <br>
`"userName": "Clark",` <br>
`"dateBirth": "2009-03-11",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>
`{` <br>
`"id": " 3",` <br>
`"userName": "Marcos Reus",` <br>
`"dateBirth": "2004-03-11",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>

---

##### -GET

Return a single user using the ID. <br>


URI: `/user/{userID}`

**Parameter Content type:**
`userID` <br>
Type: Integer <br>
Description: ID of user to return

##### **Responses:**

<REQUEST>
Code
<CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code
<CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
</REQUEST>

#### Example of response

URI: `/users/3`

`{` <br>
`"id": " 3",` <br>
`"userName": "Marcos Reus",` <br>
`"dateBirth": "2004-03-11",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>

---

##### -POST

Add new user.

URI: `/users`

**Parameter Content type:**
<br>
`    application/json
`
<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; **Example** <br>

`{` <br>
`"userName": "Martin Dario",` <br>
`"dateBirth": "1998-05-10",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 201 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> User created </STATUS>
<br>Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br> 
<br>
</REQUEST>

#### Example of response

`{` <br>
`"id": " 4",` <br>
`"userName": "Martin Dario",` <br>
`"dateBirth": "1998-05-10",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>

---

##### -DELETE

Remove a user using the ID

URI: `/users/{userID}`

**Parameter Content type:**
` userID` <br>
type: Integer <br>
Description: ID of letter to delete

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS>  Success operation </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Invalid ID supplied</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> if you don't put a leter ID
<br>

</REQUEST>

#### Example of response

URI: `/users/8`

`true` If the user has been removed. <br>
`false` If the user does not have been removed.

---

##### -PUT

Modify elements to one specific letter using their ID.

URI: `/users/{usersID}`

**Parameter Content type:**
<br>
`usersID` <br>
&nbsp;type: Integer <br>
&nbsp;Description: ID of letter to modify
<br>  &nbsp;&nbsp;&nbsp;&nbsp;

`application/json`

**Example** <br>

`{` <br>
`"userName": "Martin Dario",` <br>
`"dateBirth": "1998-05-10",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>

##### **Responses:**

<REQUEST>
Code <CODE> 200 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> User modified </STATUS>
<br> 
Code <CODE> 400 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Bad Request</STATUS>
<br>
Code <CODE> 405 </CODE>
&nbsp;&nbsp;&nbsp;&nbsp;
<STATUS> Method not allowed </STATUS> , if you don't put a letter ID
<br>
<br>
</REQUEST>

#### Example of response

URI: `/users/5`

`{` <br>
`"userName": "Martin Dario",` <br>
`"dateBirth": "1998-05-10",` <br>
`"dateTakeItem": "2021-03-11"` <br>
`}` <br>

response: <br>
`true` <br>
